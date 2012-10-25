package ch.fhnw.cbip.compiler.scanner.token;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
public class OperatorTest {

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void testToString() {
	  Operator.AddOpr addOprPlus = new Operator.AddOpr(OperatorAttribute.PLUS);
      addOprPlus.setLine(1);
      assertEquals(addOprPlus.toString(), "(ADDOPR, PLUS)");

      Operator.AddOpr addOprMinus = new Operator.AddOpr(OperatorAttribute.MINUS);
      addOprMinus.setLine(1);
      assertEquals(addOprMinus.toString(), "(ADDOPR, MINUS)");

  }

  @Test
  public void testOperator() {
	  Operator.BoolOpr boolOprCand = new Operator.BoolOpr(OperatorAttribute.CAND);
      boolOprCand.setLine(1);
      assertEquals(boolOprCand.getAttribute(), OperatorAttribute.CAND);
  }
}
