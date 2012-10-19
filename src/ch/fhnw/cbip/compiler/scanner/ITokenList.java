package ch.fhnw.cbip.compiler.scanner;

import ch.fhnw.cbip.compiler.scanner.IToken;

public interface ITokenList {
  /**
   * Add a token to the list
   * @param token
   */
  public void add(IToken token);
  
  /**
   * Resets the list pointer
   */
  public void reset();
  
  /**
   * Returns the next Token and then moves the cursor ahead by one token.
   * @return next Token
   */
  public IToken nextToken();
}
