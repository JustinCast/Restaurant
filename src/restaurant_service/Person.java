

package restaurant_service;

//Super abstract class of: employees and customers
public class Person {
    private String name;
    private int ID;

    public Person(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }

    public Person() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
    
}
