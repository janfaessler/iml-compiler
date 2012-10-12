package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.AddOprAttr;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class AddOpr extends AbstractToken {
  private final AddOprAttr attribute;

  public AddOpr(int line, AddOprAttr attribute) {
    super(Terminal.MULTOPR, line);
    this.attribute = attribute;
  }
  
  public AddOprAttr getAttribute(){
    return attribute;
  }
  
  @Override
  public String toString() {
    return "(ADDOPR, " + attribute.toString() + ")";
  }
}
