package ch.fhnw.cbip.compiler.parser;

import ch.fhnw.cbip.compiler.error.GrammarError;
import ch.fhnw.cbip.compiler.parser.ConcTree.*;
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
	
	public ConcTree.Program parse() throws GrammarError {
		// parsing the start symbol ...
		ConcTree.Program program = program();
		// ... and then consuming the SENTINEL
		consume(Terminal.SENTINEL);
		return program;
	}
	
	private ConcTree.Program program() throws GrammarError {
		System.out.println("program ::= PROGRAM IDENT auxGlobCpsDecl blockCmd");
		consume(Terminal.PROGRAM);
		Ident ident = (Ident) consume(Terminal.IDENT);
		ConcTree.AuxGlobCpsDecl auxGlobCpsDecl = auxGlobCpsDecl();
		ConcTree.BlockCmd blockCmd = blockCmd();
		return new ConcTree.Program(ident, auxGlobCpsDecl, blockCmd);
	}

	private ConcTree.Decl decl() throws GrammarError {
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
		ConcTree.AuxChangeMode auxChangeMode = auxChangeMode();
		Ident ident = (Ident) consume(Terminal.IDENT);
		consume(Terminal.COLON);
		Type type = (Type) consume(Terminal.TYPE);
		return new ConcTree.StoreDecl(auxChangeMode, ident, type);
	}

	private ConcTree.FunDecl funDecl() throws GrammarError {
		System.out.println("funDecl ::= FUN IDENT paramList RETURNS storeDecl auxGlobImpList auxLocCpsDecl blockCmd");
		consume(Terminal.FUN);
		Ident ident = (Ident) consume(Terminal.IDENT);
		ConcTree.ParamList paramList = paramList();
		consume(Terminal.RETURNS);
		ConcTree.StoreDecl storeDecl = storeDecl();
		ConcTree.AuxGlobImpList auxGlobImpList = auxGlobImpList();
		ConcTree.AuxLocCpsDecl auxLocCpsDecl = auxLocCpsDecl();
		ConcTree.BlockCmd blockCmd = blockCmd();
		return new ConcTree.FunDecl(ident, paramList, storeDecl, auxGlobImpList, auxLocCpsDecl, blockCmd);
	}

	private ConcTree.ProcDecl procDecl() throws GrammarError {
		System.out.println("procDecl ::= PROC IDENT paramList auxGlobImpList auxLocCpsDecl blockCmd");
		consume(Terminal.PROC);
		Ident ident = (Ident) consume(Terminal.IDENT);
		ConcTree.ParamList paramList = paramList();
		ConcTree.AuxGlobImpList auxGlobImpList = auxGlobImpList();
		ConcTree.AuxLocCpsDecl auxLocCpsDecl = auxLocCpsDecl();
		ConcTree.BlockCmd blockCmd = blockCmd();
		return new ConcTree.ProcDecl(ident, paramList, auxGlobImpList, auxLocCpsDecl, blockCmd);
	}

	private ConcTree.AuxGlobCpsDecl auxGlobCpsDecl() throws GrammarError {
		if (terminal == Terminal.GLOBAL) {
			System.out.println("auxGlobCpsDecl ::= GLOBAL cpsDecl");
			consume(Terminal.GLOBAL);
			return new ConcTree.AuxGlobCpsDecl(cpsDecl());
		} else {
			System.out.println("auxGlobCpsDecl ::= epsilon");
			return new ConcTree.AuxGlobCpsDeclEps();
		}
	}

	private ConcTree.AuxLocCpsDecl auxLocCpsDecl() throws GrammarError {
		if (terminal == Terminal.LOCAL) {
			System.out.println("auxLocCpsDecl ::= LOCAL cpsDecl");
			consume(Terminal.LOCAL);
			return new ConcTree.AuxLocCpsDecl(cpsDecl());
		} else {
			System.out.println("auxLocCpsDecl ::= epsilon");
			return new ConcTree.AuxLocCpsDeclEps();
		}
	}

	private ConcTree.CpsDecl cpsDecl() throws GrammarError {
		System.out.println("cpsDecl := decl repDecl");
		ConcTree.Decl decl = decl();
		ConcTree.RepDecl repDecl = repDecl();
		return new ConcTree.CpsDecl(decl, repDecl);
	}

	private ConcTree.RepDecl repDecl() throws GrammarError {
		if (terminal == Terminal.SEMICOLON) {
			System.out.println("repDecl ::= SEMICOLON decl repDecl");
			consume(Terminal.SEMICOLON);
			ConcTree.Decl decl = decl();
			ConcTree.RepDecl repDecl = repDecl();
			return new ConcTree.RepDecl(decl, repDecl);
		} else {
			System.out.println("repDecl ::= epsilon");
			return new ConcTree.RepDeclEps();
		}
	}

	private ConcTree.ParamList paramList() throws GrammarError {
		System.out.println("paramList ::= LPAREN auxParamList RPAREN");
		consume(Terminal.LPAREN);
		ConcTree.AuxParamList auxParamList = auxParamList();
		consume(Terminal.RPAREN);
		return new ConcTree.ParamList(auxParamList);
	}

	private ConcTree.AuxParamList auxParamList() throws GrammarError {
		if (terminal != Terminal.RPAREN) {
			System.out.println("auxParamList ::= param repParam");
			ConcTree.Param param = param();
			ConcTree.RepParam repParam = repParam();
			return new ConcTree.AuxParamList(param, repParam);
		} else {
			System.out.println("auxParamList ::= epsilon");
			return new ConcTree.AuxParamListEps();
		}
	}

	private ConcTree.RepParam repParam() throws GrammarError {
		if (terminal == Terminal.COMMA) {
			System.out.println("repParam ::= COMMA param repParam");
			consume(Terminal.COMMA);
			ConcTree.Param param = param();
			ConcTree.RepParam repParam = repParam();
			return new ConcTree.RepParam(param, repParam);
		} else {
			System.out.println("repParam ::= epsilon");
			return new ConcTree.RepParamEps();
		}
	}

	private ConcTree.Param param() throws GrammarError {
		System.out.println("param ::= auxFlowMode auxMechMode storeDecl");
		ConcTree.AuxFlowMode auxFlowMode = auxFlowMode();
		ConcTree.AuxMechMode auxMechMode = auxMechMode();
		ConcTree.StoreDecl storeDecl = storeDecl();
		return new ConcTree.Param(auxFlowMode, auxMechMode, storeDecl);
	}

	private ConcTree.AuxGlobImpList auxGlobImpList() throws GrammarError {
		if (terminal == Terminal.GLOBAL) {
			System.out.println("auxGlobImpList ::= GLOBAL globImpList");
			consume(Terminal.GLOBAL);
			return new ConcTree.AuxGlobImpList(globImpList());
		} else {
			System.out.println("auxGlobImpList ::= epsilon");
			return new ConcTree.AuxGlobImpListEps();
		}
	}

	private ConcTree.GlobImpList globImpList() throws GrammarError {
		System.out.println("globImpList ::= globImp repGlobImp");
		ConcTree.GlobImp globImp = globImp();
		ConcTree.RepGlobImp repGlobImp = repGlobImp();
		return new ConcTree.GlobImpList(globImp, repGlobImp);
	}

	private ConcTree.RepGlobImp repGlobImp() throws GrammarError {
		if (terminal == Terminal.COMMA) {
			System.out.println("repGlobImp ::= COMMA globImp repGlobImp");
			consume(Terminal.COMMA);
			ConcTree.GlobImp globImp = globImp();
			ConcTree.RepGlobImp repGlobImp = repGlobImp();
			return new ConcTree.RepGlobImp(globImp, repGlobImp);
		} else {
			System.out.println("repGlobImp ::= epsilon");
			return new ConcTree.RepGlobImpEps();
		}
	}

	private ConcTree.GlobImp globImp() throws GrammarError {
		System.out.println("globImp ::= auxFlowMode auxChangeMode IDENT");
		ConcTree.AuxFlowMode auxFlowMode = auxFlowMode();
		ConcTree.AuxChangeMode auxChangeMode = auxChangeMode();
		Ident ident = (Ident) consume(Terminal.IDENT);
		return new ConcTree.GlobImp(auxFlowMode, auxChangeMode, ident);
	}

	private ConcTree.Cmd cmd() throws GrammarError {
		ConcTree.Cmd ret = null;
		switch (terminal) {
			case SKIP:
				System.out.println("cmd ::= SKIP");
				consume(Terminal.SKIP);
				ret = new ConcTree.CmdSkip();
				break;
			case IF:
				System.out.println("cmd ::= IF LPAREN expr RPAREN blockCmd ELSE blockCmd");
				consume(Terminal.IF);
				consume(Terminal.LPAREN);
				ConcTree.Expr ifExpr = expr();
				consume(Terminal.RPAREN);
				ConcTree.BlockCmd ifCmd = blockCmd();
				consume(Terminal.ELSE);
				ConcTree.BlockCmd elseCmd = blockCmd();
				ret = new ConcTree.CmdIf(ifExpr, ifCmd, elseCmd);
				break;
			case CALL:
				System.out.println("cmd ::= CALL IDENT exprList auxGlobInitList");
				consume(Terminal.CALL);
				Ident ident = (Ident) consume(Terminal.IDENT);
				ConcTree.ExprList exprList = exprList();
				ConcTree.AuxGlobInitList auxGlobInitList = auxGlobInitList();
				ret = new ConcTree.CmdCall(ident, exprList,auxGlobInitList);
				break;
			case WHILE:
				System.out.println("cmd ::= WHILE LPAREN expr RPAREN blockCmd");
				consume(Terminal.WHILE);
				consume(Terminal.LPAREN);
				ConcTree.Expr whileExpr = expr();
				consume(Terminal.RPAREN);
				ConcTree.BlockCmd whileCmd = blockCmd();
				ret =  new ConcTree.CmdWhile(whileExpr, whileCmd);
				break;
			case QUESTMARK:
				System.out.println("cmd ::= QUESTMARK expr");
				consume(Terminal.QUESTMARK);
				ConcTree.Expr questExpr = expr();
				ret = new ConcTree.CmdQuest(questExpr);
				break;
			case EXCLAMARK:
				System.out.println("cmd ::= EXCLMARK expr");
				consume(Terminal.EXCLAMARK);
				ConcTree.Expr exclExpr = expr();
				ret = new ConcTree.CmdExcl(exclExpr);
				break;
			default:
				System.out.println("cmd ::= expr auxExprCmd");
				ConcTree.Expr expr = expr();
				ConcTree.AuxExprCmd auxExprCmd = auxExprCmd();
				ret = new ConcTree.CmdExpr(expr, auxExprCmd);
		}
		return ret;
	}

	private ConcTree.BlockCmd blockCmd() throws GrammarError {
		System.out.println("blockCmd ::= LBRACE cmd repCmd RBRACE");
		consume(Terminal.LBRACE);
		ConcTree.Cmd cmd = cmd();
		ConcTree.RepCmd repCmd = repCmd();
		consume(Terminal.RBRACE);
		return new ConcTree.BlockCmd(cmd, repCmd);
	}

	private ConcTree.RepCmd repCmd() throws GrammarError {
		if (terminal == Terminal.SEMICOLON) {
			System.out.println("repCmd ::= SEMICOLON cmd repCmd");
			consume(Terminal.SEMICOLON);
			ConcTree.Cmd cmd = cmd();
			ConcTree.RepCmd repCmd = repCmd();
			return new ConcTree.RepCmd(cmd, repCmd);
		} else {
			System.out.println("repCmd ::= epsilon");
			return new ConcTree.RepCmdEps();
		}
	}

	private ConcTree.AuxGlobInitList auxGlobInitList() throws GrammarError {
		if (terminal == Terminal.INIT) {
			System.out.println("auxGlobInitList ::= INIT LPAREN globInitList RPAREN");
			consume(Terminal.INIT);
			consume(Terminal.LPAREN);
			ConcTree.GlobInitList globInitList = globInitList();
			consume(Terminal.RPAREN);
			return new ConcTree.AuxGlobInitList(globInitList);
		} else {
			System.out.println("auxGlobInitList ::= epsilon");
			return new ConcTree.AuxGlobInitListEps();
		}
	}

	private ConcTree.GlobInitList globInitList() throws GrammarError {
		System.out.println("globInitList ::= IDENT repIdent");
		Ident ident = (Ident) consume(Terminal.IDENT);
		ConcTree.RepIdent repIdent = repIdent();
		return new ConcTree.GlobInitList(ident, repIdent);
	}

	private ConcTree.RepIdent repIdent() throws GrammarError {
		if (terminal != Terminal.COMMA) {
			System.out.println("repIdent ::= epsilon");
			return new ConcTree.RepIdentEps();
		} else {
			System.out.println("repIdent ::= IDENT repIdent");
			Ident ident = (Ident) consume(Terminal.IDENT);
			ConcTree.RepIdent repIdent = repIdent();
			return new ConcTree.RepIdent(ident, repIdent);
		}
	}

	private ConcTree.Expr expr() throws GrammarError {
		System.out.println("expr ::= term1 repTerm1");
		ConcTree.Term1 term1 = term1();
		ConcTree.RepTerm1 repTerm1 = repTerm1();
		return new ConcTree.Expr(term1, repTerm1);
	}

	private ConcTree.RepTerm1 repTerm1() throws GrammarError {
		if (terminal == Terminal.BOOLOPR) {
			System.out.println("repTerm1 ::= BOOLOPR term1 repTerm1");
			Operator.BoolOpr boolOpr = (Operator.BoolOpr) consume(Terminal.BOOLOPR);
			ConcTree.Term1 term1 = term1();
			ConcTree.RepTerm1 repTerm1 = repTerm1();
			return new ConcTree.RepTerm1(boolOpr, term1, repTerm1);
		} else {
			System.out.println("repTerm1 ::= epsilon");
			return new ConcTree.RepTerm1Eps();
		}
	}

	private ConcTree.Term1 term1() throws GrammarError {
		System.out.println("term1 ::= term2 repTerm2");
		ConcTree.Term2 term2 = term2();
		ConcTree.RepTerm2 repTerm2 = repTerm2();
		return new ConcTree.Term1(term2, repTerm2);
	}

	private ConcTree.RepTerm2 repTerm2() throws GrammarError {
		if (terminal == Terminal.RELOPR) {
			System.out.println("repTerm2 ::= RELOPR term2 repTerm2");
			Operator.RelOpr relOpr = (Operator.RelOpr) consume(Terminal.RELOPR);
			ConcTree.Term2 term2 = term2();
			ConcTree.RepTerm2 repTerm2 = new ConcTree.RepTerm2Eps();
			return new ConcTree.RepTerm2(relOpr, term2, repTerm2);
		} else {
			System.out.println("repTerm2 ::= epsilon");
			return new ConcTree.RepTerm2Eps();
		}
	}

	private ConcTree.Term2 term2() throws GrammarError {
		System.out.println("term2 ::= term3 repTerm3");
		ConcTree.Term3 term3 = term3();
		ConcTree.RepTerm3 repTerm3 = repTerm3();
		return new ConcTree.Term2(term3, repTerm3);
	}

	private ConcTree.RepTerm3 repTerm3() throws GrammarError {
		if (terminal == Terminal.ADDOPR) {
			System.out.println("repTerm3 ::= RELOPR term3 repTerm3");
			Operator.AddOpr addOpr = (Operator.AddOpr) consume(Terminal.ADDOPR);
			ConcTree.Term3 term3 = term3();
			ConcTree.RepTerm3 repTerm3 = repTerm3();
			return new ConcTree.RepTerm3(addOpr, term3, repTerm3);
		} else {
			System.out.println("repTerm3 ::= epsilon");
			return new ConcTree.RepTerm3Eps();
		}
	}

	private ConcTree.Term3 term3() throws GrammarError {
		System.out.println("term3 ::= factor repFactor");
		ConcTree.Factor factor = factor();
		ConcTree.RepFactor repFactor = repFactor();
		return new ConcTree.Term3(factor, repFactor);
	}

	private ConcTree.RepFactor repFactor() throws GrammarError {
		if (terminal == Terminal.MULTOPR) {
			System.out.println("repFactor ::= MULTOPR factor repFactor");
			Operator.MultOpr multOpr = (Operator.MultOpr) consume(Terminal.MULTOPR);
			ConcTree.Factor factor = factor();
			ConcTree.RepFactor repFactor = repFactor();
			return new ConcTree.RepFactor(multOpr, factor, repFactor);
		} else {
			System.out.println("repFactor ::= epsilon");
			return new ConcTree.RepFactorEps();
		}
	}

	private ConcTree.Factor factor() throws GrammarError {
		ConcTree.Factor ret = null;
		switch (terminal) {
			case LITERAL:
				System.out.println("factor ::= LITERAL");
				ret = new ConcTree.FactorLiteral((Literal) consume(Terminal.LITERAL));
				break;
			case IDENT:
				System.out.println("factor ::= IDENT auxIdent");
				Ident ident = (Ident) consume(Terminal.IDENT);
				ConcTree.AuxIdent auxIdent = auxIdent();
				ret = new ConcTree.FactorIdent(ident, auxIdent);
				break;
			case LPAREN:
				System.out.println("factor ::= LPAREN expr RPAREN");
				consume(Terminal.LPAREN);
				ConcTree.Expr expr = expr();
				consume(Terminal.RPAREN);
				ret = new ConcTree.FactorExpr(expr);
				break;
			default:
				System.out.println("factor ::= monadicOpr factor");
				ConcTree.MonadicOpr monadicOpr = monadicOpr();
				ConcTree.Factor factor = factor();
				ret = new ConcTree.FactorMonadicOpr(monadicOpr, factor);
		}
		return ret;
	}

	private ConcTree.AuxIdent auxIdent() throws GrammarError {
		ConcTree.AuxIdent ret = null;
		switch (terminal) {
			case INIT:
				System.out.println("auxIdent ::= INIT");
				consume(Terminal.INIT);
				ret = new ConcTree.AuxIdentInit();
				break;
			case LPAREN:
				System.out.println("auxIdent ::= exprList");
				ConcTree.ExprList exprList = exprList();
				ret = new ConcTree.AuxIdentExprList(exprList);
				break;
			default:
				System.out.println("auxIdent ::= epsilon");
				ret = new ConcTree.AuxIdentEps();
		}
		return ret;
	}

	private ConcTree.ExprList exprList() throws GrammarError {
		System.out.println("exprList ::= LPAREN auxExprList RPAREN");
		consume(Terminal.LPAREN);
		ConcTree.AuxExprList auxExprList = auxExprList();
		consume(Terminal.RPAREN);
		return new ConcTree.ExprList(auxExprList);
	}

	private ConcTree.AuxExprList auxExprList() throws GrammarError {
		if (terminal == Terminal.RPAREN) {
			System.out.println("auxExprList ::= epsilon");
			return new ConcTree.AuxExprListEps();
		} else {
			System.out.println("auxExprList ::= expr repExpr");
			ConcTree.Expr expr = expr();
			ConcTree.RepExpr repExpr = repExpr();
			return new ConcTree.AuxExprList(expr, repExpr);
		}
	}

	private ConcTree.RepExpr repExpr() throws GrammarError {
		if (terminal != Terminal.COMMA) {
			System.out.println("repExpr ::= epsilon");
			return new ConcTree.RepExprEps();
		} else {
			System.out.println("repExpr ::= COMMA expr repExpr");
			consume(Terminal.COMMA);
			ConcTree.Expr expr = expr();
			ConcTree.RepExpr repExpr = repExpr();
			return new ConcTree.RepExpr(expr, repExpr);
		}
	}

	private ConcTree.MonadicOpr monadicOpr() throws GrammarError {
		ConcTree.MonadicOpr ret = null;
		switch (terminal) {
			case NOT:
				System.out.println("monadicOpr ::= NOT");
				ret = new ConcTree.MonadicOpr((Operator) consume(Terminal.NOT));
				break;
			case ADDOPR:
				System.out.println("monadicOpr ::= ADDOPR");
				ret = new ConcTree.MonadicOpr((Operator) consume(Terminal.ADDOPR));
				break;
			default:
				throw new GrammarError("terminal expected: NOT | ADDOPR, terminal found: " + terminal, token.getLine());
		}
		return ret;
	}

	private ConcTree.AuxMechMode auxMechMode() throws GrammarError {
		ConcTree.AuxMechMode ret = null;
		if (terminal == Terminal.MECHMODE) {
			System.out.println("auxMechMode ::= MECHMODE");
			ret = new ConcTree.AuxMechMode((Mode.MechMode) consume(Terminal.MECHMODE));
		} else {
			System.out.println("auxMechMode ::= epsilon");
			ret = new ConcTree.AuxMechModeEps();
		}
		return ret;
	}

	private ConcTree.AuxChangeMode auxChangeMode() throws GrammarError {
		ConcTree.AuxChangeMode ret = null;
		if (terminal == Terminal.CHANGEMODE) {
			System.out.println("auxChangeMode ::= CHANGEMODE");
			ret = new ConcTree.AuxChangeMode((Mode.ChangeMode) consume(Terminal.CHANGEMODE));
		} else {
			System.out.println("auxChangeMode ::= epsilon");
			ret = new ConcTree.AuxChangeModeEps();
		}
		return ret;
	}

	private ConcTree.AuxFlowMode auxFlowMode() throws GrammarError {
		ConcTree.AuxFlowMode ret = null;
		if (terminal == Terminal.FLOWMODE) {
			System.out.println("auxFlowMode ::= FLOWMODE");
			ret = new ConcTree.AuxFlowMode((Mode.FlowMode) consume(Terminal.FLOWMODE));
		} else {
			System.out.println("auxFlowMode ::= epsilon");
			ret = new ConcTree.AuxFlowModeEps();
		}
		return ret;
	}

	private ConcTree.AuxExprCmd auxExprCmd() throws GrammarError {
		ConcTree.AuxExprCmd ret = null;
		if (terminal == Terminal.BECOMES) {
			System.out.println("auxExprCmd ::= BECOMES expr");
			consume(Terminal.BECOMES);
			ret = new ConcTree.AuxExprCmdBecomes(expr());
		} else {
			System.out.println("auxExprCmd ::= epsilon");
			ret = new ConcTree.AuxExprCmdEps();
		}
		return ret;
	}
}
