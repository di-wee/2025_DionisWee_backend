package nus.iss.apis;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ping")
@Produces(MediaType.APPLICATION_JSON)
@Consumes()
public class PingResource {

    @GET
    public Response ping() {
        return Response.ok("pong").build();
    }


}
