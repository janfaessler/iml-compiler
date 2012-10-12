package ch.fhnw.cbip.compiler.scanner.enums;

public enum ChangeModeAttr {
  CONST("CONST"), VAR("VAR");
  
  ChangeModeAttr(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
