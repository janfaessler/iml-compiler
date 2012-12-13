package ch.fhnw.cbip.compiler.parser;

import ch.fhnw.cbip.compiler.scanner.token.*;
import ch.fhnw.cbip.compiler.scanner.token.Mode.*;
import ch.fhnw.cbip.compiler.scanner.token.Operator.*;

interface ConcSyn {
	
	public class Program {
		private final Ident ident;
		private final AuxGlobCpsDecl auxGlobCpsDecl;
		private final BlockCmd blockCmd;

		public Program(Ident ident, AuxGlobCpsDecl auxGlobCpsDecl, BlockCmd blockCmd) {
			this.ident = ident;
			this.blockCmd = blockCmd;
			this.auxGlobCpsDecl = auxGlobCpsDecl;
		}
		
		public String toString() { return toString(""); }

		public String toString(String indent) {
			return indent
					+ "<Program>\n"
					+ ident.toString(indent + '\t') 
					+ auxGlobCpsDecl.toString(indent + '\t')
					+ blockCmd.toString(indent + '\t')
					+ indent
					+ "</Program>\n";
		}
	}
	
	public class AuxGlobCpsDecl  {
		private final CpsDecl cpsDecl;

		public AuxGlobCpsDecl(CpsDecl cpsDecl) {
			this.cpsDecl = cpsDecl;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxGlobCpsDecl>\n"
					+ cpsDecl.toString(indent + '\t')
					+ indent
					+ "</AuxGlobCpsDecl>\n";
		}
	}

	public class AuxGlobCpsDeclEps extends AuxGlobCpsDecl {

		public AuxGlobCpsDeclEps() {
			super(null);
		}

		public String toString(String indent) {
			return indent + "<AuxGlobCpsDeclEps/>\n";
		}
	}
	
	public class CpsDecl {
		private final Decl decl;
		private final RepDecl repDecl;

		public CpsDecl(final Decl decl, RepDecl repDecl) {
			this.decl = decl;
			this.repDecl = repDecl;
		}

		public String toString(String indent) {
			return indent
					+ "<CpsDecl>\n"
					+ decl.toString(indent + '\t')
					+ repDecl.toString(indent + '\t')
					+ indent
					+ "</CpsDecl>\n";
		}
	}
	
	public abstract class Decl {
		public abstract String toString(String indent);
	}
	
	public class RepDecl {
		private final Decl decl;
		private final RepDecl repDecl;

		public RepDecl(Decl decl, RepDecl repDecl) {
			this.decl = decl;
			this.repDecl = repDecl;
		}

		public String toString(String indent) {
			return indent
					+ "<RepDecl>\n"
					+ decl.toString(indent + '\t')
					+ repDecl.toString(indent + '\t')
					+ indent
					+ "</RepDecl>\n";
		}
	}
	
	public class RepDeclEps extends RepDecl {

		public RepDeclEps() {
			super(null, null);
		}

		public String toString(String indent) {
			return indent + "<RepDeclEps/>\n";
		}
	}
	
	public class StoreDecl extends Decl {
		private final AuxChangeMode auxChangeMode;
		private final Ident ident;
		private final Type type;

		public StoreDecl(AuxChangeMode auxChangeMode, Ident ident, Type type) {
			this.auxChangeMode = auxChangeMode;
			this.ident = ident;
			this.type = type;
		}

		public String toString(String indent) {
			return indent
					+ "<StoreDecl>\n"
					+ auxChangeMode.toString(indent + '\t')
					+ ident.toString(indent + '\t')
					+ type.toString(indent + '\t')
					+ indent
					+ "</StoreDecl>\n";
		}
	}
	public class FunDecl extends Decl {
		private final Ident ident;
		private final ParamList paramList;
		private final StoreDecl storeDecl;
		private final AuxGlobImpList auxGlobImpList;
		private final AuxLocCpsDecl auxLocCpsDecl;
		private final BlockCmd blockCmd;

		public FunDecl(Ident ident, ParamList paramList, StoreDecl storeDecl, AuxGlobImpList auxGlobImpList, AuxLocCpsDecl auxLocCpsDecl, BlockCmd blockCmd) {
			this.ident = ident;
			this.paramList = paramList;
			this.storeDecl = storeDecl;
			this.auxGlobImpList = auxGlobImpList;
			this.auxLocCpsDecl = auxLocCpsDecl;
			this.blockCmd = blockCmd;
		}

