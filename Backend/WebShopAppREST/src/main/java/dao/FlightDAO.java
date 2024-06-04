package dao;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

import beans.Flight;

public class FlightDAO {

	HashMap<String,Flight> flights=new HashMap<String,Flight>();
	public FlightDAO() {
		flights.put("1",new Flight("1","W6 4045","Beograd","Pariz",160,9384));
		flights.put("2",new Flight("2","JU 4312","Beograd","Istanbul",100,18000));
		flights.put("3",new Flight("3","W6 7291","Beograd","Barselona",165,9970));
	}
	public Collection<Flight> getAll(){
		return flights.values();
	}
	public Collection<Flight> getBy(String odrediste,double minCena,double maxCena){
		return flights.values().stream().filter(b->b.getOdrediste().equalsIgnoreCase(odrediste)
				&&b.getCena()>minCena&&b.getCena()<=maxCena).collect(Collectors.toList());
	}
	public Flight create(Flight flight) {
		flight.setId(nextId());
		flights.put(flight.getId(), flight);
		return flight;
	}
	
	public String nextId() {
		Integer maxId=-1;
		for(String id:flights.keySet()) {
			if(maxId<Integer.parseInt(id)) {
				maxId=Integer.parseInt(id);
			}
		}
		maxId++;
		return maxId.toString();
	}
}
