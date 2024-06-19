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

import beans.Factory;
import beans.User;

public class UserDAO {

    private List<User> usersList = new ArrayList<>();
    private String contextPath;

    public UserDAO() {}

    public UserDAO(String contextPath) {
        this.contextPath = contextPath;
        loadUsersFromFile();
    }

    private void loadUsersFromFile() {
        try (Reader reader = new FileReader(new File(contextPath, "resources/users.json"))) {
            Gson gson = new Gson();
            List<User> loadedUsers = gson.fromJson(reader, new TypeToken<List<User>>() {}.getType());
            if (loadedUsers != null) {
                usersList.clear();
                usersList.addAll(loadedUsers);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }
    public Collection<User> getAll() {
        return usersList;
    }

    public User getById(String id) {
        for (User user : getAll()) {
            if (user.getUsername().equals(id)) {
                return user;
            }
        }
        return null;
    }


    public User getByUsernameAndPassword(String username, String password) {
    	for (User user : usersList) {
            if (user.getUsername().equals(username)&&user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    
}
