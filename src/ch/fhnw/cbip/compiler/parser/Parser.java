package ch.fhnw.cbip.compiler.parser;

import ch.fhnw.cbip.compiler.error.GrammarError;
import ch.fhnw.cbip.compiler.parser.ConcSyn.*;
import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.ITokenList;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;
import ch.fhnw.cbip.compiler.scanner.token.*;

/**
 * 
 * @author Jan Faessler <jan.faessler@students.fhnw.ch>
 */
public class Parser {
	private final ITokenList tokenList;
	private IToken token;
	private Terminal terminal;
	
	public Parser(final ITokenList tokenList) {
		this.tokenList = tokenList;
		this.tokenList.reset();
		
		// precondition: token list contains at least the SENTINEL 
		token= tokenList.nextToken();
		// establish class invariant
		terminal= token.getTerminal();

	}
	
	private IToken consume(Terminal expectedTerminal) throws GrammarError {
		if (terminal == expectedTerminal) {
			final IToken consumedToken = token;
			if (terminal != Terminal.SENTINEL) {
				token = tokenList.nextToken();
				// maintain class invariant
				terminal = token.getTerminal();
			}
			return consumedToken;
		} else {
			throw new GrammarError("terminal expected: " + expectedTerminal + ", terminal found: " + terminal, token.getLine());
		}
	}
	
	public ConcSyn.Program parse() throws GrammarError {
		// parsing the start symbol ...
		ConcSyn.Program program = program();
		// ... and then consuming the SENTINEL
		consume(Terminal.SENTINEL);
		return program;
	}
	
	private ConcSyn.Program program() throws GrammarError {
		System.out.println("program ::= PROGRAM IDENT auxGlobCpsDecl blockCmd");
		consume(Terminal.PROGRAM);
		Ident ident = (Ident) consume(Terminal.IDENT);
		ConcSyn.AuxGlobCpsDecl auxGlobCpsDecl = auxGlobCpsDecl();
		ConcSyn.BlockCmd blockCmd = blockCmd();
		return new ConcSyn.Program(ident, auxGlobCpsDecl, blockCmd);
	}

	private ConcSyn.Decl decl() throws GrammarError {
		switch(terminal) {
			case FUN:
				System.out.println("decl ::= funDecl");
				return funDecl();
			case PROC:
				System.out.println("decl ::= procDecl");
				return procDecl();
			case CHANGEMODE:
			case IDENT:
				System.out.println("decl ::= storeDecl");
				return storeDecl();
			default:
				throw new GrammarError("decl got " + terminal, token.getLine());
		}
	}

	private StoreDecl storeDecl() throws GrammarError {
		System.out.println("storeDecl ::= auxChangeMode IDENT COLON TYPE");
		ConcSyn.AuxChangeMode auxChangeMode = auxChangeMode();
		Ident ident = (Ident) consume(Terminal.IDENT);
		consume(Terminal.COLON);
		Type type = (Type) consume(Terminal.TYPE);
		return new ConcSyn.StoreDecl(auxChangeMode, ident, type);
	}

	private ConcSyn.FunDecl funDecl() throws GrammarError {
		System.out.println("funDecl ::= FUN IDENT paramList RETURNS storeDecl auxGlobImpList auxLocCpsDecl blockCmd");
		consume(Terminal.FUN);
		Ident ident = (Ident) consume(Terminal.IDENT);
		ConcSyn.ParamList paramList = paramList();
		consume(Terminal.RETURNS);
		ConcSyn.StoreDecl storeDecl = storeDecl();
		ConcSyn.AuxGlobImpList auxGlobImpList = auxGlobImpList();
		ConcSyn.AuxLocCpsDecl auxLocCpsDecl = auxLocCpsDecl();
		ConcSyn.BlockCmd blockCmd = blockCmd();
		return new ConcSyn.FunDecl(ident, paramList, storeDecl, auxGlobImpList, auxLocCpsDecl, blockCmd);
	}

