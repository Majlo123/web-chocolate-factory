package dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.time.format.DateTimeFormatter;

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
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();
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
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void saveUsersToFile() {
        try (FileWriter writer = new FileWriter(new File(contextPath, "resources/users.json"))) {
            Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
            gson.toJson(usersList, writer);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error saving users to file: " + e.getMessage());
        }
    }

    public void updateUser(User user) {
        for (int i = 0; i < usersList.size(); i++) {
            if (usersList.get(i).getUsername().equals(user.getUsername())) {
                usersList.set(i, user);
                break;
            }
        }

        saveUsersToFile();
    }

    public User create(User user) {
        usersList.add(user);
        saveUsersToFile();
        return user;
    }

    public List<User> getManagersWithoutFactory() {
        List<User> managersWithoutFactory = new ArrayList<>();
        for (User user : usersList) {
            if ("Menadžer".equals(user.getRole()) && user.getFactory() == null) {
                managersWithoutFactory.add(user);
            }
        }
        return managersWithoutFactory;
    }

    public void assignFactoryToManager(String username, Factory factory) {
        for (User user : usersList) {
            if (user.getUsername().equals(username)) {
                user.setFactory(factory);
                updateUser(user);
                break;
            }
        }
    }

    private static class LocalDateAdapter extends TypeAdapter<LocalDate> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
