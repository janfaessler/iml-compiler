package ch.fhnw.cbip.compiler.scanner;

public interface IToken {
  /**
   * @return source code line number of the token
   */
  public int getLine();
}
