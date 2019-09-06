package com;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream o = new PrintStream(new File("D:\\Users\\886758\\Desktop\\Dummy.txt"));
		
	}

	private static void getData(String UN,String vend,String cat,PrintStream o) throws FileNotFoundException {
		String[] catArr=cat.split(",");
			for(int i=0;i<catArr.length;i++){
				System.setErr(o); 
				System.out.println("GetUPC(o,\""+catArr[i]+"\",\""+vend+"\",\""+UN+"\");");
				System.err.println("GetUPC(\""+catArr[i]+"\",\""+vend+"\",\""+UN+"\");");
			}
		
	}	
}