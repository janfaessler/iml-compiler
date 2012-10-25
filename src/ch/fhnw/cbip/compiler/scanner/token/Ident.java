package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Ident extends AbstractToken {

	private static final long serialVersionUID = 6131360454112441672L;
	private final String name;

    public Ident(String name) {
        super(Terminal.IDENT);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "(IDENT, \"" + name + "\")";
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            if (super.equals((AbstractToken) o)) {
                if (name.equals(((Ident) o).name)) {
                    return true;
                }
            }
        }
        return false;
    }

}
