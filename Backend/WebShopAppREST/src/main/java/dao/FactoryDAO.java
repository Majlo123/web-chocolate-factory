package dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import beans.Factory;
import beans.Location;

public class FactoryDAO {

	private List<Factory> factoryList = new ArrayList<>();
    private String contextPath;

    public FactoryDAO() {}
    public FactoryDAO(String contextPath) {
        this.contextPath = contextPath;
        loadFactoriesFromFile();
    }

    public List<Factory> getAll() {
        return factoryList;
    }

    private void loadFactoriesFromFile() {
        try (Reader reader = new FileReader(new File(contextPath, "resources/factories.json"))) {
            Gson gson = new Gson();
            List<Factory> loadedLocations = gson.fromJson(reader, new TypeToken<List<Factory>>() {}.getType());

            if (loadedLocations != null) {
            	factoryList.clear(); // Clear existing locations
            	factoryList.addAll(loadedLocations);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading factories from file: " + e.getMessage());
        }
    }
    public Factory getById(int id) {
        for (Factory factory : factoryList) {
            if (factory.getId() == id) {
                return factory;
            }
        }
        return null; 
    }
    public void updateFactory(Factory factory) {
        try (Writer writer = new FileWriter(new File(contextPath, "resources/factories.json"))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(factoryList, writer);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error updating factory in file: " + e.getMessage());
        }
    }
}