		public String toString(String indent) {
			return indent
					+ "<FunDecl>\n"
					+ ident.toString(indent + '\t')
					+ paramList.toString(indent + '\t')
					+ storeDecl.toString(indent + '\t')
					+ auxGlobImpList.toString(indent + '\t')
					+ auxLocCpsDecl.toString(indent + '\t')
					+ blockCmd.toString(indent + '\t')
					+ indent
					+ "</FunDecl>\n";
		}
	}
	
	public class ProcDecl extends Decl {
		private final Ident ident;
		private final ParamList paramList;
		private final AuxGlobImpList auxGlobImpList;
		private final AuxLocCpsDecl auxLocCpsDecl;
		private final BlockCmd blockCmd;

		public ProcDecl(Ident ident, ParamList paramList, AuxGlobImpList auxGlobImpList, AuxLocCpsDecl auxLocCpsDecl, BlockCmd blockCmd) {
			this.ident = ident;
			this.paramList = paramList;
			this.auxGlobImpList = auxGlobImpList;
			this.auxLocCpsDecl = auxLocCpsDecl;
			this.blockCmd = blockCmd;
		}

		public String toString(String indent) {
			return indent
					+ "<ProcDecl>\n"
					+ ident.toString(indent + '\t') 
					+ paramList.toString(indent + '\t')
					+ auxGlobImpList.toString(indent + '\t')
					+ auxLocCpsDecl.toString(indent + '\t')
					+ blockCmd.toString(indent + '\t')
					+ indent
					+ "</ProcDecl>\n";
		}
	}
	
	public class ParamList {
		private final AuxParamList auxParamList;

		public ParamList(AuxParamList auxParamList) {
			this.auxParamList = auxParamList;
		}

		public String toString(String indent) {
			return indent
					+ "<ParamList>\n"
					+ auxParamList.toString(indent + '\t')
					+ indent
					+ "</ParamList>\n";
		}
	}
	public class AuxParamList {
		private final Param param;
		private final RepParam repParam;

		public AuxParamList(Param param, RepParam repParam) {
			this.param = param;
			this.repParam = repParam;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxParamList>\n"
					+ param.toString(indent + '\t')
					+ repParam.toString(indent + '\t')
					+ indent
					+ "</AuxParamList>\n";
		}
	}
	
	public class  AuxParamListEps extends AuxParamList {
		public AuxParamListEps() {
			super(null, null);
		}
		public String toString(String indent) {
			return indent + "<AuxParamListEps/>\n";
		}
	}
	
	public class Param {
		private final AuxFlowMode auxFlowMode;
		private final AuxMechMode auxMechMode;
		private final StoreDecl storeDecl;

		public Param(AuxFlowMode auxFlowMode, AuxMechMode auxMechMode, StoreDecl storeDecl) {
			this.auxFlowMode = auxFlowMode;
			this.auxMechMode = auxMechMode;
			this.storeDecl = storeDecl;
		}

		public String toString(String indent) {
			return indent
					+ "<Param>\n"
					+ auxFlowMode.toString(indent + '\t')
					+ auxMechMode.toString(indent + '\t')
					+ storeDecl.toString(indent + '\t')
					+ indent
					+ "</Param>\n";
		}
	}
	
	public class RepParam {
		private final Param param;
		private final RepParam repParam;

		public RepParam(Param param, RepParam repParam) {
			this.param = param;
			this.repParam = repParam;
		}

		public String toString(String indent) {
			return indent
					+ "<RepParam>\n"
					+ param.toString(indent + '\t')
					+ repParam.toString(indent + '\t')
					+ indent
					+ "</RepParam>\n";
		}
	}
	
	public class RepParamEps extends RepParam {
		public RepParamEps() {
			super(null,null);
		}
		public String toString(String indent) {
			return indent + "<RepParamEps/>\n";
		}
	}
	
	public class RepIdent {
		private final Ident ident;
		private final RepIdent repIdent;

		public RepIdent(Ident ident, RepIdent repIdent) {
			this.ident = ident;
			this.repIdent = repIdent;
		}

		public String toString(String indent) {
			return indent
					+ "<RepIdent>\n"
					+ ident.toString(indent + '\t')
					+ repIdent.toString(indent + '\t')
					+ indent
					+ "</RepIdent>\n";
		}
	}
	
