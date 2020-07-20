package i5.las2peer.services.music;


import java.net.HttpURLConnection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import i5.las2peer.api.Context;
import i5.las2peer.api.ManualDeployment;
import i5.las2peer.api.ServiceException;
import i5.las2peer.api.logging.MonitoringEvent;
import i5.las2peer.restMapper.RESTService;
import i5.las2peer.restMapper.annotations.ServicePath;
import i5.las2peer.services.music.database.DatabaseManager;
import java.sql.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import org.json.simple.*;

import java.util.HashMap;
import java.util.Map;
 

/**
 *
 * 131
 *
 * This microservice was generated by the CAE (Community Application Editor). If you edit it, please
 * make sure to keep the general structure of the file and only add the body of the methods provided
 * in this main file. Private methods are also allowed, but any "deeper" functionality should be
 * outsourced to (imported) classes.
 *
 */
@ServicePath("music")
@ManualDeployment
public class Music extends RESTService {


  /*
   * Database configuration
   */
  private String jdbcDriverClassName;
  private String jdbcLogin;
  private String jdbcPass;
  private String jdbcUrl;
  private static DatabaseManager dbm;



  public Music() {
	super();
    // read and set properties values
    setFieldValues();
        // instantiate a database manager to handle database connection pooling and credentials
    dbm = new DatabaseManager(jdbcDriverClassName, jdbcLogin, jdbcPass, jdbcUrl);
  }

  @Override
  public void initResources() {
	getResourceConfig().register(RootResource.class);
  }

  // //////////////////////////////////////////////////////////////////////////////////////
  // REST methods
  // //////////////////////////////////////////////////////////////////////////////////////

  @Api
  @SwaggerDefinition(
      info = @Info(title = "131", version = "$Metadata_Version$",
          description = "$Metadata_Description$",
          termsOfService = "$Metadata_Terms$",
          contact = @Contact(name = "Philipp", email = "CAEAddress@gmail.com") ,
          license = @License(name = "BSD",
              url = "https://github.com/PhilCAEOrg/microservice-131/blob/master/LICENSE.txt") ) )
  @Path("/")
  public static class RootResource {

    private final Music service = (Music) Context.getCurrent().getService();

      /**
   * 
   * postSong
   *
   * 
   * @param song  a JSONObject
   * 
   * @return Response 
   * 
   */
  @POST
  @Path("/song")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  @ApiResponses(value = {
       @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "postResponse")
  })
  @ApiOperation(value = "postSong", notes = " ")
  public Response postSong(String song) {
    JSONObject song_JSON = (JSONObject) JSONValue.parse(song);


 
    String songTitle = (String) song_JSON.get("title"); 

    Connection connection; 
    try { 
        connection = dbm.getConnection(); 
         
        PreparedStatement statement = connection.prepareStatement("INSTER INTO songs (title) VALUES (?);"); 
        statement.setString(1, songTitle); 
        statement.executeUpdate(); 
        statement.close();
        return Response.status(HttpURLConnection.HTTP_OK).build();
    } catch(SQLException e) { 
        return Response.serverError().build();
    }
     
    
      

    return Response.status(HttpURLConnection.HTTP_OK).entity(postResult.toJSONString()).build();
    
  }

  /**
   * 
   * getSongs
   *
   * 
   *
   * 
   * @return Response 
   * 
   */
  @GET
  @Path("/song")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.TEXT_PLAIN)
  @ApiResponses(value = {
       @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "getResponse")
  })
  @ApiOperation(value = "getSongs", notes = " ")
  public Response getSongs() {




     
    // service method invocations

     




    // getResponse
    boolean getResponse_condition = true;
    if(getResponse_condition) {
      JSONObject getResult = new JSONObject();

      

      return Response.status(HttpURLConnection.HTTP_OK).entity(getResult.toJSONString()).build();
    }
    return null;
  }



  }

  // //////////////////////////////////////////////////////////////////////////////////////
  // Service methods (for inter service calls)
  // //////////////////////////////////////////////////////////////////////////////////////
  
  

  // //////////////////////////////////////////////////////////////////////////////////////
  // Custom monitoring message descriptions (can be called via RMI)
  // //////////////////////////////////////////////////////////////////////////////////////

  public Map<String, String> getCustomMessageDescriptions() {
    Map<String, String> descriptions = new HashMap<>();
    
    return descriptions;
  }

}
