package ch.fhnw.cbip.compiler.scanner.state;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.Scanner;
import ch.fhnw.cbip.compiler.scanner.token.Keyword;

public class InitialStateTest {

    private Scanner mockScanner;
    private InitialState state;

    @Before
    public void setUp() throws Exception {
        mockScanner = mock(Scanner.class);
        state = new InitialState();
    }

    @Test(expected = LexicalError.class)
    public void testHandleCharFail() throws LexicalError {
        when(mockScanner.getLineNumber()).thenReturn(1);
        char[] failChar = { '\u0277' };
        state.handleChar(failChar, mockScanner);
    }

    @Test
    public void testHandleCharLiteral() throws LexicalError {
        when(mockScanner.getLineNumber()).thenReturn(1);
        char[] literalChar = { '3' };
        LiteralState expectedState = new LiteralState();

        char[] returnChar = state.handleChar(literalChar, mockScanner);
        
        assertEquals(literalChar, returnChar);
        verify(mockScanner).setState(expectedState, false);
    }

    @Test
    public void testHandleCharLetter() throws LexicalError {
        when(mockScanner.getLineNumber()).thenReturn(1);
        char[] literalChar = { 'f' };
        LetterState expectedState = new LetterState();

        char[] returnChar = state.handleChar(literalChar, mockScanner);
        
        assertEquals(literalChar, returnChar);
        verify(mockScanner).setState(expectedState, false);
    }
    
    @Test
    public void testHandleCharSymbol() throws LexicalError {
        when(mockScanner.getLineNumber()).thenReturn(1);
        char[] symbolChar = { '+' };
        SymbolState expectedState = new SymbolState();

        char[] returnChar = state.handleChar(symbolChar, mockScanner);
        
        assertEquals(symbolChar, returnChar);
        verify(mockScanner).setState(expectedState, false);
    }
    
    @Test
    public void testHandleCharWhitespace() throws LexicalError {
        when(mockScanner.getLineNumber()).thenReturn(1);
        char[] wsChar = { '\t' };
        InitialState expectedState = new InitialState();

        char[] returnChar = state.handleChar(wsChar, mockScanner);
        
        assertTrue(returnChar.length == 0);
        verify(mockScanner).setState(expectedState, false);
    }
    
    @Test
    public void testHandleCharEOT() throws LexicalError {
        when(mockScanner.getLineNumber()).thenReturn(1);
        char[] eotChar = { '\u0003' };
        IToken expectedToken = new Keyword.Sentinel();
        expectedToken.setLine(1);
        char[] returnChar = state.handleChar(eotChar, mockScanner);
        
        assertTrue(returnChar.length == 0);
        verify(mockScanner).addToken(expectedToken);
    }

}
