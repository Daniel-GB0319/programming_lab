<!-- archivo: fecha.jsp-->
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%= (new SimpleDateFormat("E dd/MM/yyyy - hh:mm:ss a zzz").format(new Date())) %>
