package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Symbol extends AbstractToken {
  private final Terminal terminal;
  
  public Symbol(Terminal terminal, int line){
    super(terminal, line);
    this.terminal = terminal;
  }

  public Terminal getTerminal() {
    return terminal;
  }
  
  @Override
  public String toString() {
    return terminal.toString();
  }
  
  public static class Colon extends Symbol {
    public Colon(int line){
      super(Terminal.COLON, line);
    }
  }
  
  public static class Comma extends Symbol {
    public Comma(int line){
      super(Terminal.COMMA, line);
    }
  }
  
  public static class Semicolon extends Symbol {
    public Semicolon(int line){
      super(Terminal.SEMICOLON, line);
    }
  }
  
  public static class ExclaMark extends Symbol {
    public ExclaMark(int line){
      super(Terminal.EXCLAMARK, line);
    }
  }
  
  public static class QuestMark extends Symbol {
    public QuestMark(int line){
      super(Terminal.QUESTMARK, line);
    }
  }
  
  public static class Becomes extends Symbol {
    public Becomes(int line){
      super(Terminal.BECOMES, line);
    }
  }
  
  public static class LBrace extends Symbol {
    public LBrace(int line){
      super(Terminal.LBRACE, line);
    }
  }
  
  public static class RBrace extends Symbol {
    public RBrace(int line){
      super(Terminal.RBRACE, line);
    }
  }
  
  public static class LParen extends Symbol {
    public LParen(int line){
      super(Terminal.LPAREN, line);
    }
  }
  
  public static class RParen extends Symbol {
    public RParen(int line){
      super(Terminal.RPAREN, line);
    }
  }
  
  
}
