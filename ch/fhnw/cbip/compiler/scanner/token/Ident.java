package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Ident extends AbstractToken {
  private final String name;

  public Ident(String name, int line) {
    super(Terminal.IDENT, line);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "(IDENT, \"" + name + "\")";
  }
}
