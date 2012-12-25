package ch.fhnw.cbip.vm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ch.fhnw.lederer.virtualmachineHS2010.IVirtualMachine;
import ch.fhnw.lederer.virtualmachineHS2010.IVirtualMachine.ExecutionError;
import ch.fhnw.lederer.virtualmachineHS2010.VirtualMachine;

public class Machine {
	
    static final int CODE_SIZE= 1000;
    static final int STORE_SIZE= 1000;
    static IVirtualMachine vm= new VirtualMachine(CODE_SIZE, STORE_SIZE);
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		 BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    try {
		    	String s;
		    	do {
		    		s = bufferRead.readLine();
		    		doLine(s);
		    	} while (s.substring(s.length() -1, s.length()).equals(","));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		    try {
				vm.execute();
			} catch (ExecutionError e) {
				e.printStackTrace();
			}
		
	}
	
	public static void doLine(final String input) {
		String[] cmdRaw = input.split(",");
		
		Integer line = Integer.valueOf(cmdRaw[0].substring(1));
		String tmp = cmdRaw[1].substring(0, cmdRaw[1].length() - 1);
		String[] cmd = tmp.split(" ");
		
		for (int i = 0; i < cmd.length; i++) {
			cmd[i] = cmd[i].replace(')', ' ');
		    cmd[i] = cmd[i].replace('(', ' ');
		    cmd[i] = cmd[i].trim();
		}
		
		try {
			switch (cmd[0]) {
				case "Alloc": vm.Alloc(line, Integer.valueOf(cmd[1])); break;
				case "IntLoad": vm.IntLoad(line, Integer.valueOf(cmd[1])); break;
				case "Input": vm.IntInput(line, cmd[1]); break;
				case "Deref": vm.Deref(line); break;
				case "IntAdd": vm.IntAdd(line); break;
				case "Store": vm.Stop(line); break;
				case "Call": vm.Call(line, Integer.valueOf(cmd[1])); break;
				case "Output": vm.IntOutput(line, cmd[1]); break;
				case "Stop": vm.Stop(line); break;
				case "Enter": vm.Enter(line, Integer.valueOf(cmd[1]), 0); break;
				case "LoadRel": vm.LoadRel(line, Integer.valueOf(cmd[1])); break;
				case "Return": vm.Return(line, Integer.valueOf(cmd[1])); break;
				case "CondJump": vm.CondJump(line, Integer.valueOf(cmd[1])); break;
				case "UncondJump": vm.UncondJump(line, Integer.valueOf(cmd[1])); break;
				
			}
		} catch (IVirtualMachine.CodeTooSmallError e) {
            System.out.println(e.toString());
            return;
        } catch (IndexOutOfBoundsException e) {
        	System.out.println(e.toString());
        }
	}

}
