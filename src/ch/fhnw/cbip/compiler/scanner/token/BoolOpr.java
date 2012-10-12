package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.BoolOprAttr;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class BoolOpr extends AbstractToken {
  private final BoolOprAttr attribute;

  public BoolOpr(int line, BoolOprAttr attribute) {
    super(Terminal.BOOLOPR, line);
    this.attribute = attribute;
  }
  
  public BoolOprAttr getAttribute(){
    return attribute;
  }
  
  @Override
  public String toString() {
    return "(BOOLOPR, " + attribute.toString() + ")";
  }
}
