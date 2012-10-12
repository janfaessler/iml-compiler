package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class LBrace extends AbstractToken {
  public LBrace(int line){
    super(Terminal.LBRACE, line);
  }
}