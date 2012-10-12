package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Keyword extends AbstractToken {

  public Keyword(Terminal terminal, int line) {
    super(terminal, line);
  }
  
  @Override
  public String toString() {
    return getTerminal().toString();
  }
  
  public static class Programm extends Keyword {
    public Programm(int line){
      super(Terminal.PROGRAM, line);
    }
  }
  
  public static class Call extends Keyword {
    public Call(int line){
      super(Terminal.CALL, line);
    }
  }
  
  public static class If extends Keyword {
    public If(int line){
      super(Terminal.IF, line);
    }
  }
  
  public static class Else extends Keyword {
    public Else(int line){
      super(Terminal.ELSE, line);
    }
  }
  
  public static class Fun extends Keyword {
    public Fun(int line){
      super(Terminal.FUN, line);
    }
  }
  
  public static class Proc extends Keyword {
    public Proc(int line){
      super(Terminal.PROC, line);
    }
  }
  
  public static class Global extends Keyword {
    public Global(int line){
      super(Terminal.GLOBAL, line);
    }
  }
  
  public static class Local extends Keyword {
    public Local(int line){
      super(Terminal.LOCAL, line);
    }
  }
  
  public static class Not extends Keyword {
    public Not(int line){
      super(Terminal.NOT, line);
    }
  }
  
  public static class Init extends Keyword {
    public Init(int line){
      super(Terminal.INIT, line);
    }
  }
  
  public static class Returns extends Keyword {
    public Returns(int line){
      super(Terminal.RETURNS, line);
    }
  }
  
  public static class Skip extends Keyword {
    public Skip(int line){
      super(Terminal.SKIP, line);
    }
  }
  
  public static class While extends Keyword {
    public While(int line){
      super(Terminal.WHILE, line);
    }
  }
  
  public static class Do extends Keyword {
    public Do(int line){
      super(Terminal.DO, line);
    }
  }
  
  public static class EndWhile extends Keyword {
    public EndWhile(int line){
      super(Terminal.ENDWHILE, line);
    }
  }
  
  public static class Sentinel extends Keyword {
    public Sentinel(int line){
      super(Terminal.SENTINEL, line);
    }
  }
  

}
