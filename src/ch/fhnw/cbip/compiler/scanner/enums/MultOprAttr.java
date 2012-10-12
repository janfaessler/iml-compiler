package ch.fhnw.cbip.compiler.scanner.enums;

public enum MultOprAttr {
  TIMES("TIMES"), MOD("MOD");
  
  MultOprAttr(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
