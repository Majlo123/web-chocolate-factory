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

    public User findById(String id) {
        return usersList.stream()
                .filter(user -> user.getUsername().equals(id))
                .findFirst()
                .orElse(null);
    }

    public User findByUsernameAndPassword(String username, String password) {
        return usersList.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
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
}
