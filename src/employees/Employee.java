/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package employees;
import restaurant_service.Person;
import restaurant_service.Address;

/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class Employee extends Person {
    private String telephone;
    private Address address;
    private String Email;

    public Employee(Address address,String telephone, String Email,String name, int ID) {
        super(name, ID);
        this.telephone = telephone;
        this.Email = Email;
        this.address = address;
    }

    public Employee() {
    }
        
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        String output = "";
        output += "Provincia: " + address.getProvince()  + "\n" +
                "Canton: " + address.getCanton() + "\n" + 
                "Distrito: " + address.getDistrict() + "\n" + 
                "Direccion exacta: " + address.getExactAddress();
        return output;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "Employee{" + "telephone=" + telephone + ", address=" + address + ", Email=" + Email + '}';
    }

    
    
    

}
