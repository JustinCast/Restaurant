/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import restaurant_service.Order;
import restaurant_service.Restaurant;

/**
 * FXML Controller class
 *
 * @author JustinCast
 */
public class CancelledController implements Initializable {
    @FXML private TableView cancelledOrders;
    @FXML private TableColumn tableNumber;
    @FXML private TableColumn orderNumber;
    @FXML private TableColumn dishes;
    @FXML private TableColumn drinks;
    @FXML private TableColumn waiterID;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCancelledOrders();
    }   
    
    private void loadCancelledOrders(){
        tableNumber.setCellValueFactory(new PropertyValueFactory<>("aux"));
        orderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        dishes.setCellValueFactory(new PropertyValueFactory<>("dishes"));
        drinks.setCellValueFactory(new PropertyValueFactory<>("drinks"));
        waiterID.setCellValueFactory(new PropertyValueFactory<>("waiterID"));
        
        ObservableList<Order> orders = FXCollections.observableArrayList();
        Restaurant.getCANCELLED_ORDERS().stream().forEach((_item) -> {
            orders.add(_item);
        });
        
        cancelledOrders.setItems(orders);
    }
    
    public void closeButtonAction() throws Exception{
        Stage stage = (Stage) cancelledOrders.getScene().getWindow();
        stage.close();
    }
    
}
