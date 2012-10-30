package ch.fhnw.cbip.compiler.scanner.token;

import java.io.Serializable;

import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

/**
 * Abstract class for tokens.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 * @author Jan Faessler <mail@janfaessler.ch>
 */
public abstract class AbstractToken implements IToken, Serializable {
    /**
     * Serial number of the class.
     */
    private static final long serialVersionUID = -4283948226021729377L;

    /**
     * Terminal of this token.
     */
    private final Terminal terminal;

    /**
     * Source code line.
     */
    private int line;

    /**
     * Creates a token.
     * 
     * @param terminal terminal of the token
     * @param line source code line of the token
     */
    public AbstractToken(Terminal terminal) {
        this.terminal = terminal;
    }

    /**
     * Returns the token's terminal symbol.
     * 
     * @return the token's terminal symbol
     */
    protected Terminal getTerminal() {
        return terminal;
    }

    /**
     * Returns the token's line number.
     * 
     * @return line number of token
     */
    public int getLine() {
        return line;
    }

    /**
     * Sets the token's line number.
     * 
     * @param number line number of token
     */
    public void setLine(int number) {
        this.line = number;
    }

    @Override
    public String toString() {
        return terminal.toString();
    }

    @Override
    public boolean equals(Object o) {
        try {
            AbstractToken cmp = (AbstractToken) o;
            if (this.terminal == cmp.terminal) {
                if (this.line == cmp.line) {
                    return true;
                }
            }
        } catch (Exception e) {
            // do nothing
        }
        return false;
    }
    
    @Override
    public AbstractToken clone(){
        try {
            return (AbstractToken) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }
}
