package beans;


import java.time.LocalDate;

public class User {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate birthDate;
    private String role;
    private Factory factory; // Null if the user is not a manager

    public User(String username, String password, String firstName, String lastName, String gender, LocalDate birthDate, String role, Factory factory) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
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
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", role='" + role + '\'' +
                ", factory=" + (factory != null ? factory.getName() : "None") +
                '}';
    }
}
