package ch.fhnw.cbip.compiler.generator;

import ch.fhnw.cbip.compiler.error.CodeGenerationError;
import ch.fhnw.cbip.compiler.parser.AbsTree.*;

public class CodeGenerator {
	
	Program tree;
	Integer lineCounter = 0;
	StringBuilder code = new StringBuilder();
	
	public CodeGenerator(Program tree) {
		this.tree = tree;
	}
	
	public String generate() throws CodeGenerationError {

		Decl decleration = tree.getDeclerations();
		Cmd commands = tree.getCommands();

		// do store declerations
		int storeCount = 0;
		Decl currentDecl = decleration;
		while (currentDecl != null) {
			if (decleration instanceof DeclStore) {
				((DeclStore) decleration)
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
		
		// do fun declerations
		currentDecl = decleration;
		while (currentDecl != null) {
			if (decleration instanceof DeclFun) buildDeclFun(decleration);
			currentDecl = currentDecl.getNextDecl();
		}
		
		// do proc declerations
		currentDecl = decleration;
		while (currentDecl != null) {
			if (decleration instanceof DeclProc) buildDeclProc(decleration);
			currentDecl = currentDecl.getNextDecl();
		}
		
		return code.toString();
	}
	
	private void buildCommands(Cmd cmd) throws CodeGenerationError {
		if (cmd instanceof CmdAssi) {}
		else if (cmd instanceof CmdCond) {}
		else if (cmd instanceof CmdInput) {}
		else if (cmd instanceof CmdOutput) {}
		else if (cmd instanceof CmdProcCall) {}
		else if (cmd instanceof CmdSkip) {}
		else if (cmd instanceof CmdWhile) {}
		else throw new CodeGenerationError("unknown Command");
	}
	
	private void buildCmdInput() {
		
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
