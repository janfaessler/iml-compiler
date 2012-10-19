package ch.fhnw.cbip.compiler.scanner.enums;

/**
 * These are the symbols, where the Scanner does not automatically throw a
 * LexicalError
 */
public enum ScannerSymbol {
  
  LPAREN('('), RPAREN(')'), LBRACE('{'), RBRACE('}'),
  COMMA(','), COLON(':'), SEMICOLON(';'), 
  QUESTMARK('?'),  EXCLAMARK('!'), EQUALS('='), 
  ASTERISK('*'), PLUS('+'), MINUS('-'),
  SLASH('/'), LT('<'), GT('>');
  
  ScannerSymbol(int charValue){
    this.charValue = charValue;
  }
  private int charValue;
  
  public static boolean contains(int value){
    for(ScannerSymbol s : values()) {
      if(value == s.charValue){
        return true;
      }
    }
    return false;
  }
}
