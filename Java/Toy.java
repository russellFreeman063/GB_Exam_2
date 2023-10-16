public class Toy {
    private int id;

    private String name;
    private double weight;
    private int quantity;

    public Toy(int id, String name, double weight, int quantity) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.quantity = quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String printToy() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", quantity=" + quantity;
    }
}
