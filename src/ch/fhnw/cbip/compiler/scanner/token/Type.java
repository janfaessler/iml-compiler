package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;
import ch.fhnw.cbip.compiler.scanner.enums.TypeAttribute;

public class Type extends AbstractToken {

	private static final long serialVersionUID = -3946080348463945075L;
	private final TypeAttribute attribute;

    public Type(TypeAttribute attribute) {
        super(Terminal.TYPE);
        this.attribute = attribute;
    }

    public TypeAttribute getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "(TYPE, " + attribute.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (this.getClass() == o.getClass()) {
                Type cmp = (Type) o;
                if (this.attribute == cmp.attribute) {
                    if (this.getLine() == cmp.getLine()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
