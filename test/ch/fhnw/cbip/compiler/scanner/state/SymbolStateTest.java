package ch.fhnw.cbip.compiler.scanner.state;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.Scanner;
import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.token.Operator;
import ch.fhnw.cbip.compiler.scanner.token.Symbol;

/**
 * Test for SymbolState class.
 * 
 * @author Michael Kuenzli, <michael@kuenzli.eu>
 */
public class SymbolStateTest {

    private Scanner mockScanner;
    private SymbolState state;

    @Before
    public void setUp() throws Exception {
        mockScanner = mock(Scanner.class);
        state = new SymbolState();
    }

    @Test(expected = LexicalError.class)
    public void testHandleCharFail() throws LexicalError {
        when(mockScanner.getLineNumber()).thenReturn(1);
        char[] failChar = { '+', 193 };
        state.handleChar(failChar, mockScanner);
    }

    @Test
    public void testHandleCharContinue() throws LexicalError {
        char[] goodChar = { '<', '=' };
        char[] returnChar = state.handleChar(goodChar, mockScanner);
        assertEquals(goodChar, returnChar);
        verify(mockScanner).setState(state); // has to stay in the same state
    }

    @Test
    public void testHandleIllegalSymbol() {
        char[] illegalSymbol = { '+', '-', ' ' };
        when(mockScanner.getLineNumber()).thenReturn(1);
        try {
            @SuppressWarnings("unused")
            char[] returnChar = state.handleChar(illegalSymbol, mockScanner);
            fail("LexicalError expected");
        } catch (LexicalError e) {
            String msg = e.getMessage();
            assertEquals("LexicalError: Illegal symbol '+-' at line 1.", msg);
        }
    }

    @Test
    public void testHandleRParenEnd() throws LexicalError {
        char[] rparenChar = { ')', ' ' };
        int lineNumber = 1;
        Symbol expectedToken = new Symbol.RParen(lineNumber);
        InitialState expectedState = new InitialState();

        when(mockScanner.getLineNumber()).thenReturn(lineNumber);
        char[] returnChar = state.handleChar(rparenChar, mockScanner);

        assertEquals(1, returnChar.length);
        assertEquals(' ', returnChar[0]);
        verify(mockScanner).addToken(expectedToken);
        verify(mockScanner).setState(expectedState);
    }
    
    @Test
    public void testHandleGEEnd() throws LexicalError {
        char[] geChar = { '>', '=', '1' };
        int lineNumber = 1;
        Operator expectedToken = new Operator.RelOpr(lineNumber, OperatorAttribute.GE);
        InitialState expectedState = new InitialState();

        when(mockScanner.getLineNumber()).thenReturn(lineNumber);
        char[] returnChar = state.handleChar(geChar, mockScanner);

        assertEquals(1, returnChar.length);
        assertEquals('1', returnChar[0]);
        verify(mockScanner).addToken(expectedToken);
        verify(mockScanner).setState(expectedState);
    }

}
