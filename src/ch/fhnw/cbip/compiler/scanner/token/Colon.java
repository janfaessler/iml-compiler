package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Colon extends AbstractToken {
  public Colon(int line){
    super(Terminal.COLON, line);
  }
}