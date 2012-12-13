package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;
import ch.fhnw.cbip.compiler.scanner.enums.TypeAttribute;

/**
 * Type token class.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public class Type extends AbstractToken {
    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = -3946080348463945075L;

    /**
     * Type of type token.
     */
    private final TypeAttribute attribute;

    /**
     * Creates a new type token.
     * 
     * @param attribute type of type token
     */
    public Type(TypeAttribute attribute) {
        super(Terminal.TYPE);
        this.attribute = attribute;
    }

    /**
     * Returns type of type.
     * 
     * @return TypeAttribute type of type
     */
    public TypeAttribute getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "(TYPE, " + attribute.toString() + ")";
    }
    
	public String toString(final String indent) {
		return indent
				+ "<Type type=\""
				+ attribute.toString()
				+ "\" line=\""
				+ super.getLine()
				+ "\"/>\n";
	}

    @Override
    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            Type cmp = (Type) o;
            return (this.attribute == cmp.attribute) && (this.getLine() == cmp.getLine());
        }
        return false;
    }
}
