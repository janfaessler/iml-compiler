package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Global extends AbstractToken {
  public Global(int line){
    super(Terminal.GLOBAL, line);
  }
}