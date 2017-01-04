
package restaurant_service;

import employees.Waiter;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order {
    private Waiter waiter;
    private ArrayList<Drink> drinks = new ArrayList<>();
    private ArrayList<Dish> dishes = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private int orderNumber;
    private int aux;
    private  int waiterID;
    private Table table;
    private LocalDate date;

    public Order(Waiter waiter, ArrayList<Drink> drink, ArrayList<Dish> dishes,int orderNumber) {
        this.waiter = waiter;
        this.drinks = drink;
        this.dishes = dishes;
        this.orderNumber = orderNumber;
        this.waiterID = waiter.getID();
    }

    public Order() {}
    
    public Waiter getWaiter() {
        return waiter;
    }

    public void setWaiter(Waiter waiter) {
        this.waiter = waiter;
        this.waiterID = waiter.getID();
    }

    //Get drinks in string form for visualization
    public String getDrinks() {
        String output = "";
        for(Drink drink : drinks)
            output += drink.getName();
        if(output.length() == 0)
            output = "\t\t --";
        return output;
    }
    public ArrayList<Drink> getDRINKS() {
        return drinks;
    }
    
    public void setDrink(Drink drink) {
        this.drinks.add(drink);
    }
    public void setDrinks(ArrayList<Drink> drinks) {
        this.drinks = drinks;
    }

    //Get dishes in string form for visualization
    public String getDishes() {
        String output = "";
        for(Dish dish : dishes)
            output += dish.getDishName() + "\n";
        if(output.length() == 0)
            output = "\t\t\t  --";
        return output;
    }
    public ArrayList<Dish> getDISHES() {
        return dishes;
    }

    public void setDishes(Dish dish) {
        this.dishes.add(dish);
    }
    public void setDISHES(Dish dish) {
        this.dishes.add(dish);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Customer customer) {
        customers.add(customer);
    }
    
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Table getTable() {
        return table;
    }
    
    public void setTable(Table table) {
        this.table = table;
    }

    public int getAux() {
        aux = table.getTableNumber();
        return aux;
    }

    public int getWaiterID() {
        return waiterID;
    }

    public void setWaiterID(int waiterID) {
        this.waiterID = waiterID;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public void increaseRecurrence(int id){
        for(Customer customer : customers)
            if(customer.getID() == id)
                customer.setRecurrence(1);            
    }
    
    public Customer searchCustomer(int id){
        for(Customer customer : customers)
            if(customer.getID() == id)
                return customer;
        return null;
    }
    
    @Override
    public String toString() {
        return "Order{" + "waiter=" + waiter + ", drinks=" + drinks + 
                ", dishes=" + dishes + ", orderNumber=" + orderNumber + ", table=" + table + '}';
    }
    
    
}
