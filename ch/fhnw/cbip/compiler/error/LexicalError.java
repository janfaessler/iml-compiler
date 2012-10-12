package ch.fhnw.cbip.compiler.error;

public class LexicalError extends Exception {
  private static final long serialVersionUID = -487617110166683052L;
  private final int lineNumber;
  private final String message;
  
  public LexicalError(String message, int lineNumber){
    super();
    this.lineNumber = lineNumber;
    this.message = message;
  }
  
  @Override
  public String getMessage(){
    return "LexicalError: " + message + " at line " + lineNumber + ".";
  }
  
  
}
