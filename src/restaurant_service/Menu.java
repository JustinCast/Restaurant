

package restaurant_service;

import java.util.ArrayList;

//There is only one menu
public final class Menu {
    private static ArrayList<Dish> DISHES = new ArrayList<>();
    private static ArrayList<Drink> DRINKS = new ArrayList<>();
    private static int dishCounter = 1; //Static counter of the dishes

    public Menu(ArrayList<Dish> dishes,ArrayList<Drink> drinks) {
        Menu.DISHES = dishes;
        Menu.DRINKS = drinks;
        
    }

    public static ArrayList<Dish> getDISHES() {
        return DISHES;
    }
    
    public static void setDISHES(Dish dish){
        Menu.DISHES.add(dish);
        dishCounter += 1;
    }

    public static ArrayList<Drink> getDRINKS() {
        return DRINKS;
    }
    
    public static void setDRINKS(Drink drink){
        Menu.DRINKS.add(drink);
    }

    public static int getDishCounter() {
        return dishCounter;
    }
    
    //Search the dish by its name | returns null if not found
    public static Dish searchDish(int number){
        for(Dish dish : DISHES){
            if(dish.getDishNumber() == number)
                return dish;
        }
        return null;
    }
    
    //Search the dish by its name | returns null if not found
    public static Dish searchDishByName(String name){
        for(Dish dish : DISHES){
            if(dish.getDishName().equals(name))
                return dish;
        }
        return null;
    }
    
    
    
    //Search the drink by its name | returns null if not found
    public static Drink searchDrink(String drinkName){
        for(Drink drink : DRINKS)
            if(drink.getName().equals(drinkName))
                return drink;
        return null;
    }
}
