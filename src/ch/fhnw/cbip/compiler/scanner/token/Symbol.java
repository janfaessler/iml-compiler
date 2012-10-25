package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Symbol extends AbstractToken {

    public Symbol(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            if (super.equals((AbstractToken) o)) {
                return true;
            }
        }
        return false;
    }

    public static class Colon extends Symbol {
        public Colon() {
            super(Terminal.COLON);
        }
    }

    public static class Comma extends Symbol {
        public Comma() {
            super(Terminal.COMMA);
        }
    }

    public static class Semicolon extends Symbol {
        public Semicolon() {
            super(Terminal.SEMICOLON);
        }
    }

    public static class ExclaMark extends Symbol {
        public ExclaMark() {
            super(Terminal.EXCLAMARK);
        }
    }

    public static class QuestMark extends Symbol {
        public QuestMark() {
            super(Terminal.QUESTMARK);
        }
    }

    public static class Becomes extends Symbol {
        public Becomes() {
            super(Terminal.BECOMES);
        }
    }

    public static class LBrace extends Symbol {
        public LBrace() {
            super(Terminal.LBRACE);
        }
    }

    public static class RBrace extends Symbol {
        public RBrace() {
            super(Terminal.RBRACE);
        }
    }

    public static class LParen extends Symbol {
        public LParen() {
            super(Terminal.LPAREN);
        }
    }

    public static class RParen extends Symbol {
        public RParen() {
            super(Terminal.RPAREN);
        }
    }

}
