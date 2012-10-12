package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.MultOprAttr;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class MultOpr extends AbstractToken {
  private final MultOprAttr attribute;

  public MultOpr(int line, MultOprAttr attribute) {
    super(Terminal.MULTOPR, line);
    this.attribute = attribute;
  }
  
  public MultOprAttr getAttribute(){
    return attribute;
  }
  
  @Override
  public String toString() {
    return "(MULTOPR, " + attribute.toString() + ")";
  }
}
