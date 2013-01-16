package ch.fhnw.cbip.compiler.error;

public class SemanticError extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 9053690466257000714L;

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
    public SemanticError(String message, int lineNumber) {
        super();
        this.lineNumber = lineNumber;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + " at line " + lineNumber + ".";
    }

}
