package ch.fhnw.cbip.compiler.generator;

import ch.fhnw.cbip.compiler.parser.AbsTree.*;

public class CodeGenerator {
	
	Program tree;
	Integer lineCounter = 0;
	StringBuilder code = new StringBuilder();
	
	public CodeGenerator(Program tree) {
		this.tree = tree;
	}
	
	public String generate() {
		
		addLine("Alloc", "1");
		
		Decl decleration = tree.getDeclerations();
		Cmd commands = tree.getCommands();

		// do store declerations
		Decl currentDecl = decleration;
		while (currentDecl != null) {
			if (decleration instanceof DeclStore) code.append(buildDeclStore(decleration));
			currentDecl = currentDecl.getNextDecl();
		}
		
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
			if (decleration instanceof DeclFun) code.append(buildDeclFun(decleration));
			currentDecl = currentDecl.getNextDecl();
		}
		
		// do proc declerations
		currentDecl = decleration;
		while (currentDecl != null) {
			if (decleration instanceof DeclProc) code.append(buildDeclProc(decleration));
			currentDecl = currentDecl.getNextDecl();
		}
		
		return code.toString();
	}
	
	private String buildDeclStore(Decl dcl) {
		// TODO: store decleration
		return null;
	}
	
	private String buildDeclFun(Decl dcl) {
		// TODO: fun decleration
		return null;
	}
	
	private String buildDeclProc(Decl dcl) {
		// TODO: proc decleration
		return null;
	}
	
	private String buildCommands(Cmd cmd) {
		// TODO: commands
		return null;
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
