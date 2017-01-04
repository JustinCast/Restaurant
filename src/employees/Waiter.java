/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package employees;

import java.util.ArrayList;
import restaurant_service.Address;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Waiter extends Employee{
    private ArrayList languages;
    private int atendedOrders;

    public Waiter(ArrayList languages,Address address,String telephone, String Email, String name, int ID) {
        super(address,telephone, Email, name, ID);
        this.languages = languages;
    }

    public Waiter() {
    }
    
    public String getLanguages() {
        String output = "";
        for(int i = 0; i < languages.size();i++){
            output += languages.get(i).toString() + "\n";
        }
        return output;
    }

    public void setLanguages(ArrayList languages) {
        this.languages = languages;
    }

    public int getAtendedOrders() {
        return atendedOrders;
    }

    public void setAtendedOrders(int atendedOrders) {
        this.atendedOrders += atendedOrders;
    }
    
    @Override
    public String toString() {
        return "Waiter{" + "languages=" + languages + '}';
    }
    
    

}
