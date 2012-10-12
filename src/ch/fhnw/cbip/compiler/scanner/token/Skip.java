package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Skip extends AbstractToken {
  public Skip(int line){
    super(Terminal.SKIP, line);
  }
}