package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Returns extends AbstractToken {
  public Returns(int line){
    super(Terminal.RETURNS, line);
  }
}