package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.BoolVal;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class Literal extends AbstractToken {
    private final int value;
    private BoolVal bool;

    public Literal(int value) {
        super(Terminal.LITERAL);
        this.value = value;
    }

    public Literal(BoolVal bool) {
        super(Terminal.LITERAL);
        this.value = bool.getIntVal();
        this.bool = bool;
    }

    @Override
    public String toString() {
        if (bool != null) {
            return "(LITERAL, " + bool.toString() + ")";
        } else {
            return "(LITERAL, IntVal " + value + ")";
        }
    }

    public boolean getBoolVal() {
        return (value != 0);
    }

    public int getIntVal() {
        return value;
    }

    public boolean isInteger() {
        return (bool == null);
    }

    public boolean isBoolean() {
        return (bool != null);
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            if (super.equals((AbstractToken) o)) {
                if (this.value == ((Literal) o).value) {
                    return true;
                }
            }
        }
        return false;
    }

}