	private ConcSyn.ProcDecl procDecl() throws GrammarError {
		System.out.println("procDecl ::= PROC IDENT paramList auxGlobImpList auxLocCpsDecl blockCmd");
		consume(Terminal.PROC);
		Ident ident = (Ident) consume(Terminal.IDENT);
		ConcSyn.ParamList paramList = paramList();
		ConcSyn.AuxGlobImpList auxGlobImpList = auxGlobImpList();
		ConcSyn.AuxLocCpsDecl auxLocCpsDecl = auxLocCpsDecl();
		ConcSyn.BlockCmd blockCmd = blockCmd();
		return new ConcSyn.ProcDecl(ident, paramList, auxGlobImpList, auxLocCpsDecl, blockCmd);
	}

	private ConcSyn.AuxGlobCpsDecl auxGlobCpsDecl() throws GrammarError {
		if (terminal == Terminal.GLOBAL) {
			System.out.println("auxGlobCpsDecl ::= GLOBAL cpsDecl");
			consume(Terminal.GLOBAL);
			return new ConcSyn.AuxGlobCpsDecl(cpsDecl());
		} else {
			System.out.println("auxGlobCpsDecl ::= epsilon");
			return new ConcSyn.AuxGlobCpsDeclEps();
		}
	}

	private ConcSyn.AuxLocCpsDecl auxLocCpsDecl() throws GrammarError {
		if (terminal == Terminal.LOCAL) {
			System.out.println("auxLocCpsDecl ::= LOCAL cpsDecl");
			consume(Terminal.LOCAL);
			return new ConcSyn.AuxLocCpsDecl(cpsDecl());
		} else {
			System.out.println("auxLocCpsDecl ::= epsilon");
			return new ConcSyn.AuxLocCpsDeclEps();
		}
	}

	private ConcSyn.CpsDecl cpsDecl() throws GrammarError {
		System.out.println("cpsDecl := decl repDecl");
		ConcSyn.Decl decl = decl();
		ConcSyn.RepDecl repDecl = repDecl();
		return new ConcSyn.CpsDecl(decl, repDecl);
	}

	private ConcSyn.RepDecl repDecl() throws GrammarError {
		if (terminal == Terminal.SEMICOLON) {
			System.out.println("repDecl ::= SEMICOLON decl repDecl");
			consume(Terminal.SEMICOLON);
			ConcSyn.Decl decl = decl();
			ConcSyn.RepDecl repDecl = repDecl();
			return new ConcSyn.RepDecl(decl, repDecl);
		} else {
			System.out.println("repDecl ::= epsilon");
			return new ConcSyn.RepDeclEps();
		}
	}

	private ConcSyn.ParamList paramList() throws GrammarError {
		System.out.println("paramList ::= LPAREN auxParamList RPAREN");
		consume(Terminal.LPAREN);
		ConcSyn.AuxParamList auxParamList = auxParamList();
		consume(Terminal.RPAREN);
		return new ConcSyn.ParamList(auxParamList);
	}

	private ConcSyn.AuxParamList auxParamList() throws GrammarError {
		if (terminal != Terminal.RPAREN) {
			System.out.println("auxParamList ::= param repParam");
			ConcSyn.Param param = param();
			ConcSyn.RepParam repParam = repParam();
			return new ConcSyn.AuxParamList(param, repParam);
		} else {
			System.out.println("auxParamList ::= epsilon");
			return new ConcSyn.AuxParamListEps();
		}
	}

	private ConcSyn.RepParam repParam() throws GrammarError {
		if (terminal == Terminal.COMMA) {
			System.out.println("repParam ::= COMMA param repParam");
			consume(Terminal.COMMA);
			ConcSyn.Param param = param();
			ConcSyn.RepParam repParam = repParam();
			return new ConcSyn.RepParam(param, repParam);
		} else {
			System.out.println("repParam ::= epsilon");
			return new ConcSyn.RepParamEps();
		}
	}

	private ConcSyn.Param param() throws GrammarError {
		System.out.println("param ::= auxFlowMode auxMechMode storeDecl");
		ConcSyn.AuxFlowMode auxFlowMode = auxFlowMode();
		ConcSyn.AuxMechMode auxMechMode = auxMechMode();
		ConcSyn.StoreDecl storeDecl = storeDecl();
		return new ConcSyn.Param(auxFlowMode, auxMechMode, storeDecl);
	}

