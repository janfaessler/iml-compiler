package ch.fhnw.cbip.compiler.checker;

import ch.fhnw.cbip.compiler.error.SemanticError;
import ch.fhnw.cbip.compiler.parser.AbsTree;
import ch.fhnw.cbip.compiler.parser.AbsTree.Cmd;
import ch.fhnw.cbip.compiler.parser.AbsTree.CmdAssi;
import ch.fhnw.cbip.compiler.parser.AbsTree.CmdInvarFor;
import ch.fhnw.cbip.compiler.parser.AbsTree.Expr;
import ch.fhnw.cbip.compiler.parser.AbsTree.ExprDyadic;
import ch.fhnw.cbip.compiler.parser.AbsTree.ExprMonadic;
import ch.fhnw.cbip.compiler.parser.AbsTree.ExprStore;
import ch.fhnw.cbip.compiler.scanner.token.Ident;



public class Checker {
    
    public static void check(AbsTree.Program absprog) throws SemanticError{
        AbsTree.Cmd command = absprog.getCommands();
        while (command != null){
            if (command instanceof AbsTree.CmdInvarFor){
                CmdInvarFor loop = (CmdInvarFor) command;
                Ident loopcounter = loop.getLoopCounter();
                
                // Check that loopcounter is involved in the condition
                if (loop.getCondition() instanceof ExprDyadic){
                    if (Checker.searchcounter(loop.getCondition(), loopcounter) == false){ throw new SemanticError("Loop Condition does not involve the loop counter", loopcounter.getLine());};
                    
                }
                else {throw new SemanticError("The loop condition is not valid", loopcounter.getLine());}
                // Check that the loopcounter is never written to, inside the blockCmd
                Cmd blockCmd = loop.getCmd();
                while(blockCmd != null){                    
                    if (blockCmd instanceof AbsTree.CmdAssi){
                        Expr assitarget = ((CmdAssi)blockCmd).getTargetExpr();
                        if (assitarget instanceof ExprStore){
                            if (((ExprStore)assitarget).getIdent().getName().equals(loopcounter.getName())){
                                throw new SemanticError("Tried to write to loopcounter", ((ExprStore)assitarget).getIdent().getLine());
                            }
                        }
                    }
                    blockCmd = blockCmd.getNextCmd();
                }
                
            }
            command = command.getNextCmd();
        }
    }
    
    private static Boolean searchcounter(Expr expr, Ident lc){
        if (expr instanceof ExprDyadic){
            return (searchcounter(((ExprDyadic)expr).getExpr1(), lc) || searchcounter(((ExprDyadic)expr).getExpr2(), lc));
        }
        else if (expr instanceof ExprMonadic){
            return (searchcounter(((ExprMonadic)expr).getExpr(), lc));
        }
        else if (expr instanceof ExprStore){
            return (lc.getName().equals((((ExprStore)expr).getIdent().getName())));
        }
        else{
            return false;
        }
    }

}
