package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;
import ch.fhnw.cbip.compiler.scanner.enums.TypeAttribute;

public class Type extends AbstractToken {
  private final TypeAttribute attribute;

  public Type(TypeAttribute attribute, int line) {
    super(Terminal.TYPE, line);
    this.attribute = attribute;
  }

  public TypeAttribute getAttribute() {
    return attribute;
  }

  @Override
  public String toString() {
    return "(TYPE, " + attribute.toString() + ")";
  }
}
