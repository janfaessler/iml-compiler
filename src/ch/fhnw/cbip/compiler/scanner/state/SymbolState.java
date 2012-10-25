package ch.fhnw.cbip.compiler.scanner.state;

import java.util.Arrays;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IScannerContext;
import ch.fhnw.cbip.compiler.scanner.IScannerState;
import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.ScannerSymbol;
import ch.fhnw.cbip.compiler.scanner.enums.SymbolEnum;
import ch.fhnw.cbip.compiler.scanner.token.Operator;
import ch.fhnw.cbip.compiler.scanner.token.Symbol;

public class SymbolState implements IScannerState {

    @Override
    public char[] handleChar(char[] c, IScannerContext context) throws LexicalError {
        assert (c.length > 1);
        int lastChar = c.length - 1;
        if (c[lastChar] == '=') { // = is only possible follow-up
            // symbol continues
            context.setState(this, false);
        } else {
            if (('A' <= c[lastChar] && c[lastChar] <= 'Z') 
                    || ('a' <= c[lastChar] && c[lastChar] <= 'z')
                    || ('0' <= c[lastChar] && c[lastChar] <= '9') 
                    || (' ' == c[lastChar]) 
                    || ('\t' == c[lastChar])
                    || (ScannerSymbol.contains(c[lastChar]))) {
                // keyword
                String letters = new String(Arrays.copyOfRange(c, 0, c.length - 1));
                int line = context.getLineNumber();
                SymbolEnum s = SymbolEnum.match(letters);
                if (s != null) {
                    switch (s) {
                    case LPAREN:
                        context.addToken(new Symbol.LParen(line));
                        break;
                    case RPAREN:
                        context.addToken(new Symbol.RParen(line));
                        break;
                    case LBRACE:
                        context.addToken(new Symbol.LBrace(line));
                        break;
                    case RBRACE:
                        context.addToken(new Symbol.RBrace(line));
                        break;
                    case COMMA:
                        context.addToken(new Symbol.Comma(line));
                        break;
                    case SEMICOLON:
                        context.addToken(new Symbol.Semicolon(line));
                        break;
                    case COLON:
                        context.addToken(new Symbol.Colon(line));
                        break;
                    case QUESTMARK:
                        context.addToken(new Symbol.QuestMark(line));
                        break;
                    case EXCLAMARK:
                        context.addToken(new Symbol.ExclaMark(line));
                        break;
                    case BECOMES:
                        context.addToken(new Symbol.Becomes(line));
                        break;
                    case MULTOPR:
                        context.addToken(new Operator.MultOpr(OperatorAttribute.TIMES, line));
                        break;
                    case PLUS:
                        context.addToken(new Operator.AddOpr(OperatorAttribute.PLUS, line));
                        break;
                    case MINUS:
                        context.addToken(new Operator.AddOpr(OperatorAttribute.MINUS, line));
                        break;
                    case EQ:
                        context.addToken(new Operator.RelOpr(OperatorAttribute.EQ, line));
                        break;
                    case NE:
                        context.addToken(new Operator.RelOpr(OperatorAttribute.NE, line));
                        break;
                    case LT:
                        context.addToken(new Operator.RelOpr(OperatorAttribute.LT, line));
                        break;
                    case GT:
                        context.addToken(new Operator.RelOpr(OperatorAttribute.GT, line));
                        break;
                    case LE:
                        context.addToken(new Operator.RelOpr(OperatorAttribute.LE, line));
                        break;
                    case GE:
                        context.addToken(new Operator.RelOpr(OperatorAttribute.GE, line));
                        break;
                    default:
                        throw new LexicalError("No matching symbol", line);
                    }
                    c = Arrays.copyOfRange(c, lastChar, lastChar + 1);
                    context.setState(new InitialState(), true);
                } else {
                    throw new LexicalError("Illegal symbol '" + letters + "'", context.getLineNumber());
                }
            } else {
                throw new LexicalError("Illegal character " + c[lastChar] + " after symbol", context.getLineNumber());
            }
        }
        return c;
    }
    
    @Override
    public boolean equals(Object o){
      if(o.getClass() == this.getClass()){
        return true;
      } else {
        return false;
      }
    }
}
