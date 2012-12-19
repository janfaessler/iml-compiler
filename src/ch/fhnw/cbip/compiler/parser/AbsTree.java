package ch.fhnw.cbip.compiler.parser;

import ch.fhnw.cbip.compiler.scanner.token.*;
import ch.fhnw.cbip.compiler.scanner.token.Mode.*;

public interface AbsTree {
	
	public class Program {
		private final Ident ident;
		private final Decl decl;
		private final Cmd cmd;
		
		public Program(Ident ident, Decl decl, Cmd cmd) {
			this.ident = ident;
			this.decl = decl;
			this.cmd = cmd;
		}
		
		public String toString(final String indent) {
			return indent
					+ "<Program>\n"
					+ ident.toString(indent + '\t')
					+ decl.toString(indent + '\t')
					+ cmd.toString(indent + '\t')
					+ indent
					+ "</Program>\n";
		}
	}
	
	public class Decl {
		protected final Decl nextDecl;
		
		public Decl(Decl next) {
			this.nextDecl = next;
		}
		
		public String toString(final String indent) {
			return indent
					+ "<Decl>\n"
					+ indent
					+ (nextDecl != null?nextDecl.toString(indent + '\t'):"<noNextElement/>\n")
					+ indent
					+ "</Decl>\n";
		}
	}
	
	public class DeclFun extends Decl {
		private final Ident ident;
		private final Param param;
		private final DeclStore returnDecl;
		private final GlobImp globImp;
		private final Decl cpsDecl;
		private final Cmd cmd;
		
		public DeclFun(Ident ident, Param param, DeclStore returnDecl, GlobImp globImp, Decl cpsDecl, Cmd cmd, Decl nextDecl) {
			super(nextDecl);
			this.ident = ident;
			this.param = param;
			this.returnDecl = returnDecl;
			this.globImp = globImp;
			this.cpsDecl = cpsDecl;
			this.cmd = cmd;
		}
		
		public String toString(String indent) {
			return indent
					+ "<DeclFun>\n"
					+ ident.toString(indent + '\t')
					+ param.toString(indent + '\t')
					+ returnDecl.toString(indent + '\t')
					+ globImp.toString(indent + '\t')
					+ cpsDecl.toString(indent + '\t')
					+ cmd.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</DeclFun>\n";
		}
	}
	
	public class DeclProc extends Decl {
		private final Ident ident;
		private final Param param;
		private final GlobImp globImp;
		private final Decl decl;
		private final Cmd cmd;

		public DeclProc(Ident ident, Param param, GlobImp globImp, Decl cpsDecl, Cmd cmd, Decl nextDecl) {
			super(nextDecl);
			this.ident = ident;
			this.param = param;
			this.globImp = globImp;
			this.decl = cpsDecl;
			this.cmd = cmd;
		}

		public String toString(final String indent) {
			return indent
					+ "<DeclProc>\n"
					+ ident.toString(indent + '\t')
					+ param.toString(indent + '\t')
					+ globImp.toString(indent + '\t')
					+ decl.toString(indent + '\t')
					+ cmd.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</DeclProc>\n";
		}
	}
	
	public class DeclStore extends Decl {
		private final ChangeMode changeMode;
		private final Ident ident;
		private final Type type;

		public DeclStore(ChangeMode changeMode, Ident ident, Type type, Decl nextDecl) {
			super(nextDecl);
			this.changeMode = changeMode;
			this.ident = ident;
			this.type = type;
		}

		public String toString(final String indent) {
			return indent
					+ "<DeclStore>\n"
					+ changeMode.toString(indent + '\t')
					+ ident.toString(indent + '\t')
					+ type.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</DeclStore>\n";
		}
	}
	
	public class Param {
		private final FlowMode flowMode;
		private final MechMode mechMode;
		private final DeclStore storeDecl;
		private final Param nextParam;

		public Param(FlowMode flowMode, MechMode mechMode, DeclStore storeDecl, Param nextParam) {
			this.flowMode = flowMode;
			this.mechMode = mechMode;
			this.storeDecl = storeDecl;
			this.nextParam = nextParam;
		}

