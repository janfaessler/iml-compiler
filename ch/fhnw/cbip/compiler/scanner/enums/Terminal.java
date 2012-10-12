package ch.fhnw.cbip.compiler.scanner.enums;

public enum Terminal {
  LPAREN("LPAREN"), RPAREN("RPAREN"), LBRACE("LBRACE"), RBRACE("RBRACE"),

  COMMA("COMMA"), SEMICOLON("SEMICOLON"), COLON("COLON"),

  QUESTMARK("QUESTMARK"), EXCLAMARK("EXCLAMARK"), BECOMES("BECOMES"),

  MULTOPR(""), ADDOPR(""), RELOPR(""), BOOLOPR(""),

  LITERAL(""), IDENT(""),

  PROGRAM("PROGRAM"), TYPE("TYPE"), CALL("CALL"), NOT("NOT"),

  IF("IF"), ELSE("ELSE"),

  FLOWMODE(""), CHANGEMODE(""), MECHMODE(""),

  FUN("FUN"), GLOBAL("GLOBAL"), LOCAL("LOCAL"), INIT("INIT"),

  PROC("PROC"), RETURNS("RETURNS"), WHILE("WHILE"), SKIP("SKIP");

  Terminal(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
