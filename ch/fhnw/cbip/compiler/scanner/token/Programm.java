package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Programm extends AbstractToken {
  public Programm(int line){
    super(Terminal.PROGRAM, line);
  }
}