	private ConcSyn.AuxGlobImpList auxGlobImpList() throws GrammarError {
		if (terminal == Terminal.GLOBAL) {
			System.out.println("auxGlobImpList ::= GLOBAL globImpList");
			consume(Terminal.GLOBAL);
			return new ConcSyn.AuxGlobImpList(globImpList());
		} else {
			System.out.println("auxGlobImpList ::= epsilon");
			return new ConcSyn.AuxGlobImpListEps();
		}
	}

	private ConcSyn.GlobImpList globImpList() throws GrammarError {
		System.out.println("globImpList ::= globImp repGlobImp");
		ConcSyn.GlobImp globImp = globImp();
		ConcSyn.RepGlobImp repGlobImp = repGlobImp();
		return new ConcSyn.GlobImpList(globImp, repGlobImp);
	}

	private ConcSyn.RepGlobImp repGlobImp() throws GrammarError {
		if (terminal == Terminal.COMMA) {
			System.out.println("repGlobImp ::= COMMA globImp repGlobImp");
			consume(Terminal.COMMA);
			ConcSyn.GlobImp globImp = globImp();
			ConcSyn.RepGlobImp repGlobImp = repGlobImp();
			return new ConcSyn.RepGlobImp(globImp, repGlobImp);
		} else {
			System.out.println("repGlobImp ::= epsilon");
			return new ConcSyn.RepGlobImpEps();
		}
	}

	private ConcSyn.GlobImp globImp() throws GrammarError {
		System.out.println("globImp ::= auxFlowMode auxChangeMode IDENT");
		ConcSyn.AuxFlowMode auxFlowMode = auxFlowMode();
		ConcSyn.AuxChangeMode auxChangeMode = auxChangeMode();
		Ident ident = (Ident) consume(Terminal.IDENT);
		return new ConcSyn.GlobImp(auxFlowMode, auxChangeMode, ident);
	}

	private ConcSyn.Cmd cmd() throws GrammarError {
		ConcSyn.Cmd ret = null;
		switch (terminal) {
			case SKIP:
				System.out.println("cmd ::= SKIP");
				consume(Terminal.SKIP);
				ret = new ConcSyn.CmdSkip();
				break;
			case IF:
				System.out.println("cmd ::= IF LPAREN expr RPAREN blockCmd ELSE blockCmd");
				consume(Terminal.IF);
				consume(Terminal.LPAREN);
				ConcSyn.Expr ifExpr = expr();
				consume(Terminal.RPAREN);
				ConcSyn.BlockCmd ifCmd = blockCmd();
				consume(Terminal.ELSE);
				ConcSyn.BlockCmd elseCmd = blockCmd();
				ret = new ConcSyn.CmdIf(ifExpr, ifCmd, elseCmd);
				break;
			case CALL:
				System.out.println("cmd ::= CALL IDENT exprList auxGlobInitList");
				consume(Terminal.CALL);
				Ident ident = (Ident) consume(Terminal.IDENT);
				ConcSyn.ExprList exprList = exprList();
				ConcSyn.AuxGlobInitList auxGlobInitList = auxGlobInitList();
				ret = new ConcSyn.CmdCall(ident, exprList,auxGlobInitList);
				break;
			case WHILE:
				System.out.println("cmd ::= WHILE LPAREN expr RPAREN blockCmd");
				consume(Terminal.WHILE);
				consume(Terminal.LPAREN);
				ConcSyn.Expr whileExpr = expr();
				consume(Terminal.RPAREN);
				ConcSyn.BlockCmd whileCmd = blockCmd();
				ret =  new ConcSyn.CmdWhile(whileExpr, whileCmd);
				break;
			case QUESTMARK:
				System.out.println("cmd ::= QUESTMARK expr");
				consume(Terminal.QUESTMARK);
				ConcSyn.Expr questExpr = expr();
				ret = new ConcSyn.CmdQuest(questExpr);
				break;
			case EXCLAMARK:
				System.out.println("cmd ::= EXCLMARK expr");
				consume(Terminal.EXCLAMARK);
				ConcSyn.Expr exclExpr = expr();
				ret = new ConcSyn.CmdExcl(exclExpr);
				break;
			default:
				System.out.println("cmd ::= expr auxExprCmd");
				ConcSyn.Expr expr = expr();
				ConcSyn.AuxExprCmd auxExprCmd = auxExprCmd();
				ret = new ConcSyn.CmdExpr(expr, auxExprCmd);
		}
		return ret;
	}

