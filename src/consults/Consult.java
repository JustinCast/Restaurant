/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package consults;

import employees.Chef;
import employees.Waiter;
import java.util.ArrayList;
import java.util.Date;
import restaurant_service.Customer;
import restaurant_service.Dish;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Consult {
    private Waiter waitherOfTheMonth;
    private Chef chefOfTheMonth;
    private final ArrayList<Dish> mostConsumedDishes;
    private Date rushHour;
    private Customer mostFrecuent;

    public Consult(Waiter waitherOfTheMonth, Chef chefOfTheMonth, ArrayList<Dish> mostConsumedDishes, Date rushHour, Customer mostFrecuent) {
        this.waitherOfTheMonth = waitherOfTheMonth;
        this.chefOfTheMonth = chefOfTheMonth;
        this.mostConsumedDishes = mostConsumedDishes;
        this.rushHour = rushHour;
        this.mostFrecuent = mostFrecuent;
    }

    public Waiter getWaitherOfTheMonth() {
        return waitherOfTheMonth;
    }

    public void setWaitherOfTheMonth(Waiter waitherOfTheMonth) {
        this.waitherOfTheMonth = waitherOfTheMonth;
    }

    public Chef getChefOfTheMonth() {
        return chefOfTheMonth;
    }

    public void setChefOfTheMonth(Chef chefOfTheMonth) {
        this.chefOfTheMonth = chefOfTheMonth;
    }

    public ArrayList<Dish> getMostConsumedDishes() {
        return mostConsumedDishes;
    }

    public void setMostConsumedDishes(Dish dish) {
        this.mostConsumedDishes.add(dish);
    }

    public Date getRushHour() {
        return rushHour;
    }

    public void setRushHour(Date rushHour) {
        this.rushHour = rushHour;
    }

    public Customer getMostFrecuent() {
        return mostFrecuent;
    }

    public void setMostFrecuent(Customer mostFrecuent) {
        this.mostFrecuent = mostFrecuent;
    }

    @Override
    public String toString() {
        return "Consult{" + "waitherOfTheMonth=" + waitherOfTheMonth + ", chefOfTheMonth=" 
                + chefOfTheMonth + ", mostConsumedDishes=" + mostConsumedDishes + ", rushHour=" + rushHour 
                + ", mostFrecuent=" + mostFrecuent + '}';
    }
    
    

}
