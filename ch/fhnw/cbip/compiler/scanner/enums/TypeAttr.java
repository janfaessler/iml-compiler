package ch.fhnw.cbip.compiler.scanner.enums;

public enum TypeAttr {
  INT("INT"), BOOL("BOOL");

  TypeAttr(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