	private ConcSyn.BlockCmd blockCmd() throws GrammarError {
		System.out.println("blockCmd ::= LBRACE cmd repCmd RBRACE");
		consume(Terminal.LBRACE);
		ConcSyn.Cmd cmd = cmd();
		ConcSyn.RepCmd repCmd = repCmd();
		consume(Terminal.RBRACE);
		return new ConcSyn.BlockCmd(cmd, repCmd);
	}

	private ConcSyn.RepCmd repCmd() throws GrammarError {
		if (terminal == Terminal.SEMICOLON) {
			System.out.println("repCmd ::= SEMICOLON cmd repCmd");
			consume(Terminal.SEMICOLON);
			ConcSyn.Cmd cmd = cmd();
			ConcSyn.RepCmd repCmd = repCmd();
			return new ConcSyn.RepCmd(cmd, repCmd);
		} else {
			System.out.println("repCmd ::= epsilon");
			return new ConcSyn.RepCmdEps();
		}
	}

	private ConcSyn.AuxGlobInitList auxGlobInitList() throws GrammarError {
		if (terminal == Terminal.INIT) {
			System.out.println("auxGlobInitList ::= INIT LPAREN globInitList RPAREN");
			consume(Terminal.INIT);
			consume(Terminal.LPAREN);
			ConcSyn.GlobInitList globInitList = globInitList();
			consume(Terminal.RPAREN);
			return new ConcSyn.AuxGlobInitList(globInitList);
		} else {
			System.out.println("auxGlobInitList ::= epsilon");
			return new ConcSyn.AuxGlobInitListEps();
		}
	}

	private ConcSyn.GlobInitList globInitList() throws GrammarError {
		System.out.println("globInitList ::= IDENT repIdent");
		Ident ident = (Ident) consume(Terminal.IDENT);
		ConcSyn.RepIdent repIdent = repIdent();
		return new ConcSyn.GlobInitList(ident, repIdent);
	}

	private ConcSyn.RepIdent repIdent() throws GrammarError {
		if (terminal != Terminal.COMMA) {
			System.out.println("repIdent ::= epsilon");
			return new ConcSyn.RepIdentEps();
		} else {
			System.out.println("repIdent ::= IDENT repIdent");
			Ident ident = (Ident) consume(Terminal.IDENT);
			ConcSyn.RepIdent repIdent = repIdent();
			return new ConcSyn.RepIdent(ident, repIdent);
		}
	}

	private ConcSyn.Expr expr() throws GrammarError {
		System.out.println("expr ::= term1 repTerm1");
		ConcSyn.Term1 term1 = term1();
		ConcSyn.RepTerm1 repTerm1 = repTerm1();
		return new ConcSyn.Expr(term1, repTerm1);
	}

	private ConcSyn.RepTerm1 repTerm1() throws GrammarError {
		if (terminal == Terminal.BOOLOPR) {
			System.out.println("repTerm1 ::= BOOLOPR term1 repTerm1");
			Operator.BoolOpr boolOpr = (Operator.BoolOpr) consume(Terminal.BOOLOPR);
			ConcSyn.Term1 term1 = term1();
			ConcSyn.RepTerm1 repTerm1 = repTerm1();
			return new ConcSyn.RepTerm1(boolOpr, term1, repTerm1);
		} else {
			System.out.println("repTerm1 ::= epsilon");
			return new ConcSyn.RepTerm1Eps();
		}
	}

	private ConcSyn.Term1 term1() throws GrammarError {
		System.out.println("term1 ::= term2 repTerm2");
		ConcSyn.Term2 term2 = term2();
		ConcSyn.RepTerm2 repTerm2 = repTerm2();
		return new ConcSyn.Term1(term2, repTerm2);
	}

