package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Not extends AbstractToken {
  public Not(int line){
    super(Terminal.NOT, line);
  }
}