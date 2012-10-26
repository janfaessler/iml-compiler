package ch.fhnw.cbip.compiler.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayUtilsTest {

    @Test
    public void testConcatCharArray() {
        char[] a =  {'a', 'b', 'c'};
        char[] b =  {'d', 'e'};
        char[] ab_expected =  {'a', 'b', 'c', 'd', 'e'};
        char[] ab_actual = ArrayUtils.concatCharArray(a, b);
        
        assertEquals(ab_expected.length, ab_actual.length);
        for(int i = 0; i < ab_expected.length; i++){
            assertEquals(ab_expected[i], ab_actual[i]);
        }
    }

    @Test
    public void testExpandCharArray() {
        char[] a =  {'a', 'b', 'c'};
        char[] a_expected =  {'a', 'b', 'c', 'd'};
        char[] a_actual = ArrayUtils.expandCharArray(a, 'd');
        
        assertEquals(a_expected.length, a_actual.length);
        for(int i = 0; i < a_expected.length; i++){
            assertEquals(a_expected[i], a_actual[i]);
        }
    }

}
