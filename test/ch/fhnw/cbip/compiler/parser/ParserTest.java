package ch.fhnw.cbip.compiler.parser;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.error.GrammarError;
import ch.fhnw.cbip.compiler.parser.ConcSyn.Program;
import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.ITokenList;
import ch.fhnw.cbip.compiler.scanner.TokenList;
import ch.fhnw.cbip.compiler.scanner.enums.*;
import ch.fhnw.cbip.compiler.scanner.token.*;


public class ParserTest {
    private ITokenList devideTokenList;

    @Before
    public void setUp() throws Exception {
        devideTokenList = new TokenList();
        
        // program intDiv
        addToken(new Keyword.Program(), 1);
        addToken(new Ident("intDiv"), 1);

        // global
        addToken(new Keyword.Global(), 2);

        // proc divide(in copy m:int32, in copy n:int32, out ref q:int32, out ref r:int32)
        addToken(new Keyword.Proc(), 3);
        addToken(new Ident("divide"), 3);

        addToken(new Symbol.LParen(), 3);

        addToken(new Mode.FlowMode(ModeAttribute.IN), 3);
        addToken(new Mode.MechMode(ModeAttribute.COPY), 3);
        addToken(new Ident("m"), 3);
        addToken(new Symbol.Colon(), 3);
        addToken(new Type(TypeAttribute.INT32), 3);
        addToken(new Symbol.Comma(), 3);

        addToken(new Mode.FlowMode(ModeAttribute.IN), 3);
        addToken(new Mode.MechMode(ModeAttribute.COPY), 3);
        addToken(new Ident("n"), 3);
        addToken(new Symbol.Colon(), 3);
        addToken(new Type(TypeAttribute.INT32), 3);
        addToken(new Symbol.Comma(), 3);

        addToken(new Mode.FlowMode(ModeAttribute.OUT), 3);
        addToken(new Mode.MechMode(ModeAttribute.REF), 3);
        addToken(new Ident("q"), 3);
        addToken(new Symbol.Colon(), 3);
        addToken(new Type(TypeAttribute.INT32), 3);
        addToken(new Symbol.Comma(), 3);

        addToken(new Mode.FlowMode(ModeAttribute.OUT), 3);
        addToken(new Mode.MechMode(ModeAttribute.REF), 3);
        addToken(new Ident("r"), 3);
        addToken(new Symbol.Colon(), 3);
        addToken(new Type(TypeAttribute.INT32), 3);

        addToken(new Symbol.RParen(), 3);

        // {
        addToken(new Symbol.LBrace(), 4);

        // q init := 0;
        addToken(new Ident("q"), 5);
        addToken(new Keyword.Init(), 5);
        addToken(new Symbol.Becomes(), 5);
        addToken(new Literal(0), 5);
        addToken(new Symbol.Semicolon(), 5);

        // r init := m;
        addToken(new Ident("r"), 6);
        addToken(new Keyword.Init(), 6);
        addToken(new Symbol.Becomes(), 6);
        addToken(new Ident("m"), 6);
        addToken(new Symbol.Semicolon(), 6);

        // while (r >= n) {
        addToken(new Keyword.While(), 7);
        addToken(new Symbol.LParen(), 7);
        addToken(new Ident("r"), 7);
        addToken(new Operator.RelOpr(OperatorAttribute.GE), 7);
        addToken(new Ident("n"), 7);
        addToken(new Symbol.RParen(), 7);
        addToken(new Symbol.LBrace(), 7);

        // q := q + 1;
        addToken(new Ident("q"), 8);
        addToken(new Symbol.Becomes(), 8);
        addToken(new Ident("q"), 8);
        addToken(new Operator.AddOpr(OperatorAttribute.PLUS), 8);
        addToken(new Literal(1), 8);
        addToken(new Symbol.Semicolon(), 8);

        // r := r - n
        addToken(new Ident("r"), 9);
        addToken(new Symbol.Becomes(), 9);
        addToken(new Ident("r"), 9);
        addToken(new Operator.AddOpr(OperatorAttribute.MINUS), 9);
        addToken(new Ident("n"), 9);

        // }
        addToken(new Symbol.RBrace(), 10);

        // };
        addToken(new Symbol.RBrace(), 11);
        addToken(new Symbol.Semicolon(), 11);

        // var m:int32;
        addToken(new Mode.ChangeMode(ModeAttribute.VAR), 13);
        addToken(new Ident("m"), 13);
        addToken(new Symbol.Colon(), 13);
        addToken(new Type(TypeAttribute.INT32), 13);
        addToken(new Symbol.Semicolon(), 13);

        // var n:int32;
        addToken(new Mode.ChangeMode(ModeAttribute.VAR), 14);
        addToken(new Ident("n"), 14);
        addToken(new Symbol.Colon(), 14);
        addToken(new Type(TypeAttribute.INT32), 14);
        addToken(new Symbol.Semicolon(), 14);

        // var q:int32;
        addToken(new Mode.ChangeMode(ModeAttribute.VAR), 15);
        addToken(new Ident("q"), 15);
        addToken(new Symbol.Colon(), 15);
        addToken(new Type(TypeAttribute.INT32), 15);
        addToken(new Symbol.Semicolon(), 15);

        // var r:int32
        addToken(new Mode.ChangeMode(ModeAttribute.VAR), 16);
        addToken(new Ident("r"), 16);
        addToken(new Symbol.Colon(), 16);
        addToken(new Type(TypeAttribute.INT32), 16);

        // {
        addToken(new Symbol.LBrace(), 17);

        // ? m init;
        addToken(new Symbol.QuestMark(), 18);
        addToken(new Ident("m"), 18);
        addToken(new Keyword.Init(), 18);
        addToken(new Symbol.Semicolon(), 18);

        // ? n init;
        addToken(new Symbol.QuestMark(), 19);
        addToken(new Ident("n"), 19);
        addToken(new Keyword.Init(), 19);
        addToken(new Symbol.Semicolon(), 19);

        // call divide(m, n, q init, r init);
        addToken(new Keyword.Call(), 20);
        addToken(new Ident("divide"), 20);
        addToken(new Symbol.LParen(), 20);

        addToken(new Ident("m"), 20);
        addToken(new Symbol.Comma(), 20);

        addToken(new Ident("n"), 20);
        addToken(new Symbol.Comma(), 20);

        addToken(new Ident("q"), 20);
        addToken(new Keyword.Init(), 20);
        addToken(new Symbol.Comma(), 20);

        addToken(new Ident("r"), 20);
        addToken(new Keyword.Init(), 20);

        addToken(new Symbol.RParen(), 20);
        addToken(new Symbol.Semicolon(), 20);

        // ! q;
        addToken(new Symbol.ExclaMark(), 21);
        addToken(new Ident("q"), 21);
        addToken(new Symbol.Semicolon(), 21);

        // ! r
        addToken(new Symbol.ExclaMark(), 22);
        addToken(new Ident("r"), 22);

        // }
        addToken(new Symbol.RBrace(), 23);
        addToken(new Keyword.Sentinel(), 23);
    }


    @Test
    public void testScanDivide() throws GrammarError {

        Parser parser = new Parser(devideTokenList);
        Program prog = parser.parse();
        //System.out.println(prog.toString());
    }
    
    private void addToken(IToken token, int line) {
        token.setLine(line);
        devideTokenList.add(token);
    }

}
