/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant_service;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class DrinkTest extends TestCase {
    private Drink drink;
    
    public DrinkTest() {
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
        drink = new Drink("En agua", "Fresco Tamarindo", 500, 375);
    }
    
    @After
    @Override
    public void tearDown() {
        drink = null;
    }

    /**
     * Test of getType method, of class Drink.
     */
    @Test
    public void testGetType() {
        String expectedType = "En agua";
        String type = drink.getType();
        assertEquals("Los tipos de bebida no son iguales",expectedType, type);
        
    }

    /**
     * Test of setType method, of class Drink.
     */
    @Test
    public void testSetType() {
        String settingType = "En Leche";
        drink.setType(settingType);
        String expectedType = drink.getType();
        assertEquals("El tipo de la bebida es el mismo al que trata de ingresar",expectedType,settingType);
        
    }

    /**
     * Test of getName method, of class Drink.
     */
    @Test
    public void testGetName() {
        String expectedName = "Fresco Tamarindo";
        String name = drink.getName();
        assertEquals("El nombre de la bebida no coincide con el esperado", expectedName,name);
    }

    /**
     * Test of setName method, of class Drink.
     */
    @Test
    public void testSetName() {
        String settingName = "Coca Cola";
        String expectedName = drink.getName();
        assertNotEquals("Los nombres son iguales", settingName,expectedName);
    }

    /**
     * Test of getPrice method, of class Drink.
     */
    @Test
    public void testGetPrice() {
        float price = 500;
        float expectedPrice = drink.getPrice();
        assertEquals("Los precios no coinciden", price, expectedPrice);
    }

    /**
     * Test of setPrice method, of class Drink.
     */
    @Test
    public void testSetPrice() {
        float settingPrice = 400;
        float expectedPrice = drink.getPrice();
        assertNotEquals("Los precios son iguales", settingPrice, expectedPrice);
    }

    /**
     * Test of getSize method, of class Drink.
     */
    @Test
    public void testGetSize() {
        int expectedSize = 375;
        int size = drink.getSize();
        assertEquals("El tamaño de la bebida esperado y el de la bebida no coinciden", expectedSize, size);
    }

    /**
     * Test of setSize method, of class Drink.
     */
    @Test
    public void testSetSize() {
        float settingSize = 600;
        float size = drink.getSize();
        assertNotEquals("Los tamaños son iguales", settingSize, size);
    }

    /**
     * Test of toString method, of class Drink.
     */
    @Test
    public void testToString() {
    }
    
}
