datatype term
  = LITERAL
  | IDENT
  | LPAREN
  | RPAREN
  | COMMA
  | SEMICOLON
  | COLON
  | QUESTMARK
  | EXCLMARK
  | BECOMES
  | LBRACE
  | RBRACE
  | MULTOPR
  | ADDOPR
  | RELOPR
  | BOOLOPR
  | TYPE
  | CHANGEMODE
  | MECHMODE
  | FLOWMODE
  | ELSE
  | FUN
  | GLOBAL
  | IF
  | INIT
  | LOCAL
  | NOT
  | PROC
  | PROGRAM
  | RETURNS
  | SKIP
  | WHILE
  | FOR
  | INVAR
  | CALL

val string_of_term =
  fn LITERAL    => "LITERAL"
   | IDENT      => "IDENT"
   | LPAREN     => "LPAREN"
   | RPAREN     => "RPAREN"
   | COMMA      => "COMMA"
   | SEMICOLON  => "SEMICOLON"
   | COLON      => "COLON"
   | QUESTMARK  => "QUESTMARK"
   | EXCLMARK   => "EXCLMARK"
   | BECOMES    => "BECOMES"
   | LBRACE     => "LBRACE"
   | RBRACE     => "RBRACE"
   | MULTOPR    => "MULTOPR"
   | ADDOPR     => "ADDOPR"
   | RELOPR     => "RELOPR"
   | BOOLOPR    => "BOOLOPR"
   | TYPE       => "TYPE"
   | CHANGEMODE => "CHANGEMODE"
   | MECHMODE   => "MECHMODE"
   | FLOWMODE   => "FLOWMODE"
   | ELSE       => "ELSE"
   | FUN        => "FUN"
   | GLOBAL     => "GLOBAL"
   | IF         => "IF"
   | INIT       => "INIT"
   | LOCAL      => "LOCAL"
   | NOT        => "NOT"
   | PROC       => "PROC"
   | PROGRAM    => "PROGRAM"
   | RETURNS    => "RETURNS"
   | SKIP       => "SKIP"
   | WHILE      => "WHILE"
   | FOR        => "FOR"
   | INVAR       => "INVAR"
   | CALL       => "CALL"

datatype nonterm
  = program
  | decl
  | storeDecl
  | funDecl
  | procDecl
  | auxGlobCpsDecl
  | auxLocCpsDecl
  | cpsDecl
  | repDecl
  | paramList
  | auxParamList
  | repParam
  | param
  | auxGlobImpList
  | globImpList
  | repGlobImp
  | globImp
  | cmd
  | blockCmd
  | auxGlobInitList
  | globInitList
  | repCmd
  | repIdent
  | expr
  | repTerm1
  | term1
  | repTerm2
  | term2
  | repTerm3
  | term3
  | repFactor
  | factor
  | auxIdent
  | exprList
  | auxExprList
  | repExpr
  | auxExprSemi
  | monadicOpr
  | auxMechMode
  | auxChangeMode
  | auxFlowMode

val string_of_nonterm =
  fn program         => "program"
   | decl            => "decl"
   | storeDecl       => "storeDecl"
   | funDecl         => "funDecl"
   | procDecl        => "procDecl"
   | auxGlobCpsDecl  => "auxGlobCpsDecl"
   | auxLocCpsDecl   => "auxLocCpsDecl"
   | cpsDecl         => "cpsDecl"
   | repDecl         => "repDecl"
   | paramList       => "paramList"
   | auxParamList    => "auxParamList"
   | repParam        => "repParam"
   | param           => "param"
   | auxGlobImpList  => "auxGlobImpList"
   | globImpList     => "globImpList"
   | repGlobImp      => "repGlobImp"
   | globImp         => "globImp"
   | cmd             => "cmd"
   | blockCmd        => "blockCmd"
   | auxGlobInitList => "auxGlobInitList"
   | globInitList    => "globInitList"
   | repCmd          => "repCmd"
   | repIdent        => "repIdendt"
   | expr            => "expr"
   | repTerm1        => "repTerm1"
   | term1           => "term1"
   | repTerm2        => "repTerm2"
   | term2           => "term2"
   | repTerm3        => "repTerm3"
   | term3           => "term3"
   | repFactor       => "repFactor"
   | factor          => "factor"
   | auxIdent       => "auxIdent"
   | exprList        => "exprList"
   | auxExprList     => "auxExprList"
   | repExpr         => "repExpr"
   | auxExprSemi     => "auxExprSemi"
   | monadicOpr      => "monadicOpr"
   | auxMechMode     => "auxMechMode"
   | auxChangeMode   => "auxChangeMode"
   | auxFlowMode     => "auxFlowMode"

val string_of_gramsym = (string_of_term, string_of_nonterm)

