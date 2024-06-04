package beans;

public class Chocolate {
    private int id;
    private String name;
    private int price;
    private String chocolate_kind;
    private String chocolate_type;
    private int weight;
    private String description;
    private String image;

    public Chocolate(int id, String name, int price, String chocolate_kind, String chocolate_type, int weight, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.chocolate_kind = chocolate_kind;
        this.chocolate_type = chocolate_type;
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
        return chocolate_kind;
    }

    public void setChocolateKind(String chocolate_kind) {
        this.chocolate_kind = chocolate_kind;
    }

    public String getChocolateType() {
        return chocolate_type;
    }

    public void setChocolateType(String chocolate_type) {
        this.chocolate_type = chocolate_type;
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

