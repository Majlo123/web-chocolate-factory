package services;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.validation.constraints.Pattern;
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

import beans.Book;
import dao.BookDAO;

@Path("/books")
public class BookService {
	@Context
	ServletContext ctx;
	public BookService() {}
	@PostConstruct
	public void init() {
		if(ctx.getAttribute("bookDAO")==null) {
			ctx.setAttribute("bookDAO", new BookDAO());
		}
	}
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Book> getAllBooks() {
		BookDAO dao=(BookDAO) ctx.getAttribute("bookDAO");
		return dao.getAll();
	}
	@GET
	@Path("/{publisher}/{minPrice}/{maxPrice}")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Book> getByPublisher(
	    @PathParam("publisher") String publisher,
	    @PathParam("minPrice") double minPrice,
	    @PathParam("maxPrice") double maxPrice) {
	    
	    BookDAO dao = (BookDAO) ctx.getAttribute("bookDAO");
	    return dao.getByPublisher(publisher, minPrice, maxPrice);
	}
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Book book) {
		if(!isBookValid(book)) return Response.status(Response.Status.BAD_REQUEST).build();
		BookDAO dao = (BookDAO) ctx.getAttribute("bookDAO");
		Book addedBook=dao.create(book);
		return Response.ok(addedBook).build();
	}
	@DELETE
	@Path("/{name}")
	public Response deleteByName(@PathParam("name") String name) {
		if(!isBookNameExist(name)) return Response.status(Response.Status.BAD_REQUEST).build();
		BookDAO dao = (BookDAO) ctx.getAttribute("bookDAO");
		Book deletedBook=dao.deleteByName(name);
		return Response.ok(deletedBook).build();
	}
	
	public boolean isBookValid(Book book) {
		if(book.getName()==null||book.getName().isBlank()) return false;
		if(book.getAuthor()==null||book.getAuthor().isBlank()) return false;
		if(book.getPublisher()==null||book.getPublisher().isBlank()) return false;
		if(book.getPrice()<0) return false;
		return true;
	}
	public boolean isBookNameExist(String name) {
		BookDAO dao = (BookDAO) ctx.getAttribute("bookDAO");
	    return dao.isBookExist(name);
	}
}
