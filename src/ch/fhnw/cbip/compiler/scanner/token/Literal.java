package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.BoolVal;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

/**
 * Literal token class.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public class Literal extends AbstractToken {

    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = 7555556718921057885L;

    /**
     * Integer value of literal.
     */
    private final int value;

    /**
     * Boolean value of literal, only set if actually a boolean.
     */
    private BoolVal bool;

    /**
     * Creates a new Int32 Literal token.
     * 
     * @param int Integer value of Literal token
     */
    public Literal(int value) {
        super(Terminal.LITERAL);
        this.value = value;
    }

    /**
     * Creates a new Boolean Literal token.
     * 
     * @param int boolean value of Literal token
     */
    public Literal(BoolVal bool) {
        super(Terminal.LITERAL);
        this.value = bool.getIntVal();
        this.bool = bool;
    }

    @Override
    public String toString() {
        if (bool != null) {
            return "(LITERAL, " + bool.toString() + ")";
        } else {
            return "(LITERAL, IntVal " + value + ")";
        }
    }

    /**
     * Return boolean value of literal.
     * 
     * @return boolean value or true if integer value != 0
     */
    public boolean getBoolVal() {
        return (value != 0);
    }

    /**
     * Returns Integer value of literal.
     * 
     * @return Int32 value or 1 if true or 0 if false.
     */
    public int getIntVal() {
        return value;
    }

    /**
     * Is Integer literal.
     * 
     * @return true if integer literal.
     */
    public boolean isInteger() {
        return (bool == null);
    }

    /**
     * Is Boolean literal.
     * 
     * @return true if boolean literal.
     */
    public boolean isBoolean() {
        return (bool != null);
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == this.getClass()) {
            if (super.equals((AbstractToken) o)) {
                return (this.value == ((Literal) o).value);
            }
        }
        return false;
    }

}
