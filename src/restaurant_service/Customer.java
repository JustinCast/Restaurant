
package restaurant_service;

//Customer class, required for various operations in the program
public class Customer extends Person {
    private int recurrence = 0;

    public Customer(String name, int ID, int recurrence) {
        super(name, ID);
        this.recurrence = recurrence;
    }

    public Customer() {}

    public int getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(int recurrence) {
        this.recurrence += recurrence;
    }
    
    
    
}
