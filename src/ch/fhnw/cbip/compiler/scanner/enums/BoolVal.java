package ch.fhnw.cbip.compiler.scanner.enums;

/**
 * Enumeration class for boolean values.
 * 
 * @author Michael Kuenzli
 *
 */
public enum BoolVal {
  TRUE(1), FALSE(0);

  /**
   * Creates a boolean
   * 
   * @param value if value is 1 the boolean is true, if value is 0 the boolean is false
   */
  BoolVal(int value) {
    this.value = value;
  }

  /**
   * the value of the boolean, can only be 0 or 1
   */
  private int value;

  /**
   * returns the if the boolean is true or false as integer
   * 
   * @return an int, can only be 0 or 1
   */
  public int getIntVal() {
    return value;
  }

  @Override
  public String toString() {
    if (value == 0) {
      return "BoolVal false";
    } else {
      return "BoolVal true";
    }
  }

}