local
  open FixFoxi.FixFoxiCore
in

val productions =
[
(*
    expr   ::= term3 (ADDOPR term3)*
    term3  ::= factor (MULTOPR factor)*
    factor ::= IDENT
            |  LPAREN expr RPAREN
*)
(program,
    [[T PROGRAM, T IDENT, N auxGlobCpsDecl, N blockCmd]]),
(decl, 
    [[N storeDecl],
	 [N funDecl],
	 [N procDecl]]),
(storeDecl,
	[[N auxChangeMode, T IDENT, T COLON, T TYPE]]),
(funDecl, 
	[[T FUN, T IDENT, N paramList, T RETURNS, N storeDecl, 
		N auxGlobImpList, N auxLocCpsDecl, N blockCmd]]),
(procDecl,
    [[T PROC, T IDENT, N paramList,  N auxGlobImpList, 
	N auxLocCpsDecl, N blockCmd]]),
(auxGlobCpsDecl,
    [[T GLOBAL, N cpsDecl],
	 []]),
(auxLocCpsDecl,
    [[T LOCAL, N cpsDecl],
	 []]),
(cpsDecl,
    [[N decl, N repDecl]]),
(repDecl,
    [[T COMMA, N decl, N repDecl],
	 []]),
(paramList,
    [[T LPAREN, N auxParamList, T RPAREN]]),
(auxParamList,
    [[N param, N repParam],
	 []]),
(repParam,
    [[T COMMA, N param, N repParam],
	 []]), 
(param,
    [[N auxFlowMode, N auxMechMode, N storeDecl]]),
(auxGlobImpList,
    [[T GLOBAL, N globImpList],
	 []]),
(globImpList,
    [[N globImp, N repGlobImp]]),
(repGlobImp,
    [[T COMMA, N globImp, N repGlobImp],
	 []]),
(globImp,
    [[N auxFlowMode, N auxChangeMode, T IDENT]]),
(cmd,
    [[T SKIP],
	 [N expr, T BECOMES, N expr],
	 [T CALL, T IDENT,N exprList,N auxGlobInitList],
	 [T IF, T LPAREN, N expr, T RPAREN, N blockCmd, 
		T ELSE, N blockCmd],
	 [T WHILE, T LPAREN, N expr, T RPAREN, N blockCmd],
	 [T FOR, T LPAREN, T IDENT, T COLON, T TYPE, 
		T SEMICOLON, T IDENT, T INIT, T BECOMES, N expr, 
		T SEMICOLON, N expr, T SEMICOLON, T IDENT, T BECOMES, 
		N expr, T SEMICOLON, T INVAR, N expr, T RPAREN, N blockCmd],
	 [T QUESTMARK, N expr],
	 [T EXCLMARK, N expr]]),
(blockCmd,
    [[T LBRACE, N cmd, N repCmd, T RBRACE]]),
(repCmd,
    [[T SEMICOLON, N cmd, N repCmd],
	 []]),
(auxGlobInitList,
    [[T INIT, N globInitList],
	 []]),
(globInitList,
    [[T IDENT, N repIdent]]),
(repIdent,
    [[T COMMA, T IDENT, N repIdent],
	 []]),
(expr,
	[[N term1, N repTerm1]]),
(repTerm1,
    [[T BOOLOPR, N term1, N repTerm1],
	 []]),
(term1,
    [[N term2, N repTerm2]]),
(repTerm2,
    [[T RELOPR, N term2],
	 []]),
(term2,
    [[N term3, N repTerm3]]),
(repTerm3,
    [[T ADDOPR, N term3, N repTerm3],
	 []]),
(term3,
    [[N factor, N repFactor]]),
(repFactor,
    [[T MULTOPR, N factor, N repFactor],
	 []]),
(factor,
    [[T LITERAL],
	 [T IDENT, N auxIdent],
	 [N monadicOpr, N factor],
	 [T LPAREN, N expr, T RPAREN]]),
(auxIdent,
    [[T INIT],
	 [N exprList],
	 []]),
(exprList, 
    [[T LPAREN, N auxExprList, T RPAREN]]),
(auxExprList,
    [[N expr, N repExpr],
	 []]),
(repExpr,
    [[T COMMA, N expr, N repExpr],
	 []]),
(auxExprSemi,
    [[T SEMICOLON, N expr],
	 []]),	 
(monadicOpr,
    [[T NOT],
	 [T ADDOPR]]),
(auxMechMode,
    [[T MECHMODE],
	 []]),
(auxChangeMode,
    [[T CHANGEMODE],
	 []]),
(auxFlowMode,
    [[T FLOWMODE],
	 []])
]

val S = program

val result = fix_foxi productions S string_of_gramsym

end (* local *)
