/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package employees;
import restaurant_service.Address;
/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Chef extends Employee{
    private int preparedOrders;
    public Chef(Address address,String telephone, String Email, String name, int ID) {
        super(address,telephone, Email, name, ID);
    }

    public Chef() {
    }

    public int getPreparedOrders() {
        return preparedOrders;
    }

    public void setPreparedOrders(int preparedOrders) {
        this.preparedOrders += preparedOrders;
    }
    
    
    
}
