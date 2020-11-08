package org.acme.resteasy;

import org.acme.resteasy.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Path("/resteasy")
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
    public List<User> add(User user){
        Long id = 0L;
        if(users.size()>0) {
            User userOfMaxId = users.stream().reduce((a, b) -> a.getId().compareTo(b.getId()) > 0 ? a : b).get();
            //Long id = Long.valueOf(users.size()+1);
            id = Long.valueOf(userOfMaxId == null ? 1 : userOfMaxId.getId() + 1);
        }else
            id = 1L;
        user.setId(id);
        /*user.setName("user"+id);
        user.setPassword("pwd"+id);
        user.setUserName("u"+id);*/
        users.add(user);
        return users;
    }

    @GET
    @Path("/users/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Optional<User> findById(@PathParam("uid") long uid){
        return users.stream()
                .filter(item->item.getId().equals(uid))
                .findFirst();
    }

    @GET
    @Path("/users")
    @Produces("application/json")
    public List<User> list(){
        return users;
    }

    @DELETE @Path("/users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> remove (@PathParam("userId") long id){
        users.removeIf(usr->usr.getId().equals(id));
        return users;
    }
}