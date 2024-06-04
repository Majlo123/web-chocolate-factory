package services;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import beans.Chocolate;
import dao.ChocolateDAO;
@Path("/chocolates")
public class ChocolateService {
	@Context
	ServletContext ctx;
	public ChocolateService() {}
	@PostConstruct
	public void init() {
        if (ctx.getAttribute("chocolateDAO") == null) {
            String contextPath = ctx.getRealPath("/");
            ctx.setAttribute("chocolateDAO", new ChocolateDAO(contextPath));
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createChocolate(Chocolate chocolate) {
        ChocolateDAO chocolateDAO = (ChocolateDAO) ctx.getAttribute("chocolateDAO");
        Chocolate createdChocolate = chocolateDAO.create(chocolate);
        if (createdChocolate != null) {
            return Response.status(Response.Status.CREATED).entity(createdChocolate).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error creating chocolate.").build();
        }
    }
}
