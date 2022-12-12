package servicio_json;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Response;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.google.gson.*;

// la URL del servicio web es http://localhost:8080/Servicio/rest/ws
// donde:
//	"Servicio" es el dominio del servicio web (es decir, el nombre de archivo Servicio.war)
//	"rest" se define en la etiqueta <url-pattern> de <servlet-mapping> en el archivo WEB-INF\web.xml
//	"ws" se define en la siguiente anotacin @Path de la clase Servicio

@Path("ws")
public class Servicio{
  static DataSource pool = null;
  static{		
    try{
      Context ctx = new InitialContext();
      pool = (DataSource)ctx.lookup("java:comp/env/jdbc/datasource_Servicio");
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  static Gson j = new GsonBuilder().registerTypeAdapter(byte[].class,new AdaptadorGsonBase64()).setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").create();

  @POST
  @Path("alta_articulo")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response alta(String json) throws Exception{
    ParamAltaArticulo p = (ParamAltaArticulo) j.fromJson(json,ParamAltaArticulo.class);
    Articulo articulo = p.articulo;

    Connection conexion = pool.getConnection();

    if (articulo.nombre == null || articulo.nombre.equals(""))
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar el nombre del articulo"))).build();

    if (articulo.descripcion == null || articulo.descripcion.equals(""))
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar la descripcion del articulo"))).build();

    if (articulo.precio == null)
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar el precio unitario del articulo"))).build();

    if (articulo.cantidad == null)
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar la cantidad disponible del articulo"))).build();

    if (articulo.foto == null)
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar la imagen del articulo"))).build();

    try{
      conexion.setAutoCommit(false);

      PreparedStatement stmt_1 = conexion.prepareStatement("INSERT INTO articulos(id_articulo,nombre,descripcion,precio,cantidad,foto) VALUES (0,?,?,?,?,?)");
 
      try{
        stmt_1.setString(1,articulo.nombre);
        stmt_1.setString(2,articulo.descripcion);
        stmt_1.setFloat(3,articulo.precio);
        stmt_1.setInt(4,articulo.cantidad);
        stmt_1.setBytes(5,articulo.foto);

        stmt_1.executeUpdate();
      }
      finally{
        stmt_1.close();
      }

      conexion.commit();
    }
    catch (Exception e){
      conexion.rollback();
      return Response.status(400).entity(j.toJson(new Error(e.getMessage()))).build();
    }
    finally{
      conexion.setAutoCommit(true);
      conexion.close();
    }
    return Response.ok().build();
  }

  @POST
  @Path("consulta_articulo")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response consulta(String json) throws Exception{
    ParamQueryArticulo p = (ParamQueryArticulo) j.fromJson(json,ParamQueryArticulo.class);
    String nombre = p.nombre;
    String descripcion = p.descripcion;

    Connection conexion= pool.getConnection();

    try{
      PreparedStatement stmt_1 = conexion.prepareStatement("SELECT a.nombre,a.descripcion,a.precio,a.cantidad,a.foto FROM articulos a WHERE nombre LIKE '?%' OR descripcion LIKE '%?%' ");
      try{
        stmt_1.setString(1,nombre);
        stmt_1.setString(2,descripcion);

        ResultSet rs = stmt_1.executeQuery();
        try{
          if (rs.next()){
            Articulo r = new articulo();
            r.nombre = rs.getString(1);
            r.descripcion = rs.getString(2);
            r.precio = rs.getFloat(3);
            r.cantidad = rs.getInt(4);
	          r.foto = rs.getBytes(5);
            return Response.ok().entity(j.toJson(r)).build();
          }
          return Response.status(400).entity(j.toJson(new Error("Ningun artículo coincide con el nombre o descripcion"))).build();
        }
        finally{
          rs.close();
        }
      }
      finally{
        stmt_1.close();
      }
    }
    catch (Exception e){
      return Response.status(400).entity(j.toJson(new Error(e.getMessage()))).build();
    }
    finally{
      conexion.close();
    }
  }

  @POST
  @Path("modifica_carrito")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response modifica(String json) throws Exception{
    ParamModificaarticulo p = (ParamModificaarticulo) j.fromJson(json,ParamModificaarticulo.class);
    articulo articulo = p.articulo;

    Connection conexion= pool.getConnection();

    if (articulo.email == null || articulo.email.equals(""))
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar el email"))).build();

    if (articulo.nombre == null || articulo.nombre.equals(""))
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar el nombre"))).build();

    if (articulo.descripcion == null || articulo.descripcion.equals(""))
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar el apellido paterno"))).build();

    if (articulo.fecha_nacimiento == null)
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar la fecha de nacimiento"))).build();

    conexion.setAutoCommit(false);
    try{
      PreparedStatement stmt_1 = conexion.prepareStatement("UPDATE articulos SET nombre=?,descripcion=?,apellido_materno=?,fecha_nacimiento=?,telefono=?,genero=? WHERE email=?");
      try{
        stmt_1.setString(1,articulo.nombre);
        stmt_1.setString(2,articulo.descripcion);

        if (articulo.apellido_materno != null)
          stmt_1.setString(3,articulo.apellido_materno);
        else
          stmt_1.setNull(3,Types.VARCHAR);

        stmt_1.setTimestamp(4,articulo.fecha_nacimiento);

        if (articulo.telefono != null)
          stmt_1.setLong(5,articulo.telefono);
        else
          stmt_1.setNull(5,Types.BIGINT);

        stmt_1.setString(6,articulo.genero);
        stmt_1.setString(7,articulo.email);
        stmt_1.executeUpdate();
      }
      finally{
        stmt_1.close();
      }

      PreparedStatement stmt_2 = conexion.prepareStatement("DELETE FROM fotos_articulos WHERE id_articulo=(SELECT id_articulo FROM articulos WHERE email=?)");
      try{
        stmt_2.setString(1,articulo.email);
        stmt_2.executeUpdate();
      }
      finally{
        stmt_2.close();
      }

      if (articulo.foto != null){
        PreparedStatement stmt_3 = conexion.prepareStatement("INSERT INTO fotos_articulos(id_foto,foto,id_articulo) VALUES (0,?,(SELECT id_articulo FROM articulos WHERE email=?))");
        try{
          stmt_3.setBytes(1,articulo.foto);
          stmt_3.setString(2,articulo.email);
          stmt_3.executeUpdate();
        }
        finally{
          stmt_3.close();
        }
      }
      conexion.commit();
    }
    catch (Exception e){
      conexion.rollback();
      return Response.status(400).entity(j.toJson(new Error(e.getMessage()))).build();
    }
    finally{
      conexion.setAutoCommit(true);
      conexion.close();
    }
    return Response.ok().build();
  }

  @POST
  @Path("borra_articulo")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response borra(String json) throws Exception{
    ParamBorraarticulo p = (ParamBorraarticulo) j.fromJson(json,ParamBorraarticulo.class);
    String email = p.email;

    Connection conexion= pool.getConnection();

    try{
      PreparedStatement stmt_1 = conexion.prepareStatement("SELECT 1 FROM articulos WHERE email=?");
      try{
        stmt_1.setString(1,email);

        ResultSet rs = stmt_1.executeQuery();
        try{
          if (!rs.next())
		return Response.status(400).entity(j.toJson(new Error("El email no existe"))).build();
        }
        finally{
          rs.close();
        }
      }
      finally{
        stmt_1.close();
      }
      conexion.setAutoCommit(false);
      PreparedStatement stmt_2 = conexion.prepareStatement("DELETE FROM fotos_articulos WHERE id_articulo=(SELECT id_articulo FROM articulos WHERE email=?)");
      try{
        stmt_2.setString(1,email);
	stmt_2.executeUpdate();
      }
      finally{
        stmt_2.close();
      }

      PreparedStatement stmt_3 = conexion.prepareStatement("DELETE FROM articulos WHERE email=?");
      try{
        stmt_3.setString(1,email);
	stmt_3.executeUpdate();
      }
      finally{
        stmt_3.close();
      }
      conexion.commit();
    }
    catch (Exception e){
      conexion.rollback();
      return Response.status(400).entity(j.toJson(new Error(e.getMessage()))).build();
    }
    finally{
      conexion.setAutoCommit(true);
      conexion.close();
    }
    return Response.ok().build();
  }
}
