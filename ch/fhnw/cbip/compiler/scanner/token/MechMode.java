package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.MechModeAttr;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class MechMode extends AbstractToken {
  private final MechModeAttr attribute;

  public MechMode(int line, MechModeAttr attribute) {
    super(Terminal.MECHMODE, line);
    this.attribute = attribute;
  }
  
  public MechModeAttr getAttribute(){
    return attribute;
  }
  
  @Override
  public String toString() {
    return "(MECHMODE, " + attribute.toString() + ")";
  }
}
