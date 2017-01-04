/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import java.util.ArrayList;
import restaurant_service.Customer;
import restaurant_service.Dish;
import restaurant_service.Drink;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Report {
    private final ArrayList<Dish> totalPerPlate;
    private final ArrayList<Drink> totalPerDrink;
    private ArrayList totalSold;
    private final ArrayList<Customer> totalServedPerCustomer;

    public Report(ArrayList<Dish> totalPerPlate, ArrayList<Drink> totalPerDrink, ArrayList totalSold, ArrayList<Customer> totalServedPerCustomer) {
        this.totalPerPlate = totalPerPlate;
        this.totalPerDrink = totalPerDrink;
        this.totalSold = totalSold;
        this.totalServedPerCustomer = totalServedPerCustomer;
    }

    public ArrayList<Dish> getTotalPerPlate() {
        return totalPerPlate;
    }

    public void setTotalPerPlate(Dish plate) {
        this.totalPerPlate.add(plate);
    }

    public ArrayList<Drink> getTotalPerDrink() {
        return totalPerDrink;
    }

    public void setTotalPerDrink(Drink drink) {
        this.totalPerDrink.add(drink);
    }

    public ArrayList getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(ArrayList totalSold) {
        this.totalSold = totalSold;
    }

    public ArrayList<Customer> getTotalServedPerCustomer() {
        return totalServedPerCustomer;
    }

    public void setTotalServedPerCustomer(Customer customer) {
        this.totalServedPerCustomer.add(customer);
    }

    @Override
    public String toString() {
        return "Report{" + "totalPerPlate=" + totalPerPlate + ", totalPerDrink=" 
                + totalPerDrink + ", totalSold=" + totalSold + ", totalServedPerCustomer=" + totalServedPerCustomer + '}';
    }
    
    
    
    

}
