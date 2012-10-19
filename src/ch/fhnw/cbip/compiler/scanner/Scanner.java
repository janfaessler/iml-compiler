package ch.fhnw.cbip.compiler.scanner;

public class Scanner implements IScannerContext {
  private ITokenList tokenList;
  private IScannerState currentState;
  
  public void addToken(IToken token){
    tokenList.add(token);
  }
  
  public void setState(IScannerState state){
    currentState = state;
  }
  
  public IScannerState getState(){
    return currentState;
  }
  
  public Scanner(String input){
    
  }
  
  
}
