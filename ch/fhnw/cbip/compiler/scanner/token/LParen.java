package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class LParen extends AbstractToken {
  public LParen(int line){
    super(Terminal.LPAREN, line);
  }
}