package ch.fhnw.cbip.compiler.scanner;

public class Scanner {
  private ITokenList<IToken> tokenList;
  private IScannerState currentState;
  
  public void addToken(IToken token){
    tokenList.add(token);
  }
  
}
