package ch.fhnw.cbip.compiler.scanner;

/**
 * Token interface.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public interface IToken {
    /**
     * Returns line of where the token locates within the source code.
     * @return source code line number of the token
     */
    public int getLine();

    /**
     * Sets the line of where the token locates within the source code.
     * @param the line number
     */
    public void setLine(int number);

    /**
     * Returns a deep copy of the token.
     * @return deep copy of the token
     */
    public IToken copy();
}
