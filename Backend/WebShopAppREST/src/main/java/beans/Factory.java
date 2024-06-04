package beans;

import java.util.List;

public class Factory {
    private int id;
    private String name;
    private Location location;
    private String logo;
    private double averageRating;
    private String workingHours;
    private String workStatus;
    private List<Chocolate> chocolates;
    private String comments;

    public Factory(int id, String name, Location location, String logo, double averageRating, String workingHours, String workStatus, List<Chocolate> chocolates, String comments) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.logo = logo;
        this.averageRating = averageRating;
        this.workingHours = workingHours;
        this.workStatus = workStatus;
        this.chocolates = chocolates;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public List<Chocolate> getChocolates() {
        return chocolates;
    }

    public void setChocolates(List<Chocolate> chocolates) {
        this.chocolates = chocolates;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

