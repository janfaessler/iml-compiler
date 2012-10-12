package ch.fhnw.cbip.compiler.scanner.enums;

public enum FlowModeAttr {
  IN("IN"), OUT("OUT"), INOUT("INOUT");
  
  FlowModeAttr(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
