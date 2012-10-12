package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.RelOprAttr;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class RelOpr extends AbstractToken {
  private final RelOprAttr attribute;

  public RelOpr(int line, RelOprAttr attribute) {
    super(Terminal.RELOPR, line);
    this.attribute = attribute;
  }
  
  public RelOprAttr getAttribute(){
    return attribute;
  }
  
  @Override
  public String toString() {
    return "(RELOPR, " + attribute.toString() + ")";
  }
}
