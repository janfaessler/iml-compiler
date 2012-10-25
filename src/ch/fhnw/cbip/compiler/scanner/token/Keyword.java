package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Keyword extends AbstractToken {

    public Keyword(Terminal terminal) {
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

    public static class Program extends Keyword {
        public Program() {
            super(Terminal.PROGRAM);
        }
    }

    public static class Call extends Keyword {
        public Call() {
            super(Terminal.CALL);
        }
    }

    public static class If extends Keyword {
        public If() {
            super(Terminal.IF);
        }
    }

    public static class Else extends Keyword {
        public Else() {
            super(Terminal.ELSE);
        }
    }

    public static class Fun extends Keyword {
        public Fun() {
            super(Terminal.FUN);
        }
    }

    public static class Proc extends Keyword {
        public Proc() {
            super(Terminal.PROC);
        }
    }

    public static class Global extends Keyword {
        public Global() {
            super(Terminal.GLOBAL);
        }
    }

    public static class Local extends Keyword {
        public Local() {
            super(Terminal.LOCAL);
        }
    }

    public static class Not extends Keyword {
        public Not() {
            super(Terminal.NOT);
        }
    }

    public static class Init extends Keyword {
        public Init() {
            super(Terminal.INIT);
        }
    }

    public static class Returns extends Keyword {
        public Returns() {
            super(Terminal.RETURNS);
        }
    }

    public static class Skip extends Keyword {
        public Skip() {
            super(Terminal.SKIP);
        }
    }

    public static class While extends Keyword {
        public While() {
            super(Terminal.WHILE);
        }
    }

    public static class Do extends Keyword {
        public Do() {
            super(Terminal.DO);
        }
    }

    public static class EndWhile extends Keyword {
        public EndWhile() {
            super(Terminal.ENDWHILE);
        }
    }

    public static class Sentinel extends Keyword {
        public Sentinel() {
            super(Terminal.SENTINEL);
        }
    }

}
