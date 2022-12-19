import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SeguimientoSesion extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            //Obtenenmos la sesion actual o creamos una de ser necesario 
            HttpSession sesion = request.getSession(true);
            // Datos de la sesion
            String idSesion = sesion.getId();
            long fechaCreacion = sesion.getCreationTime();
            long fechaUltimoAcceso = sesion.getLastAccessedTime();
            // Incrementa el contador de la sesion del cliente "cuenta.sesion"
            Integer cuenta = (Integer)sesion.getAttribute("cuenta.sesion");
            if(cuenta == null)
                cuenta = 1;
            else
                cuenta = cuenta +1;
            sesion.setAttribute("cuenta.sesion", cuenta);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SeguimientoSesion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>(Gonzalez Barrientos Geovanni Daniel) Has visitado esta pagina "+ cuenta + ((cuenta == 1)?" vez":" veces.") + "</h1>");
            out.println("<h2>Datos de la sesion actual: </h2>");
            out.println("Id de la sesion: " + idSesion + "</h3>");
            out.println("<h3>Fecha de creacion: " + (new Date(fechaCreacion)) + "</h3>");
            out.println("<h3>Se accedio por ultima vez: " + (new Date(fechaUltimoAcceso)) + "</h3>");
            out.println("<h2>Atributos: </h2>");
            Enumeration nombres = sesion.getAttributeNames();
            while (nombres.hasMoreElements()){
                String param = (String)nombres.nextElement();
                out.println("<h3>" + param + ": " + sesion.getAttribute(param) + "</h3>");
            }
            out.println("</body>");
            out.println("</html>");
        } // try
    } // processRequest 

    /*Metodo que se encarga de la peticion HTTP GET */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    } // get

    /*Metodo que se encarga de la peticion HTTP POST */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    } // post

    /*Devuelve una descripcion breve del servlet */
    @Override
    public String getServletInfo(){
        return "Servlet SeguimientoSesion";
    }

} // Class CuentaCookie