package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Operator extends AbstractToken {
    private final OperatorAttribute attribute;

    public Operator(Terminal terminal, OperatorAttribute attribute, int line) {
        super(terminal, line);
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
        public AddOpr(OperatorAttribute attribute, int line) throws LexicalError {
            super(Terminal.ADDOPR, attribute, line);
            if ((attribute != OperatorAttribute.PLUS) && (attribute != OperatorAttribute.MINUS))
                throw new LexicalError("Invalid AddOpr attribute", line);
        }
    }

    public static class BoolOpr extends Operator {
        public BoolOpr(OperatorAttribute attribute, int line) throws LexicalError {
            super(Terminal.BOOLOPR, attribute, line);
            if ((attribute != OperatorAttribute.CAND) && (attribute != OperatorAttribute.COR))
                throw new LexicalError("Invalid BoolOpr attribute", line);
        }
    }

    public static class MultOpr extends Operator {
        public MultOpr(OperatorAttribute attribute, int line) throws LexicalError {
            super(Terminal.MULTOPR, attribute, line);
            if ((attribute != OperatorAttribute.MOD) && (attribute != OperatorAttribute.TIMES)
                    && (attribute != OperatorAttribute.DIV))
                throw new LexicalError("Invalid MultOpr attribute", line);
        }
    }

    public static class RelOpr extends Operator {
        public RelOpr(OperatorAttribute attribute, int line) throws LexicalError {
            super(Terminal.RELOPR, attribute, line);
            if ((attribute != OperatorAttribute.NE) && (attribute != OperatorAttribute.EQ)
                    && (attribute != OperatorAttribute.LT) && (attribute != OperatorAttribute.GT)
                    && (attribute != OperatorAttribute.LE) && (attribute != OperatorAttribute.GE))
                throw new LexicalError("Invalid RelOpr attribute", line);
        }
    }

}
