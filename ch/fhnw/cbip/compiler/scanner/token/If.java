package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class If extends AbstractToken {
  public If(int line){
    super(Terminal.IF, line);
  }
}