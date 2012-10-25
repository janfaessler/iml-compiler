package ch.fhnw.cbip.compiler.scanner;

public interface IToken {
  /**
   * @return source code line number of the token
   */
  public int getLine();
  
  /**
   * @param the line number
   */
  public void setLine(int number);
  
  /**
   * @return A clone of the token
   */
  public IToken copy();
}