	public class RepIdentEps extends RepIdent {
		public RepIdentEps() {
			super(null,null);
		}
		public String toString(String indent) {
			return indent + "<RepIdentEps/>\n";
		}
	}
	
	public class AuxGlobImpList {
		private final GlobImpList globImpList;

		public AuxGlobImpList(GlobImpList globImpList) {
			this.globImpList = globImpList;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxGlobImpList>\n"
					+ globImpList.toString(indent + '\t')
					+ indent
					+ "</AuxGlobImpList>\n";
		}
	}
	
	public class AuxGlobImpListEps extends AuxGlobImpList {
		
		public AuxGlobImpListEps() {
			super(null);
		}
		
		public String toString(String indent) {
			return indent + "<AuxGlobImpListEps/>\n";
		}
	}
	
	public class GlobImpList {
		private final GlobImp globImp;
		private final RepGlobImp repGlobImp;

		public GlobImpList(GlobImp globImp, RepGlobImp repGlobImp) {
			this.globImp = globImp;
			this.repGlobImp = repGlobImp;
		}

		public String toString(String indent) {
			return indent
					+ "<GlobImpList>\n"
					+ globImp.toString(indent + '\t')
					+ repGlobImp.toString(indent + '\t')
					+ indent
					+ "</GlobImpList>\n";
		}
	}
	
	public class GlobImp {
		private final AuxFlowMode auxFlowMode;
		private final AuxChangeMode auxChangeMode;
		private final Ident ident;

		public GlobImp(AuxFlowMode auxFlowMode, AuxChangeMode auxChangeMode, Ident ident) {
			this.auxFlowMode = auxFlowMode;
			this.auxChangeMode = auxChangeMode;
			this.ident = ident;
		}

		public String toString(String indent) {
			return indent
					+ "<GlobImp>\n"
					+ auxFlowMode.toString(indent + '\t')
					+ auxChangeMode.toString(indent + '\t')
					+ ident.toString(indent + '\t')
					+ indent
					+ "</GlobImp>\n";
		}
	}
	
	public class RepGlobImp {
		private final GlobImp globImp;
		private final RepGlobImp repGlobImp;

		public RepGlobImp(GlobImp globImp, RepGlobImp repGlobImp) {
			this.globImp = globImp;
			this.repGlobImp = repGlobImp;
		}

		public String toString(String indent) {
			return indent
					+ "<RepGlobImp>\n"
					+ globImp.toString(indent + '\t')
					+ repGlobImp.toString(indent + '\t')
					+ indent
					+ "</RepGlobImp>\n";
		}
	}
	
	public class RepGlobImpEps extends RepGlobImp {
		public RepGlobImpEps() {
			super(null, null);
		}

		public String toString(String indent) {
			return indent + "<RepGlobImpEps/>\n";
		}
	}
	
	public class AuxChangeMode {
		private final ChangeMode changeMode;
		public AuxChangeMode(ChangeMode changeMode) {
			this.changeMode = changeMode;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxChangeMode>\n"
					+ changeMode.toString(indent + '\t')
					+ indent
					+ "</AuxChangeMode>\n";
		}
	}
	
	public class AuxChangeModeEps extends AuxChangeMode {
		public AuxChangeModeEps() {
			super(null);
		}
		public String toString(String indent) {
			return indent
					+ "<AuxChangeModeEps/>\n";
		}
	}
	
	public class AuxLocCpsDecl {
		private final CpsDecl cpsDecl;
		public AuxLocCpsDecl(CpsDecl cpsDecl) {
			this.cpsDecl = cpsDecl;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxLocCpsDecl>\n"
					+ cpsDecl.toString(indent + '\t')
					+ indent
					+ "</AuxLocCpsDecl>\n";
		}
	}
	
	public class AuxLocCpsDeclEps extends AuxLocCpsDecl {
		public AuxLocCpsDeclEps() {
			super(null);
		}
		public String toString(String indent) {
			return indent + "<AuxLocCpsDeclEps/>\n";
		}
	}
	
	public class AuxFlowMode {
		private final FlowMode flowMode;

		public AuxFlowMode(FlowMode flowMode) {
			this.flowMode = flowMode;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxFlowMode>\n"
					+ flowMode.toString(indent + '\t') 
					+ indent
					+ "</AuxFlowMode>\n";
		}
	}
	
