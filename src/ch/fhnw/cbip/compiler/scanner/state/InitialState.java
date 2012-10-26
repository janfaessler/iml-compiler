package ch.fhnw.cbip.compiler.scanner.state;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IScannerContext;
import ch.fhnw.cbip.compiler.scanner.IScannerState;
import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.enums.ScannerSymbol;
import ch.fhnw.cbip.compiler.scanner.token.Keyword;

/**
 * Initial State of the scanner.
 * 
 * @author Michael Kuenzli, <michael@kuenzli.eu>
 */
public class InitialState implements IScannerState {

    @Override
    public char[] handleChar(char[] c, IScannerContext context) throws LexicalError {
        assert (c.length == 1);

        if ('0' <= c[0] && c[0] <= '9') {
            // is a number
            context.setState(new LiteralState(), false);
            return c;
        }
        if (('A' <= c[0] && c[0] <= 'Z') || ('a' <= c[0] && c[0] <= 'z')) {
            // is a letter
            context.setState(new LetterState(), false);
            return c;
        }
        if (ScannerSymbol.contains((int) c[0])) {
            // is a symbol
            context.setState(new SymbolState(), false);
            return c;
        }
        if ((' ' == c[0]) || ('\t' == c[0]) || ('\n' == c[0])) {
            // is white space
            context.setState(new InitialState(), false);
            return new char[0];
        }
        if ('\u0003' == c[0]) {
            // end of text
            IToken token = new Keyword.Sentinel();
            token.setLine(context.getLineNumber());
            context.addToken(token);
            return new char[0];
        } else {
            // is something else
            throw new LexicalError("Illegal character found", context.getLineNumber());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            return true;
        } else {
            return false;
        }

    }
}
