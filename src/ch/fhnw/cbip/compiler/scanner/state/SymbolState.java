package ch.fhnw.cbip.compiler.scanner.state;

import java.util.Arrays;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IScannerContext;
import ch.fhnw.cbip.compiler.scanner.IScannerState;
import ch.fhnw.cbip.compiler.scanner.enums.ScannerSymbol;
import ch.fhnw.cbip.compiler.scanner.enums.SymbolList;

/**
 * Symbol State of the scanner. 
 * 
 * Processes all symbols (operators).
 * 
 * @author Michael Kuenzli, <michael@kuenzli.eu>
 */
public class SymbolState implements IScannerState {

    @Override
    public char[] handleChar(char[] c, IScannerContext context) throws LexicalError {
        assert (c.length > 1);
        int lastChar = c.length - 1;
        if (c[lastChar] == '=' || c[lastChar] == '+' || c[lastChar] == '-') { // =+- are only possible follow-up
            // symbol continues
            context.setState(this, false);
        } else {
            if (('A' <= c[lastChar] && c[lastChar] <= 'Z') 
                    || ('a' <= c[lastChar] && c[lastChar] <= 'z')
                    || ('0' <= c[lastChar] && c[lastChar] <= '9') 
                    || (' ' == c[lastChar]) 
                    || ('\t' == c[lastChar])
                    || ('\n' == c[lastChar]) 
                    || (ScannerSymbol.contains(c[lastChar]))) {
                // keyword
                String letters = new String(Arrays.copyOfRange(c, 0, c.length - 1));
                SymbolList s = SymbolList.match(letters);
                if (s != null) {
                    s.setLine(context.getLineNumber());
                    context.addToken(s.getToken());
                    c = Arrays.copyOfRange(c, lastChar, lastChar + 1);
                    context.setState(new InitialState(), true);
                } else {
                    throw new LexicalError("Illegal symbol '" + letters + "'", context.getLineNumber());
                }
            } else {
                throw new LexicalError("Illegal character " + c[lastChar] + " after symbol", context.getLineNumber());
            }
        }
        return c;
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