		public String toString(String indent) {
			return indent
					+ "<Param>\n"
					+ flowMode.toString(indent + '\t')
					+ mechMode.toString(indent + '\t')
					+ storeDecl.toString(indent + '\t')
					+ nextParam.toString(indent + '\t')
					+ indent
					+ "</Param>\n";
		}
	}
	
	public class GlobImp {
		private final FlowMode flowMode;
		private final ChangeMode changeMode;
		private final Ident ident;
		private final GlobImp nextGlobImp;

		public GlobImp(FlowMode flowMode, ChangeMode changeMode, Ident ident, GlobImp nextGlobImp) {
			this.flowMode = flowMode;
			this.changeMode = changeMode;
			this.ident = ident;
			this.nextGlobImp = nextGlobImp;
		}

		public String toString(final String indent) {
			return indent
					+ "<GlobImp>\n"
					+ flowMode.toString(indent + '\t')
					+ changeMode.toString(indent + '\t')
					+ ident.toString(indent + '\t')
					+ nextGlobImp.toString(indent + '\t')
					+ indent
					+ "</GlobImp>\n";
		}
	}
	
	
	public class Cmd {
		private final Cmd nextCmd;
		public Cmd(Cmd nextCmd) {
			this.nextCmd = nextCmd;
		}
	    public String toString(final String indent) {
			return indent
					+ "<Cmd>\n"
					+ indent
					+ (nextCmd != null?nextCmd.toString(indent + '\t'):"<noNextElement/>\n")
					+ indent
					+ "</Cmd>\n";
		}
	}
	
	public class CmdSkip extends Cmd {

	    public CmdSkip(Cmd nextCmd) {
			super(nextCmd);
		}

		public String toString(final String indent) {
			return indent
					+ "<CmdSkip>\n"
					+ super.toString(indent + '\t')
					+ indent
					+ "</CmdSkip>\n";
		}
	}
	
	public class CmdAssi extends Cmd {
		private final Expr targetExpr;
		private final Expr sourceExpr;

		public CmdAssi(Expr targetExpr, Expr sourceExpr, Cmd nextCmd) {
			super(nextCmd);
			this.targetExpr = targetExpr;
			this.sourceExpr = sourceExpr;
		}

	    public String toString(final String indent) {
			return indent
					+ "<CmdAssi>\n"
					+ targetExpr.toString(indent + '\t')
					+ sourceExpr.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</CmdAssi>\n";
		}
	}
	
	public class CmdCond extends Cmd {
		private final Expr expr;
		private final Cmd ifCmd;
		private final Cmd elseCmd;

		public CmdCond(Expr expr, Cmd ifCmd, Cmd elseCmd, Cmd nextCmd) {
			super(nextCmd);
			this.expr = expr;
			this.ifCmd = ifCmd;
			this.elseCmd = elseCmd;
		}

	    public String toString(String indent) {
			return indent
					+ "<CmdCond>\n"
					+ expr.toString(indent + '\t')
					+ ifCmd.toString(indent + '\t')
					+ elseCmd.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</CmdCond>\n";
		}
	}
	
	public class CmdWhile extends Cmd {
		private final Expr expr;
		private final Cmd cmd;

		public CmdWhile(Expr expr, Cmd cmd, Cmd nextCmd) {
			super(nextCmd);
			this.expr = expr;
			this.cmd = cmd;
		}

	    public String toString(final String indent) {
			return indent
					+ "<CmdWhile>\n"
					+ expr.toString(indent + '\t')
					+ cmd.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</CmdWhile>\n";
		}
	}
	
	public class CmdProcCall extends Cmd {
		
		private final RoutineCall routineCall;
		private final GlobInit globInit;

		public CmdProcCall(RoutineCall routineCall, GlobInit globInit, Cmd nextCmd) {
			super(nextCmd);
			this.routineCall = routineCall;
			this.globInit = globInit;
		}

		public String toString(final String indent) {
			return indent
					+ "<ExprCall>\n"
					+ routineCall.toString(indent + '\t')
					+ globInit.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</ExprCall>\n";
		}
	}
	
	public class CmdInput extends Cmd {
		private final Expr expr;

		public CmdInput(Expr expr, Cmd nextCmd) {
			super(nextCmd);
			this.expr = expr;
		}

	    public String toString(String indent) {
			return indent
					+ "<CmdInput>\n"
					+ expr.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</CmdInput>\n";
		}
	}
	
