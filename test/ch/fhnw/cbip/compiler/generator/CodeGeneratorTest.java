package ch.fhnw.cbip.compiler.generator;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import ch.fhnw.cbip.compiler.parser.AbsTree.Program;

public class CodeGeneratorTest {


    private CodeGenerator codeGenerator;
    private Program mockTree;

    @Before
    public void setUp() throws Exception {
        mockTree = mock(Program.class);
        codeGenerator = new CodeGenerator(mockTree);
    }
    
    @Test
    public void countingStateTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        Method mStartCountingState = CodeGenerator.class.getDeclaredMethod("startCountingState", null);
        mStartCountingState.setAccessible(true);
        
        Method mStopCountingState = CodeGenerator.class.getDeclaredMethod("stopCountingState", null);
        mStopCountingState.setAccessible(true);
        
        Method mAddLine = CodeGenerator.class.getDeclaredMethod("addLine", String.class, String.class);
        mAddLine.setAccessible(true);
        
        mStartCountingState.invoke(codeGenerator);
        mAddLine.invoke(codeGenerator, "blubb","");
        mAddLine.invoke(codeGenerator, "blubb","");
            mStartCountingState.invoke(codeGenerator);
            mAddLine.invoke(codeGenerator, "blubb","");
            mAddLine.invoke(codeGenerator, "blubb","");
                mStartCountingState.invoke(codeGenerator);
                mAddLine.invoke(codeGenerator, "blubb","");
                mAddLine.invoke(codeGenerator, "blubb","");
                    mStartCountingState.invoke(codeGenerator);
                    mAddLine.invoke(codeGenerator, "blubb","");
                    mAddLine.invoke(codeGenerator, "blubb","");
                    assertEquals(2,(int)mStopCountingState.invoke(codeGenerator));
                mAddLine.invoke(codeGenerator, "blubb","");
                mAddLine.invoke(codeGenerator, "blubb","");
                assertEquals(6,(int)mStopCountingState.invoke(codeGenerator));
            mAddLine.invoke(codeGenerator, "blubb","");
            mAddLine.invoke(codeGenerator, "blubb","");
            assertEquals(10,(int)mStopCountingState.invoke(codeGenerator));
        mAddLine.invoke(codeGenerator, "blubb","");
        mAddLine.invoke(codeGenerator, "blubb","");
        assertEquals(14,(int)mStopCountingState.invoke(codeGenerator));
    }

}
