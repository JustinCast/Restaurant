/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restaurant_service;

import employees.Employee;
import java.awt.Image;
import java.util.ArrayList;

//There only one in the program
public final class Restaurant {
    private static String name;
    private static Image logo;
    private static String telephone;
    private static Address address;
    private static String Email;
    private static final ArrayList<Order> ORDERS = new ArrayList<>(); //ordenes que están esperando ser tomadas
    private static final ArrayList<Order> TAKEN_ORDERS = new ArrayList<>(); //ordenes que están esperando ser preparadas
    private static final ArrayList<Order> PREPARED_ORDERS = new ArrayList<>();  //ordenes que ya fueron tomadas
    private static final ArrayList<Order> CANCELLED_ORDERS = new ArrayList<>(); //ordenes que ya fueron tomadas y preparadas
    private static int orderCounter = 1;
    private static final ArrayList<Employee> EMPLOYEES = new ArrayList<>();
    private static final ArrayList<Customer> CUSTOMER_HISTORY = new ArrayList<>();
    private static final ArrayList<Table> BUSY_TABLES = new ArrayList<>();
    private static final ArrayList<Table> PENDING_ATTEND = new ArrayList<>();
    private static final ArrayList<Table> FREE_TABLES = new ArrayList<>();
    private static final int MASTER_KEY = 1524;


    public Restaurant() {
    }
    
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Restaurant.name = name;
    }

    public static Image getLogo() {
        return logo;
    }

    public static void setLogo(Image logo) {
        Restaurant.logo = logo;
    }

    public static String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone
     */
    public static void setTelephone(String telephone) {
        Restaurant.telephone = telephone;
    }

    /**
     *
     * @return address
     */
    public static Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public static void setAddress(Address address) {
        Restaurant.address = address;
    }
    
    /**
     *
     * @return Email
     */
    public static String getEmail() {
        return Email;
    }

    /**
     *
     * @param Email
     */
    public static void setEmail(String Email) {
        Restaurant.Email = Email;
    }

    /**
     *
     * @return EMPLOYEES 
     */
    public static ArrayList<Employee> getEmployees() {
        return EMPLOYEES;
    }

    /**
     *
     * @param employee
     */
    public static void setEmployees(Employee employee) {
        Restaurant.EMPLOYEES.add(employee);
    }

    public static ArrayList<Customer> getCUSTOMER_HISTORY() {
        return CUSTOMER_HISTORY;
    }
    
    public static void setCUSTOMER_HISTORY(Customer customer){
        CUSTOMER_HISTORY.add(customer);
    }
    
    /**
     *
     * @return the ArrayList for handle orders in other class
     */
    public static ArrayList<Order> getOrders() {
        return ORDERS;
    }

    /**
     *
     * @param order
     */
    public static void setOrders(Order order) {
        ORDERS.add(order);
        orderCounter += 1;
    }

    /**
     *
     * @return TAKEN_ORDERS
     */
    public static ArrayList<Order> getTAKEN_ORDERS() {
        return TAKEN_ORDERS;
    }
    
    /**
     *
     * @param order
     */
    public static void setTakenOrders(Order order){
        TAKEN_ORDERS.add(order);
    }
    
    /**
     *
     * @return orderCounter
     */
    public static int getOrderCounter() {
        return orderCounter;
    }
    
    /**
     *
     * @param orderNumber
     * @return order
     */
    public static Order searchOrder(int orderNumber){
        for(Order order : ORDERS)
            if(order.getOrderNumber() == orderNumber)
                return order;
        return null;
    }

    /**
     *
     * @param orderNumber
     * @return order
     */
    public static Order searchTakenOrder(int orderNumber){
        for(Order order : TAKEN_ORDERS)
            if(order.getOrderNumber() == orderNumber)
                return order;
        return null;
    }
    /**
     *
     * @param orderNumber
     * @return order
     */
    public static Order searchPreparedOrder(int orderNumber){
        for(Order order : PREPARED_ORDERS)
            if(order.getOrderNumber() == orderNumber)
                return order;
        return null;
    }
    
    /**
     *
     * @param ID
     * @return employee;

     */
    public static Employee searchEmployee(int ID){
        for(Employee employee : Restaurant.getEmployees())
            if(employee.getID() == ID)
                return employee;
        
        return null;
    }
    /**
     * @param name
     * @return 
     * busca el cliente por el nombre, necesario para algunas operaciones
    */
    public static Employee searchEmployeeByName(String name){
        for(Employee employee : Restaurant.getEmployees())
            if(employee.getName().equals(name))
                return employee;
        return null;
    }

    /**
     *
     * @return BUSY_TABLES
     */
    public static ArrayList<Table> getBUSY_TABLES() {
        return BUSY_TABLES ;
    }
    
    /**
     *
     * @param busy
     */
    public static void setBUSY_TABLES(Table busy){
        BUSY_TABLES.add(busy);
    }
    
    /**
     *
     * @return PENDING_ATTEND
     */
    public static ArrayList<Table> getPENDING_ATTEND() {
        return PENDING_ATTEND;
    }
    
    /**
     *
     * @param pending
     */
    public static void setPENDING_ATTEND(Table pending){
        PENDING_ATTEND.add(pending);
    }

    /**
     *
     * @return FREE_TABLES
     */
    public static ArrayList<Table> getFREE_TABLES() {
        return FREE_TABLES;
    }
    
    /**
     *
     * @param free
     */
    public static void setFREE_TABLES(Table free){
        FREE_TABLES.add(free);
    }
    /**
     * 
     * @return PREPARED_ORDERS
     */
    public static ArrayList<Order> getPREPARED_ORDERS(){
        return PREPARED_ORDERS;
    }
    
    /**
    * this method add order to prepared, necesary to know who is the chef of the month
     * @param order
    */
    public static void setPREPARED_ORDERS(Order order){
        PREPARED_ORDERS.add(order);
    }
    
    /**
     *
     * @return CANCELLED_ORDERS
     */
    public static ArrayList<Order> getCANCELLED_ORDERS() {
        return CANCELLED_ORDERS;
    }
    
    /**
     *
     * @param order
     */
    public static void setCANCELLED_ORDERS(Order order){
        CANCELLED_ORDERS.add(order);
    }
    
    @Override
    public String toString() {
        return "Restaurant{" + "name=" + name + ", logo=" + logo + 
                ", telephone=" + telephone + ", address=" + address + ", Email=" + Email + '}';
    }
    
    public static int getMASTER_KEY(){
        return MASTER_KEY;
    }   


}
