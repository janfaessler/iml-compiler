package ch.fhnw.cbip.compiler.scanner.enums;

public enum ModeAttribute {
  
  // Mech Mode
  COPY("COPY"), REF("REF"),
  
  // Flow Mode
  IN("IN"), OUT("OUT"), INOUT("INOUT"),
  
  // Change Mode
  CONST("CONST"), VAR("VAR");
  
  ModeAttribute(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
