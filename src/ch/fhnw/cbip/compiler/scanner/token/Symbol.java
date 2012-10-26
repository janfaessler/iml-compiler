package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Symbol extends AbstractToken {

	private static final long serialVersionUID = 8396852952140113385L;

	public Symbol(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (o.getClass() == this.getClass()) {
                if (super.equals((AbstractToken) o)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static class Colon extends Symbol {

		private static final long serialVersionUID = -5957108226551908022L;

		public Colon() {
            super(Terminal.COLON);
        }
    }

    public static class Comma extends Symbol {

		private static final long serialVersionUID = 6857008891745539417L;

		public Comma() {
            super(Terminal.COMMA);
        }
    }

    public static class Semicolon extends Symbol {

		private static final long serialVersionUID = 6695798249551185686L;

		public Semicolon() {
            super(Terminal.SEMICOLON);
        }
    }

    public static class ExclaMark extends Symbol {

		private static final long serialVersionUID = -4438981643984196791L;

		public ExclaMark() {
            super(Terminal.EXCLAMARK);
        }
    }

    public static class QuestMark extends Symbol {

		private static final long serialVersionUID = -4230336958022241898L;

		public QuestMark() {
            super(Terminal.QUESTMARK);
        }
    }

    public static class Becomes extends Symbol {

		private static final long serialVersionUID = 4869909955231080470L;

		public Becomes() {
            super(Terminal.BECOMES);
        }
    }

    public static class LBrace extends Symbol {

		private static final long serialVersionUID = 4194464821518588448L;

		public LBrace() {
            super(Terminal.LBRACE);
        }
    }

    public static class RBrace extends Symbol {

		private static final long serialVersionUID = -7875174837681837309L;

		public RBrace() {
            super(Terminal.RBRACE);
        }
    }

    public static class LParen extends Symbol {

		private static final long serialVersionUID = -574865805781420981L;

		public LParen() {
            super(Terminal.LPAREN);
        }
    }

    public static class RParen extends Symbol {

		private static final long serialVersionUID = 912277612849109158L;

		public RParen() {
            super(Terminal.RPAREN);
        }
    }

}
