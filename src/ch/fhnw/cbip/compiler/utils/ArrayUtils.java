package ch.fhnw.cbip.compiler.utils;

/**
 * Array utilities.
 * 
 * @author Michael Kuenzli <michael@kuenzli.eu>
 */
public class ArrayUtils {
    /**
     * Concatenates two char arrays.
     * 
     * @param A char array on the left
     * @param B char array on the right
     * @return combination of A and B
     */
    public static char[] concatCharArray(char[] A, char[] B) {
        char[] C = new char[A.length + B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);
        return C;
    }

    /**
     * Expand a char array by a single character.
     * 
     * @param A char array to expand
     * @param B single character to add
     * @return expanded char array
     */
    public static char[] expandCharArray(char[] A, char B) {
        char[] C = new char[A.length + 1];
        System.arraycopy(A, 0, C, 0, A.length);
        C[C.length - 1] = B;
        return C;
    }
}
