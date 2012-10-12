package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Init extends AbstractToken {
  public Init(int line){
    super(Terminal.INIT, line);
  }
}