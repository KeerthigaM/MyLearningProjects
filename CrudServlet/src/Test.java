import java.sql.*;
import java.text.ParseException;

public class Test {

	public static void main(String[] args) throws ParseException ,ClassNotFoundException, SQLException
	{
		String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" +currentDir);
        String msAccessDBName =currentDir.replace("\\", "/") + "/DB/KirthiMSAccessDB.mdb";
        Connection con = null;	
        Class.forName("org.hsqldb.jdbcDriver");	
        con=DriverManager.getConnection("jdbc:ucanaccess://"+msAccessDBName);		
		if(con!=null)
	    {
	    	System.out.println("Success");
	    	Statement s=con.createStatement();
	    	createTable(s);
	    	//dropTable(s);
	    	System.out.println((int)(Math.random() * 50 + 1));
	    } 
		con.close();
	}
	private static void dropTable(Statement s)  throws SQLException 
	{    
	   String deleteTable = "DROP TABLE kirthiTbl";
	   boolean status=s.execute(deleteTable);
	   System.out.println("Delete Status "+status);
	}
	public static void createTable(Statement s) throws SQLException 
	{    
       String createTable = "CREATE TABLE kirthiTbl (id Integer, name Text(32), password Text(32), email Text(32), county Text(32))";
       boolean status=s.execute(createTable);
       System.out.println("Create Status "+status);
	}
}