	private ConcSyn.RepTerm2 repTerm2() throws GrammarError {
		if (terminal == Terminal.RELOPR) {
			System.out.println("repTerm2 ::= RELOPR term2 repTerm2");
			Operator.RelOpr relOpr = (Operator.RelOpr) consume(Terminal.RELOPR);
			ConcSyn.Term2 term2 = term2();
			ConcSyn.RepTerm2 repTerm2 = repTerm2();
			return new ConcSyn.RepTerm2(relOpr, term2, repTerm2);
		} else {
			System.out.println("repTerm2 ::= epsilon");
			return new ConcSyn.RepTerm2Eps();
		}
	}

	private ConcSyn.Term2 term2() throws GrammarError {
		System.out.println("term2 ::= term3 repTerm3");
		ConcSyn.Term3 term3 = term3();
		ConcSyn.RepTerm3 repTerm3 = repTerm3();
		return new ConcSyn.Term2(term3, repTerm3);
	}

	private ConcSyn.RepTerm3 repTerm3() throws GrammarError {
		if (terminal == Terminal.ADDOPR) {
			System.out.println("repTerm3 ::= RELOPR term3 repTerm3");
			Operator.AddOpr addOpr = (Operator.AddOpr) consume(Terminal.ADDOPR);
			ConcSyn.Term3 term3 = term3();
			ConcSyn.RepTerm3 repTerm3 = repTerm3();
			return new ConcSyn.RepTerm3(addOpr, term3, repTerm3);
		} else {
			System.out.println("repTerm3 ::= epsilon");
			return new ConcSyn.RepTerm3Eps();
		}
	}

	private ConcSyn.Term3 term3() throws GrammarError {
		System.out.println("term3 ::= factor repFactor");
		ConcSyn.Factor factor = factor();
		ConcSyn.RepFactor repFactor = repFactor();
		return new ConcSyn.Term3(factor, repFactor);
	}

	private ConcSyn.RepFactor repFactor() throws GrammarError {
		if (terminal == Terminal.MULTOPR) {
			System.out.println("repFactor ::= MULTOPR factor repFactor");
			Operator.MultOpr multOpr = (Operator.MultOpr) consume(Terminal.MULTOPR);
			ConcSyn.Factor factor = factor();
			ConcSyn.RepFactor repFactor = repFactor();
			return new ConcSyn.RepFactor(multOpr, factor, repFactor);
		} else {
			System.out.println("repFactor ::= epsilon");
			return new ConcSyn.RepFactorEps();
		}
	}

	private ConcSyn.Factor factor() throws GrammarError {
		ConcSyn.Factor ret = null;
		switch (terminal) {
			case LITERAL:
				System.out.println("factor ::= LITERAL");
				ret = new ConcSyn.FactorLiteral((Literal) consume(Terminal.LITERAL));
				break;
			case IDENT:
				System.out.println("factor ::= IDENT auxIdent");
				Ident ident = (Ident) consume(Terminal.IDENT);
				ConcSyn.AuxIdent auxIdent = auxIdent();
				ret = new ConcSyn.FactorIdent(ident, auxIdent);
				break;
			case LPAREN:
				System.out.println("factor ::= LPAREN expr RPAREN");
				consume(Terminal.LPAREN);
				ConcSyn.Expr expr = expr();
				consume(Terminal.RPAREN);
				ret = new ConcSyn.FactorExpr(expr);
				break;
			default:
				System.out.println("factor ::= monadicOpr factor");
				ConcSyn.MonadicOpr monadicOpr = monadicOpr();
				ConcSyn.Factor factor = factor();
				ret = new ConcSyn.FactorMonadicOpr(monadicOpr, factor);
		}
		return ret;
	}

	private ConcSyn.AuxIdent auxIdent() throws GrammarError {
		ConcSyn.AuxIdent ret = null;
		switch (terminal) {
			case INIT:
				System.out.println("auxIdent ::= INIT");
				consume(Terminal.INIT);
				ret = new ConcSyn.AuxIdentInit();
				break;
			case LPAREN:
				System.out.println("auxIdent ::= exprList auxGlobInitList");
				ConcSyn.ExprList exprList = exprList();
				ConcSyn.AuxGlobInitList auxGlobInitList = auxGlobInitList();
				ret = new ConcSyn.AuxIdentExprList(exprList, auxGlobInitList);
				break;
			default:
				System.out.println("auxIdent ::= epsilon");
				ret = new ConcSyn.AuxIdentEps();
		}
		return ret;
	}

