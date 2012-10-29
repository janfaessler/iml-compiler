package ch.fhnw.cbip.compiler.scanner;

import ch.fhnw.cbip.compiler.scanner.IToken;

/**
 * Output of Scanner, a list of token objects.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public interface ITokenList {
    /**
     * Add a token to the list.
     * 
     * @param token IToken to add
     */
    public void add(IToken token);

    /**
     * Resets the list iterator.
     */
    public void reset();

    /**
     * Returns the next Token and then moves the cursor ahead by one token.
     * 
     * @return next Token
     */
    public IToken nextToken();
}
