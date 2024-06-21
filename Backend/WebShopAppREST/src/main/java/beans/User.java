package beans;

import java.time.LocalDate;
import javax.json.bind.annotation.JsonbDateFormat;

public class User {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String gender;
    @JsonbDateFormat("yyyy-MM-dd")
    private LocalDate birth_date;
    private String role;
    private Factory factory; 

    public User() {}
    public User(String username, String password, String first_name, String last_name, String gender, LocalDate birth_date, String role, Factory factory) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.birth_date = birth_date;
        this.role = role;
        this.factory = "Menadžer".equals(role) ? factory : null;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birth_date;
    }

    public void setBirthDate(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        if (!"Menadžer".equals(role)) {
            this.factory = null;
        }
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        if ("Menadžer".equals(this.role)) {
            this.factory = factory;
        }
    }
}
