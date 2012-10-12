package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Proc extends AbstractToken {
  public Proc(int line){
    super(Terminal.PROC, line);
  }
}