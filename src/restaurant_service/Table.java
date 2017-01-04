
package restaurant_service;

import java.util.ArrayList;

/**
 * 
 * assigned to Order and Restaurant
 */
public class Table {
    private int tableNumber;
    private ArrayList<Customer> customers;

    public Table(int tableNumber, ArrayList<Customer> customers) {
        this.tableNumber = tableNumber;
        this.customers = customers;
    }

    public Table() {}
    
    /**
     *
     * @return tableNumber
     */
    public int getTableNumber() {
        return tableNumber;
    }

    /**
     *
     * @param tableNumber
     */
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    /**
     *
     * @return customers
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     *
     * @param customers
     */
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Table{" + "tableNumber=" + tableNumber + ", customers=" + customers + '}';
    }
    
    

}
