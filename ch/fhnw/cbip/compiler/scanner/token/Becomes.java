package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Becomes extends AbstractToken {
  public Becomes(int line){
    super(Terminal.BECOMES, line);
  }
}