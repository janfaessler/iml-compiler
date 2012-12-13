package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.ModeAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

/**
 * Mode token class.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public abstract class Mode extends AbstractToken {

    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = -4598107261849280663L;
    
    /**
     * Characterizes type of mode token.
     */
    private final ModeAttribute attribute;
    
    /**
     * Creates a new mode token.
     * @param terminal Terminal symbol
     * @param attribute type of mode token
     */
    public Mode(Terminal terminal, ModeAttribute attribute) {
        super(terminal);
        this.attribute = attribute;
    }

    /**
     * Returns the mode token type.
     * @return mode token type
     */
    public ModeAttribute getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "(" + getTerminal().toString() + ", " + attribute.toString() + ")";
    }
    
	public String toString(final String indent) {
		return indent
				+ "<Mode name=\""
				+ getTerminal().toString()
				+ "\" attribute=\""
				+ attribute.toString()
				+ "\" line=\""
				+ super.getLine()
				+ "\"/>\n";
	}

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == this.getClass()) {
            if (super.equals((AbstractToken) o)) {
                return (attribute == ((Mode) o).attribute);
            }
        }
        return false;
    }

    /**
     * Class for ChangeMode token.
     */
    public static class ChangeMode extends Mode {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -7329795145778696425L;

        /**
         * Creates a new ChangeMode token.
         */
        public ChangeMode(ModeAttribute attribute) {
            super(Terminal.CHANGEMODE, attribute);
            assert (attribute == ModeAttribute.CONST || attribute == ModeAttribute.VAR);
        }

    }
    
    /**
     * Class for FlowMode token.
     */
    public static class FlowMode extends Mode {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -578137168175777227L;

        /**
         * Creates a new FlowMode token.
         */
        public FlowMode(ModeAttribute attribute) {
            super(Terminal.FLOWMODE, attribute);
            assert (attribute == ModeAttribute.IN || attribute == ModeAttribute.INOUT || attribute == ModeAttribute.OUT);
        }
    }
    
    /**
     * Class for MechMode token.
     */
    public static class MechMode extends Mode {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 5477900678682157567L;

        /**
         * Creates a new MechMode token.
         */
        public MechMode(ModeAttribute attribute) {
            super(Terminal.MECHMODE, attribute);
            assert (attribute == ModeAttribute.COPY || attribute == ModeAttribute.REF);
        }
    }

}
