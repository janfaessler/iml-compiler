package ch.fhnw.cbip.compiler.scanner.enums;

import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.token.Operator;
import ch.fhnw.cbip.compiler.scanner.token.Symbol;

public enum SymbolList {

  LPAREN("(", new Symbol.LParen()), 
  RPAREN(")", new Symbol.RParen()), 
  LBRACE("{", new Symbol.LBrace()), 
  RBRACE("}", new Symbol.RBrace()),
  COMMA(",", new Symbol.Comma()), 
  SEMICOLON(";", new Symbol.Semicolon()), 
  COLON(":", new Symbol.Colon()),
  QUESTMARK("?", new Symbol.QuestMark()), 
  EXCLAMARK("!", new Symbol.ExclaMark()), 
  BECOMES(":=", new Symbol.Becomes(null)),
  MULTOPR("*", new Operator.MultOpr(OperatorAttribute.TIMES)), 
  PLUS("+", new Operator.AddOpr(OperatorAttribute.PLUS)), 
  MINUS("-", new Operator.AddOpr(OperatorAttribute.MINUS)), 
  EQ("=", new Operator.RelOpr(OperatorAttribute.EQ)), 
  NE("/=", new Operator.RelOpr(OperatorAttribute.NE)), 
  LT("<", new Operator.RelOpr(OperatorAttribute.LT)), 
  GT(">", new Operator.RelOpr(OperatorAttribute.GT)), 
  LE("<=", new Operator.RelOpr(OperatorAttribute.LE)), 
  GE(">=", new Operator.RelOpr(OperatorAttribute.GE)),
  INCREMENT("++", new Operator.CrementOpr(OperatorAttribute.INCR)),
  DECREMENT("--", new Operator.CrementOpr(OperatorAttribute.DECR)),
  BECOMES_ADD("+:=", new Symbol.Becomes(OperatorAttribute.PLUS)),
  BECOMES_SUB("-:=", new Symbol.Becomes(OperatorAttribute.MINUS)),
  BECOMES_MUL("*:=", new Symbol.Becomes(OperatorAttribute.TIMES)),
  BECOMES_DIV("/:=", new Symbol.Becomes(OperatorAttribute.DIV)),
  ;
  
  private String pattern;
  private IToken token;
  
  SymbolList(String pattern, IToken t) {
    this.pattern = pattern;   
    this.token = t;
  }
  
  public static SymbolList match(String toMatch) {
    for (SymbolList s : values()) {
      if (s.pattern.equals(toMatch)) {
        return s;
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
