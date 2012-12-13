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
					+ nextDecl.toString(indent + '\t')
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
					+ nextCmd.toString(indent + '\t')
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

		public CmdProcCall(Cmd nextCmd) {
			super(nextCmd);
		}
		// TODO: CmdProcCall
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
		// TODO: Expressions
	}

}
