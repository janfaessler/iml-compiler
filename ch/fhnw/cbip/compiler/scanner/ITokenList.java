package fhnw.cbip.compiler.scanner;

import ch.fhnw.cbip.compiler.scanner.IToken;

public interface ITokenList<T extends IToken> {
    void add(T token);
    
    void reset();
    
    
  
 
 
}
