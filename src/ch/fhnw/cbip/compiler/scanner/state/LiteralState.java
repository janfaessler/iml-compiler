package ch.fhnw.cbip.compiler.scanner.state;

import java.util.Arrays;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IScannerContext;
import ch.fhnw.cbip.compiler.scanner.IScannerState;
import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.enums.ScannerSymbol;
import ch.fhnw.cbip.compiler.scanner.token.Literal;

public class LiteralState implements IScannerState {

  @Override
  public char[] handleChar(char[] c, IScannerContext context)
      throws LexicalError {
    assert(c.length > 1);
    int lastChar = c.length - 1;
    if (('0' <= c[lastChar] && c[lastChar] <= '9')) {
      // literal continues
      context.setState(this);
    } else {
      // literal ends
      if (ScannerSymbol.contains(c[lastChar]) 
          || (' ' == c[0])
          || ('\t' == c[0])) {
        String literal = new String(Arrays.copyOfRange(c, 0, c.length - 2));
        Literal token = new Literal(Integer.parseInt(literal), context.getLineNumber());
        context.addToken((IToken)token);
        c = new char[0];
        context.setState(new InitialState());
      } else {
        throw new LexicalError("Illegal character after literal",
            context.getLineNumber());
      }
    }
    return c;
  }

}
