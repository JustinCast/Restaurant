/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import restaurant_service.Dish;
import restaurant_service.Drink;
import restaurant_service.Menu;
import restaurant_service.Order;
import restaurant_service.Restaurant;

/**
 * FXML Controller class
 *
 * @author JustinCast
 * Esta clase se utilizará para manejar la ventana emergente para tomar la orden
 */
public class EmergentTakingOrderController implements Initializable {
    @FXML private AnchorPane emergentPane;
    @FXML private ComboBox orderNumberCmbx;
    @FXML private TextArea seeFoodAndDrinksAdded;  
    @FXML private GridPane dishScrollGrid;
    @FXML private GridPane drinkScrollGrid;
    @FXML private AnchorPane dishPane;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        emergentPane.setId("emergent");
        emergentPane.getStyleClass().add("emergent");
        /**
         * Ciclo para agregar los numeros de ordenes
         * al ComboBox que será desplegado
         */
        for(Order order : Restaurant.getOrders())
            orderNumberCmbx.getItems().add(order.getOrderNumber());       
        fillDishScrollPane();
        fillDrinkScrollPane();
        seeFoodAndDrinksAdded.setEditable(false);
    }    
    
    public void takeOrder(){                    
 
        if(orderNumberCmbx.getSelectionModel().getSelectedItem() == null){
            JOptionPane.showMessageDialog(null, "No puede dejar campos vacíos");
            return;
        }
        Order order = Restaurant.searchOrder(Integer.parseInt(orderNumberCmbx.getSelectionModel().getSelectedItem().toString()));
        if(order == null)
            JOptionPane.showMessageDialog(null, "¡El numero de orden no existe!");
        else{
            int aux = 0;
            while(aux == 0){
                List<String> choices = new ArrayList<>();
                choices.add("Platillo");
                choices.add("Bebida");
                ChoiceDialog<String> dialog = new ChoiceDialog<>("Platillo", choices);
                dialog.setTitle("Selección");
                dialog.setContentText("Platillo o bebida:");

                // Traditional way to get the response value.
                Optional<String> result2 = dialog.showAndWait();
                if(result2.isPresent()){            
                    
                    if("Platillo".equals(result2.get())){
                        int dishNumber = Integer.parseInt(JOptionPane.showInputDialog(null,"Numero de plato"));
                        Dish dish = Menu.searchDish(dishNumber);
                        if(dish == null)
                            JOptionPane.showMessageDialog(null,"¡El platillo no ha sido encontrado!");
                        else{
                            order.setDishes(dish);
                            
                        }
                    }
                    else{
                        String drinkName = "";
                        while(drinkName.length() == 0)     
                            drinkName = JOptionPane.showInputDialog(null,"Ingrese el nombre de la bebida");

                        Drink drink = Menu.searchDrink(drinkName);
                        if(drink == null)
                            JOptionPane.showMessageDialog(null, "¡La bebida no ha sido encontrada!");
                        else{
                            order.setDrink(drink);
                        }
                    }

                    Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert2.setTitle("Agregar otra orden");
                    alert2.setHeaderText("¿Desea agregar otra orden?");
                    alert2.setContentText("Por favor, elija su opcion");

                    ButtonType buttonTypeOne = new ButtonType("Si");
                    ButtonType buttonTypeTwo = new ButtonType("No",ButtonBar.ButtonData.CANCEL_CLOSE);
                    alert2.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
                    Optional<ButtonType> typed = alert2.showAndWait();
                    if (typed.get() == buttonTypeOne){
                        aux = 0;
                    }else {
                        aux = 1;
                    }
            }
            if(aux == 1){
                JOptionPane.showMessageDialog(null, "Orden tomada");
                //Restaurant.setBUSY_TABLES(order.getTable());
                //Restaurant.getPENDING_ATTEND().remove(order.getTable());
                Restaurant.setTakenOrders(order);
                Restaurant.getOrders().remove(order);
            } 
            }
        }
    }

    private void fillDishScrollPane() {
        
        int i = 0;        
        for(Dish dish : Menu.getDISHES()){
            CheckBox checkBox = new CheckBox(dish.getDishName());
            dishScrollGrid.add(checkBox,0,i);           
            i += 2;            
            checkBox.setOnAction((ActionEvent e) ->{
                if(checkBox.isSelected())
                   seeFoodAndDrinksAdded.appendText(dish.getDishName() + "\n");
                else
                    for(String string : seeFoodAndDrinksAdded.getText().split("\\n")){
                        if(string.equals(checkBox.getText()))
                            seeFoodAndDrinksAdded.replaceSelection("");
                    }
            });
        }
    }

    private void fillDrinkScrollPane() {
        int i = 0;
        for(Drink drink : Menu.getDRINKS()){
            CheckBox checkBox = new CheckBox(drink.getName());
            drinkScrollGrid.add(checkBox,0,i);
            i += 2;
        }
    }
}
