package ch.fhnw.cbip.compiler.scanner.enums;

public enum AddOprAttr {
  PLUS("PLUS"), MINUS("MINUS");  
  
  AddOprAttr(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
