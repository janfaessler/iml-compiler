package ch.fhnw.cbip.compiler.scanner;

/**
 * Context interface for Scanner state pattern.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public interface IScannerContext {
    /**
     * Sets next state.
     * 
     * @param state next scanner state
     */
    public void setState(IScannerState state, boolean keepCurrent);

    /**
     * Returns current scanner state.
     * 
     * @return current scanner state
     */
    public IScannerState getState();

    /**
     * Adds token to the context's token list.
     * 
     * @param token
     */
    public void addToken(IToken token);

    /**
     * Get current line number of parsed code.
     * 
     * @return line number
     */
    public int getLineNumber();

}
