package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

/**
 * Ident token class.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public class Ident extends AbstractToken {

    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = 6131360454112441672L;

    /**
     * Token's name.
     */
    private final String name;

    /**
     * Creates a new Ident token.
     * 
     * @param name token name
     */
    public Ident(String name) {
        super(Terminal.IDENT);
        this.name = name;
    }

    /**
     * Returns the token's name
     * 
     * @return name of token
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(IDENT, \"" + name + "\")";
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o.getClass() == this.getClass()) {
                if (super.equals((AbstractToken) o)) {
                    if (name.equals(((Ident) o).name)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