	public class CmdOutput extends Cmd {
		private final Expr expr;

		public CmdOutput(Expr expr, Cmd nextCmd) {
			super(nextCmd);
			this.expr = expr;
		}

	    public String toString(String indent) {
			return indent
					+ "<CmdOutput>\n"
					+ expr.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</CmdOutput>\n";
		}
	}
	
	public class Expr {
		public String toString(String indent) { return ""; }
	}
	
	public class ExprLiteral extends Expr {
		private final Literal literal;

		public ExprLiteral(Literal literal) {
			this.literal = literal;
		}

		public String toString(String indent) {
			return indent
					+ "<ExprLiteral>\n"
					+ literal.toString(indent + '\t')
					+ indent
					+ "</ExprLiteral>\n";
		}
	}
	
	public class ExprStore extends Expr {
		private final Ident ident;
		private final boolean isInit;

		public ExprStore(Ident ident, boolean isInit) {
			this.ident = ident;
			this.isInit = isInit;
		}

		public String toString(String indent) {
			return indent
					+ "<ExprStore>\n"
					+ ident.toString(indent + '\t')
					+ indent
					+ "<IsInit>" + isInit + "</IsInit>\n"
					+ indent
					+ "</ExprStore>\n";
		}
	}
	
	public class ExprFunCall extends Expr {
		private final RoutineCall routineCall;

		public ExprFunCall(RoutineCall routineCall) {
			this.routineCall = routineCall;
		}

		public String toString(String indent) {
			return indent
					+ "<ExprCall>\n"
					+ routineCall.toString(indent + '\t')
					+ super.toString(indent + '\t')
					+ indent
					+ "</ExprCall>\n";
		}
	}
	
	public class ExprMonadic extends Expr {
		private final Operator operator;
		private final Expr expr;

		public ExprMonadic(Operator operator,Expr expr) {
			this.operator = operator;
			this.expr = expr;
		}

		public String toString(String indent) {
			return indent
					+ "<ExprMonadic>\n"
					+ operator.toString(indent + '\t')
					+ expr.toString(indent + '\t')
					+ indent
					+ "</ExprMonadic>\n";
		}
	}
	
	public final class ExprDyadic extends Expr {
		private final Operator operator;
		private final Expr expr1;
		private final Expr expr2;

		public ExprDyadic(Operator operator, Expr expr1, Expr expr2) {
			this.operator = operator;
			this.expr1 = expr1;
			this.expr2 = expr2;
		}

		public String toString(String indent) {
			return indent
					+ "<ExprDyadic>\n"
					+ operator.toString(indent + '\t')
					+ expr1.toString(indent + '\t')
					+ expr2.toString(indent + '\t')
					+ indent
					+ "</ExprDyadic>\n";
		}
	}
	
	public class RoutineCall {
		private final Ident ident;
		private final ExprList exprList;
		
		public RoutineCall(Ident ident, ExprList exprList) {
			this.ident = ident;
			this.exprList = exprList;
		}
		
		public String toString(String indent) {
			return indent
					+ "<RoutineCall>\n"
					+ ident.toString(indent + '\t')
					+ exprList.toString(indent + '\t')
					+ indent
					+ "</RoutineCall>\n";
		}
		
	}
	
	public class ExprList {
		private final Expr expr;
		private final ExprList exprList;

		public ExprList(Expr expr, ExprList exprList) {
			this.expr = expr;
			this.exprList = exprList;
		}

		public String toString(String indent) {
			return indent
					+ "<ExprList>\n"
					+ expr.toString(indent + '\t')
					+ exprList.toString(indent + '\t')
					+ indent
					+ "</ExprList>\n";
		}
	}
	
	public final class GlobInit {
		private final Ident ident;
		private final GlobInit globInit;

		public GlobInit(Ident ident, GlobInit globInit) {
			this.ident = ident;
			this.globInit = globInit;
		}

		public String toString(String indent) {
			return indent
					+ "<GlobInit>\n"
					+ ident.toString(indent + '\t')
					+ indent
					+ (globInit != null?globInit.toString(indent + '\t'):"<noNextElement/>\n")
					+ indent
					+ "</GlobInit>\n";
		}
	}

}
