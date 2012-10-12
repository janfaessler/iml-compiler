package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Else extends AbstractToken {
  public Else(int line){
    super(Terminal.ELSE, line);
  }
}