package dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import beans.Chocolate;
import beans.Factory;

public class FactoryDAO {

    private List<Factory> factoryList = new ArrayList<>();
    private String contextPath;
    private Gson gson = new Gson();
    
    public FactoryDAO() {}
    public FactoryDAO(String contextPath) throws Exception {
        this.contextPath = contextPath;
        loadFactoriesFromFile();
    }

    public List<Factory> getAll() {
        return factoryList;
    }
    public List<Factory> searchFactories(String name, String chocolateName, String location, Double averageRating) {
        return factoryList.stream()
                .filter(factory -> (name == null || factory.getName().toLowerCase().contains(name.toLowerCase())) &&
                                   (chocolateName == null || factory.getChocolates().stream().anyMatch(chocolate -> chocolate.getName().toLowerCase().contains(chocolateName.toLowerCase()))) &&
                                   (location == null || factory.getLocation().getCity().toLowerCase().contains(location.toLowerCase()) || factory.getLocation().getState().toLowerCase().contains(location.toLowerCase())) &&
                                   (averageRating == null || factory.getAverageRating() >= averageRating))
                .collect(Collectors.toList());
    }
    private void loadFactoriesFromFile() throws Exception {
    	Path path = Paths.get(FactoryDAO.class.getResource("/static/factories.json").toURI());
    	Type listType = new TypeToken<List<Factory>>() {}.getType();
        String json = String.join("", Files.readAllLines(path));
        factoryList = gson.fromJson(json, listType);
    }
    private void saveFactoriesToFile() throws IOException, URISyntaxException {
    	Path path = Paths.get(FactoryDAO.class.getResource("/static/factories.json").toURI());
    	Writer writer = new FileWriter(String.join("", Files.readAllLines(path)), StandardCharsets.UTF_8);
        gson.toJson(factoryList, writer);
        writer.close();
    }
    public Factory getById(int id) {
        for (Factory factory : factoryList) {
            if (factory.getId() == id) {
                return factory;
            }
        }
        return null;
    }
    public Factory create(Factory factory) throws IOException, URISyntaxException {
    	factory.setId(nextId());
    	factoryList.add(factory);
        saveFactoriesToFile();
        return factory;
    }

    public int nextId() {
        int maxId = -1;
        for (Factory factory : factoryList) {
            if (maxId < factory.getId()) {
                maxId = factory.getId();
            }
        }
        maxId++;
        return maxId;
    }
    public void updateFactory(Factory factory) {
        for (int i = 0; i < factoryList.size(); i++) {
            if (factoryList.get(i).getId() == factory.getId()) {
                factoryList.set(i, factory);
                break;
            }
        }
        try (Writer writer = new FileWriter(new File(contextPath, "resources/factories.json"))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(factoryList, writer);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error updating factory in file: " + e.getMessage());
        }
    }

    public void deleteChocolateFromFactory(int chocolateId, int factoryId) {
        Factory factory = getById(factoryId);
        if (factory != null) {
            Chocolate chocolateToRemove = null;
            for (Chocolate chocolate : factory.getChocolates()) {
                if (chocolate.getId() == chocolateId) {
                    chocolateToRemove = chocolate;
                    break;
                }
            }
            if (chocolateToRemove != null) {
                factory.getChocolates().remove(chocolateToRemove);
                updateFactory(factory);
            }
        }
    }
    public void deleteFactory(int factoryId) throws IOException, URISyntaxException {
        Factory factory = getById(factoryId);
        if (factory != null) {
            factoryList.remove(factory);
            saveFactoriesToFile();
        } 
   
    }
    }

