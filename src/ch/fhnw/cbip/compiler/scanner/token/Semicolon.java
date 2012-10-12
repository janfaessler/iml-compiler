package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Semicolon extends AbstractToken {
  public Semicolon(int line){
    super(Terminal.SEMICOLON, line);
  }
}