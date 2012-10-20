package ch.fhnw.cbip.compiler.scanner.state;

import java.util.Arrays;

import ch.fhnw.cbip.compiler.error.LexicalError;
import ch.fhnw.cbip.compiler.scanner.IScannerContext;
import ch.fhnw.cbip.compiler.scanner.IScannerState;
import ch.fhnw.cbip.compiler.scanner.enums.BoolVal;
import ch.fhnw.cbip.compiler.scanner.enums.KeywordList;
import ch.fhnw.cbip.compiler.scanner.enums.ModeAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.OperatorAttribute;
import ch.fhnw.cbip.compiler.scanner.enums.ScannerSymbol;
import ch.fhnw.cbip.compiler.scanner.enums.TypeAttribute;
import ch.fhnw.cbip.compiler.scanner.token.*;

public class LetterState implements IScannerState {

    @Override
    public char[] handleChar(char[] c, IScannerContext context) throws LexicalError {
        assert (c.length > 1);
        int lastChar = c.length - 1;
        int line = context.getLineNumber();

        if (('A' <= c[lastChar] && c[lastChar] <= 'Z') || ('a' <= c[lastChar] && c[lastChar] <= 'z')
                || ('0' <= c[lastChar] && c[lastChar] <= '9')) {
            // keyword or identifier continues
            context.setState(this);
        } else {
            // keyword or identifier ends
            if (ScannerSymbol.contains(c[lastChar]) || (' ' == c[lastChar]) || ('\t' == c[lastChar])) {
                String letters = new String(Arrays.copyOfRange(c, 0, c.length - 1));
                KeywordList k = KeywordList.match(letters);
                if (k == null) {
                    // is an identifier
                    context.addToken(new Ident(letters, line));
                    c = Arrays.copyOfRange(c, lastChar, lastChar + 1);
                    context.setState(new InitialState());
                } else {
                    // is a keyword
                    switch (k) {
                    case BOOL:
                        context.addToken(new Type(TypeAttribute.BOOL, line));
                        break;
                    case CALL:
                        context.addToken(new Keyword.Call(line));
                        break;
                    case CAND:
                        context.addToken(new Operator.BoolOpr(OperatorAttribute.CAND, line));
                        break;
                    case CONST:
                        context.addToken(new Mode.ChangeMode(ModeAttribute.CONST, line));
                        break;
                    case COPY:
                        context.addToken(new Mode.MechMode(ModeAttribute.COPY, line));
                        break;
                    case COR:
                        context.addToken(new Operator.RelOpr(OperatorAttribute.COR, line));
                        break;
                    case DIV:
                        context.addToken(new Operator.MultOpr(OperatorAttribute.DIV, line));
                        break;
                    case ELSE:
                        context.addToken(new Keyword.Else(line));
                        break;
                    case ENDWHILE:
                        context.addToken(new Keyword.EndWhile(line));
                        break;
                    case FALSE:
                        context.addToken(new Literal(BoolVal.FALSE, line));
                        break;
                    case FUN:
                        context.addToken(new Keyword.Fun(line));
                        break;
                    case GLOBAL:
                        context.addToken(new Keyword.Global(line));
                        break;
                    case IF:
                        context.addToken(new Keyword.If(line));
                        break;
                    case IN:
                        context.addToken(new Mode.FlowMode(ModeAttribute.IN, line));
                        break;
                    case INIT:
                        context.addToken(new Keyword.Init(line));
                        break;
                    case INOUT:
                        context.addToken(new Mode.FlowMode(ModeAttribute.INOUT, line));
                        break;
                    case INT32:
                        context.addToken(new Type(TypeAttribute.INT32, line));
                        break;
                    case LOCAL:
                        context.addToken(new Keyword.Global(line));
                        break;
                    case MOD:
                        context.addToken(new Operator.MultOpr(OperatorAttribute.MOD, line));
                        break;
                    case NOT:
                        context.addToken(new Keyword.Not(line));
                        break;
                    case OUT:
                        context.addToken(new Mode.FlowMode(ModeAttribute.OUT, line));
                        break;
                    case PROC:
                        context.addToken(new Keyword.Proc(line));
                        break;
                    case PROGRAM:
                        context.addToken(new Keyword.Program(line));
                        break;
                    case REF:
                        context.addToken(new Mode.MechMode(ModeAttribute.REF, line));
                        break;
                    case RETURNS:
                        context.addToken(new Keyword.Returns(line));
                        break;
                    case SKIP:
                        context.addToken(new Keyword.Skip(line));
                        break;
                    case TRUE:
                        context.addToken(new Literal(BoolVal.TRUE, line));
                        break;
                    case VAR:
                        context.addToken(new Mode.ChangeMode(ModeAttribute.VAR, line));
                        break;
                    case WHILE:
                        context.addToken(new Keyword.While(line));
                        break;
                    }
                    c = Arrays.copyOfRange(c, lastChar, lastChar + 1);
                    context.setState(new InitialState());
                }

            } else {
                // illegal character found
                throw new LexicalError("Syntax error, illegal character '" + c[lastChar] + "' found", line);
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
