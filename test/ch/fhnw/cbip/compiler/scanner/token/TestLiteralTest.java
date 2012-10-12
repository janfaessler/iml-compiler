package ch.fhnw.cbip.compiler.scanner.token;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.scanner.enums.BoolVal;

public class TestLiteralTest {

  private final int testVal = 123;
  private Literal integerLiteral;
  private Literal boolTrue;
  private Literal boolFalse;

  @Before
  public void setUp() throws Exception {
    integerLiteral = new Literal(testVal, 1);
    boolTrue = new Literal(BoolVal.TRUE, 1);
    boolFalse = new Literal(BoolVal.FALSE, 1);
  }

  @Test
  public void testToString() {
    assertEquals("(LITERAL, IntVal " + testVal + ")", integerLiteral.toString());
    assertEquals("(LITERAL, BoolVal true)", boolTrue.toString());
    assertEquals("(LITERAL, BoolVal false)", boolFalse.toString());
  }

  @Test
  public void testLiteralIntInt() {
    assertTrue(integerLiteral.isInteger());
    assertFalse(integerLiteral.isBoolean());

    assertEquals(testVal, integerLiteral.getIntVal());
    assertTrue(integerLiteral.getBoolVal());
  }

  @Test
  public void testLiteralBoolValInt() {

    assertTrue(boolTrue.getBoolVal());
    assertFalse(boolFalse.getBoolVal());

    assertTrue(boolTrue.isBoolean());
    assertFalse(boolTrue.isInteger());

    assertTrue(boolFalse.isBoolean());
    assertFalse(boolFalse.isInteger());
  }

}
