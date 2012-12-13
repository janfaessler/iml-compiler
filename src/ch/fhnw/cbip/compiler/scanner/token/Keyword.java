package ch.fhnw.cbip.compiler.scanner.token;

import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

/**
 * Keyword token class.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public abstract class Keyword extends AbstractToken {

    /**
     * Serial id for serialization (used for deep copy).
     */
    private static final long serialVersionUID = -8567652592705498509L;

    /**
     * Creates a new Keyword token.
     * 
     * @param Terminal terminal symbol
     */
    public Keyword(Terminal terminal) {
        super(terminal);
    }

    @Override
    public String toString() {
        return getTerminal().toString();
    }
  

    @Override
    public boolean equals(Object o) {
        if (o != null && o.getClass() == this.getClass()) {
            return super.equals((AbstractToken) o);
        }
        return false;
    }

    /**
     * Class for Program token.
     */
    public static class Program extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 220115417436954381L;

        /**
         * Creates a new Program token.
         */
        public Program() {
            super(Terminal.PROGRAM);
        }
    }

    /**
     * Class for Call token.
     */
    public static class Call extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -1392614207457993722L;

        /**
         * Creates a new Call token.
         */
        public Call() {
            super(Terminal.CALL);
        }
    }

    /**
     * Class for If token.
     */
    public static class If extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 1126161917213292769L;

        /**
         * Creates a new If token.
         */
        public If() {
            super(Terminal.IF);
        }
    }

    /**
     * Class for Else token.
     */
    public static class Else extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -4222427209276785712L;

        /**
         * Creates a new Else token.
         */
        public Else() {
            super(Terminal.ELSE);
        }
    }

    /**
     * Class for Fun token.
     */
    public static class Fun extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -7587236950093744879L;

        /**
         * Creates a new Fun token.
         */
        public Fun() {
            super(Terminal.FUN);
        }
    }

    /**
     * Class for Proc token.
     */
    public static class Proc extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -1057675802192142460L;

        /**
         * Creates a new Proc token.
         */
        public Proc() {
            super(Terminal.PROC);
        }
    }

    /**
     * Class for Global token.
     */
    public static class Global extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -2015093342905421953L;

        /**
         * Creates a new Global token.
         */
        public Global() {
            super(Terminal.GLOBAL);
        }
    }

    /**
     * Class for Local token.
     */
    public static class Local extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -1398811311850458778L;

        /**
         * Creates a new Local token.
         */
        public Local() {
            super(Terminal.LOCAL);
        }
    }

    /**
     * Class for Not token.
     */
    public static class Not extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -3210418048940606523L;

        /**
         * Creates a new Not token.
         */
        public Not() {
            super(Terminal.NOT);
        }
    }

    /**
     * Class for Init token.
     */
    public static class Init extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -2426734801243585840L;

        /**
         * Creates a new Init token.
         */
        public Init() {
            super(Terminal.INIT);
        }
    }

    /**
     * Class for Returns token.
     */
    public static class Returns extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -5550220128673521049L;

        /**
         * Creates a new Returns token.
         */
        public Returns() {
            super(Terminal.RETURNS);
        }
    }

    /**
     * Class for Skip token.
     */
    public static class Skip extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -5084876609579271929L;

        /**
         * Creates a new Skip token.
         */
        public Skip() {
            super(Terminal.SKIP);
        }
    }

    /**
     * Class for While token.
     */
    public static class While extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -5647674698207175462L;

        /**
         * Creates a new While token.
         */
        public While() {
            super(Terminal.WHILE);
        }
    }

    /**
     * Class for Do token.
     */
    public static class Do extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = -6382633665809742571L;

        /**
         * Creates a new Do token.
         */
        public Do() {
            super(Terminal.DO);
        }
    }

    /**
     * Class for EndWhile token.
     */
    public static class EndWhile extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 3090362601755421022L;

        /**
         * Creates a new EndWhile token.
         */
        public EndWhile() {
            super(Terminal.ENDWHILE);
        }
    }

    /**
     * Class for Sentinel token.
     */
    public static class Sentinel extends Keyword {
        /**
         * Serial id for serialization (used for deep copy).
         */
        private static final long serialVersionUID = 7615416643710193275L;

        /**
         * Creates a new Sentinel token.
         */
        public Sentinel() {
            super(Terminal.SENTINEL);
        }
    }

}
