package ch.fhnw.cbip.compiler.scanner.enums;

public enum KeywordList {
	
	BOOL("bool"), CALL("call"), CAND("cand"), CONST("const"), COPY("copy"), COR("cor"),
	DIV("div"), ELSE("else"), FALSE("false"), FUN("fun"), GLOBAL("global"), IF("if"),
	IN("in"), INIT("init"), INOUT("inout"), INT32("int32"), LOCAL("local"), MOD("mod"),
	NOT("not"), OUT("out"), PROC("proc"), PROGRAM("program"), REF("ref"), RETURNS("returns"),
	SKIP("skip"), TRUE("true"), VAR("var"), WHILE("while");
	
	KeywordList(String s){
		this.pattern = s;
	}
	
	private String pattern;
	
	public static KeywordList match(String toMatch){
		for (KeywordList k : values()){
			if(k.pattern.equals(toMatch)){
				return k;
			}
		}
		return null;
	}
	

}
