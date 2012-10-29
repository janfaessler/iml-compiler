package ch.fhnw.cbip.compiler.scanner;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Implementation of ITokenlist.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public class TokenList implements ITokenList {
    /**
     * Internal token list.
     */
    private LinkedList<IToken> tokenList;

    /**
     * Internal iterator.
     */
    private Iterator<IToken> listIterator;

    /**
     * Constructor for TokenList. Initializes a new token list.
     */
    public TokenList() {
        tokenList = new LinkedList<IToken>();
    }

    @Override
    public void add(IToken token) {
        if (token != null) {
            tokenList.add(token);
        }
    }

    @Override
    public void reset() {
        listIterator = null;
    }

    @Override
    public IToken nextToken() {
        if (listIterator == null) {
            listIterator = tokenList.iterator();
        }
        return listIterator.next();
    }

    @Override
    public boolean equals(Object o) {
        if (o != null) {
            if (this.getClass().equals(o.getClass())) {
                TokenList cmp = (TokenList) o;
                Iterator<IToken> thisIt = tokenList.iterator();
                Iterator<IToken> cmpIt = cmp.tokenList.iterator();

                while (thisIt.hasNext() && cmpIt.hasNext()) {
                    IToken thisToken = thisIt.next();
                    IToken cmpToken = cmpIt.next();
                    if (!thisToken.equals(cmpToken))
                        return false;
                }
                if (thisIt.hasNext() || cmpIt.hasNext()) {
                    return false;
                } else {
                    return true;
                }

            }
        }
        return false;
    }

}
