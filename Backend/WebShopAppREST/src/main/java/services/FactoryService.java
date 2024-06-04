package services;

import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Chocolate;
import beans.Factory;
import beans.Location;
import dao.ChocolateDAO;
import dao.FactoryDAO;

@Path("/factories")
public class FactoryService {

    @Context
    ServletContext ctx;

    public FactoryService() {}

    @PostConstruct
    public void init() {
        if (ctx.getAttribute("factoryDAO") == null) {
            String contextPath = ctx.getRealPath("/");
            ctx.setAttribute("factoryDAO", new FactoryDAO(contextPath));
        }
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Factory> getAllLocations() {
    	FactoryDAO dao = (FactoryDAO) ctx.getAttribute("factoryDAO");
        return dao.getAll();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Factory getById(@PathParam("id") int id) {
    	FactoryDAO dao = (FactoryDAO) ctx.getAttribute("factoryDAO");
        return dao.getById(id);
    }
    @PUT
    @Path("/{factoryId}/addChocolate/{chocolateId}")
    public Response addChocolateToFactory(@PathParam("factoryId") int factoryId, @PathParam("chocolateId") int chocolateId) {
        FactoryDAO factoryDAO = (FactoryDAO) ctx.getAttribute("factoryDAO");
        ChocolateDAO chocolateDAO = (ChocolateDAO) ctx.getAttribute("chocolateDAO");
        
        Factory factory = factoryDAO.getById(factoryId);
        Chocolate chocolate = chocolateDAO.getById(chocolateId);
        
        if (factory != null && chocolate != null) {
            factory.getChocolates().add(chocolate);
            factoryDAO.updateFactory(factory);
            return Response.status(Response.Status.OK).entity("Chocolate added to factory.").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Factory or chocolate not found.").build();
        }
    }
}