	private ConcSyn.ExprList exprList() throws GrammarError {
		System.out.println("exprList ::= LPAREN auxExprList RPAREN");
		consume(Terminal.LPAREN);
		ConcSyn.AuxExprList auxExprList = auxExprList();
		consume(Terminal.RPAREN);
		return new ConcSyn.ExprList(auxExprList);
	}

	private ConcSyn.AuxExprList auxExprList() throws GrammarError {
		if (terminal == Terminal.RPAREN) {
			System.out.println("auxExprList ::= epsilon");
			return new ConcSyn.AuxExprListEps();
		} else {
			System.out.println("auxExprList ::= expr repExpr");
			ConcSyn.Expr expr = expr();
			ConcSyn.RepExpr repExpr = repExpr();
			return new ConcSyn.AuxExprList(expr, repExpr);
		}
	}

	private ConcSyn.RepExpr repExpr() throws GrammarError {
		if (terminal != Terminal.COMMA) {
			System.out.println("repExpr ::= epsilon");
			return new ConcSyn.RepExprEps();
		} else {
			System.out.println("repExpr ::= COMMA expr repExpr");
			consume(Terminal.COMMA);
			ConcSyn.Expr expr = expr();
			ConcSyn.RepExpr repExpr = repExpr();
			return new ConcSyn.RepExpr(expr, repExpr);
		}
	}

	private ConcSyn.MonadicOpr monadicOpr() throws GrammarError {
		ConcSyn.MonadicOpr ret = null;
		switch (terminal) {
			case NOT:
				System.out.println("monadicOpr ::= NOT");
				ret = new ConcSyn.MonadicOpr((Operator) consume(Terminal.NOT));
				break;
			case ADDOPR:
				System.out.println("monadicOpr ::= ADDOPR");
				ret = new ConcSyn.MonadicOpr((Operator) consume(Terminal.ADDOPR));
				break;
			default:
				throw new GrammarError("terminal expected: NOT | ADDOPR, terminal found: " + terminal, token.getLine());
		}
		return ret;
	}

	private ConcSyn.AuxMechMode auxMechMode() throws GrammarError {
		ConcSyn.AuxMechMode ret = null;
		if (terminal == Terminal.MECHMODE) {
			System.out.println("auxMechMode ::= MECHMODE");
			ret = new ConcSyn.AuxMechMode((Mode.MechMode) consume(Terminal.MECHMODE));
		} else {
			System.out.println("auxMechMode ::= epsilon");
			ret = new ConcSyn.AuxMechModeEps();
		}
		return ret;
	}

	private ConcSyn.AuxChangeMode auxChangeMode() throws GrammarError {
		ConcSyn.AuxChangeMode ret = null;
		if (terminal == Terminal.CHANGEMODE) {
			System.out.println("auxChangeMode ::= CHANGEMODE");
			ret = new ConcSyn.AuxChangeMode((Mode.ChangeMode) consume(Terminal.CHANGEMODE));
		} else {
			System.out.println("auxChangeMode ::= epsilon");
			ret = new ConcSyn.AuxChangeModeEps();
		}
		return ret;
	}

	private ConcSyn.AuxFlowMode auxFlowMode() throws GrammarError {
		ConcSyn.AuxFlowMode ret = null;
		if (terminal == Terminal.FLOWMODE) {
			System.out.println("auxFlowMode ::= FLOWMODE");
			ret = new ConcSyn.AuxFlowMode((Mode.FlowMode) consume(Terminal.FLOWMODE));
		} else {
			System.out.println("auxFlowMode ::= epsilon");
			ret = new ConcSyn.AuxFlowModeEps();
		}
		return ret;
	}

	private ConcSyn.AuxExprCmd auxExprCmd() throws GrammarError {
		ConcSyn.AuxExprCmd ret = null;
		if (terminal == Terminal.BECOMES) {
			System.out.println("auxExprCmd ::= BECOMES expr");
			consume(Terminal.BECOMES);
			ret = new ConcSyn.AuxExprCmdBecomes(expr());
		} else {
			System.out.println("auxExprCmd ::= epsilon");
			ret = new ConcSyn.AuxExprCmdEps();
		}
		return ret;
	}
}
