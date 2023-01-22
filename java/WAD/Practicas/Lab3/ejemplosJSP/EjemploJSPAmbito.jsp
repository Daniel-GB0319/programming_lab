<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Ejemplo Ámbito</title>
    </head>
    <body>
        <!--Este programa incrementa un contador. El valor es guardado en el
        objeto implicito application con el nombre atrCounter. -->

        <%
            Integer contador = (Integer) pageContext.getAttribute("atrCounter",PageContext.APPLICATION_SCOPE);
            if (contador == null)
                contador = 1;
            else
                contador++;
            //PageContext.APPLICATION_SCOPE
            //PageContext.SESSION_SCOPE
            //PageContext.REQUEST_SCOPE
            //PageContext.PAGE_SCOPE
            pageContext.setAttribute("atrCounter", contador, PageContext.APPLICATION_SCOPE);
        %>
        <h1>Ejemplo del seguimiento a nivel de aplicacion</h1>
        <h2>Esta pagina ha sido visitada <%=contador%><%=contador==1?" vez.":" veces."%> (Gonzalez Barrientos Geovanni Daniel)</h2>
    </body>
</html>