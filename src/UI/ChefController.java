
package UI;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import employees.Chef;
import employees.Employee;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
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
   @FXML private JFXTextField preparOrder;
   @FXML private JFXTextField chefID;
   @FXML private AnchorPane preparingWindow; 
   @FXML private Tooltip ordersTip;
   @FXML private Tooltip chefsTip;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preparOrder.setStyle("-fx-text-inner-color: white;");
        chefID.setStyle("-fx-text-inner-color: white;");
        preparingWindow.setId("preparingWindow");
        preparingWindow.getStyleClass().add("preparingWindow");
        setOrders();
        String out = "";
        for(Order order : Restaurant.getTAKEN_ORDERS())
            out += order.getOrderNumber() + "\n";
        ordersTip.setText(out);
        out = "";
        for(Employee employee : Restaurant.getEmployees()){
            if(employee instanceof Chef)
               out += employee.getName() + ": " + employee.getID() + "\n";
        }
        chefsTip.setText(out);
       
           
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
        if(chefID.getText().length() != 0 && preparOrder.getText().length() != 0){
            Employee employee = Restaurant.searchEmployee(Integer.parseInt(chefID.getText()));
            if(employee instanceof Chef){
                Order order = Restaurant.searchTakenOrder(Integer.parseInt(preparOrder.getText()));
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
    
    public void seeChefs(){
         try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Employees.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){}
    }
    
    public void closeButtonAction() throws Exception{
        Stage stage = (Stage) chefID.getScene().getWindow();
        stage.close();
    }
}
