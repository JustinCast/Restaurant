
package restaurant_service;

import java.util.ArrayList;


public class Dish {
    private int dishNumber;
    private String dishName;
    private ArrayList MainIngredients;
    private int PriceWihtoutTax;
    private float calories;
    private String state;

    public Dish(int dishNumber, String dishName, ArrayList MainIngredients, int PriceWihtoutTax, 
            float calories, String state) {
        this.dishNumber = dishNumber;
        this.dishName = dishName;
        this.MainIngredients = MainIngredients;
        this.PriceWihtoutTax = PriceWihtoutTax;
        this.calories = calories;
        this.state = state;
    }

    public Dish() {}
    
    public int getDishNumber() {
        return dishNumber;
    }

    public void setDishNumber(int dishNumber) {
        this.dishNumber = dishNumber;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }
    //Get a string for visualization
    public String getMainIngredients() {
        String output = "";
        for(int i  = 0; i < MainIngredients.size(); i++)
            output += MainIngredients.get(i).toString() + "\n";
        return output;
    }

    public void setMainIngredients(ArrayList MainIngredients) {
        this.MainIngredients = MainIngredients;
    }

    public int getPriceWihtoutTax() {
        return PriceWihtoutTax;
    }

    public void setPriceWihtoutTax(int PriceWihtoutTax) {
        this.PriceWihtoutTax = PriceWihtoutTax;
    }

    public float getCalories() {
        return calories;
    }

    public void setCalories(float calories) {
        this.calories = calories;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Dish{" + "dishNumber=" + dishNumber + ", dishName=" + dishName + 
                ", MainIngredients=" + MainIngredients + ", PriceWihtoutTax=" + PriceWihtoutTax + 
                ", calories=" + calories + ", state=" + state + '}';
    }
    
    

}
