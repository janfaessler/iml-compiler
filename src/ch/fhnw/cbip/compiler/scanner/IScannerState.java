package ch.fhnw.cbip.compiler.scanner;

import ch.fhnw.cbip.compiler.error.LexicalError;

/**
 * State interface for Scanner state pattern.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public interface IScannerState {
    /**
     * Method for state to process the current char (-sequence). Processed chars get removed and a
     * token gets added to the context if it makes sense.
     * 
     * @param c current char sequence
     * @param context Scanner context
     * @return array of unprocessed chars
     * @throws LexicalError thrown if syntax problem occurs
     */
    public char[] handleChar(char[] c, IScannerContext context) throws LexicalError;
}
