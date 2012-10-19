package ch.fhnw.cbip.compiler.scanner;

import ch.fhnw.cbip.compiler.error.LexicalError;

public interface IScannerState {
  public char[]  handleChar(char[] c, IScannerContext context) throws LexicalError;
}
