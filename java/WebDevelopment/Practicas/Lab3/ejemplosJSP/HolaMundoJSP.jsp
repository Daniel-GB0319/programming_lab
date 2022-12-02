<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ejemplo JSP</title>
    </head>
    <body style="background-color: #e6e6fa">
        <div align="center">
            <%java.util.Date hoy = new java.util.Date();%>
        </div>
        <h1>Hello Mundo desde JSP</h1>
        <h2>Hoy es: <%=hoy%> (Gonzalez Barrientos Geovanni Daniel)</h2>
    </body>
</html>