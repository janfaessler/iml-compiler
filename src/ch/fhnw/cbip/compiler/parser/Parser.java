package ch.fhnw.cbip.compiler.parser;

import ch.fhnw.cbip.compiler.error.GrammarError;
import ch.fhnw.cbip.compiler.parser.ConcSyn.AuxChangeModeEps;
import ch.fhnw.cbip.compiler.parser.ConcSyn.*;
import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.ITokenList;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;
import ch.fhnw.cbip.compiler.scanner.token.Ident;
import ch.fhnw.cbip.compiler.scanner.token.Literal;
import ch.fhnw.cbip.compiler.scanner.token.Mode;
import ch.fhnw.cbip.compiler.scanner.token.Operator;
import ch.fhnw.cbip.compiler.scanner.token.Type;

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
		final ConcSyn.Program program = program();
		// ... and then consuming the SENTINEL
		consume(Terminal.SENTINEL);
		return program;
	}
	
	private Program program() throws GrammarError {
		System.out.println("program ::= PROGRAM IDENT auxGlobCpsDecl blockCmd");
		consume(Terminal.PROGRAM);
		Ident ident = (Ident) consume(Terminal.IDENT);
		AuxGlobCpsDecl auxGlobCpsDecl = auxGlobCpsDecl();
		BlockCmd blockCmd = blockCmd();
		return new Program(ident, auxGlobCpsDecl, blockCmd);
	}

	private Decl decl() throws GrammarError {
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
		AuxChangeMode auxChangeMode = auxChangeMode();
		Ident ident = (Ident) consume(Terminal.IDENT);
		consume(Terminal.COLON);
		final Type type = (Type) consume(Terminal.TYPE);
		return new StoreDecl(auxChangeMode, ident, type);
	}

	private FunDecl funDecl() throws GrammarError {
		System.out.println("funDecl ::= FUN IDENT paramList RETURNS storeDecl auxGlobImpList auxLocCpsDecl blockCmd");
		consume(Terminal.FUN);
		Ident ident = (Ident) consume(Terminal.IDENT);
		ParamList paramList = paramList();
		consume(Terminal.RETURNS);
		StoreDecl storeDecl = storeDecl();
		AuxGlobImpList auxGlobImpList = auxGlobImpList();
		AuxLocCpsDecl auxLocCpsDecl = auxLocCpsDecl();
		BlockCmd blockCmd = blockCmd();
		return new FunDecl(ident, paramList, storeDecl, auxGlobImpList, auxLocCpsDecl, blockCmd);
	}

	private ProcDecl procDecl() throws GrammarError {
		System.out.println("procDecl ::= PROC IDENT paramList auxGlobImpList auxLocCpsDecl blockCmd");
		consume(Terminal.PROC);
		Ident ident = (Ident) consume(Terminal.IDENT);
		ParamList paramList = paramList();
		AuxGlobImpList auxGlobImpList = auxGlobImpList();
		AuxLocCpsDecl auxLocCpsDecl = auxLocCpsDecl();
		BlockCmd blockCmd = blockCmd();
		return new ProcDecl(ident, paramList, auxGlobImpList, auxLocCpsDecl, blockCmd);
	}

	private AuxGlobCpsDecl auxGlobCpsDecl() throws GrammarError {
		if (terminal == Terminal.GLOBAL) {
			System.out.println("auxGlobCpsDecl ::= GLOBAL cpsDecl");
			consume(Terminal.GLOBAL);
			return new AuxGlobCpsDecl(cpsDecl());
		} else {
			System.out.println("auxGlobCpsDecl ::= epsilon");
			return new AuxGlobCpsDeclEps();
		}
	}

	private AuxLocCpsDecl auxLocCpsDecl() throws GrammarError {
		if (terminal == Terminal.LOCAL) {
			System.out.println("auxLocCpsDecl ::= LOCAL cpsDecl");
			consume(Terminal.LOCAL);
			return new AuxLocCpsDecl(cpsDecl());
		} else {
			System.out.println("auxLocCpsDecl ::= epsilon");
			return new AuxLocCpsDeclEps();
		}
	}

	private CpsDecl cpsDecl() throws GrammarError {
		System.out.println("cpsDecl := decl repDecl");
		Decl decl = decl();
		RepDecl repDecl = repDecl();
		return new CpsDecl(decl, repDecl);
	}

	private RepDecl repDecl() throws GrammarError {
		if (terminal == Terminal.SEMICOLON) {
			System.out.println("repDecl ::= SEMICOLON decl repDecl");
			consume(Terminal.SEMICOLON);
			Decl decl = decl();
			RepDecl repDecl = repDecl();
			return new RepDecl(decl, repDecl);
		} else {
			System.out.println("repDecl ::= epsilon");
			return new RepDeclEps();
		}
	}

	private ParamList paramList() throws GrammarError {
		System.out.println("paramList ::= LPAREN auxParamList RPAREN");
		consume(Terminal.LPAREN);
		AuxParamList auxParamList = auxParamList();
		consume(Terminal.RPAREN);
		return new ParamList(auxParamList);
	}

	private AuxParamList auxParamList() throws GrammarError {
		if (terminal != Terminal.RPAREN) {
			System.out.println("auxParamList ::= param repParam");
			Param param = param();
			RepParam repParam = repParam();
			return new AuxParamList(param, repParam);
		} else {
			System.out.println("auxParamList ::= epsilon");
			return new AuxParamListEps();
		}
	}

	private RepParam repParam() throws GrammarError {
		if (terminal == Terminal.COMMA) {
			System.out.println("repParam ::= COMMA param repParam");
			consume(Terminal.COMMA);
			Param param = param();
			RepParam repParam = repParam();
			return new RepParam(param, repParam);
		} else {
			System.out.println("repParam ::= epsilon");
			return new RepParamEps();
		}
	}

	private Param param() throws GrammarError {
		System.out.println("param ::= auxFlowMode auxMechMode storeDecl");
		AuxFlowMode auxFlowMode = auxFlowMode();
		AuxMechMode auxMechMode = auxMechMode();
		StoreDecl storeDecl = storeDecl();
		return new Param(auxFlowMode, auxMechMode, storeDecl);
	}

	private AuxGlobImpList auxGlobImpList() throws GrammarError {
		if (terminal == Terminal.GLOBAL) {
			System.out.println("auxGlobImpList ::= GLOBAL globImpList");
			consume(Terminal.GLOBAL);
			return new AuxGlobImpList(globImpList());
		} else {
			System.out.println("auxGlobImpList ::= epsilon");
			return new AuxGlobImpListEps();
		}
	}

	private GlobImpList globImpList() throws GrammarError {
		System.out.println("globImpList ::= globImp repGlobImp");
		GlobImp globImp = globImp();
		RepGlobImp repGlobImp = repGlobImp();
		return new GlobImpList(globImp, repGlobImp);
	}

	private RepGlobImp repGlobImp() throws GrammarError {
		if (terminal == Terminal.COMMA) {
			System.out.println("repGlobImp ::= COMMA globImp repGlobImp");
			consume(Terminal.COMMA);
			GlobImp globImp = globImp();
			RepGlobImp repGlobImp = repGlobImp();
			return new RepGlobImp(globImp, repGlobImp);
		} else {
			System.out.println("repGlobImp ::= epsilon");
			return new RepGlobImpEps();
		}
	}

	private GlobImp globImp() throws GrammarError {
		System.out.println("globImp ::= auxFlowMode auxChangeMode IDENT");
		AuxFlowMode auxFlowMode = auxFlowMode();
		AuxChangeMode auxChangeMode = auxChangeMode();
		Ident ident = (Ident) consume(Terminal.IDENT);
		return new GlobImp(auxFlowMode, auxChangeMode, ident);
	}

	private Cmd cmd() throws GrammarError {
		Cmd ret = null;
		switch (terminal) {
			case SKIP:
				System.out.println("cmd ::= SKIP");
				consume(Terminal.SKIP);
				ret = new CmdSkip();
			case IF:
				System.out.println("cmd ::= IF LPAREN expr RPAREN blockCmd ELSE blockCmd");
				consume(Terminal.IF);
				consume(Terminal.LPAREN);
				Expr ifExpr = expr();
				consume(Terminal.RPAREN);
				BlockCmd ifCmd = blockCmd();
				consume(Terminal.ELSE);
				BlockCmd elseCmd = blockCmd();
				ret = new CmdIf(ifExpr, ifCmd, elseCmd);
			case CALL:
				System.out.println("cmd ::= CALL IDENT exprList auxGlobInitList");
				consume(Terminal.CALL);
				Ident ident = (Ident) consume(Terminal.IDENT);
				ExprList exprList = exprList();
				AuxGlobInitList auxGlobInitList = auxGlobInitList();
				ret = new CmdCall(ident, exprList,auxGlobInitList);
				break;
			case QUESTMARK:
				System.out.println("cmd ::= QUESTMARK expr");
				consume(Terminal.QUESTMARK);
				Expr questExpr = expr();
				ret = new CmdQuest(questExpr);
			case EXCLAMARK:
				System.out.println("cmd ::= EXCLMARK expr");
				consume(Terminal.EXCLAMARK);
				Expr exclExpr = expr();
				ret = new CmdExcl(exclExpr);
			default:
				System.out.println("cmd ::= expr auxExprCmd");
				Expr expr = expr();
				AuxExprCmd auxExprCmd = auxExprCmd();
				ret = new CmdExpr(expr, auxExprCmd);
		}
		return ret;
	}

	private BlockCmd blockCmd() throws GrammarError {
		System.out.println("blockCmd ::= LBRACE cmd repCmd RBRACE");
		consume(Terminal.LBRACE);
		Cmd cmd = cmd();
		RepCmd repCmd = repCmd();
		consume(Terminal.RBRACE);
		return new BlockCmd(cmd, repCmd);
	}

	private RepCmd repCmd() throws GrammarError {
		if (terminal == Terminal.SEMICOLON) {
			System.out.println("repCmd ::= SEMICOLON cmd repCmd");
			consume(Terminal.SEMICOLON);
			Cmd cmd = cmd();
			RepCmd repCmd = repCmd();
			return new RepCmd(cmd, repCmd);
		} else {
			System.out.println("repCmd ::= epsilon");
			return new RepCmdEps();
		}
	}

	private AuxGlobInitList auxGlobInitList() throws GrammarError {
		if (terminal == Terminal.INIT) {
			System.out.println("auxGlobInitList ::= INIT LPAREN globInitList RPAREN");
			consume(Terminal.INIT);
			consume(Terminal.LPAREN);
			GlobInitList globInitList = globInitList();
			consume(Terminal.RPAREN);
			return new AuxGlobInitList(globInitList);
		} else {
			System.out.println("auxGlobInitList ::= epsilon");
			return new AuxGlobInitListEps();
		}
	}

	private GlobInitList globInitList() throws GrammarError {
		System.out.println("globInitList ::= IDENT repIdent");
		Ident ident = (Ident) consume(Terminal.IDENT);
		RepIdent repIdent = repIdent();
		return new GlobInitList(ident, repIdent);
	}

	private RepIdent repIdent() throws GrammarError {
		if (terminal != Terminal.COMMA) {
			System.out.println("repIdent ::= epsilon");
			return new RepIdentEps();
		} else {
			System.out.println("repIdent ::= IDENT repIdent");
			Ident ident = (Ident) consume(Terminal.IDENT);
			RepIdent repIdent = repIdent();
			return new RepIdent(ident, repIdent);
		}
	}

	private Expr expr() throws GrammarError {
		System.out.println("expr ::= term1 repTerm1");
		Term1 term1 = term1();
		RepTerm1 repTerm1 = repTerm1();
		return new Expr(term1, repTerm1);
	}

	private RepTerm1 repTerm1() throws GrammarError {
		if (terminal == Terminal.BOOLOPR) {
			System.out.println("repTerm1 ::= BOOLOPR term1 repTerm1");
			Operator.BoolOpr boolOpr = (Operator.BoolOpr) consume(Terminal.BOOLOPR);
			Term1 term1 = term1();
			RepTerm1 repTerm1 = repTerm1();
			return new RepTerm1(boolOpr, term1, repTerm1);
		} else {
			System.out.println("repTerm1 ::= epsilon");
			return new RepTerm1Eps();
		}
	}

	private Term1 term1() throws GrammarError {
		System.out.println("term1 ::= term2 repTerm2");
		Term2 term2 = term2();
		RepTerm2 repTerm2 = repTerm2();
		return new Term1(term2, repTerm2);
	}

	private RepTerm2 repTerm2() throws GrammarError {
		if (terminal == Terminal.RELOPR) {
			System.out.println("repTerm2 ::= RELOPR term2 repTerm2");
			Operator.RelOpr relOpr = (Operator.RelOpr) consume(Terminal.RELOPR);
			Term2 term2 = term2();
			RepTerm2 repTerm2 = repTerm2();
			return new RepTerm2(relOpr, term2, repTerm2);
		} else {
			System.out.println("repTerm2 ::= epsilon");
			return new RepTerm2Eps();
		}
	}

	private Term2 term2() throws GrammarError {
		System.out.println("term2 ::= term3 repTerm3");
		Term3 term3 = term3();
		RepTerm3 repTerm3 = repTerm3();
		return new Term2(term3, repTerm3);
	}

	private RepTerm3 repTerm3() throws GrammarError {
		if (terminal == Terminal.ADDOPR) {
			System.out.println("repTerm3 ::= RELOPR term3 repTerm3");
			Operator.AddOpr addOpr = (Operator.AddOpr) consume(Terminal.ADDOPR);
			Term3 term3 = term3();
			RepTerm3 repTerm3 = repTerm3();
			return new RepTerm3(addOpr, term3, repTerm3);
		} else {
			System.out.println("repTerm3 ::= epsilon");
			return new RepTerm3Eps();
		}
	}

	private Term3 term3() throws GrammarError {
		System.out.println("term3 ::= factor repFactor");
		Factor factor = factor();
		RepFactor repFactor = repFactor();
		return new Term3(factor, repFactor);
	}

	private RepFactor repFactor() throws GrammarError {
		if (terminal == Terminal.MULTOPR) {
			System.out.println("repFactor ::= MULTOPR factor repFactor");
			Operator.MultOpr multOpr = (Operator.MultOpr) consume(Terminal.MULTOPR);
			Factor factor = factor();
			RepFactor repFactor = repFactor();
			return new RepFactor(multOpr, factor, repFactor);
		} else {
			System.out.println("repFactor ::= epsilon");
			return new RepFactorEps();
		}
	}

	private Factor factor() throws GrammarError {
		switch (terminal) {
			case LITERAL:
				System.out.println("factor ::= LITERAL");
				return new FactorLiteral((Literal) consume(Terminal.LITERAL));
			case IDENT:
				System.out.println("factor ::= IDENT auxIdent");
				Ident ident = (Ident) consume(Terminal.IDENT);
				AuxIdent auxIdent = auxIdent();
				return new FactorIdent(ident, auxIdent);
			case LPAREN:
				System.out.println("factor ::= LPAREN expr RPAREN");
				consume(Terminal.LPAREN);
				Expr expr = expr();
				consume(Terminal.RPAREN);
				return new FactorExpr(expr);
			default:
				System.out.println("factor ::= monadicOpr factor");
				MonadicOpr monadicOpr = monadicOpr();
				Factor factor = factor();
				return new FactorMonadicOpr(monadicOpr, factor);
		}
	}

	private AuxIdent auxIdent() throws GrammarError {
		switch (terminal) {
			case INIT:
				System.out.println("auxIdent ::= INIT");
				consume(Terminal.INIT);
				return new AuxIdentInit();
			case LPAREN:
				System.out.println("auxIdent ::= exprList auxGlobInitList");
				ExprList exprList = exprList();
				AuxGlobInitList auxGlobInitList = auxGlobInitList();
				return new AuxIdentExprList(exprList, auxGlobInitList);
			default:
				System.out.println("auxIdent ::= epsilon");
				return new AuxIdentEps();
		}
	}

	private ExprList exprList() throws GrammarError {
		System.out.println("exprList ::= LPAREN auxExprList RPAREN");
		consume(Terminal.LPAREN);
		AuxExprList auxExprList = auxExprList();
		consume(Terminal.RPAREN);
		return new ExprList(auxExprList);
	}

	private AuxExprList auxExprList() throws GrammarError {
		if (terminal == Terminal.RPAREN) {
			System.out.println("auxExprList ::= epsilon");
			return new AuxExprListEps();
		} else {
			System.out.println("auxExprList ::= expr repExpr");
			Expr expr = expr();
			RepExpr repExpr = repExpr();
			return new AuxExprList(expr, repExpr);
		}
	}

	private RepExpr repExpr() throws GrammarError {
		if (terminal != Terminal.COMMA) {
			System.out.println("repExpr ::= epsilon");
			return new RepExprEps();
		} else {
			System.out.println("repExpr ::= COMMA expr repExpr");
			consume(Terminal.COMMA);
			Expr expr = expr();
			RepExpr repExpr = repExpr();
			return new RepExpr(expr, repExpr);
		}
	}

	private MonadicOpr monadicOpr() throws GrammarError {
		switch (terminal) {
		case NOT:
			System.out.println("monadicOpr ::= NOT");
			return new MonadicOpr((Operator) consume(Terminal.NOT));
		case ADDOPR:
			System.out.println("monadicOpr ::= ADDOPR");
			return new MonadicOpr((Operator) consume(Terminal.ADDOPR));
		default:
			throw new GrammarError("terminal expected: NOT | ADDOPR, terminal found: " + terminal, token.getLine());
		}
	}

	private AuxMechMode auxMechMode() throws GrammarError {
		if (terminal == Terminal.MECHMODE) {
			System.out.println("auxMechMode ::= MECHMODE");
			return new AuxMechMode((Mode.MechMode) consume(Terminal.MECHMODE));
		} else {
			System.out.println("auxMechMode ::= epsilon");
			return new AuxMechModeEps();
		}
	}

	private AuxChangeMode auxChangeMode() throws GrammarError {
		if (terminal == Terminal.CHANGEMODE) {
			System.out.println("auxChangeMode ::= CHANGEMODE");
			return new AuxChangeMode((Mode.ChangeMode) consume(Terminal.CHANGEMODE));
		} else {
			System.out.println("auxChangeMode ::= epsilon");
			return new AuxChangeModeEps();
		}
	}

	private AuxFlowMode auxFlowMode() throws GrammarError {
		if (terminal == Terminal.FLOWMODE) {
			System.out.println("auxFlowMode ::= FLOWMODE");
			return new AuxFlowMode((Mode.FlowMode) consume(Terminal.FLOWMODE));
		} else {
			System.out.println("auxFlowMode ::= epsilon");
			return new AuxFlowModeEps();
		}
	}

	private AuxExprCmd auxExprCmd() throws GrammarError {
		if (terminal == Terminal.BECOMES) {
			System.out.println("auxExprCmd ::= BECOMES expr");
			consume(Terminal.BECOMES);
			return new AuxExprCmdBecomes(expr());
		} else {
			System.out.println("auxExprCmd ::= epsilon");
			return new AuxExprCmdEps();
		}
	}
}
