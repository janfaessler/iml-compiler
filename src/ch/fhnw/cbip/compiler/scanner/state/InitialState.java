package ch.fhnw.cbip.compiler.scanner.state;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IScannerContext;
import ch.fhnw.cbip.compiler.scanner.IScannerState;
import ch.fhnw.cbip.compiler.scanner.enums.ScannerSymbol;

/**
 * 
 * @author michael
 * 
 */
public class InitialState implements IScannerState {

  @Override
  public char[] handleChar(char[] c, IScannerContext context)
      throws LexicalError {
    assert (c.length == 1);

    if ('0' <= c[0] && c[0] <= '9') { 
     // is a number
      context.setState(new LiteralState(), false);
    }
    if (('A' <= c[0] && c[0] <= 'Z') || ('a' <= c[0] && c[0] <= 'z')) {
      // is a letter
      context.setState(new LetterState(), false);
    }
    if (ScannerSymbol.contains((int) c[0])) {
      // is a symbol
      context.setState(new SymbolState(), false);
    }
    if ((' ' == c[0]) || ('\t' == c[0])) {
      // is white space
      context.setState(new InitialState(), false);
      c = new char[0];
    } else {
      // is something else
      throw new LexicalError("Illegal character found", context.getLineNumber());
    }
    return c;
  }
  
  @Override
  public boolean equals(Object o){
    if(o.getClass() == this.getClass()){
      return true;
    } else {
      return false;
    }
  }

}
