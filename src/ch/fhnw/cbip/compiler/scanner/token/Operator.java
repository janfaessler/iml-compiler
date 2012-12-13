package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

/**
 * Operator token class.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public abstract class Operator extends AbstractToken {

    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = -6086653757673370032L;

    /**
     * Type of operator.
     */
    private final OperatorAttribute attribute;

    /**
     * Creates a new operator token.
     * 
     * @param terminal Terminal symbol
     * @param attribute operator token type
     */
    public Operator(Terminal terminal, OperatorAttribute attribute) {
        super(terminal);
        this.attribute = attribute;
    }

    /**
     * Returns the operator token type.
     * 
     * @return token type
     */
    public OperatorAttribute getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "(" + getTerminal().toString() + ", " + attribute.toString() + ")";
    }
    
	public String toString(final String indent) {
		return indent
				+ "<Operator name=\""
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
                return (attribute == ((Operator) o).attribute);
            }
        }
        return false;
    }

    /**
     * Class for AddOpr token.
     */
    public static class AddOpr extends Operator {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 4599140378783118938L;

        /**
         * Creates a new token addition Operators.
         */
        public AddOpr(OperatorAttribute attribute) {
            super(Terminal.ADDOPR, attribute);
            assert (attribute == OperatorAttribute.PLUS || attribute == OperatorAttribute.MINUS);
        }
    }

    /**
     * Class for BoolOpr token.
     */
    public static class BoolOpr extends Operator {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 9000095108807672446L;

        /**
         * Creates a new token for boolean Operators.
         */
        public BoolOpr(OperatorAttribute attribute) {
            super(Terminal.BOOLOPR, attribute);
            assert (attribute == OperatorAttribute.CAND || attribute == OperatorAttribute.COR);
        }
    }

    /**
     * Class for MultOpr token.
     */
    public static class MultOpr extends Operator {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 7908092160119701296L;

        /**
         * Creates a new token for multiplication Operators.
         */
        public MultOpr(OperatorAttribute attribute) {
            super(Terminal.MULTOPR, attribute);
            assert (attribute == OperatorAttribute.TIMES || attribute == OperatorAttribute.MOD || attribute == OperatorAttribute.DIV);
        }
    }

    /**
     * Class for RelOpr token.
     */
    public static class RelOpr extends Operator {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -5780932918206995185L;

        /**
         * Creates a new token for relational Operators.
         */
        public RelOpr(OperatorAttribute attribute) {
            super(Terminal.RELOPR, attribute);
            assert (attribute == OperatorAttribute.EQ || attribute == OperatorAttribute.NE
                    || attribute == OperatorAttribute.LT || attribute == OperatorAttribute.GT
                    || attribute == OperatorAttribute.LE || attribute == OperatorAttribute.GE);
        }
    }

}
