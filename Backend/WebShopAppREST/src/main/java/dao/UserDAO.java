package dao;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import beans.User;

public class UserDAO {

	private List<User> usersList = new ArrayList<>();
    private String contextPath;

    public UserDAO() {}
    public UserDAO(String contextPath) {
        this.contextPath = contextPath;
        loadUsersFromFile();
    }

    public Collection<User> getAll() {
        return usersList;
    }

    private void loadUsersFromFile() {
        try (Reader reader = new FileReader(new File(contextPath, "resources/users.json"))) {
            Gson gson = new Gson();
            List<User> loadedLocations = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());

            if (loadedLocations != null) {
            	usersList.clear(); // Clear existing locations
            	usersList.addAll(loadedLocations);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }
}
