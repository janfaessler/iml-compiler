package ch.fhnw.cbip.compiler.scanner.state;

import java.util.Arrays;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IScannerContext;
import ch.fhnw.cbip.compiler.scanner.IScannerState;
import ch.fhnw.cbip.compiler.scanner.enums.ScannerSymbol;
import ch.fhnw.cbip.compiler.scanner.enums.TypeAttribute;

public class LetterState implements IScannerState {

  @Override
  public char[] handleChar(char[] c, IScannerContext context)
      throws LexicalError {
    assert(c.length > 1);
    int lastChar = c.length - 1;
    
    if(('A' <= c[lastChar] && c[lastChar] <= 'Z') 
        || ('a' <= c[lastChar] && c[lastChar] <= 'z') 
        || ('0' <= c[lastChar] && c[lastChar] <= '9')){
      // keyword or identifier continues
      context.setState(this);
    } else {
      // keyword or identifier ends
      if(ScannerSymbol.contains(c[lastChar]) 
          || (' ' == c[lastChar])
          || ('\t' == c[lastChar])){
        String letters = new String(Arrays.copyOfRange(c, 0, c.length - 1));
        int line = context.getLineNumber();
        
      }
    }
    
    
    // TODO Auto-generated method stub
    return null;
  }

}
