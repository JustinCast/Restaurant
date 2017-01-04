

package restaurant_service;

public class Drink {
    private String type;
    private String name;
    private int price;
    private int size;

    public Drink(String type, String name, int price, int size) {
        this.type = type;
        this.name = name;
        this.price = price;
        this.size = size;
    }

    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Drink{" + "type=" + type + ", name=" + name + ", price=" + price + ", size=" + size + '}';
    }

    
    
    
}
