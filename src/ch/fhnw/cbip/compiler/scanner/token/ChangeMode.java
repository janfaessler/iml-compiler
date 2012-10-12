package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.ChangeModeAttr;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class ChangeMode extends AbstractToken {
  private final ChangeModeAttr attribute;

  public ChangeMode(int line, ChangeModeAttr attribute) {
    super(Terminal.CHANGEMODE, line);
    this.attribute = attribute;
  }
  
  public ChangeModeAttr getAttribute(){
    return attribute;
  }
  
  @Override
  public String toString() {
    return "(CHANGEMODE, " + attribute.toString() + ")";
  }
}
