/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
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
    @FXML private Text totalText;
    @FXML private TextField textFieldSearchDish;
    @FXML private TextField textFieldSearchDrink;
    @FXML private Button btnSearchDish;
    @FXML private Button btnSearchDrink;
    private int total = 0;
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
        btnSearchDish.setStyle("-fx-background-image: url('/UI/images/search.png'); -fx-background-repeat: no-repeat;"
                + " -fx-background-radius: 30; background-position: center");
        btnSearchDrink.setStyle("-fx-background-image: url('/UI/images/search.png'); -fx-background-repeat: no-repeat;"
                + " -fx-background-radius: 30; background-position: center");

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
        totalText.setText("");
        ArrayList<String> auxiliar = new ArrayList<>();
        int i = 0;        
        for(Dish dish : Menu.getDISHES()){
            CheckBox checkBox = new CheckBox(dish.getDishName());
            dishScrollGrid.add(checkBox,0,i);  
            
            Spinner spinner = new Spinner(1, 10, 1);
            spinner.setId(checkBox.getText());    //CREACIÓN Y MANEJO DEL SPINNER
            spinner.setDisable(true);
            dishScrollGrid.add(spinner,1,i); 
            
            i += 1;            
            checkBox.setOnAction((ActionEvent e) ->{
                if(checkBox.isSelected()){                   
                    seeFoodAndDrinksAdded.appendText(dish.getDishName() + "\n");
                    total += Menu.searchDishByName(checkBox.getText()).getPriceWihtoutTax(); //se aumenta el total
                    
                    //desactiva el spinner correspondiente al platillo
                    dishScrollGrid.getChildren().stream().forEach((Node tmp) -> {
                        if(tmp instanceof Spinner) // se verifica la instancia, ya que los checkbox no poseen ID
                            if(tmp.getId().equals(checkBox.getText()))                       
                                tmp.setDisable(false);                                                    
                    });
                }
                else{
                    //activa el spinner correspondiente al platillo
                    dishScrollGrid.getChildren().stream().forEach((Node tmp) -> {
                        if(tmp instanceof Spinner) // se verifica la instancia, ya que los checkbox no poseen ID
                            if(tmp.getId().equals(checkBox.getText())){                      
                                tmp.setDisable(true);
                                ((Spinner) tmp).decrement(Integer.parseInt(((Spinner)tmp).getValue().toString())); //funciona como un reset
                            }
                            
                    });
                    /**
                     * Para 'borrar' el elemento deseleccionado
                     */
                    for(String string : seeFoodAndDrinksAdded.getText().split("\\n")){
                        if(!string.equals(checkBox.getText()))
                            auxiliar.add(string);
                        else
                            total -=Menu.searchDishByName(checkBox.getText()).getPriceWihtoutTax(); //se disminuye el total
                    }
                    // se limpia el textArea
                    seeFoodAndDrinksAdded.clear();
                    /**
                     * Para actualiza el textarea                     
                     */                   
                    for(String string : auxiliar)
                        seeFoodAndDrinksAdded.appendText(string + "\n");
                    
                    // se limpia el ArrayList también
                    auxiliar.clear();                    
                }         
                totalText.setText("Total: ¢" + String.valueOf(total));
            });
        }
    }

    private void fillDrinkScrollPane() {
        totalText.setText("");
        drinkScrollGrid.setMinSize(2, 2);
        ArrayList<String> auxiliar = new ArrayList<>();
        int i = 0;
        for(Drink drink : Menu.getDRINKS()){
            CheckBox checkBox = new CheckBox(drink.getName());            
            drinkScrollGrid.add(checkBox,0,i);            
            Spinner spinner = new Spinner(1, 10, 1);
            spinner.setId(checkBox.getText());       //CREACIÓN Y MANEJO DEL SPINNER
            spinner.setDisable(true);
            drinkScrollGrid.add(spinner,1,i); 
            
            i += 1;
            checkBox.setOnAction((ActionEvent e) ->{
                if(checkBox.isSelected()){
                   seeFoodAndDrinksAdded.appendText(drink.getName() + "\n");
                   total += Menu.searchDrink(drink.getName()).getPrice(); //se aumenta el total
                   
                   //desactiva el spinner correspondiente al platillo
                    drinkScrollGrid.getChildren().stream().forEach((Node tmp) -> {
                        if(tmp instanceof Spinner) // se verifica la instancia, ya que los checkbox no poseen ID
                            if(tmp.getId().equals(checkBox.getText()))                       
                                tmp.setDisable(false);                                                    
                    });
                }
                else{
                    //activa el spinner correspondiente al platillo
                    drinkScrollGrid.getChildren().stream().forEach((Node tmp) -> {
                        if(tmp instanceof Spinner) // se verifica la instancia, ya que los checkbox no poseen ID
                            if(tmp.getId().equals(checkBox.getText())){                      
                                tmp.setDisable(true);
                                ((Spinner) tmp).decrement(Integer.parseInt(((Spinner)tmp).getValue().toString())); //funciona como un reset
                            }
                            
                    });
                    /**
                     * Para 'borrar' el elemento deseleccionado
                     */
                    for(String string : seeFoodAndDrinksAdded.getText().split("\\n")){
                        if(!string.equals(checkBox.getText()))
                            auxiliar.add(string);
                        else
                            total -= Menu.searchDrink(string).getPrice(); // se disminuye el total 
                    }
                    
                    // se limpia el textArea
                    seeFoodAndDrinksAdded.clear();
                    /**
                     * Para actualiza el textarea                     
                     */                   
                    for(String string : auxiliar){
                        seeFoodAndDrinksAdded.appendText(string + "\n");                        
                    }
                    // se limpia el ArrayList también
                    auxiliar.clear();                                          
                }   
                totalText.setText("Total: ¢" + String.valueOf(total));
            });
        }
        
    }
}
