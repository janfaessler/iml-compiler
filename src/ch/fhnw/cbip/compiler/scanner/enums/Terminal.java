package ch.fhnw.cbip.compiler.scanner.enums;

public enum Terminal {
  LPAREN("LPAREN"), RPAREN("RPAREN"), LBRACE("LBRACE"), RBRACE("RBRACE"),

  COMMA("COMMA"), SEMICOLON("SEMICOLON"), COLON("COLON"),

  QUESTMARK("QUESTMARK"), EXCLAMARK("EXCLAMARK"), BECOMES("BECOMES"),

  MULTOPR("MULTOPR"), ADDOPR("ADDOPR"), RELOPR("RELOPR"), BOOLOPR("BOOLOPR"),

  LITERAL("LITERAL"), IDENT("IDENT"),

  PROGRAM("PROGRAM"), TYPE("TYPE"), CALL("CALL"), NOT("NOT"),

  IF("IF"), ELSE("ELSE"),

  FLOWMODE("FLOWMODE"), CHANGEMODE("CHANGEMODE"), MECHMODE("MECHMODE"),

  FUN("FUN"), GLOBAL("GLOBAL"), LOCAL("LOCAL"), INIT("INIT"),

  PROC("PROC"), RETURNS("RETURNS"), SKIP("SKIP"),
  
  WHILE("WHILE"), DO("DO"), ENDWHILE("ENDWHILE"),
  
  SENTINEL("SENTINEL"), CREMENT("CREMENT");


  Terminal(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
