package ch.fhnw.cbip.compiler.error;
/**
 * CodeGeneration error is thrown by the CodeGenerator if a error occurs.
 * 
 * @author Jan Faessler, <jan.faessler@students.fhnw.ch>
 */
public class GenerationError extends Exception {

	/**
	 * Serial id for serialization (used for deep copy).
	 */
	private static final long serialVersionUID = 7579885743779527204L;

    /**
     * Message of the exception.
     */
    private final String message;

    /**
     * Creates a new CodeGeneration error exception.
     * 
     * @param message error message
     * @param lineNumber corresponding line number in source code
     */
    public GenerationError(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "GenerationError: " + message + ".";
    }
}
