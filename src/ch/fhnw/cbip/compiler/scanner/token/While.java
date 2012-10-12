package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class While extends AbstractToken {
  public While(int line){
    super(Terminal.WHILE, line);
  }
}