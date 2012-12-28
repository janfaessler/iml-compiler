package ch.fhnw.cbip.compiler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import ch.fhnw.cbip.compiler.error.*;
import ch.fhnw.cbip.compiler.generator.CodeGenerator;
import ch.fhnw.cbip.compiler.parser.*;
import ch.fhnw.cbip.compiler.scanner.ITokenList;
import ch.fhnw.cbip.compiler.scanner.Scanner;
import ch.fhnw.cbip.vm.Machine;
import ch.fhnw.cbip.vm.MachineError;
import ch.fhnw.lederer.virtualmachineHS2010.IVirtualMachine.ExecutionError;

public class Compiler {
	
	public void compile(BufferedReader source) throws IOException, LexicalError, GrammarError, GenerationError, ExecutionError, MachineError {
		Scanner scanner = new Scanner();
		System.out.print("Scanning:");
		ITokenList tokenList = scanner.scan(source);
		System.out.println(" Success!");
		System.out.print("\nTokenList: ");
		System.out.println(tokenList.toString());
		System.out.println("\nParsing:");
		Parser parser = new Parser(tokenList);
		ConcTree.Program concreteTree = parser.parse();
		System.out.println("Success!");
		System.out.println("\nConcrete syntax tree:");
		System.out.println(concreteTree.toString(""));
		System.out.print("\nGenerating abstract syntax tree:");
		AbsTree.Program abstractTree = concreteTree.toAbstract();
		System.out.println("Success!");
		System.out.println("Abstract syntax tree:");
		System.out.println(abstractTree.toString(""));
		System.out.println("\nGenerating code:");
		CodeGenerator generator = new CodeGenerator(abstractTree);
		String code = generator.generate();
		System.out.println(code);
		System.out.println("\n Running code:");
		Machine machine = new Machine();
		machine.run(new BufferedReader(new StringReader(code)));
	}
	
	public static void main(String[] args) {
		try {
			
			InputStreamReader isr = new InputStreamReader(new FileInputStream("res/code.iml"));
			Compiler compiler = new Compiler();
			compiler.compile(new BufferedReader(isr));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
