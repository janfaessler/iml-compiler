package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

/**
 * Symbol token class.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public abstract class Symbol extends AbstractToken {
    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = 8396852952140113385L;

    /**
     * Creates a new Smybol token.
     * 
     * @param terminal Terminal symbol
     */
    public Symbol(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == this.getClass()) {
            return (super.equals((AbstractToken) o));
        }
        return false;
    }

    /**
     * Class for Colon token.
     */
    public static class Colon extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -5957108226551908022L;

        /**
         * Creates a new Colon token.
         */
        public Colon() {
            super(Terminal.COLON);
        }
    }

    /**
     * Class for Comma token.
     */
    public static class Comma extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 6857008891745539417L;

        /**
         * Creates a new Comma token.
         */
        public Comma() {
            super(Terminal.COMMA);
        }
    }

    /**
     * Class for Semicolon token.
     */
    public static class Semicolon extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 6695798249551185686L;

        /**
         * Creates a new Semicolon token.
         */
        public Semicolon() {
            super(Terminal.SEMICOLON);
        }
    }

    /**
     * Class for Exclamation Mark token.
     */
    public static class ExclaMark extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -4438981643984196791L;

        /**
         * Creates a new Exclamation Mark token.
         */
        public ExclaMark() {
            super(Terminal.EXCLAMARK);
        }
    }

    /**
     * Class for Question Mark token.
     */
    public static class QuestMark extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -4230336958022241898L;

        /**
         * Creates a new Question Mark token.
         */
        public QuestMark() {
            super(Terminal.QUESTMARK);
        }
    }

    /**
     * Class for Becomes token ':='.
     */
    public static class Becomes extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 4869909955231080470L;
        
        /**
         * Type of operator.
         */
        private final OperatorAttribute attribute;

        /**
         * Creates a new Becomes token ':='.
         */
        public Becomes(OperatorAttribute attribute) {
            super(Terminal.BECOMES);
            this.attribute = attribute;
        }
        
        /**
         * Returns the becomes operator token type.
         * 
         * @return token type
         */
        public OperatorAttribute getAttribute() {
            return attribute;
        }
        
        public String toString(final String indent) {
    		return indent
    				+ "<Symbol name=\""
    				+ getTerminal().toString()
    				+ "\" attribute=\""
    				+ attribute.toString()
    				+ "\" line=\""
    				+ super.getLine()
    				+ "\"/>\n";
    	}
    }

    /**
     * Class for Left Brace token.
     */
    public static class LBrace extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 4194464821518588448L;

        /**
         * Creates a new Left Brace token.
         */
        public LBrace() {
            super(Terminal.LBRACE);
        }
    }

    /**
     * Class for Right Brace token.
     */
    public static class RBrace extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -7875174837681837309L;

        /**
         * Creates a new Right Brace token.
         */
        public RBrace() {
            super(Terminal.RBRACE);
        }
    }

    /**
     * Class for Left Parenthesis token.
     */
    public static class LParen extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -574865805781420981L;

        /**
         * Creates a new Left Parenthesis token.
         */
        public LParen() {
            super(Terminal.LPAREN);
        }
    }

    /**
     * Class for Right Parenthesis token.
     */
    public static class RParen extends Symbol {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 912277612849109158L;

        /**
         * Creates a new Right Parenthesis token.
         */
        public RParen() {
            super(Terminal.RPAREN);
        }
    }

}
