package ch.fhnw.cbip.compiler.scanner;

import fhnw.cbip.compiler.scanner.AbstractToken;
import fhnw.cbip.compiler.scanner.Terminal;

public class Literal extends AbstractToken {
  private final int value;

  Literal(int value, int line) {
    super(Terminal.LITERAL, line);
    this.value = value;
  }

  @Override
  public String toString() {
    return "(LITERAL," + value + ")";
  }

}
