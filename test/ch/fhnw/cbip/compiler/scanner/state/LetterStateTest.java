package ch.fhnw.cbip.compiler.scanner.state;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.Scanner;
import ch.fhnw.cbip.compiler.scanner.enums.TypeAttribute;
import ch.fhnw.cbip.compiler.scanner.token.Ident;
import ch.fhnw.cbip.compiler.scanner.token.Type;

public class LetterStateTest {

    private Scanner mockScanner;
    private LetterState state;

    @Before
    public void setUp() throws Exception {
        mockScanner = mock(Scanner.class);
        state = new LetterState();
    }
    
    @Test(expected = LexicalError.class)
    public void testHandleCharFail() throws LexicalError {
        when(mockScanner.getLineNumber()).thenReturn(1);
        char[] failChar = { 'a', 192 };
        state.handleChar(failChar, mockScanner);
    }
    
    @Test
    public void testHandleCharContinue() throws LexicalError {
        char[] goodChar = { 'a', '0' };
        char[] returnChar = state.handleChar(goodChar, mockScanner);
        assertEquals(goodChar, returnChar);
        verify(mockScanner).setState(state); // has to stay in the same state
    }
    
    @Test
    public void testHandleIdentEnd() throws LexicalError {
        char[] identChar = { 'a', 'b', ' ' };
        int lineNumber = 1;
        Ident expectedToken = new Ident("ab", lineNumber);
        InitialState expectedState = new InitialState();

        when(mockScanner.getLineNumber()).thenReturn(lineNumber);
        char[] returnChar = state.handleChar(identChar, mockScanner);

        assertEquals(1, returnChar.length);
        assertEquals(' ', returnChar[0]);
        verify(mockScanner).addToken(expectedToken);
        verify(mockScanner).setState(expectedState);
    }
    
    @Test
    public void testHandleKeywordEnd() throws LexicalError {
        char[] boolChar = { 'b', 'o', 'o', 'l', ' ' };
        int lineNumber = 1;
        Type expectedToken = new Type(TypeAttribute.BOOL, lineNumber);
        InitialState expectedState = new InitialState();

        when(mockScanner.getLineNumber()).thenReturn(lineNumber);
        char[] returnChar = state.handleChar(boolChar, mockScanner);

        assertEquals(1, returnChar.length);
        assertEquals(' ', returnChar[0]);
        verify(mockScanner).addToken(expectedToken);
        verify(mockScanner).setState(expectedState);
    }
}
