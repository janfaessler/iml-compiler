package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Comma extends AbstractToken {
  public Comma(int line){
    super(Terminal.COMMA, line);
  }
}