	public class AuxFlowModeEps extends AuxFlowMode {

		public AuxFlowModeEps() {
			super(null);
		}

		public String toString(String indent) {
			return indent + "<AuxFlowModeEps/>\n";
		}
	}
	
	public class AuxMechMode {
		private final MechMode mechMode;

		public AuxMechMode(MechMode mechMode) {
			this.mechMode = mechMode;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxMechMode>\n"
					+ mechMode.toString(indent + '\t') 
					+ indent
					+ "</AuxMechMode>\n";
		}
	}
	
	public class AuxMechModeEps extends AuxMechMode {
		public AuxMechModeEps() {
			super(null);
		}
		public String toString(String indent) {
			return indent + "<AuxMechModeEps/>\n";
		}
	}
	
	public class BlockCmd {
		private final Cmd cmd;
		private final RepCmd repCmd;

		public BlockCmd(Cmd cmd, RepCmd repCmd) {
			this.cmd = cmd;
			this.repCmd = repCmd;
		}

		public String toString(String indent) {
			return indent
					+ "<BlockCmd>\n"
					+ cmd.toString(indent + '\t')
					+ repCmd.toString(indent + '\t')
					+ indent
					+ "</BlockCmd>\n";
		}
	}
	
	public abstract class Cmd {
		public abstract String toString(String indent);
	}
	
	public class RepCmd {
		private final Cmd cmd;
		private final RepCmd repCmd;

		public RepCmd(Cmd cmd, RepCmd repCmd) {
			this.cmd = cmd;
			this.repCmd = repCmd;
		}

		public String toString(String indent) {
			return indent
					+ "<RepCmd>\n"
					+ cmd.toString(indent + '\t')
					+ repCmd.toString(indent + '\t')
					+ indent
					+ "</RepCmd>\n";
		}
	}
	
	public class RepCmdEps extends RepCmd {
		
		public RepCmdEps() {
			super(null, null);
		}
		
		public String toString(String indent) {
			return indent + "<RepCmdEps/>\n";
		}
		
	}
	
	public class CmdSkip extends Cmd {

		public String toString(String indent) {
			return indent + "<CmdSkip/>\n";
		}
	}
	
	public class CmdExpr extends Cmd{
		private final Expr expr;
		private final AuxExprCmd auxExprCmd;

		public CmdExpr(Expr expr, AuxExprCmd auxExprCmd) {
			this.expr = expr;
			this.auxExprCmd = auxExprCmd;
		}

		public String toString(String indent) {
			return indent
					+ "<CmdExpr>\n"
					+ expr.toString(indent + '\t')
					+ auxExprCmd.toString(indent + '\t')
					+ indent
					+ "</CmdExpr>\n";
		}
	}
	
	public class CmdCall extends Cmd {
		private final Ident ident;
		private final ExprList exprList;
		private final AuxGlobInitList auxGlobInitList;

		public CmdCall(Ident ident, ExprList exprList, AuxGlobInitList auxGlobInitList) {
			this.ident = ident;
			this.exprList = exprList;
			this.auxGlobInitList = auxGlobInitList;
		}

		public String toString(String indent) {
			return indent
					+ "<CmdCall>\n"
					+ ident.toString(indent + '\t')
					+ exprList.toString(indent + '\t')
					+ auxGlobInitList.toString(indent + '\t')
					+ indent
					+ "</CmdCall>\n";
		}
	}
	
	public class CmdWhile extends Cmd {
		private final Expr expr;
		private final BlockCmd blockCmd;

		public CmdWhile(Expr expr, BlockCmd blockCmd) {
			this.expr = expr;
			this.blockCmd = blockCmd;
		}

		public String toString(final String indent) {
			return indent
					+ "<CmdWhile>\n"
					+ expr.toString(indent + '\t')
					+ blockCmd.toString(indent + '\t')
					+ indent
					+ "</CmdWhile>\n";
		}
	}
	
	public class CmdIf extends Cmd {
		private final Expr expr;
		private final BlockCmd ifCmd;
		private final BlockCmd elseCmd;

		public CmdIf(Expr expr, BlockCmd ifCmd, BlockCmd elseCmd) {
			this.expr = expr;
			this.ifCmd = ifCmd;
			this.elseCmd = elseCmd;
		}

