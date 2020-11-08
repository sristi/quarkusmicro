package service;

import org.acme.resteasy.db.UserEjb;
import org.acme.resteasy.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/resteasy")
public class UserService {
    @Inject UserEjb userEjb;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello from quarkus REST api...";
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> add(User user){
        userEjb.save(user);
        return userEjb.getUsers();
    }

    @GET
    @Path("/users/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public User findById(@PathParam("uid") long uid){
        return userEjb.find(uid);
    }

    @GET
    @Path("/users")
    @Produces("application/json")
    public List<User> list(){
        return userEjb.getUsers();
    }

    @DELETE @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> remove (@PathParam("userId") long id){
        userEjb.remove(userEjb.find(id));
        return userEjb.getUsers();
    }

}
