package ch.fhnw.cbip.compiler.scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.state.InitialState;
import ch.fhnw.cbip.compiler.utils.ArrayUtils;

/**
 * Scanner takes the source code and generates a TokenList from it.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public class Scanner implements IScannerContext {
    /**
     * Token list to be generated.
     */
    private ITokenList tokenList = null;

    /**
     * Current state (for state pattern).
     */
    private IScannerState currentState = null;

    /**
     * Currently parsed line number.
     */
    private int lineNumber = 0;

    /**
     * Whether there should be added something to the current char-sequence.
     */
    private boolean keepCurrent = false;

    @Override
    public void addToken(IToken token) {
        if (tokenList != null) {
            tokenList.add(token);
        }
    }

    @Override
    public void setState(IScannerState state, boolean keepCurrent) {
        if (state != null) {
            currentState = state;
        }
        this.keepCurrent = keepCurrent;
    }

    @Override
    public IScannerState getState() {
        return currentState;
    }

    /**
     * Main task of Scanner. Generates a token list from source code input.
     * 
     * @param input IML source code as BufferedReader
     * @return ITokenList to be processed by the Parser
     * @throws IOException thrown if issues with the BufferedReader occur
     * @throws LexicalError thrown if syntax problems occur
     */
    public ITokenList scan(BufferedReader input) throws IOException, LexicalError {
        tokenList = new TokenList(); // reset token list
        currentState = new InitialState();
        String currentLine = "";
        char[] currentChar = {};
        lineNumber = 0;
        while ((currentLine = input.readLine()) != null) {
            lineNumber++;
            currentLine = currentLine + "\n"; // readLine removes end-of-line character
            Iterator<Character> chars = stringIterator(currentLine);

            // call states as long there are characters of the line to process
            while (chars.hasNext() || currentChar.length > 0) {
                if (!keepCurrent)
                    currentChar = ArrayUtils.expandCharArray(currentChar, (char) chars.next());
                currentChar = currentState.handleChar(currentChar, this);
            }
        }
        // process end of file
        if (currentState.equals(new InitialState())) {
            char[] end_of_text = { '\u0003' };
            currentState.handleChar(end_of_text, this);
        } else {
            throw new LexicalError("Unexpected file end", lineNumber);
        }
        return tokenList;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Creates a Character-Iterator of a given string. <br />
     * Since the String cannot be modified, there is no remove() implemented.
     * @param string String to be treaded as collection which has an iterator
     * @return Character-Iterator of the given String
     */
    private static Iterator<Character> stringIterator(final String string) {
        if (string == null)
            throw new NullPointerException();

        return new Iterator<Character>() {
            private int index = 0;

            public boolean hasNext() {
                return index < string.length();
            }

            public Character next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                return string.charAt(index++);
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

}
