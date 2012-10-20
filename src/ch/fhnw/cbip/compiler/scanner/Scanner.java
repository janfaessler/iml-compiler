package ch.fhnw.cbip.compiler.scanner;

import java.io.BufferedReader;
import java.io.IOException;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.state.InitialState;
import ch.fhnw.cbip.compiler.utils.ArrayUtils;

public class Scanner implements IScannerContext {
    private ITokenList tokenList = null;
    private IScannerState currentState = null;
    private int lineNumber = 0;

    public void addToken(IToken token) {
        if(tokenList != null){
            tokenList.add(token);
        }
    }

    public void setState(IScannerState state) {
        if(currentState != null){
            currentState = state;
        }
    }

    public IScannerState getState() {
        return currentState;
    }

    public ITokenList scan(BufferedReader input) throws IOException, LexicalError {
        tokenList = new TokenList(); // reset token list
        currentState = new InitialState();
        String currentLine = "";
        char[] currentChar = {};
        lineNumber = 0;
        while ((currentLine = input.readLine()) != null) {
            lineNumber++;
            for (char c : currentLine.toCharArray()) {
                currentChar = currentState.handleChar(ArrayUtils.expandCharArray(currentChar, c), this);
            }
        }
        return tokenList;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }

}
