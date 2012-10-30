package ch.fhnw.cbip.compiler.scanner;

/**
 * Token interface.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public interface IToken extends Cloneable{
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
     * Clone implementation for Cloneable interface.
     * @return clone of IToken object
     */
    public IToken clone();
}
