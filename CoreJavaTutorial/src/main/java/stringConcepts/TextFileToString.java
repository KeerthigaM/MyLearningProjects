package stringConcepts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class TextFileToString 
{
	static String TextFilePath=System.getProperty("user.dir")+"/src/stringConcepts/TableNamesList.txt";
	public static void main(String[] args) throws FileNotFoundException, IOException
	{		
		readFromTextFile();
		clearFromTextFile();
	}
	private static void clearFromTextFile() throws IOException
	{	
	    PrintWriter pw = new PrintWriter(new FileWriter(TextFilePath));
	    pw.write("");
	    pw.flush(); 
	    pw.close();
	}
	public static void readFromTextFile() throws FileNotFoundException, IOException 
	{	
		BufferedReader br = new BufferedReader(new FileReader(TextFilePath));
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) 
		    {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		String textFileData = sb.toString();
		System.out.println(textFileData);
		br.close();
	}
}
