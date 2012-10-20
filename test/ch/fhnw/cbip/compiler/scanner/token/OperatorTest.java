package ch.fhnw.cbip.compiler.scanner.token;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
public class OperatorTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testToString() {
    try {
      Operator.AddOpr addOprPlus = new Operator.AddOpr(OperatorAttribute.PLUS,
          1);
      assertEquals(addOprPlus.toString(), "(ADDOPR, PLUS)");

      Operator.AddOpr addOprMinus = new Operator.AddOpr(OperatorAttribute.MINUS,
          1);
      assertEquals(addOprMinus.toString(), "(ADDOPR, MINUS)");

    } catch (LexicalError e) {
      fail("Unexpected LexicalError");
    }

  }

  @Test
  public void testOperator() {
    try {
      Operator.BoolOpr boolOprCand = new Operator.BoolOpr(OperatorAttribute.CAND,
          1);
      assertEquals(boolOprCand.getAttribute(), OperatorAttribute.CAND);

    } catch (LexicalError e) {
      fail("Unexpected LexicalError");
    }
  }

  @Test(expected = LexicalError.class)
  public void testInvalidOperatorAttribute() throws LexicalError {
    @SuppressWarnings("unused")
    Operator.RelOpr invrelOpr = new Operator.RelOpr(OperatorAttribute.COR, 1);
  }
}
