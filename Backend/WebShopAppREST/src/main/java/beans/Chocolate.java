package beans;

public class Chocolate {
    private int id;
    private String name;
    private int price;
    private String chocolateKind;
    private String chocolateType;
    private int weight;
    private String description;
    private String image;

    public Chocolate(int id, String name, int price, String chocolateKind, String chocolateType, int weight, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.chocolateKind = chocolateKind;
        this.chocolateType = chocolateType;
        this.weight = weight;
        this.description = description;
        this.image = image;
    }
    public Chocolate() {}

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getChocolateKind() {
        return chocolateKind;
    }

    public void setChocolateKind(String chocolateKind) {
        this.chocolateKind = chocolateKind;
    }

    public String getChocolateType() {
        return chocolateType;
    }

    public void setChocolateType(String chocolateType) {
        this.chocolateType = chocolateType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

