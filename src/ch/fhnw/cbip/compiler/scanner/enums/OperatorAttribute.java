package ch.fhnw.cbip.compiler.scanner.enums;

public enum OperatorAttribute {
  // AddOpr
  PLUS("PLUS"), MINUS("MINUS"),
  
  // MultOpr
  TIMES("TIMES"), MOD("MOD"), DIV("DIV"),
  
  // RelOpr
  EQ("EQ"), NE("NE"), LT("LT"), GT("GT"), LE("LE"), GE("GE"),
  
  // BoolOpr
  CAND("CAND"), COR("COR"), 
  
  //CrementOpr
  INCR("INCR"), DECR("DECR"),
  ;  
  
  OperatorAttribute(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
