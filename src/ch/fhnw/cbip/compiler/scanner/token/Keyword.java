package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class Keyword extends AbstractToken {

    private static final long serialVersionUID = -8567652592705498509L;

    public Keyword(Terminal terminal) {
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

    public static class Program extends Keyword {

        private static final long serialVersionUID = 220115417436954381L;

        public Program() {
            super(Terminal.PROGRAM);
        }
    }

    public static class Call extends Keyword {

        private static final long serialVersionUID = -1392614207457993722L;

        public Call() {
            super(Terminal.CALL);
        }
    }

    public static class If extends Keyword {

        private static final long serialVersionUID = 1126161917213292769L;

        public If() {
            super(Terminal.IF);
        }
    }

    public static class Else extends Keyword {

        private static final long serialVersionUID = -4222427209276785712L;

        public Else() {
            super(Terminal.ELSE);
        }
    }

    public static class Fun extends Keyword {

        private static final long serialVersionUID = -7587236950093744879L;

        public Fun() {
            super(Terminal.FUN);
        }
    }

    public static class Proc extends Keyword {

        private static final long serialVersionUID = -1057675802192142460L;

        public Proc() {
            super(Terminal.PROC);
        }
    }

    public static class Global extends Keyword {

        private static final long serialVersionUID = -2015093342905421953L;

        public Global() {
            super(Terminal.GLOBAL);
        }
    }

    public static class Local extends Keyword {

        private static final long serialVersionUID = -1398811311850458778L;

        public Local() {
            super(Terminal.LOCAL);
        }
    }

    public static class Not extends Keyword {

        private static final long serialVersionUID = -3210418048940606523L;

        public Not() {
            super(Terminal.NOT);
        }
    }

    public static class Init extends Keyword {

        private static final long serialVersionUID = -2426734801243585840L;

        public Init() {
            super(Terminal.INIT);
        }
    }

    public static class Returns extends Keyword {

        private static final long serialVersionUID = -5550220128673521049L;

        public Returns() {
            super(Terminal.RETURNS);
        }
    }

    public static class Skip extends Keyword {

        private static final long serialVersionUID = -5084876609579271929L;

        public Skip() {
            super(Terminal.SKIP);
        }
    }

    public static class While extends Keyword {

        private static final long serialVersionUID = -5647674698207175462L;

        public While() {
            super(Terminal.WHILE);
        }
    }

    public static class Do extends Keyword {

        private static final long serialVersionUID = -6382633665809742571L;

        public Do() {
            super(Terminal.DO);
        }
    }

    public static class EndWhile extends Keyword {

        private static final long serialVersionUID = 3090362601755421022L;

        public EndWhile() {
            super(Terminal.ENDWHILE);
        }
    }

    public static class Sentinel extends Keyword {

        private static final long serialVersionUID = 7615416643710193275L;

        public Sentinel() {
            super(Terminal.SENTINEL);
        }
    }

}
