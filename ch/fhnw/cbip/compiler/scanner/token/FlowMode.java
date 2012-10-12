package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.FlowModeAttr;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class FlowMode extends AbstractToken {
  private final FlowModeAttr attribute;

  public FlowMode(int line, FlowModeAttr attribute) {
    super(Terminal.FLOWMODE, line);
    this.attribute = attribute;
  }
  
  public FlowModeAttr getAttribute(){
    return attribute;
  }
  
  @Override
  public String toString() {
    return "(FLOWMODE, " + attribute.toString() + ")";
  }
}
