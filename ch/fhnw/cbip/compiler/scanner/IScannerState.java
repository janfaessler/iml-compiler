package ch.fhnw.cbip.compiler.scanner;

import ch.fhnw.cbip.compiler.error.LexicalError;

public interface IScannerState {
  public IScannerState handleChar(char[] c) throws LexicalError;
}
