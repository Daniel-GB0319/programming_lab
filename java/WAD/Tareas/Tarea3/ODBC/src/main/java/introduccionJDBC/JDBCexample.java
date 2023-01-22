package introduccionJDBC;


// STEP 1: Import required packages
import java.sql.*;

public class JDBCexample {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/empresa";
    
    // Database credentials
    static final String USER = "daniel@localhost";
    static final String PASS = "daniel1234";
    
    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        
        try{
            // STEP 2: Register JDBC driver
            //Class.forName("com.mysql.jdbc.Driver");
            Class.forName(JDBC_DRIVER);
            
            // STEP 3: Open a connection
            System.out.println("Connectiong to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // step 4: Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "UPDATE Employees set age=30 WHERE id=103";
            
            // Let us check if it returns a true Result Set or not.
            Boolean ret = stmt.execute(sql);
            System.out.println("Return value is : "+ ret.toString());
            
            // Let us update age of the record with ID = 103
            int rows = stmt.executeUpdate(sql);
            System.out.println("Rows impacted : "+ rows);
                    
            // Let us select all the records and dispaly them.
            sql = "SELECT id, first, last, age FROM Employees";
            // STEP 5: Extract data from result set
            try (ResultSet rs = stmt.executeQuery(sql)) {
                // STEP 5: Extract data from result set
                while (rs.next()) {
                    // Retrieve by column name
                    int id = rs.getInt("ïd");
                    int age = rs.getInt("age");
                    String first = rs.getString("first");
                    String last = rs.getString("last");
                    
                    // Display values
                    System.out.print("ID : "+ id);
                    System.out.print(", Age : "+ age);
                    System.out.print(", First : "+ first);
                    System.out.print(", Last : "+ last);
                } // while
                // STEP 6: Clean up environment
            }
            stmt.close();
            conn.close();
        } catch(SQLException se){
            // Handle errors for JDBC

        } catch(ClassNotFoundException e){
            // Hnadle errors for Class.forName

        } finally{
            // Finally block used to close resources
            try{
                if(stmt != null)
                    stmt.close();
            } catch(SQLException se2){
            } // Nothing we can do.
            
            try{
                if(conn != null)
                    conn.close();
            } catch(SQLException se){
            } // catch
        } // finally
        System.out.println("Goodbye!");
    } // main
    
} // Class JDBCexample
