import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CuentaCookie extends HttpServlet{
    /* Manipula las posibles peticiones enviadas por el cliente
     * utilizando el atributo method=get o method=post.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
            /*Obtendremos el valor actual de la cookie CuentaCookie
             * iterando entre las cookies que se reciban
             */
            String nCuenta = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null){
                for(int i = 0; i< cookies.length; i++){
                    if(cookies[i].getName().equals("Cuenta.cookie")){
                        nCuenta = cookies[i].getValue();
                        break;
                    } // if
                } // for
            } // if
            /*Incrementa el contador para esta pagina
             * El valor es guardado en la cookie con el nombre "Cuenta.cookie"
             */
            Integer conteo = null;
            if(nCuenta == null){
                conteo = new Integer(1);
            }else{
                conteo = new Integer(Integer.parseInt(nCuenta)+1);
            } // else
            Cookie newCookie = new Cookie("Cuenta.cookie", conteo.toString());
            response.addCookie(newCookie);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CuentaCookie</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>(Gonzalez Daniel) Has visitado esta pagina "+ conteo.toString() + ((conteo.intValue() == 1)?"vez":" veces.") + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } // try
    } // processRequest 

    /*Metodo que se encarga de la peticion HTTP GET */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        processRequest(request, response);
    }
} // Class CuentaCookie