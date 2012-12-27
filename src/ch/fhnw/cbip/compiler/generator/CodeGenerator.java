package ch.fhnw.cbip.compiler.generator;

import java.util.ArrayList;
import java.util.HashMap;

import ch.fhnw.cbip.compiler.error.CodeGenerationError;
import ch.fhnw.cbip.compiler.parser.AbsTree.*;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class CodeGenerator {
	
	Program tree;
	Integer lineCounter = 0;
	StringBuilder code = new StringBuilder();
	HashMap<String,Integer> variables = new HashMap<String,Integer>();
	ArrayList<Integer> cmdCounter = new ArrayList<Integer>();
	boolean countingState = false;
	
	public CodeGenerator(Program tree) {
		this.tree = tree;
	}
	
	public String generate() throws CodeGenerationError {

		Decl declaration = tree.getDeclarations();
		Cmd commands = tree.getCommands();

		// do store declarations
		int storeCount = 0;
		Decl currentDecl = declaration;
		while (currentDecl != null) {
			if (declaration instanceof DeclStore) {
				variables.put(((DeclStore) declaration).getIdent().getName(), storeCount);
				storeCount++;
			}

			currentDecl = currentDecl.getNextDecl();
		}
		
		addLine("Alloc", storeCount);
		
		// do commands
		Cmd currentCmd = commands;
		while (currentCmd != null) {
			buildCommands(currentCmd);
			currentCmd = currentCmd.getNextCmd();
		}
		
		addLine("Stop");
		
		// do fun declarations
		currentDecl = declaration;
		while (currentDecl != null) {
			if (declaration instanceof DeclFun) buildDeclFun(declaration);

			currentDecl = currentDecl.getNextDecl();
		}
		
		// do proc declarations
		currentDecl = declaration;
		while (currentDecl != null) {
			if (declaration instanceof DeclProc) buildDeclProc(declaration);
			currentDecl = currentDecl.getNextDecl();
		}
		
		// TODO: replace routine call addresses
		
		return code.toString();
	}
	
	private void buildCommands(Cmd cmd) throws CodeGenerationError {
		if (cmd instanceof CmdAssi) {}
		else if (cmd instanceof CmdCond) buildCmdCond((CmdCond) cmd);
		else if (cmd instanceof CmdInput) buildCmdInput((CmdInput) cmd);
		else if (cmd instanceof CmdOutput) buildCmdOutput((CmdOutput) cmd);
		else if (cmd instanceof CmdProcCall) buildCmdProcCall((CmdProcCall) cmd);
		else if (cmd instanceof CmdSkip) buildCmdSkip((CmdSkip) cmd);
		else if (cmd instanceof CmdWhile) buildCmdWhile((CmdWhile) cmd);
		else throw new CodeGenerationError("unknown Command");
	}
	
	private void buildCmdCond(CmdCond cmd) throws CodeGenerationError {
		
		// count the commands from the if part
		startCountingState();
		Cmd currentCmd = cmd.getIfCmd();
		while (currentCmd != null) {
			buildCommands(currentCmd);
			currentCmd = currentCmd.getNextCmd();
		}
		Integer cmdIfCount = stopCountingState();
		
		// count the commands from the else part
		startCountingState();
		currentCmd = cmd.getElseCmd();
		while (currentCmd != null) {
			buildCommands(currentCmd);
			currentCmd = currentCmd.getNextCmd();
		}
		Integer cmdElseCount = stopCountingState();
		
		// jump to the else part when the expression is false
		resolveExpression(cmd.getExpr());
		addLine("CondJump", lineCounter + cmdIfCount + 2);
		
		// build if commands
		buildCommands(cmd.getIfCmd());
		addLine("UncondJump", lineCounter + cmdElseCount + 1);
		
		// build else commands
		buildCommands(cmd.getElseCmd());
	}
	
	private void buildCmdInput(CmdInput cmd) {
		
		ExprStore expr = (ExprStore) cmd.getExpr();
		String variableName = expr.getIdent().getName();
		
		addLine("IntLoad", variables.get(variableName));
		addLine("IntInput", variableName);
	}
	
	private void buildCmdOutput(CmdOutput cmd) {
		
		ExprStore expr = (ExprStore) cmd.getExpr();
		String variableName = expr.getIdent().getName();
		
		addLine("IntLoad", variables.get(variableName));
		addLine("Deref");
		addLine("IntOutput", variableName);
	}
	
	private void buildCmdProcCall(CmdProcCall cmd) throws CodeGenerationError {
		addLine("Alloc", 0);
		ExprList currentList = cmd.getRoutineCall().getExprList();
		while (currentList != null) {
			resolveExpression(currentList.getExpr());
			currentList = currentList.getExprList();
		}
		addLine("Call", getCallReplacement(cmd.getRoutineCall().getIdent().getName()));
	}
	
	private void buildCmdSkip(CmdSkip cmd) {
		addLine("UncondJump", lineCounter + 1);
	}
	
	private void buildCmdWhile(CmdWhile cmd) throws CodeGenerationError {
		
		// count the commands in the while loop
		startCountingState();
		Cmd currentCmd = cmd.getCmd();
		while (currentCmd != null) {
			buildCommands(currentCmd);
			currentCmd = currentCmd.getNextCmd();
		}
		Integer cmdCount = stopCountingState();
		
		// jump out of the wile when the expression is false
		resolveExpression(cmd.getExpr());
		addLine("CondJump", lineCounter + cmdCount + 1);
		
		// build the commands
		currentCmd = cmd.getCmd();
		while (currentCmd != null) {
			buildCommands(currentCmd);
			currentCmd = currentCmd.getNextCmd();
		}
	}
	
	private void resolveExpression(Expr expr) throws CodeGenerationError {
		if (expr instanceof ExprDyadic) {
			ExprDyadic e = (ExprDyadic) expr;
			if (e.getOperator().getTerminal() == Terminal.BOOLOPR) {
				
				// resolve expression 1
				resolveExpression(e.getExpr1());
				
				// count the commands for expression 2
				startCountingState();
				resolveExpression(e.getExpr2());
				Integer cmdCount = stopCountingState();
				
				// jump if expression 1 is false
				addLine("CondJump", lineCounter + cmdCount + 1);
				
				// resolve expression 2
				resolveExpression(e.getExpr2());
				
			} else if (e.getOperator().getTerminal() == Terminal.ADDOPR || 
					   e.getOperator().getTerminal() == Terminal.MULTOPR ||
					   e.getOperator().getTerminal() == Terminal.RELOPR) {
				
				resolveExpression(e.getExpr1());
				resolveExpression(e.getExpr2());
				
				switch (String.valueOf(e.getOperator().getAttribute())) {
					case "PLUS": addLine("IntPlus"); break;
					case "MINUS": addLine("IntMinus"); break;
					case "TIMES": addLine("IntMult"); break;
					case "DIV": addLine("IntDiv"); break;
					case "MOD": addLine("IntMod"); break;
					default: addLine("Int" + e.getOperator().getAttribute()); break;
				}
			} 
			else throw new CodeGenerationError("unknown terminal for a Dyadic Expression");
		}
		else if (expr instanceof ExprFunCall) {
			ExprFunCall e = (ExprFunCall) expr;
			addLine("Alloc", 1);
			ExprList currentList = e.getRoutineCall().getExprList();
			while (currentList != null) {
				resolveExpression(currentList.getExpr());
				currentList = currentList.getExprList();
			}
			addLine("Call", getCallReplacement(e.getRoutineCall().getIdent().getName()));
		} 
		else if (expr instanceof ExprLiteral) {
			ExprLiteral e = (ExprLiteral) expr;
			addLine("IntLoad", e.getLiteral().getIntVal());
		} 
		else if (expr instanceof ExprMonadic) {
			// TODO: ExprMonadic
		} 
		else if (expr instanceof ExprStore) {
			ExprStore e = (ExprStore) expr;			
			addLine("IntLoad", variables.get(e.getIdent().getName()));
		}
		else throw new CodeGenerationError("unknown expression");
	}

	private void buildDeclFun(Decl dcl) {
		// TODO: fun decleration
	}
	
	private void buildDeclProc(Decl dcl) {
		// TODO: proc decleration
	}

	private void addLine(String cmd) {
		addLine(cmd, "");
	}
	
	private void addLine(String cmd, Integer param) {
		addLine(cmd, String.valueOf(param));
	}
	
	private void addLine(String cmd, String params) {
		if (countingState) cmdCounter.set(cmdCounter.size() - 1, cmdCounter.get(cmdCounter.size() - 1) + 1);
		else {
			code.append("(");
			code.append(lineCounter);
			code.append(',');
			code.append(cmd);
			if (params.length() > 0) {
				code.append(' ');
				code.append(params);
			}
			code.append("),\n");
			lineCounter++;
		}
	}
	
	private void startCountingState() {
		cmdCounter.add(0);
		countingState = true;
	}
	
	private Integer stopCountingState() {
		countingState = false;
		Integer result = cmdCounter.get(cmdCounter.size() - 1);
		cmdCounter.remove(cmdCounter.size() - 1);
		if (cmdCounter.size() > 0) cmdCounter.set(cmdCounter.size() - 1, cmdCounter.get(cmdCounter.size() - 1) + result);
		return result;
	}
	
	private String getCallReplacement(String name) { return ">>" + name + "<<"; }
}