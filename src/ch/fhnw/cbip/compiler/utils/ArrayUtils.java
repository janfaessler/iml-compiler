package ch.fhnw.cbip.compiler.utils;

public class ArrayUtils {
    public static char[] concatCharArray(char[] A, char[] B) {
        char[] C = new char[A.length + B.length];
        System.arraycopy(A, 0, C, 0, A.length);
        System.arraycopy(B, 0, C, A.length, B.length);
        return C;
    }

    public static char[] expandCharArray(char[] A, char B) {
        char[] C = new char[A.length + 1];
        System.arraycopy(A, 0, C, 0, A.length);
        C[C.length - 1] = B;
        return C;
    }
}
