
package restaurant_service;

import employees.Waiter;
import java.util.ArrayList;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderTest extends TestCase{
    private Order order;
    
    public OrderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    @Override
    public void setUp() {
        ArrayList languages = new ArrayList();
        languages.add("italiano");
        languages.add("Ingles");
        Address address = new Address("Alajuela", "San Carlos", "Aguas Zarcas", "25 mts sur de la iglesia");
        Waiter waiter = new Waiter(languages, address, "85781158", "justinCastillovalladares@gmail.com", "justin",0);
        Drink drinks = new Drink("Batido", "Coco", 1200, 500);
        ArrayList drink = new ArrayList();
        drink.add(drinks);
        ArrayList MainIngredients = new ArrayList();
        MainIngredients.add("Mango");
        MainIngredients.add("Arroz");
        Dish dish = new Dish(1, "Arroz con Mango", MainIngredients, 3000, 600, "");
        ArrayList dishes = new ArrayList();
        dishes.add(dish);
        order = new Order(waiter, drink, dishes, 1);
        Table table = new Table();
        table.setTableNumber(1);
        order.setTable(table);
    }
    
    @After
    @Override
    public void tearDown() {
        order = null;
    }

    /**
     * Test of getWaiter method, of class Order.
     */
    @Test
    public void testGetWaiter() {
        Waiter expectedWaiter = order.getWaiter();
        Waiter result = order.getWaiter();
        assertEquals("Los meseros no coinciden", expectedWaiter, result);
    }

    /**
     * Test of setWaiter method, of class Order.
     */
    @Test
    public void testSetWaiter() {
        ArrayList languages = new ArrayList();
        languages.add("italiano");
        languages.add("Ingles");
        Address address = new Address("Alajuela", "San Carlos", "Aguas Zarcas", "25 mts sur de la iglesia");
        Waiter settingWaiter = new Waiter(languages, address, "85781158", "justinCastillovalladares@gmail.com", "Franciscos",0);
        Waiter result = order.getWaiter();
        assertNotEquals("El mesero que esta tratando de ingresar es igual al de la orden",settingWaiter, result);
    }

    

    /**
     * Test of getDRINKS method, of class Order.
     */
    @Test
    public void testGetDRINKS() {
        ArrayList expectedDrinks = order.getDRINKS();
        ArrayList result = order.getDRINKS();
        assertEquals("Las bebidas no son las mismas",expectedDrinks,result);
    }

    /**
     * Test of setDrink method, of class Order.
     */
    @Test
    public void testSetDrink() {
        ArrayList<Drink> drinks = new ArrayList<>();
        ArrayList<Drink> result = order.getDRINKS();
        assertNotEquals("Las bebidas que está tratando de ingresar son las mismas",drinks, result);
    }

    /**
     * Test of getDishes method, of class Order.
     */
    @Test
    public void testGetDISHES() {
        ArrayList<Dish> expectedDishes = order.getDISHES();
        ArrayList<Dish> result =  order.getDISHES();
        assertEquals("Los platillos no coinciden",expectedDishes, result);      
    }

    /**
     * Test of setDishes method, of class Order.
     */
    @Test
    public void testSetDISHES() {
        ArrayList<Dish> expectedDishes = new ArrayList<>();
        ArrayList<Dish> result = order.getDISHES();
        assertNotSame("Los platillos que está tratando de ingresar son los mismos que los de la orden",expectedDishes, result);
    }

    /**
     * Test of getOrderNumber method, of class Order.
     */
    @Test
    public void testGetOrderNumber() {
        int orderNumber = 1;
        int result = order.getOrderNumber();
        assertEquals("Los numeros de ordenes no coinciden", orderNumber, result);
    }

    /**
     * Test of setOrderNumber method, of class Order.
     */
    @Test
    public void testSetOrderNumber() {
        int orderNumber = 0;
        int result = order.getOrderNumber();
        assertNotEquals("El numero de orden que está tratando de ingresar es el mismo que el de la orden", orderNumber, result);
    }

    /**
     * Test of getTable method, of class Order.
     */
    @Test
    public void testGetTable() {
        Table table = order.getTable();
        Table result = order.getTable();
        assertEquals("Las mesas no coinciden", table,result);
    }

    /**
     * Test of setTable method, of class Order.
     */
    @Test
    public void testSetTable() {
        Table table = new Table();
        table.setTableNumber(2);
        Table result = order.getTable();
        assertNotEquals("La mesa que está tratando de ingresar es igual a la mesa existente" ,table, result);
    }
    
}
