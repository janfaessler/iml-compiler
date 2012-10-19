package ch.fhnw.cbip.compiler.scanner;

import java.util.Iterator;
import java.util.LinkedList;

public class TokenList implements ITokenList {
  private LinkedList<IToken> tokenList;
  private Iterator<IToken> listIterator;
  
  public TokenList(){
    tokenList = new LinkedList<IToken>();
  }
  
  
  @Override
  public void add(IToken token) {
    if(token != null){
      tokenList.add(token);       
    }   
  }

  @Override
  public void reset() {
    listIterator = null;
  }

  @Override
  public IToken nextToken() {
    if(listIterator == null){
      listIterator = tokenList.iterator();
    }
    return listIterator.next();
  }

}
