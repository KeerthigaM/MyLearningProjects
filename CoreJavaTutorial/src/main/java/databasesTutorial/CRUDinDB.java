package databasesTutorial;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUDinDB {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		File resourcesDirectory = new File("src/main/resources");
		String filePath = resourcesDirectory.getAbsolutePath();
		Connection con = null;
		con = CreateDBConnection.derbyDB(con, filePath);
		Statement st = con.createStatement();
		createTable(st,"employee");
		listTable(st);
		insertRow(st,"employee");
		readTable(st,"employee");
		dropTable(st,"employee");
	}

	public static void listTable(Statement st) throws SQLException {
		String listTable="select s.schemaname || '.' || t.tablename from sys.systables t, sys.sysschemas s where t.schemaid = s.schemaid and t.tabletype = 'T' order by s.schemaname,t.tablename";
		ResultSet rs = st.executeQuery(listTable);
		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
	}
	// CREATE TABLE
	public static String createTable(Statement st, String tableName) throws SQLException {
		String createTable = "CREATE TABLE " + tableName + " (id INT, name VARCHAR(32))";
		//For other DB we need to use Integer for INT and TEXT for VARCHAR
		st.execute(createTable);
		return tableName;
	}
	// INSERT ROWS
	public static void insertRow(Statement st, String tableName) throws SQLException 
	{
	  for (int i = 0; i < 2; i++) 
	  {
		int randomNo = (int) (Math.random() * 1000.0);
		String addRow = "INSERT INTO "+tableName+" VALUES ( "+randomNo+", 'NameOf"+String.valueOf(randomNo)+"')";
		st.execute(addRow);
	  }
	}
	//READ TABLE
	public static void readTable(Statement st, String tableName) throws SQLException 
	{
		String readTable = "SELECT * FROM " + tableName;
        st.execute(readTable);
        ResultSet rs = st.getResultSet();
        while((rs!=null) && (rs.next()))
        {
            System.out.println(rs.getString(1) + " | " + rs.getString(2));
        }
	}	
	//DROP TABLE
	public static void dropTable(Statement s, String tableName) throws SQLException
	{
		String dropTable = "DROP TABLE " + tableName;
        s.execute(dropTable);
	}
}