	    public String toString(String indent) {
			return indent
					+ "<CmdIf>\n"
					+ expr.toString(indent + '\t')
					+ ifCmd.toString(indent + '\t')
					+ elseCmd.toString(indent + '\t')
					+ indent
					+ "</CmdIf>\n";
		}
	}
	
	public class CmdQuest extends Cmd {
		private final Expr expr;

		public CmdQuest(Expr expr) {
			this.expr = expr;
		}

		public String toString(String indent) {
			return indent
					+ "<CmdQuest>\n"
					+ expr.toString(indent + '\t')
					+ indent
					+ "</CmdQuest>\n";
		}
	}
	
	public class CmdExcl extends Cmd {
		private final Expr expr;

		public CmdExcl(Expr expr) {
			this.expr = expr;
		}

		public String toString(String indent) {
			return indent
					+ "<CmdExcl>\n"
					+ expr.toString(indent + '\t')
					+ indent
					+ "</CmdExcl>\n";
		}
	}
	
	public class AuxGlobInitList {
		private final GlobInitList globInitList;

		public AuxGlobInitList(GlobInitList globInitList) {
			this.globInitList = globInitList;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxGlobInitList>\n"
					+ globInitList.toString(indent + '\t')
					+ indent
					+ "</AuxGlobInitList>\n";
		}
	}
	
	public class AuxGlobInitListEps extends AuxGlobInitList {
		
		public AuxGlobInitListEps() {
			super(null);
		}
		
		public String toString(String indent) {
			return indent + "<AuxGlobInitListEps/>\n";
		}
	}
	
	public class GlobInitList {
		private final Ident ident;
		private final RepIdent repIdent;

		public GlobInitList(Ident ident, RepIdent repIdent) {
			this.ident = ident;
			this.repIdent = repIdent;
		}

		public String toString(String indent) {
			return indent
					+ "<GlobInitList>\n"
					+ ident.toString(indent + '\t')
					+ repIdent.toString(indent + '\t')
					+ indent
					+ "</GlobInitList>\n";
		}
	}
	
	public class ExprList {
		private final AuxExprList auxExprList;

		public ExprList(AuxExprList auxExprList) {
			this.auxExprList = auxExprList;
		}

		public String toString(String indent) {
			return indent
					+ "<ExprList>\n"
					+ auxExprList.toString(indent + '\t')
					+ indent
					+ "</ExprList>\n";
		}
	}
	
	public class AuxExprList  {
		private final Expr expr;
		private final RepExpr repExpr;

		public AuxExprList(Expr expr, RepExpr repExpr) {
			this.expr = expr;
			this.repExpr = repExpr;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxExprList>\n"
					+ expr.toString(indent + '\t')
					+ repExpr.toString(indent + '\t')
					+ indent
					+ "</AuxExprList>\n";
		}
	}
	
	public class AuxExprListEps extends AuxExprList {
		public AuxExprListEps() {
			super(null, null);
		}
		public String toString(String indent) {
			return indent + "<AuxExprListEps/>\n";
		}
	}
	
	public abstract  class AuxExprCmd {
		public abstract String toString(String indent);
	}
	
	public class AuxExprCmdBecomes extends AuxExprCmd {
		private final Expr expr;

		public AuxExprCmdBecomes(Expr expr) {
			this.expr = expr;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxExprCmdBecomes>\n"
					+ expr.toString(indent + '\t')
					+ indent
					+ "</AuxExprCmdBecomes>\n";
		}
	}
	
	public class AuxExprCmdEps extends AuxExprCmd {
		public String toString(String indent) {
			return indent + "<AuxExprCmdEps/>\n";
		}
	}
	
	public class Expr {
		private final Term1 term1;
		private final RepTerm1 repTerm1;

		public Expr(Term1 term1, RepTerm1 repTerm1) {
			this.term1 = term1;
			this.repTerm1 = repTerm1;
		}

		public String toString(String indent) {
			return indent
					+ "<Expr>\n"
					+ term1.toString(indent + '\t')
					+ repTerm1.toString(indent + '\t')
					+ indent
					+ "</Expr>\n";
		}
	}
	
	public class RepExpr {
		private final Expr expr;
		private final RepExpr repExpr;

