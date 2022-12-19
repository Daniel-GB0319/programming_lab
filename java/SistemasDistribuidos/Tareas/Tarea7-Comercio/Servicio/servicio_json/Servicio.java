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

    if (articulo.precio >= 1)
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar un precio unitario válido para el articulo"))).build();

    if (articulo.cantidad >= 1)
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar una cantidad válida de articulos disponibles"))).build();

    if (articulo.foto == null)
      return Response.status(400).entity(j.toJson(new Error("Se debe ingresar la imagen del articulo"))).build();

    try{
      conexion.setAutoCommit(false);

      PreparedStatement stmt_1 = conexion.prepareStatement("INSERT INTO articulos(id_articulo,nombre,descripcion,precio,cantidad,foto) VALUES (0,?,?,?,?,?)");
 
      try{
        stmt_1.setString(1,articulo.nombre);
        stmt_1.setString(2,articulo.descripcion);
        stmt_1.setInt(3,articulo.precio);
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
    String buscador = p.buscador;

    Connection conexion= pool.getConnection();

    try{
      PreparedStatement stmt_1 = conexion.prepareStatement("SELECT a.id_articulo, a.nombre,a.descripcion,a.precio,a.cantidad,a.foto FROM articulos a WHERE nombre LIKE '%?%' OR descripcion LIKE '%?%' ");
      try{
        stmt_1.setString(1,buscador);
        stmt_1.setString(2,buscador);

        ResultSet rs = stmt_1.executeQuery();
        try{
          if (rs.next()){
            Articulo r = new Articulo();
            int id;
            id = rs.getInt(0);
            r.nombre = rs.getString(1);
            r.descripcion = rs.getString(2);
            r.precio = rs.getInt(3);
            r.cantidad = rs.getInt(4);
	          r.foto = rs.getBytes(5);
            return Response.ok().entity(j.toJson(id,r)).build();
          }
          return Response.status(400).entity(j.toJson(new Error("Ningun nombre o descripción de artículo coincide con la frase ingresada en el buscador"))).build();
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
    ParamModArticulo p = (ParamModArticulo) j.fromJson(json,ParamModArticulo.class);
    Articulo articulo = p.articulo;
    int cantidad = p.cantidad;
    int id = p.id_articulo;

    Connection conexion= pool.getConnection();


    if (articulo.cantidad - cantidad < 0)
      return Response.status(400).entity(j.toJson(new Error("No hay suficiente cantidad del producto solicitado"))).build();


    conexion.setAutoCommit(false);
    try{
      PreparedStatement stmt_1 = conexion.prepareStatement("UPDATE articulos SET cantidad=? WHERE id_articulo=?");
      try{
        stmt_1.setInt(0,articulo.cantidad - cantidad);
        stmt_1.setString(1,id);
        stmt_1.executeUpdate();
      }
      finally{
        stmt_1.close();
      }

      PreparedStatement stmt_2 = conexion.prepareStatement("INSERT INTO carrito_compra(id_articulo,cantidad) VALUES (?,?)");
      try{
        stmt_2.setInt(0,id);
        stmt_2.setInt(1,cantidad);
        stmt_2.executeUpdate();
      }
      finally{
        stmt_2.close();
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
    ParamDelArticulo p = (ParamDelArticulo) j.fromJson(json,ParamDelArticulo.class);
    int id = p.id_articulo;

    Connection conexion= pool.getConnection();

    conexion.setAutoCommit(false);
    try{
      PreparedStatement stmt_1 = conexion.prepareStatement("DELETE FROM carrito_compra WHERE id_articulo=?");
      try{
        stmt_1.setInt(1,id);
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
}
