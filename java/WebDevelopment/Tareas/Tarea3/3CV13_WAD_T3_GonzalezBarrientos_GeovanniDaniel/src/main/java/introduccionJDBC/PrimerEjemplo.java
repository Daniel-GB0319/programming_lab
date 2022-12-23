package introduccionJDBC;
// Paso1. Importar paquetes requeridos
import java.sql.*;

public class PrimerEjemplo {

    // Nombre del controlador JDBC y URL de la base de datos
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    static final String DB_URL = "jdbc:mysql://localhost:3306/empresa";
    
    // Credenciales de la base de datos
    static final String USER = "root";
    static final String PASS = "GdGb10$2000@";
//    jdbc:mysql://localhost:3306/?user=root
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        
        try{
            // Paso 2: rEGISTRAR CONTROLADOR jdbc
            //Class.forName("com.mysql.jdbc.Driver"); // Deprecated
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Paso 3: Abrir una conexion
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
            // Paso 4: Ejecutar una consulta
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";
            // Paso 5: Extraer datos del conjunto de resultados
            try (ResultSet rs = stmt.executeQuery(sql)) {
                // Paso 5: Extraer datos del conjunto de resultados
                while (rs.next()) {
                    // Recupera por nombre de columna
                    int id = rs.getInt("id");
                    int age = rs.getInt("age");
                    String first = rs.getString("first");
                    String last = rs.getString("last");
                    
                    // Mostrar valores
                    System.out.print("ID: "+id);
                    System.out.print(", Age: "+age);
                    System.out.print(", First: "+first);
                    System.out.println(", Last: "+last);
                } // while
                // Paso 6: Limpiar el entorno
            }
            stmt.close();
            conn.close();
        } catch(SQLException | ClassNotFoundException se){
            // Manejar errores para JDBC

        }
        // Manejar errores para Class.forName
         finally {
            // Bloque finally utilizado para cerrar recursos
            try{
                if(stmt != null)
                    stmt.close();
            }catch(SQLException se2){
            } // Nada que podamos hacer
            
            try{
                if(conn != null){
                    conn.close();
                } // if
            }catch(SQLException se){
            } // catch
        } // finally
        System.out.println("Goodbye!");
    } // main
} // Class PrimerEjemplo
