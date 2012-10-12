package ch.fhnw.cbip.compiler.scanner.enums;

public enum RelOprAttr {
  EQ("EQ"), NE("NE"), LT("LT"), GT("GT"), LE("LE"), GE("GE");
  
  RelOprAttr(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
