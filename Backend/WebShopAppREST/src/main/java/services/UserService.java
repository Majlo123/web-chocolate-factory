package services;

import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Factory;
import beans.User;
import dao.FactoryDAO;
import dao.UserDAO;

@Path("/users")
public class UserService {

    @Context
    ServletContext ctx;

    public UserService() {}

    @PostConstruct
    public void init() {
        if (ctx.getAttribute("userDAO") == null) {
            String contextPath = ctx.getRealPath("/");
            ctx.setAttribute("userDAO", new UserDAO(contextPath));
        }
        if (ctx.getAttribute("factoryDAO") == null) {
            String contextPath = ctx.getRealPath("/");
            ctx.setAttribute("factoryDAO", new FactoryDAO(contextPath));
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<User> getAllUsers() {
        UserDAO dao = (UserDAO) ctx.getAttribute("userDAO");
        return dao.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") String id) {
        UserDAO dao = (UserDAO) ctx.getAttribute("userDAO");
        return dao.getById(id);
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(User user) {
        UserDAO dao = (UserDAO) ctx.getAttribute("userDAO");
        return dao.getByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @POST
    @Path("/signupworker/{factoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUpWorker(@PathParam("factoryId") int factoryId, User user) {
        UserDAO dao = (UserDAO) ctx.getAttribute("userDAO");
        FactoryDAO factoryDao = (FactoryDAO) ctx.getAttribute("factoryDAO");
        if (dao.getById(user.getUsername()) != null) {
            return Response.status(Response.Status.CONFLICT).entity("Username exists.").build();
        }

        Factory factory = factoryDao.getById(factoryId);
        if (factory == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Factory not found.").build();
        }

        user.setRole("Radnik");
        user.setFactory(factory);

        dao.create(user);

        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @POST
    @Path("/signupuser")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signUpUser(User user) {
        UserDAO dao = (UserDAO) ctx.getAttribute("userDAO");


        if (dao.getById(user.getUsername()) != null) {
            return Response.status(Response.Status.CONFLICT).entity("Username exists.").build();
        }

        user.setRole("Kupac");
        dao.create(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }
}
