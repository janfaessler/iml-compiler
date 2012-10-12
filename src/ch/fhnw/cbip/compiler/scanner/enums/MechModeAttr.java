package ch.fhnw.cbip.compiler.scanner.enums;

public enum MechModeAttr {
  COPY("COPY"), REF("REF");
  
  MechModeAttr(String toString) {
    this.toString = toString;
  }

  private String toString;

  @Override
  public String toString() {
    return toString;
  }
}
