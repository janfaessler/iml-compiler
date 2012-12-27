package ch.fhnw.cbip.compiler.generator;

import java.util.ArrayList;
import java.util.HashMap;

import ch.fhnw.cbip.compiler.error.CodeGenerationError;
import ch.fhnw.cbip.compiler.parser.AbsTree.ExprDyadic;
import ch.fhnw.cbip.compiler.parser.AbsTree.ExprStore;
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
		
		addLine("Alloc", String.valueOf(storeCount));
		
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
		
		return code.toString();
	}
	
	private void buildCommands(Cmd cmd) throws CodeGenerationError {
		if (cmd instanceof CmdAssi) {}
		else if (cmd instanceof CmdCond) {} // TODO: CmdCond
		else if (cmd instanceof CmdInput) buildCmdInput((CmdInput) cmd);
		else if (cmd instanceof CmdOutput) buildCmdOutput((CmdOutput) cmd);
		else if (cmd instanceof CmdProcCall) {} // TODO: CmdProcCall
		else if (cmd instanceof CmdSkip) {} // TODO: CmdSkip
		else if (cmd instanceof CmdWhile) buildCmdWhile((CmdWhile) cmd);
		else throw new CodeGenerationError("unknown Command");
	}
	
	private void buildCmdInput(CmdInput cmd) {
		
		ExprStore expr = (ExprStore) cmd.getExpr();
		String variableName = expr.getIdent().getName();
		
		addLine("IntLoad", String.valueOf(variables.get(variableName)));
		addLine("IntInput", variableName);
	}
	
	private void buildCmdOutput(CmdOutput cmd) {
		
		ExprStore expr = (ExprStore) cmd.getExpr();
		String variableName = expr.getIdent().getName();
		
		addLine("IntLoad", String.valueOf(variables.get(variableName)));
		addLine("Deref");
		addLine("IntOutput", variableName);
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
		resolveBoolExpression(cmd.getExpr());
		addLine("CondJump", String.valueOf(cmdCount));
		
		// build the commands
		currentCmd = cmd.getCmd();
		while (currentCmd != null) {
			buildCommands(currentCmd);
			currentCmd = currentCmd.getNextCmd();
		}
	}
	
	private void resolveBoolExpression(Expr expr) throws CodeGenerationError {
		if (expr instanceof ExprDyadic) {
			ExprDyadic e = (ExprDyadic) expr;
			if (e.getOperator().getTerminal() == Terminal.BOOLOPR) {
				resolveBoolExpression(e.getExpr1());
				
				
				startCountingState();
				resolveBoolExpression(e.getExpr2());
				Integer cmdCount = stopCountingState();
				addLine("CondJump", String.valueOf(cmdCount));
				
				resolveBoolExpression(e.getExpr2());
			} else if (e.getOperator().getTerminal() == Terminal.RELOPR) {
				
			} else throw new CodeGenerationError("unknown terminal for a Dyadic Expr");
		}
		else if (expr instanceof ExprFunCall) {} // TODO: ExprFunCall
		else if (expr instanceof ExprLiteral) {} // TODO: ExprLiteral
		else if (expr instanceof ExprMonadic) {} // TODO: ExprMonadic
		else if (expr instanceof ExprStore) {} // TODO: ExprStore
		else throw new CodeGenerationError("unknown expression");
	}
	
	
	private void buildDeclStore(Decl dcl) {
		// TODO: store decleration
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
}
