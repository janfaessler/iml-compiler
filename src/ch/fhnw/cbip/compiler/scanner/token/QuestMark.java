package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.AbstractToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public class QuestMark extends AbstractToken {
  public QuestMark(int line){
    super(Terminal.QUESTMARK, line);
  }
}