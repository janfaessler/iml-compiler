package ch.fhnw.cbip.compiler.scanner.token;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
public class TestOperatorTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testToString() {
    try {
      Operator.AddOpr addOprPlus = new Operator.AddOpr(1,
          OperatorAttribute.PLUS);
      assertEquals(addOprPlus.toString(), "(ADDOPR, PLUS)");

      Operator.AddOpr addOprMinus = new Operator.AddOpr(1,
          OperatorAttribute.MINUS);
      assertEquals(addOprMinus.toString(), "(ADDOPR, MINUS)");

    } catch (LexicalError e) {
      fail("Unexpected LexicalError");
    }

  }

  @Test
  public void testOperator() {
    try {
      Operator.BoolOpr boolOprCand = new Operator.BoolOpr(1,
          OperatorAttribute.CAND);
      assertEquals(boolOprCand.getAttribute(), OperatorAttribute.CAND);

    } catch (LexicalError e) {
      fail("Unexpected LexicalError");
    }
  }

  @Test(expected = LexicalError.class)
  public void testInvalidOperatorAttribute() throws LexicalError {
    @SuppressWarnings("unused")
    Operator.RelOpr invrelOpr = new Operator.RelOpr(1, OperatorAttribute.COR);
  }
}