		public RepExpr(Expr expr, RepExpr repExpr) {
			this.expr = expr;
			this.repExpr = repExpr;
		}

		public String toString(String indent) {
			return indent
					+ "<RepExpr>\n"
					+ expr.toString(indent + '\t')
					+ repExpr.toString(indent + '\t')
					+ indent
					+ "</RepExpr>\n";
		}
	}
	
	public class RepExprEps extends RepExpr {
		public RepExprEps() {
			super(null, null);
		}
		public String toString(String indent) {
			return indent + "<RepExprEps/>\n";
		}
	}
	
	public class Term1 {
		private final Term2 term2;
		private final RepTerm2 repTerm2;

		public Term1(Term2 term2, RepTerm2 repTerm2) {
			this.term2 = term2;
			this.repTerm2 = repTerm2;
		}

		public String toString(String indent) {
			return indent
					+ "<Term1>\n"
					+ term2.toString(indent + '\t')
					+ repTerm2.toString(indent + '\t')
					+ indent
					+ "</Term1>\n";
		}
	}
	
	public class RepTerm1 {
		private final BoolOpr boolOpr;
		private final Term1 term1;
		private final RepTerm1 repTerm1;

		public RepTerm1(BoolOpr boolOpr, Term1 term1, RepTerm1 repTerm1) {
			this.boolOpr = boolOpr;
			this.term1 = term1;
			this.repTerm1 = repTerm1;
		}

		public String toString(String indent) {
			return indent
					+ "<RepTerm1>\n"
					+ boolOpr.toString(indent + '\t')
					+ term1.toString(indent + '\t')
					+ repTerm1.toString(indent + '\t')
					+ indent
					+ "</RepTerm1>\n";
		}
	}
	
	public class RepTerm1Eps extends RepTerm1 {
		public RepTerm1Eps() {
			super(null, null, null);
		}
		public String toString(String indent) {
			return indent + "<RepTerm1Eps/>\n";
		}
	}
	
	public class Term2 {
		private final Term3 term3;
		private final RepTerm3 repTerm3;

		public Term2(Term3 term3, RepTerm3 repTerm3) {
			this.term3 = term3;
			this.repTerm3 = repTerm3;
		}

		public String toString(String indent) {
			return indent
					+ "<Term2>\n"
					+ term3.toString(indent + '\t')
					+ repTerm3.toString(indent + '\t')
					+ indent
					+ "</Term2>\n";
		}
	}
	
	public class RepTerm2 {
		private final RelOpr relOpr;
		private final Term2 term2;
		private final RepTerm2 repTerm2;

		public RepTerm2(RelOpr relOpr, Term2 term2, RepTerm2 repTerm2) {
			this.relOpr = relOpr;
			this.term2 = term2;
			this.repTerm2 = repTerm2;
		}

		public String toString(String indent) {
			return indent
					+ "<RepTerm2>\n"
					+ relOpr.toString(indent + '\t')
					+ term2.toString(indent + '\t')
					+ repTerm2.toString(indent + '\t')
					+ indent
					+ "</RepTerm2>\n";
		}
	}
	
	public class RepTerm2Eps extends RepTerm2 {
		public RepTerm2Eps() {
			super(null, null, null);
		}
		public String toString(String indent) {
			return indent + "<RepTerm2Eps/>\n";
		}
	}
	
	public class Term3 {
		private final Factor factor;
		private final RepFactor repFactor;

		public Term3(Factor factor, RepFactor repFactor) {
			this.factor = factor;
			this.repFactor = repFactor;
		}

		public String toString(String indent) {
			return indent
					+ "<Term3>\n"
					+ factor.toString(indent + '\t')
					+ repFactor.toString(indent + '\t')
					+ indent
					+ "</Term3>\n";
		}
	}
	
	public class RepTerm3 {
		private final AddOpr addOpr;
		private final Term3 term3;
		private final RepTerm3 repTerm3;

		public RepTerm3(AddOpr addOpr, Term3 term3, RepTerm3 repTerm3) {
			this.addOpr = addOpr;
			this.term3 = term3;
			this.repTerm3 = repTerm3;
		}

		public String toString(String indent) {
			return indent
					+ "<RepTerm3>\n"
					+ addOpr.toString(indent + '\t')
					+ term3.toString(indent + '\t')
					+ repTerm3.toString(indent + '\t')
					+ indent
					+ "</RepTerm3>\n";
		}
	}
	
