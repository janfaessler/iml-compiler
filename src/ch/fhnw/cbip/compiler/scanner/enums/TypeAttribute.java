package ch.fhnw.cbip.compiler.scanner.enums;

public enum TypeAttribute {
  INT("INT"), BOOL("BOOL");

  TypeAttribute(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
