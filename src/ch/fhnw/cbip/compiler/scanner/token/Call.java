package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Call extends AbstractToken {
  public Call(int line){
    super(Terminal.CALL, line);
  }
}