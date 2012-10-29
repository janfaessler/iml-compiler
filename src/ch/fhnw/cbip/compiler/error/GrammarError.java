package ch.fhnw.cbip.compiler.error;

/**
 * Lexical error is thrown by the Parser if a grammatical error occurs.
 * 
 * @author Michael Kuenzli, <michael@kuenzli.eu>
 */
public class GrammarError extends Exception {
    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = 3165383600003294482L;

    /**
     * Line number of exception within the source code.
     */
    private final int lineNumber;

    /**
     * Message of the exception.
     */
    private final String message;

    /**
     * Creates a new grammar error exception.
     * 
     * @param message error message
     * @param lineNumber corresponding line number in source code
     */
    public GrammarError(String message, int lineNumber) {
        super();
        this.lineNumber = lineNumber;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "LexicalError: " + message + " at line " + lineNumber + ".";
    }

}
