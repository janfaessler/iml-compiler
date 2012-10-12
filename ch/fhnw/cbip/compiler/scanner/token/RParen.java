package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class RParen extends AbstractToken {
  public RParen(int line){
    super(Terminal.RPAREN, line);
  }
}