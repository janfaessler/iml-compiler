package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Operator extends AbstractToken {

	private static final long serialVersionUID = -6086653757673370032L;
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

		private static final long serialVersionUID = 4599140378783118938L;

		public AddOpr(OperatorAttribute attribute) {
            super(Terminal.ADDOPR, attribute);
            assert (attribute == OperatorAttribute.PLUS || attribute == OperatorAttribute.MINUS);
        }
    }

    public static class BoolOpr extends Operator {

		private static final long serialVersionUID = 9000095108807672446L;

		public BoolOpr(OperatorAttribute attribute) {
            super(Terminal.BOOLOPR, attribute);
            assert (attribute == OperatorAttribute.CAND || attribute == OperatorAttribute.COR);
        }
    }

    public static class MultOpr extends Operator {

		private static final long serialVersionUID = 7908092160119701296L;

		public MultOpr(OperatorAttribute attribute) {
            super(Terminal.MULTOPR, attribute);
            assert (attribute == OperatorAttribute.TIMES || attribute == OperatorAttribute.MOD || attribute == OperatorAttribute.DIV);
        }
    }

    public static class RelOpr extends Operator {

		private static final long serialVersionUID = -5780932918206995185L;

		public RelOpr(OperatorAttribute attribute) {
            super(Terminal.RELOPR, attribute);
            assert (attribute == OperatorAttribute.EQ || attribute == OperatorAttribute.NE || attribute == OperatorAttribute.LT
            		|| attribute == OperatorAttribute.GT || attribute == OperatorAttribute.LE || attribute == OperatorAttribute.GE);
        }
    }

}
