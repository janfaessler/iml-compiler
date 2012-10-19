package ch.fhnw.cbip.compiler.scanner.state;

import java.util.Arrays;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IScannerContext;
import ch.fhnw.cbip.compiler.scanner.IScannerState;
import ch.fhnw.cbip.compiler.scanner.enums.Keyword;
import ch.fhnw.cbip.compiler.scanner.enums.ScannerSymbol;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;
import ch.fhnw.cbip.compiler.scanner.enums.TypeAttribute;
import ch.fhnw.cbip.compiler.scanner.token.Ident;
import ch.fhnw.cbip.compiler.scanner.token.Type;

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
        
        Terminal.valueOf(letters);
        Keyword k = Keyword.match(letters);
        if(k == null){
          // is an identifier
          context.addToken(new Ident(letters, line));
          c = Arrays.copyOfRange(c, lastChar, lastChar+1);
          context.setState(new InitialState());
        } else {
          //is a keyword
          switch(k){
            case BOOL:
              context.addToken(new Type(TypeAttribute.BOOL, line));
              break;
            // TODO a lot
          }
          c = Arrays.copyOfRange(c, lastChar, lastChar+1);
          context.setState(new InitialState());
        }
        
      }
    }
    return c;
  }

}
