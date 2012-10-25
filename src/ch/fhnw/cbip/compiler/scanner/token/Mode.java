package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.ModeAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Mode extends AbstractToken {
    private final ModeAttribute attribute;

    public Mode(Terminal terminal, ModeAttribute attribute) {
        super(terminal);
        this.attribute = attribute;
    }

    public ModeAttribute getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        return "(" + getTerminal().toString() + ", " + attribute.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            if (super.equals((AbstractToken) o)) {
                if (attribute == ((Mode) o).attribute) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class ChangeMode extends Mode {
        public ChangeMode(ModeAttribute attribute) {
            super(Terminal.CHANGEMODE, attribute);
            assert (attribute == ModeAttribute.CONST || attribute == ModeAttribute.VAR);
        }

    }

    public static class FlowMode extends Mode {
        public FlowMode(ModeAttribute attribute) {
            super(Terminal.FLOWMODE, attribute);
            assert (attribute == ModeAttribute.IN || attribute == ModeAttribute.INOUT || attribute == ModeAttribute.OUT);
        }
    }

    public static class MechMode extends Mode {
        public MechMode(ModeAttribute attribute) {
            super(Terminal.MECHMODE, attribute);
            assert (attribute == ModeAttribute.COPY || attribute == ModeAttribute.REF);
        }
    }

}
