package services;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import beans.Flight;
import dao.FlightDAO;
@Path("/flights")
public class FlightServices {
	@Context
	ServletContext ctx;
	public FlightServices() {}
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("flightDAO")==null) {
			ctx.setAttribute("flightDAO", new FlightDAO());
			
		}
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Flight> getAllFlights() {
		FlightDAO dao=(FlightDAO) ctx.getAttribute("flightDAO");
		return dao.getAll();
	}
	@GET
	@Path("{odrediste}/{minCena}/{maxCena}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Flight> getBy(@PathParam("odrediste") String odrediste,
			@PathParam("minCena") double minCena,@PathParam("maxCena") double maxCena) {
		FlightDAO dao=(FlightDAO) ctx.getAttribute("flightDAO");
		return dao.getBy(odrediste, minCena, maxCena);
	}
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Flight flight) {
		if(!isFlight(flight)) return Response.status(Response.Status.BAD_REQUEST).build();
		FlightDAO dao=(FlightDAO) ctx.getAttribute("flightDAO");
		Flight addedFlight=dao.create(flight);
		return Response.ok(addedFlight).build();
	}
	
	public boolean isFlight(Flight flight) {
		if(flight.getOdrediste()==null||flight.getOdrediste().isBlank()) return false;
		if(flight.getPolaziste()==null||flight.getPolaziste().isBlank()) return false;
		if(flight.getSifra()==null||flight.getSifra().isBlank()) return false;
		if(flight.getTrajanje()<0) return false;
		if(flight.getCena()<0) return false;
			return true;
	}
}
