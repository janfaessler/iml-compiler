package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Fun extends AbstractToken {
  public Fun(int line){
    super(Terminal.FUN, line);
  }
}