package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Local extends AbstractToken {
  public Local(int line){
    super(Terminal.LOCAL, line);
  }
}