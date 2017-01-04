/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurant_service;

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
public class TableTest {
    private Table table;
    
    public TableTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        table = new Table();
        table.setTableNumber(1);
    }
    
    @After
    public void tearDown() {
        table = null;
    }

    /**
     * Test of getTableNumber method, of class Table.
     */
    @Test
    public void testGetTableNumber() {
        int tableNumber = 1;
        int result = table.getTableNumber();
        assertEquals("Los numeros de mesa no son iguales" ,tableNumber, result);
    }

    /**
     * Test of setTableNumber method, of class Table.
     */
    @Test
    public void testSetTableNumber() {
        int tableNumber = 2;
        int result = table.getTableNumber();
        assertNotEquals("El numero de mesa que está tratando de ingresar es el mismo que el existente" ,tableNumber, result);
    }
    
}
