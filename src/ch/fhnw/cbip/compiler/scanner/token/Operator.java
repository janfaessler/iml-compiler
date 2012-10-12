package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Operator extends AbstractToken {
  private final Terminal terminal;
  private final OperatorAttribute attribute;

  public Operator(int line, Terminal terminal, OperatorAttribute attribute) {
    super(terminal, line);
    this.terminal = terminal;
    this.attribute = attribute;
  }

  public OperatorAttribute getAttribute() {
    return attribute;
  }

  @Override
  public String toString() {
    return "(" + terminal.toString() + ", " + attribute.toString() + ")";
  }

  public static class AddOpr extends Operator {
    public AddOpr(int line, OperatorAttribute attribute) throws LexicalError {
      super(line, Terminal.ADDOPR, attribute);
      if ((attribute != OperatorAttribute.PLUS)
          && (attribute != OperatorAttribute.MINUS))
        throw new LexicalError("Invalid AddOpr attribute", line);
    }
  }

  public static class BoolOpr extends Operator {
    public BoolOpr(int line, OperatorAttribute attribute) throws LexicalError {
      super(line, Terminal.BOOLOPR, attribute);
      if ((attribute != OperatorAttribute.CAND)
          && (attribute != OperatorAttribute.COR))
        throw new LexicalError("Invalid BoolOpr attribute", line);
    }
  }
  
  public static class MultOpr extends Operator {
    public MultOpr(int line, OperatorAttribute attribute) throws LexicalError {
      super(line, Terminal.MULTOPR, attribute);
      if ((attribute != OperatorAttribute.MOD)
          && (attribute != OperatorAttribute.TIMES))
        throw new LexicalError("Invalid MultOpr attribute", line);
    }
  }
  
  public static class RelOpr extends Operator {
    public RelOpr(int line, OperatorAttribute attribute) throws LexicalError {
      super(line, Terminal.RELOPR, attribute);
      if ((attribute != OperatorAttribute.NE)
          && (attribute != OperatorAttribute.EQ)
          && (attribute != OperatorAttribute.LT)
          && (attribute != OperatorAttribute.GT)
          && (attribute != OperatorAttribute.LE)
          && (attribute != OperatorAttribute.GE))
        throw new LexicalError("Invalid RelOpr attribute", line);
    }
  }

}
