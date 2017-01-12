
package UI;

import com.jfoenix.controls.JFXTextArea;
import employees.Chef;
import employees.Employee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import restaurant_service.Order;
import restaurant_service.Restaurant;

/**
 * FXML Controller class
 *
 * @author JustinCast
 */
public class ChefController implements Initializable {
   @FXML private JFXTextArea ordersToPrepare;
   @FXML private ComboBox takenOrdersNums;
   @FXML private ComboBox chefs;
   @FXML private AnchorPane preparingWindow; 
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        preparingWindow.setId("preparingWindow");
        preparingWindow.getStyleClass().add("preparingWindow");
        setOrders();
        loadTakenOrders();
        loadChefs();
  
    }    
    /**
     * Method to show the orders that the chef must prepare
     */
    private void setOrders(){
        ordersToPrepare.setText("");
        String output;
        for(Order order : Restaurant.getTAKEN_ORDERS()){
            output = "Orden numero "+ order.getOrderNumber() + "\n";
            ordersToPrepare.appendText(output + order.getDishes());
            output = "°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°\n";
            ordersToPrepare.appendText(output);
        }
        
    }
    
    public void prepareOrder() throws Exception{
        if(Restaurant.getTAKEN_ORDERS().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ordenes");
            alert.setContentText("No hay ordenes por preparar pendientes");
            alert.showAndWait();
            closeButtonAction();
            return;
        }
        if(chefs.getSelectionModel().getSelectedItem() != null && takenOrdersNums.getSelectionModel().getSelectedItem() != null){
            Employee employee = Restaurant.searchEmployeeByName(chefs.getSelectionModel().getSelectedItem().toString());
            if(employee instanceof Chef){
                Order order = Restaurant.searchTakenOrder(Integer.parseInt(takenOrdersNums.getSelectionModel().getSelectedItem().toString()));
                if(order == null){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ordenes");
                    alert.setContentText("No se ha podido encontrar la orden");
                    alert.showAndWait();
                }
                else{
                    ((Chef) employee).setPreparedOrders(1);
                    Restaurant.setPREPARED_ORDERS(order);
                    Restaurant.getTAKEN_ORDERS().remove(order);
                    Restaurant.getPENDING_ATTEND().remove(order.getTable());
                    Restaurant.getBUSY_TABLES().remove(order.getTable());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Orden preparada");
                    alert.setContentText("Se ha preparado la orden satisfactoriamente");
                    alert.showAndWait();
                    setOrders();
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empleados");
                alert.setContentText("El ID no corresponde a un chef");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No puede dejar campos vacios");
            alert.showAndWait();
        }
    }
    
    private void loadTakenOrders(){
        for(Order order : Restaurant.getTAKEN_ORDERS())
            takenOrdersNums.getItems().add(order.getOrderNumber());
    }
    
    private void loadChefs(){
        for(Employee employee : Restaurant.getEmployees())
            if(employee instanceof Chef)
                chefs.getItems().add(employee.getName());
    }
    
    public void closeButtonAction() throws Exception{
        Stage stage = (Stage) chefs.getScene().getWindow();
        stage.close();
    }
}
