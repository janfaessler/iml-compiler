package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Operator extends AbstractToken {
    private final OperatorAttribute attribute;

    public Operator(Terminal terminal, OperatorAttribute attribute) {
        super(terminal);
        this.attribute = attribute;
    }

    public OperatorAttribute getAttribute() {
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
                if (attribute == ((Operator) o).attribute) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class AddOpr extends Operator {
        public AddOpr(OperatorAttribute attribute) {
            super(Terminal.ADDOPR, attribute);
            assert (attribute == OperatorAttribute.PLUS || attribute == OperatorAttribute.MINUS);
        }
    }

    public static class BoolOpr extends Operator {
        public BoolOpr(OperatorAttribute attribute) {
            super(Terminal.BOOLOPR, attribute);
            assert (attribute == OperatorAttribute.CAND || attribute == OperatorAttribute.COR);
        }
    }

    public static class MultOpr extends Operator {
        public MultOpr(OperatorAttribute attribute) {
            super(Terminal.MULTOPR, attribute);
            assert (attribute == OperatorAttribute.TIMES || attribute == OperatorAttribute.MOD || attribute == OperatorAttribute.DIV);
        }
    }

    public static class RelOpr extends Operator {
        public RelOpr(OperatorAttribute attribute) {
            super(Terminal.RELOPR, attribute);
            assert (attribute == OperatorAttribute.EQ || attribute == OperatorAttribute.NE || attribute == OperatorAttribute.LT
            		|| attribute == OperatorAttribute.GT || attribute == OperatorAttribute.LE || attribute == OperatorAttribute.GE);
        }
    }

}