	public class RepTerm3Eps extends RepTerm3 {
		public RepTerm3Eps() {
			super(null, null, null);
		}
		public String toString(String indent) {
			return indent + "<RepTerm3Eps/>\n";
		}
	}
	
	public abstract class Factor {
		public abstract String toString(String indent);
	}
	
	public class RepFactor {
		private final MultOpr multOpr;
		private final Factor factor;
		private final RepFactor repFactor;

		public RepFactor(MultOpr multOpr, Factor factor, RepFactor repFactor) {
			this.multOpr = multOpr;
			this.factor = factor;
			this.repFactor = repFactor;
		}

		public String toString(String indent) {
			return indent
					+ "<RepFactor>\n"
					+ multOpr.toString(indent + '\t')
					+ factor.toString(indent + '\t')
					+ repFactor.toString(indent + '\t')
					+ indent
					+ "</RepFactor>\n";
		}
	}
	
	public class RepFactorEps extends RepFactor {
		public RepFactorEps() {
			super(null, null, null);
		}
		public String toString(String indent) {
			return indent + "<RepFactorEps/>\n";
		}
	}
	
	public class FactorLiteral extends Factor {
		private final Literal literal;

		public FactorLiteral(Literal literal) {
			this.literal = literal;
		}

		public String toString(String indent) {
			return indent
					+ "<FactorLiteral>\n"
					+ literal.toString(indent + '\t')
					+ indent
					+ "</FactorLiteral>\n";
		}
	}
	
	public class FactorIdent extends Factor {
		private final Ident ident;
		private final AuxIdent auxIdent;

		public FactorIdent(Ident ident, AuxIdent auxIdent) {
			this.ident = ident;
			this.auxIdent = auxIdent;
		}

		public String toString(String indent) {
			return indent
					+ "<FactorIdent>\n"
					+ ident.toString(indent + '\t')
					+ auxIdent.toString(indent + '\t')
					+ indent
					+ "</FactorIdent>\n";
		}
	}
	
	public class FactorMonadicOpr extends Factor {
		private final MonadicOpr monadicOpr;
		private final Factor factor;

		public FactorMonadicOpr(MonadicOpr monadicOpr, Factor factor) {
			this.monadicOpr = monadicOpr;
			this.factor = factor;
		}

		public String toString(String indent) {
			return indent
					+ "<FactorMonadicOpr>\n"
					+ monadicOpr.toString(indent + '\t')
					+ factor.toString(indent + '\t')
					+ indent
					+ "</FactorMonadicOpr>\n";
		}
	}
	
	public class FactorExpr extends Factor {
		private final Expr expr;

		public FactorExpr(Expr expr) {
			this.expr = expr;
		}

		public String toString(String indent) {
			return indent
					+ "<FactorExpr>\n"
					+ expr.toString(indent + '\t')
					+ indent
					+ "</FactorExpr>\n";
		}
	}
	
	public abstract class AuxIdent {
		public abstract String toString(String indent);
	}
	
	public class AuxIdentInit extends AuxIdent {

		public String toString(String indent) {
			return indent + "<AuxIdentInit/>\n";
		}
	}
	
	public class AuxIdentExprList extends AuxIdent {
		private final ExprList exprList;
		private final AuxGlobInitList auxGlobInitList;

		public AuxIdentExprList(ExprList exprList, AuxGlobInitList auxGlobInitList) {
			this.exprList = exprList;
			this.auxGlobInitList = auxGlobInitList;
		}

		public String toString(String indent) {
			return indent
					+ "<AuxIdentExprList>\n"
					+ exprList.toString(indent + '\t')
					+ auxGlobInitList.toString(indent + '\t')
					+ indent
					+ "</AuxIdentExprList>\n";
		}
	}
	
	public class AuxIdentEps extends AuxIdent {

		public String toString(String indent) {
			return indent + "<AuxIdentEps/>\n";
		}
	}
	
	public class MonadicOpr {
		private final Operator operator;

		public MonadicOpr(Operator operator) {
			this.operator = operator;
		}

		public String toString(String indent) {
			return indent
					+ "<MonadicOpr>\n"
					+ operator.toString(indent + '\t')
					+ indent
					+ "</MonadicOpr>\n";
		}
	}
}
