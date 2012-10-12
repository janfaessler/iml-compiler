package ch.fhnw.cbip.compiler.scanner;

import ch.fhnw.cbip.compiler.scanner.IToken;

public interface ITokenList<T extends IToken> {
  /**
   *  Add a token to the list
   * @param token
   */
  public void add(T token);
  
  /**
   * Resets the Token list?!
   */
  public void reset();
  
  /**
   * Returns the next Token and then moves the cursor ahead by one token.
   * @return next Token
   */
  public T nextToken();
}
