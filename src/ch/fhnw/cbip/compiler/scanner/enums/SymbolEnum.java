package ch.fhnw.cbip.compiler.scanner.enums;

public enum SymbolEnum {

  LPAREN("("), RPAREN(")"), LBRACE("{"), RBRACE("}"),
  COMMA(","), SEMICOLON(";"), COLON(":"),
  QUESTMARK("?"), EXCLAMARK("!"), BECOMES(":="),
  MULTOPR("*"), PLUS("+"), MINUS("-"), 
  EQ("="), NE("/="), LT("<"), GT(">"), LE("<="), GE(">=");
  
  private String pattern;
  
  SymbolEnum(String pattern){
    this.pattern = pattern;    
  }
  
  public static SymbolEnum match(String toMatch) {
    for (SymbolEnum s : values()) {
      if (s.pattern.equals(toMatch)) {
        return s;
      }
    }
    return null;
  }
}
