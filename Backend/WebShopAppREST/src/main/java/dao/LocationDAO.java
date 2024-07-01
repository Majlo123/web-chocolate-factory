package dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Factory;
import beans.Location;

public class LocationDAO {

    private List<Location> locationsList = new ArrayList<>();
    private String contextPath;
    private Gson gson = new Gson();
    public LocationDAO() {}
    public LocationDAO(String contextPath) throws Exception {
        this.contextPath = contextPath;
        loadLocationsFromFile();
    }

    public Collection<Location> getAll() {
        return locationsList;
    }
    
    private void loadLocationsFromFile() throws Exception {
    	Path path = Paths.get(LocationDAO.class.getResource("/static/locations.json").toURI());
    	Type listType = new TypeToken<List<Location>>() {}.getType();
        String json = String.join("", Files.readAllLines(path));
        locationsList = gson.fromJson(json, listType);
    }
}
