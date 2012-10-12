package ch.fhnw.cbip.compiler.scanner.enums;

public enum BoolOprAttr {
  CAND("CAND"), COR("COR");
  
  BoolOprAttr(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
