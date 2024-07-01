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
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.Chocolate;
import beans.Factory;

public class ChocolateDAO {

    private List<Chocolate> chocolateList = new ArrayList<>();
    private String contextPath;
    private Gson gson = new Gson();
    
    public ChocolateDAO() {}
    public ChocolateDAO(String contextPath) throws Exception {
        this.contextPath = contextPath;
        loadChocolatesFromFile();
    }

    public List<Chocolate> getAll() {
        return chocolateList;
    }

    private void loadChocolatesFromFile() throws Exception {
    	Path path = Paths.get(ChocolateDAO.class.getResource("/static/chocolates.json").toURI());
    	Type listType = new TypeToken<List<Chocolate>>() {}.getType();
        String json = String.join("", Files.readAllLines(path));
        chocolateList = gson.fromJson(json, listType);
    }
    private void saveChocolatesToFile() throws IOException, URISyntaxException {
    	Path path = Paths.get(ChocolateDAO.class.getResource("/static/chocolates.json").toURI());
    	Writer writer = new FileWriter(String.join("", Files.readAllLines(path)), StandardCharsets.UTF_8);
        gson.toJson(chocolateList, writer);
        writer.close();
    }

    public Chocolate create(Chocolate chocolate) throws IOException, URISyntaxException {
        chocolate.setId(nextId());
        chocolateList.add(chocolate);
        saveChocolatesToFile();
        return chocolate;
    }

    public int nextId() {
        int maxId = -1;
        for (Chocolate chocolate : chocolateList) {
            if (maxId < chocolate.getId()) {
                maxId = chocolate.getId();
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
