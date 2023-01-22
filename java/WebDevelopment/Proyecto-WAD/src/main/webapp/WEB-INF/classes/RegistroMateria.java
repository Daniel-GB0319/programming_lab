import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistroMateria extends HttpServlet {
    // Manipula las petioiones enviadas por el cliente, utilizando el atributo method=get o method=post. 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()){
        // Respondemos las peticiones
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Registro</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Registro del alumno exitoso</h1>");
        out.println("</body>");
        out.println("</html>");
        out.close();
        } // try
    } // processRequest
    

    // Metodo que se encarga de la peticion HTTP GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    } // doGet 
    
    
    // Metodo que se encarga de la peticion HTTP POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    } // doPost

    
    // Devuelve una descripcion breve del servlet
    @Override
    public String getServletInfo(){
        return "Servlet Registro";
    } // getServletInfo
    
} // Registro
