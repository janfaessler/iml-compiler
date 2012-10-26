package ch.fhnw.cbip.compiler.scanner.token;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import ch.fhnw.cbip.compiler.scanner.IToken;
import ch.fhnw.cbip.compiler.scanner.enums.Terminal;

public abstract class AbstractToken implements IToken, Serializable {
    /**
     * Serial number of the class
     */
    private static final long serialVersionUID = -4283948226021729377L;

    /**
     * Terminal of this token
     */
    private final Terminal terminal;

    /**
     * Source code line
     */
    private int line;

    /**
     * Creates a token
     * 
     * @param terminal terminal of the token
     * @param line source code line of the token
     */
    public AbstractToken(Terminal terminal) {
        this.terminal = terminal;
    }

    /**
     * @return the token's terminal symbol
     */
    protected Terminal getTerminal() {
        return terminal;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int number) {
        this.line = number;
    }

    @Override
    public String toString() {
        return terminal.toString();
    }

    @Override
    public boolean equals(Object o) {
        try {
            AbstractToken cmp = (AbstractToken) o;
            if (this.terminal == cmp.terminal) {
                if (this.line == cmp.line) {
                    return true;
                }
            }
        } catch (Exception e) {
            // do nothing
        }
        return false;
    }

    public IToken copy() {
        IToken obj = null;
        try {
            // Write the object out to a byte array
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(this);
            out.flush();
            out.close();

            // Make an input stream from the byte array and read a copy of the object back in.
            ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            obj = (IToken) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }
}
