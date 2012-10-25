package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class AbstractToken implements IToken {
    /**
     * Terminal of this token
     */
    private final Terminal terminal;

    /**
     * Source code line
     */
    private int line;

    /**
     * Creates a token
     * 
     * @param terminal terminal of the token
     * @param line source code line of the token
     */
    public AbstractToken(Terminal terminal) {
        this.terminal = terminal;
    }

    /**
     * @return the token's terminal symbol
     */
    protected Terminal getTerminal() {
        return terminal;
    }

    public int getLine() {
        return line;
    }
    
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
}
