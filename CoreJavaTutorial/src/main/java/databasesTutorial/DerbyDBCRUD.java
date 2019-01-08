package databasesTutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DerbyDBCRUD 
{
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		//Embedded DB
		embeddedAndclientServerDerby("jdbc:derby:kirthidb;create=true;user=kirthi;password=2492","/.derby");		
		//ClientServer / Network server DB
		//embeddedAndclientServerDerby("jdbc:derby://localhost:1527/kirthidb;create=true;user=kirthi;password=2492","\\clientServerDerbyDB\\db-derby\\bin");
	}	
	private static void embeddedAndclientServerDerby(String URL, String derbyHome) throws InstantiationException, IllegalAccessException, ClassNotFoundException 
	{
		Connection con = null;
        Statement st = null;
        PreparedStatement pstUpdate = null;
        PreparedStatement pstDelete = null;
        ResultSet rs = null;        
        try 
        {            
            System.setProperty("derby.system.home", System.getProperty("user.dir")+derbyHome);            
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();			
            con = DriverManager.getConnection(URL);
            st = con.createStatement(); 
            //Create
            st.executeUpdate("CREATE TABLE CARS(ID INT PRIMARY KEY, NAME VARCHAR(30), PRICE INT)");
            //Insert
            st.executeUpdate("INSERT INTO CARS VALUES(1, 'Audi', 1000)");
            st.executeUpdate("INSERT INTO CARS VALUES(2, 'Mercedes', 2000)");
            st.executeUpdate("INSERT INTO CARS VALUES(3, 'Skoda', 3000)");
            st.executeUpdate("INSERT INTO CARS VALUES(4, 'Volvo', 4000)");
            st.executeUpdate("INSERT INTO CARS VALUES(5, 'Bentley', 5000)");
            st.executeUpdate("INSERT INTO CARS VALUES(6, 'Citroen', 6000)");
            st.executeUpdate("INSERT INTO CARS VALUES(7, 'Hummer', 7000)");
            st.executeUpdate("INSERT INTO CARS VALUES(8, 'Volkswagen', 8000)"); 
            //Update
            pstUpdate = con.prepareStatement("UPDATE CARS SET PRICE = ? WHERE ID = ?");
            pstUpdate.setInt(1, 9000);
            pstUpdate.setInt(2, 1);
            pstUpdate.executeUpdate();
            //Delete
            pstDelete = con.prepareStatement("DELETE FROM CARS WHERE ID = ?");
            pstDelete.setInt(1, 1);
            pstDelete.executeUpdate();
            //Drop
            rs = st.executeQuery("SELECT * FROM KIRTHI.CARS");
            while (rs.next()) {
                System.out.print(rs.getInt(1));
                System.out.print(" ");
                System.out.print(rs.getString(2));
                System.out.print(" ");
                System.out.println(rs.getString(3));
            }
            st.executeUpdate("DROP TABLE CARS");   
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
         } catch (SQLException ex) 
         { 
        	 Logger lgr = Logger.getLogger(DerbyDBCRUD.class.getName());
            if (((ex.getErrorCode() == 50000) && (ex.getSQLState().equals("XJ015"))) 
            	||	((ex.getErrorCode() == 45000) && (ex.getSQLState().equals("08006"))))
                {lgr.log(Level.INFO, "Derby shut down normally");}
            else 
                {lgr.log(Level.SEVERE, ex.getMessage(),ex);}
         }  
        	 finally
         {
            try {
                if (st != null) {st.close();}
                if (pstDelete != null) {pstDelete.close();}
                if (pstUpdate != null) {pstUpdate.close();}
                if (con != null) {con.close();}                
            } catch (SQLException ex) {
                Logger lgr = Logger.getLogger(DerbyDBCRUD.class.getName());
                lgr.log(Level.WARNING, ex.getMessage(), ex);
            }
         }
	}
}
