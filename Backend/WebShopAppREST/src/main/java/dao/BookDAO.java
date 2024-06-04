package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import beans.Book;

public class BookDAO {
	HashMap<String,Book> books=new HashMap<String,Book>();
	public BookDAO() {
		books.put("1", new Book("1", "Rat i mir", "Lav Tolstoj", "Vulkan",2000.0));
		books.put("2", new Book("2", "Koreni", "Dobrica Ćosić", "Logos", 3000.0));
		books.put("3", new Book("3", "Braća Karamazovi", "Fjodor Dostojevski", "Laguna", 2500.0));
		books.put("4", new Book("4", "Na Drini ćuprija", "Ivo Andrić", "Logos", 1800.0));
	}
	public Collection<Book> getAll(){
		return books.values();
		
	}
	public Collection<Book> getByPublisher(String publisher,double minPrice,double maxPrice){
		return books.values().stream().filter(b->b.getPublisher().equalsIgnoreCase(publisher)
				&& b.getPrice()>minPrice&&b.getPrice()<=maxPrice)
				.collect(Collectors.toList());
	}
	public Book create(Book book) {
		book.setId(nextId());
		books.put(book.getId(), book);
		return book;		
	}
	public Book deleteByName(String name) {
		for(Book book:books.values()) {
			if(book.getName().equalsIgnoreCase(name)) {
				return books.remove(book.getId());
			}
		}
		return null;
	}
	public boolean isBookExist(String name) {
		return books.values().stream().anyMatch(b->b.getName().equalsIgnoreCase(name));
	}
	
	public String nextId() {
		Integer maxId=-1;
		for(String id:books.keySet()) {
			if(maxId<Integer.parseInt(id)) {
				maxId=Integer.parseInt(id);
			}
		}
		maxId++;
		return maxId.toString();
	}
}
