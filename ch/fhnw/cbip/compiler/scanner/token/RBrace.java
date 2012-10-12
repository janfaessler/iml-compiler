package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class RBrace extends AbstractToken {
  public RBrace(int line){
    super(Terminal.RBRACE, line);
  }
}