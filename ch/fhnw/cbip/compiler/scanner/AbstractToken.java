package ch.fhnw.cbip.compiler.scanner;

import fhnw.cbip.compiler.scanner.IToken;
import fhnw.cbip.compiler.scanner.Terminal;

public abstract class AbstractToken implements IToken {
  private final Terminal terminal;
  private final int line;

  public AbstractToken(Terminal terminal, int line) {
    this.terminal = terminal;
    this.line = line;
  }

  protected Terminal getTerminal() {
    return terminal;
  }
  
  public int getLine(){
    return line;
  }
  
  @Override
  public String toString() {
    return terminal.toString();
  }
}
