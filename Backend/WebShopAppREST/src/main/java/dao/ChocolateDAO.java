package dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Chocolate;

public class ChocolateDAO {

	private List<Chocolate> chocolateList = new ArrayList<>();
    private String contextPath;

    public ChocolateDAO() {}
    public ChocolateDAO(String contextPath) {
        this.contextPath = contextPath;
        loadChocolatesFromFile();
    }

    public List<Chocolate> getAll() {
        return chocolateList;
    }

    private void loadChocolatesFromFile() {
        try (Reader reader = new FileReader(new File(contextPath, "resources/chocolates.json"))) {
            Gson gson = new Gson();
            List<Chocolate> loadedLocations = gson.fromJson(reader, new TypeToken<List<Chocolate>>() {}.getType());

            if (loadedLocations != null) {
            	chocolateList.clear(); // Clear existing locations
            	chocolateList.addAll(loadedLocations);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading chocolates from file: " + e.getMessage());
        }
    }
    public Chocolate create(Chocolate chocolate) {
    	chocolate.setId(nextId());
		chocolateList.add(chocolate);
		loadChocolatesFromFile();
		return chocolate;		
	}
    public int nextId() {
		int maxId=-1;
		for (Chocolate chocolate : chocolateList) {
            if (maxId<chocolate.getId()) {
                maxId=chocolate.getId();
            }
        }
		maxId++;
		return maxId;
	}
	 public Chocolate getById(int id) {
        for (Chocolate chocolate : chocolateList) {
            if (chocolate.getId() == id) {
                return chocolate;
            }
        }
        return null; 
    }
}
