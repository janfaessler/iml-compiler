package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;
import ch.fhnw.cbip.compiler.scanner.enums.TypeAttr;

public class Type extends AbstractToken {
  private final TypeAttr attribute;

  public Type(TypeAttr attribute, int line) {
    super(Terminal.TYPE, line);
    this.attribute = attribute;
  }

  public TypeAttr getAttribute() {
    return attribute;
  }

  @Override
  public String toString() {
    return "(TYPE, " + attribute.toString() + ")";
  }
}
