package org.acme.resteasy;

import org.acme.resteasy.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/resteasy")
public class ExampleResource {

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello from quarkus REST api...";
    }

    List<User> users = new ArrayList<User>();

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(User user){
        Long id = Long.valueOf(users.size()+1);
        user.setId(id);
        /*user.setName("user"+id);
        user.setPassword("pwd"+id);
        user.setUserName("u"+id);*/
        users.add(user);
        return Response.ok(user).build();
    }

    @GET
    @Path("/users/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<User> findById(Long uid){
        return users.stream()
                .filter(item->item.getId().equals(uid))
                .findFirst();
    }

    @GET
    @Path("/users")
    @Produces("application/json")
    public Response list(){
        return Response.ok(users).build();
    }

    @DELETE @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response remove (Long userId){
        users.removeIf(usr->usr.getId().equals(userId));
        return Response.ok(users).build();
    }
}