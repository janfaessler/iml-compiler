package ch.fhnw.cbip.compiler.error;

/**
 * Lexical error is thrown by the Scanner if a syntax error occurs.
 * 
 * @author Michael Kuenzli, <michael@kuenzli.eu>
 */
public class LexicalError extends Exception {
    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = -487617110166683052L;

    /**
     * Line number of exception within the source code.
     */
    private final int lineNumber;

    /**
     * Message of the exception.
     */
    private final String message;

    /**
     * Creates a new lexical error exception.
     * 
     * @param message error message
     * @param lineNumber corresponding line number in source code
     */
    public LexicalError(String message, int lineNumber) {
        super();
        this.lineNumber = lineNumber;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + " at line " + lineNumber + ".";
    }

}
