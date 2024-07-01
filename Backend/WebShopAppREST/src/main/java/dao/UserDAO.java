package dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import beans.User;

public class UserDAO {

    private List<User> usersList = new ArrayList<>();
    private String contextPath;
    private Gson gson;

    public UserDAO() {}

    public UserDAO(String contextPath) throws Exception {
        this.contextPath = contextPath;
        this.gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();
        loadUsersFromFile();
    }

    private void loadUsersFromFile() throws Exception {
        Path path = Paths.get(UserDAO.class.getResource("/static/users.json").toURI());
        Type listType = new TypeToken<List<User>>() {}.getType();
        String json = String.join("", Files.readAllLines(path));
        usersList = gson.fromJson(json, listType);
    }

    private void saveUsersToFile() throws IOException, URISyntaxException {
        Path path = Paths.get(UserDAO.class.getResource("/static/users.json").toURI());
        Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8);
        gson.toJson(usersList, writer);
        writer.close();
    }

    public Collection<User> getAll() {
        return usersList;
    }

    public Collection<User> searchUsers(String firstName, String lastName, String username) {
        return usersList.stream()
                .filter(user -> (firstName == null || user.getFirstName().toLowerCase().contains(firstName.toLowerCase())) &&
                                (lastName == null || user.getLastName().toLowerCase().contains(lastName.toLowerCase())) &&
                                (username == null || user.getUsername().toLowerCase().contains(username.toLowerCase())))
                .collect(Collectors.toList());
    }

    public Collection<User> sortUsers(String sortBy, boolean ascending) {
        Comparator<User> comparator;
        switch (sortBy) {
            case "firstName":
                comparator = Comparator.comparing(User::getFirstName);
                break;
            case "lastName":
                comparator = Comparator.comparing(User::getLastName);
                break;
            case "username":
                comparator = Comparator.comparing(User::getUsername);
                break;
            default:
                throw new IllegalArgumentException("Invalid sort parameter");
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        return usersList.stream().sorted(comparator).collect(Collectors.toList());
    }

    public Collection<User> filterUsers(String role, String userType) {
        return usersList.stream()
                .filter(user -> (role == null || user.getRole().equals(role))) 
                .collect(Collectors.toList());
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
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) throws IOException, URISyntaxException {
        usersList.add(user);
        saveUsersToFile();
    }

    public void updateUser(User user) throws IOException, URISyntaxException {
        User existingUser = getById(user.getUsername());
        if (existingUser != null) {
            usersList.remove(existingUser);
            usersList.add(user);
            saveUsersToFile();
        }
    }

    public void deleteUser(String username) throws IOException, URISyntaxException {
        User user = getById(username);
        if (user != null) {
            usersList.remove(user);
            saveUsersToFile();
        }
    }

    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        @Override
        public void write(JsonWriter jsonWriter, LocalDate localDate) throws IOException {
            jsonWriter.value(localDate.format(formatter));
        }

        @Override
        public LocalDate read(JsonReader jsonReader) throws IOException {
            return LocalDate.parse(jsonReader.nextString(), formatter);
        }
    }
}
