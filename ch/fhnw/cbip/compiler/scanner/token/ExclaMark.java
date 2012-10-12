package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class ExclaMark extends AbstractToken {
  public ExclaMark(int line){
    super(Terminal.EXCLAMARK, line);
  }
}