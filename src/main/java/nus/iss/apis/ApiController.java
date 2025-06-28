package nus.iss.apis;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public class ApiController {

    @GET
    @Path("/ping")
    public Response ping() {
        return Response.ok("pong").build();
    }

}
