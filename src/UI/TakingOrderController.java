/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
import employees.Employee;
import employees.Waiter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import restaurant_service.Order;
import restaurant_service.Restaurant;
import restaurant_service.Table;

/**
 * FXML Controller class
 *
 * @author Justin Castillo Valladares
 */
public final class TakingOrderController implements Initializable {
    @FXML private ComboBox waitersCmbx;
    @FXML private ComboBox numberOfClients;
    @FXML private JFXButton btnAccept;

    private static int identifier = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String out = "";
        for(Employee employee : Restaurant.getEmployees()){
            if(employee instanceof Waiter){
                out += employee.getName() + ": " + employee.getID() + "\n";
            }
        }
        numberOfClients.setStyle("-fx-text-inner-color: white;");
        addWaiterToCmbx();
        addNumbersToCmbx();
    }   
    
    
    public void seeTables()throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TableServies.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();
            identifier = 1;
        }catch(Exception e){}
    }
    
    public void verify() throws IOException, Exception{
        if(waitersCmbx.getSelectionModel().getSelectedItem() == null || numberOfClients.getSelectionModel().getSelectedItem() == null)
            JOptionPane.showMessageDialog(null, "¡No puede dejar campos vacios!");
        else if(identifier == 0)
            JOptionPane.showMessageDialog(null, "Primero debe ubicar los clientes en una mesa");
        else{    
            Employee employee = Restaurant.searchEmployeeByName(waitersCmbx.getSelectionModel().getSelectedItem().toString()); //devuelve el elemento seleccionado en forma de String           
            LocalDate now = LocalDate.now();
            Order order = new Order();
            order.setDate(now);
            
            ((Waiter) employee).setAtendedOrders(1);
            order.setWaiter((Waiter) employee);
            order.setOrderNumber(Restaurant.getOrderCounter());
            Table table = new Table();
            table.setTableNumber(TableServiesController.getTableNumber());
            order.setTable(table);
            Restaurant.setOrders(order);
            Restaurant.setPENDING_ATTEND(table);
                      
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));
                stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
                stage.setResizable(false);
                stage.show();
            }catch(Exception e){}
            closeButtonAction();
            
        }
    }
    
    private void addWaiterToCmbx(){
        waitersCmbx.setPromptText("Seleccione un mesero");
        String name;
//        int ID;
        for(Employee employee : Restaurant.getEmployees()){
            if(employee instanceof Waiter){
                name = employee.getName();
//                ID = employee.getID();
                waitersCmbx.getItems().add(name);
            }
        }
    }
    
    
    /**
     * Añade numeros al ComboBox de 'número de clientes'
     */
    private void addNumbersToCmbx(){
        numberOfClients.setPromptText("Número de clientes");
        for(int i=1;i < 11; i++){
            numberOfClients.getItems().add(i);
        } 
    }
    
    public void closeButtonAction() throws Exception{
        Stage stage = (Stage) btnAccept.getScene().getWindow();
        stage.close();
    }
}
