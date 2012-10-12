package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Keyword extends AbstractToken {
  private final Terminal terminal;

  public Keyword(int line, Terminal terminal) {
    super(terminal, line);
    this.terminal = terminal;
  }
  
  public Terminal getTerminal(){
    return terminal;
  }
  
  @Override
  public String toString() {
    return terminal.toString();
  }
  
  public static class Programm extends Keyword {
    public Programm(int line){
      super(line, Terminal.PROGRAM);
    }
  }
  
  public static class Becomes extends Keyword {
    public Becomes(int line){
      super(line, Terminal.BECOMES);
    }
  }
  
  public static class Call extends Keyword {
    public Call(int line){
      super(line, Terminal.CALL);
    }
  }
  
  public static class If extends Keyword {
    public If(int line){
      super(line, Terminal.IF);
    }
  }
  
  public static class Else extends Keyword {
    public Else(int line){
      super(line, Terminal.ELSE);
    }
  }
  
  public static class Fun extends Keyword {
    public Fun(int line){
      super(line, Terminal.FUN);
    }
  }
  
  public static class Proc extends Keyword {
    public Proc(int line){
      super(line, Terminal.PROC);
    }
  }
  
  public static class Global extends Keyword {
    public Global(int line){
      super(line, Terminal.GLOBAL);
    }
  }
  
  public static class Local extends Keyword {
    public Local(int line){
      super(line, Terminal.LOCAL);
    }
  }
  
  public static class Not extends Keyword {
    public Not(int line){
      super(line, Terminal.NOT);
    }
  }
  
  public static class Init extends Keyword {
    public Init(int line){
      super(line, Terminal.INIT);
    }
  }
  
  public static class Returns extends Keyword {
    public Returns(int line){
      super(line, Terminal.RETURNS);
    }
  }
  
  public static class Skip extends Keyword {
    public Skip(int line){
      super(line, Terminal.SKIP);
    }
  }
  
  public static class While extends Keyword {
    public While(int line){
      super(line, Terminal.WHILE);
    }
  }
  
  public static class Do extends Keyword {
    public Do(int line){
      super(line, Terminal.DO);
    }
  }
  
  public static class EndWhile extends Keyword {
    public EndWhile(int line){
      super(line, Terminal.ENDWHILE);
    }
  }
  
  public static class Sentinel extends Keyword {
    public Sentinel(int line){
      super(line, Terminal.SENTINEL);
    }
  }
  

}
