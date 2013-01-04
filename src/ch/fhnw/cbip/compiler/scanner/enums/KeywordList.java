package ch.fhnw.cbip.compiler.scanner.enums;

import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.token.Keyword;
import ch.fhnw.cbip.compiler.scanner.token.Literal;
import ch.fhnw.cbip.compiler.scanner.token.Mode;
import ch.fhnw.cbip.compiler.scanner.token.Operator;
import ch.fhnw.cbip.compiler.scanner.token.Type;

/**
 * A list of all the keywords.
 * 
 * @author Michael Kuenzli
 *
 */
public enum KeywordList {
	
	BOOL("bool", new Type(TypeAttribute.BOOL)), 
	CALL("call", new Keyword.Call()), 
	CAND("cand", new Operator.BoolOpr(OperatorAttribute.CAND)), 
	CONST("const", new Mode.ChangeMode(ModeAttribute.CONST)), 
	COPY("copy", new Mode.MechMode(ModeAttribute.COPY)), 
	COR("cor", new Operator.RelOpr(OperatorAttribute.COR)),
	DIV("div", new Operator.MultOpr(OperatorAttribute.DIV)), 
	ELSE("else", new Keyword.Else()), 
	ENDWHILE("endwhile", new Keyword.EndWhile()), 
	FALSE("false", new Literal(BoolVal.FALSE)), 
	FOR("for", new Keyword.For()),
	FUN("fun", new Keyword.Fun()), 
	GLOBAL("global", new Keyword.Global()), 
	IF("if", new Keyword.If()),
	IN("in", new Mode.FlowMode(ModeAttribute.IN)), 
	INIT("init", new Keyword.Init()), 
	INOUT("inout", new Mode.FlowMode(ModeAttribute.INOUT)), 
	INT32("int32", new Type(TypeAttribute.INT32)), 
	INVAR("invar", new Keyword.Invar()),
	LOCAL("local", new Keyword.Global()), 
	MOD("mod", new Operator.MultOpr(OperatorAttribute.MOD)),
	NOT("not", new Keyword.Not()), 
	OUT("out", new Mode.FlowMode(ModeAttribute.OUT)), 
	PROC("proc", new Keyword.Proc()), 
	PROGRAM("program", new Keyword.Program()), 
	REF("ref", new Mode.MechMode(ModeAttribute.REF)), 
	RETURNS("returns", new Keyword.Returns()),
	SKIP("skip", new Keyword.Skip()), 
	TRUE("true", new Literal(BoolVal.TRUE)), 
	VAR("var", new Mode.ChangeMode(ModeAttribute.VAR)), 
	WHILE("while", new Keyword.While());
	
	/**
	 * Creates a Keyword list
	 * 
	 * @param s 
	 * @param t
	 */
	KeywordList(String s, IToken t){
		this.pattern = s;
		this.token = t;
		
	}
	
	private String pattern;
	private IToken token;
	
	public static KeywordList match(String toMatch){
		for (KeywordList k : values()){
			if(k.pattern.equals(toMatch)){
				return k;
			}
		}
		return null;
	}
	
	public void setLine(int number) {
		token.setLine(number);
	}
	
	public IToken getToken() {
		return token.clone();
	}

}
