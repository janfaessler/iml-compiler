package ch.fhnw.cbip.compiler.scanner.enums;

public enum Keyword {
	
	BOOL("bool"), CALL("call"), CAND("cand"), CONST("const"), COPY("copy");
	
	Keyword(String s){
		this.pattern = s;
	}
	
	private String pattern;

}
