/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
 */
public final class MenuController implements Initializable {
    @FXML private TableView<Dish> DishTable;
    @FXML private TableColumn number;
    @FXML private TableColumn DishName;
    @FXML private TableColumn ingredientsColumn;
    @FXML private TableColumn PriceWithoutTax;
    @FXML private TableColumn calories;
    @FXML private TableView DrinkTable;
    @FXML private TableColumn type;
    @FXML private TableColumn DrinkName;    
    @FXML private TableColumn price;    
    @FXML private TableColumn MlSize;    
    @FXML private AnchorPane menuPane;
    private static int loader = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        menuPane.setId("menu");
        menuPane.getStyleClass().add("menu");
        if(loader == 0){
            loadDishes(0);
            loadDrinks(0);
        }
        else{
            loadDishes(1);
            loadDrinks(1);
        }
    }
    
    private void loadDishes(int identifier){
        if(identifier == 0){
            ArrayList ingredients = new ArrayList();
            ingredients.add("Pechuga de pollo");
            ingredients.add("Tocineta");
            ingredients.add("Crema dulce");
            ingredients.add("Zanahoria");
            ingredients.add("Culantro");
            ingredients.add("Sal");
            ingredients.add("Ajo");
            ingredients.add("Cebolla");
            Dish dish = new Dish(Menu.getDishCounter(), "Pollo en salsa blanca", ingredients, 3500, 450, "");
            Menu.setDISHES(dish);

            ArrayList ingredients2 = new ArrayList();
            ingredients2.add("Arroz");
            ingredients2.add("Frijoles");
            ingredients2.add("Salsa Lizano");
            ingredients2.add("Ajo");
            ingredients2.add("Cebolla");
            ingredients2.add("Sal");
            ingredients2.add("Queso");
            ingredients2.add("Huevo");
            ingredients2.add("Platano");
            Dish dish2 = new Dish(Menu.getDishCounter(), "Gallo Pinto", ingredients2, 2500, 425, "");
            Menu.setDISHES(dish2);

            ArrayList ingredients3 = new ArrayList();
            ingredients3.add("Masa");
            ingredients3.add("Pollo");
            ingredients3.add("Salsa Lizano");
            ingredients3.add("Ajo");
            ingredients3.add("Cebolla");
            ingredients3.add("Sal");
            ingredients3.add("Culantro");
            ingredients3.add("Zanahoria");
            ingredients3.add("Vainica");
            Dish dish3 = new Dish(Menu.getDishCounter(), "Tamal de Pollo", ingredients3, 700, 350, "");
            Menu.setDISHES(dish3);

            ArrayList ingredients4 = new ArrayList();
            ingredients4.add("Arroz");
            ingredients4.add("Posta de Cerdo");
            ingredients4.add("Salsa BBQ");
            ingredients4.add("Albahaca");
            ingredients4.add("Ajo");
            ingredients4.add("Cebollino");
            ingredients4.add("Sal");
            ingredients4.add("Culantro");
            ingredients4.add("Zanahoria");
            Dish dish4 = new Dish(Menu.getDishCounter(), "Arroz con Cerdo", ingredients4, 4000, 550, "");
            Menu.setDISHES(dish4);

            ArrayList ingredients5 = new ArrayList();
            ingredients5.add("Tortilla");
            ingredients5.add("Carne desmechada");
            ingredients5.add("Repollo");
            ingredients5.add("Salsa de Tomate");
            ingredients5.add("Mayonesa");
            Dish dish5 = new Dish(Menu.getDishCounter(), "Tacos", ingredients5, 1000, 395, "");
            Menu.setDISHES(dish5);
        }
        ObservableList<Dish> dishes = FXCollections.observableArrayList();
        Menu.getDISHES().stream().forEach((_item) -> {
            dishes.add(_item);
        });
        number.setCellValueFactory(new PropertyValueFactory<>("dishNumber"));
        DishName.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        ingredientsColumn.setCellValueFactory(new PropertyValueFactory<>("MainIngredients"));
        PriceWithoutTax.setCellValueFactory(new PropertyValueFactory<>("PriceWihtoutTax"));
        calories.setCellValueFactory(new PropertyValueFactory<>("calories"));
        
        DishTable.setItems(dishes);         
        
    }
    
    private void loadDrinks(int identifier){
        if(identifier == 0){
            String typeS = "En leche";
            String name = "Batido de Arándano";
            Drink drink = new Drink(typeS, name, 1200, 420);
            Menu.setDRINKS(drink);

            typeS = "En leche";
            name = "Batido de Piña";
            Drink drink2 = new Drink(typeS, name, 1000, 420);
            Menu.setDRINKS(drink2);

            typeS = "En leche";
            name = "Batido de Banano";
            Drink drink3 = new Drink(typeS, name, 1000, 420);
            Menu.setDRINKS(drink3);

            typeS = "En Agua";
            name = "Batido de Frutas";
            Drink drink4 = new Drink(typeS, name, 800, 420);
            Menu.setDRINKS(drink4);

            typeS = "En Agua";
            name = "Batido de Guayaba";
            Drink drink5 = new Drink(typeS, name, 800, 420);
            Menu.setDRINKS(drink5);

            typeS = "Gaseosa";
            name = "Coca Cola";
            Drink drink6 = new Drink(typeS, name, 700, 400);
            Menu.setDRINKS(drink6);

            typeS = "Gaseosa";
            name = "Squirt";
            Drink drink7 = new Drink(typeS, name, 700, 400);
            Menu.setDRINKS(drink7);

            typeS = "Gaseosa";
            name = "Fresca";
            Drink drink8 = new Drink(typeS, name, 700, 400);
            Menu.setDRINKS(drink8);

            typeS = "Gaseosa";
            name = "Fanta Naranja";
            Drink drink9 = new Drink(typeS, name, 700, 400);
            Menu.setDRINKS(drink9);

            typeS = "Gaseosa";
            name = "Fanta Uva";
            Drink drink10 = new Drink(typeS, name, 700, 400);
            Menu.setDRINKS(drink10);

            typeS = "Gaseosa";
            name = "Fanta Colita";
            Drink drink11 = new Drink(typeS, name, 700, 400);
            Menu.setDRINKS(drink11);

            typeS = "Fresco";
            name = "Cas";
            Drink drink12 = new Drink(typeS, name, 500, 375);
            Menu.setDRINKS(drink12);

            typeS = "Fresco";
            name = "Zanahoria";
            Drink drink13 = new Drink(typeS, name, 500, 375);
            Menu.setDRINKS(drink13);

            typeS = "Fresco";
            name = "Naranja";
            Drink drink14 = new Drink(typeS, name, 500, 375);
            Menu.setDRINKS(drink14);

            
            typeS = "Fresco";
            name = "Mora";
            Drink drink15 = new Drink(typeS, name, 500, 375);
            Menu.setDRINKS(drink15);
        }
        ObservableList<Drink> drinks = FXCollections.observableArrayList();
        Menu.getDRINKS().stream().forEach((_item) -> {
            drinks.add(_item);
        });
        
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        DrinkName.setCellValueFactory(new PropertyValueFactory<>("name"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        MlSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        
        DrinkTable.setItems(drinks);
    }
    
    public void addDish(){
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setResizable(false);
        alert.initOwner((Stage) DrinkTable.getScene().getWindow());
        alert.setTitle("Agregar Platillo");
        alert.initStyle(StageStyle.DECORATED);
        
        GridPane addDishGP = new GridPane();
        JFXTextField dishNameTxtField = new JFXTextField();
        dishNameTxtField.setPromptText("Nombre Platillo");
        addDishGP.add(dishNameTxtField, 1, 1);
        
        JFXTextField priceWithoutTax = new JFXTextField();
        priceWithoutTax.setPromptText("Precio sin impuestos");
        addDishGP.add(priceWithoutTax, 1, 2);
        
        JFXTextField ingredient = new JFXTextField();
        ingredient.setPromptText("Ingrediente");
        addDishGP.add(ingredient, 1, 3);
        
        JFXTextArea textArea = new JFXTextArea();
        textArea.setEditable(false);
        addDishGP.add(textArea, 1, 4);
        
        ArrayList MainIngredients = new ArrayList();// arrayList donde se encontrarán todos los ingredientes del platillo
        
        JFXButton button = new JFXButton("Agregar Ingrediente");           
        button.setButtonType(JFXButton.ButtonType.RAISED); //el botón está elevado y no plano
        button.setStyle("-fx-background-color:  #138D75;-fx-text-fill: white; -fx-font-size: 14");
        button.setOnMouseClicked((MouseEvent t) -> {
            textArea.appendText(ingredient.getText() + "\n");
            ingredient.clear();
            MainIngredients.add(ingredient.getText());
        });
        addDishGP.add(button, 1, 5);
        
        alert.getDialogPane().setContent(addDishGP);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Dish dish = new Dish(Menu.getDishCounter(), dishNameTxtField.getText(), MainIngredients, Integer.parseInt(priceWithoutTax.getText()),
                        2, "");
                Menu.setDISHES(dish);
                loadDishes(1);
            }
        });
        
    }
    
    public void addDrink(){
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setResizable(false);
        alert.initOwner((Stage) DrinkTable.getScene().getWindow());
        alert.setTitle("Agregar Bebida");
        alert.initStyle(StageStyle.DECORATED);
        
        GridPane addDrinkGP = new GridPane();
        JFXComboBox drinkType = new JFXComboBox();
        drinkType.setValue("Tipo Bebida");
        drinkType.getItems().addAll("En Agua","En Leche", "Batido en Agua", "Batido en Leche", "Batido en Agua");
        addDrinkGP.add(drinkType, 0, 1);
        
        JFXTextField drinkName = new JFXTextField();
        drinkName.setPromptText("Nombre Bebida");
        addDrinkGP.add(drinkName, 1, 2);
        
        JFXTextField drinkPrice = new JFXTextField();
        drinkPrice.setPromptText("Precio");
        addDrinkGP.add(drinkPrice, 1, 3);
        
        JFXTextField ml = new JFXTextField();
        ml.setPromptText("Tamaño de la bebida(ml)");
        addDrinkGP.add(ml, 1, 4);
               
        
        alert.getDialogPane().setContent(addDrinkGP);
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Drink drink = new Drink(drinkType.getSelectionModel().getSelectedItem().toString(), drinkName.getText(), Integer.parseInt(drinkPrice.getText()), 
                        Integer.parseInt(ml.getText()));
                Menu.setDRINKS(drink);
                loadDrinks(1);
            }
        });
    }
    
    public void deleteDish(){
        
        Alert confirmationPassword = new Alert(AlertType.CONFIRMATION, "Ingrese su contraseña para procesar la solicitud");
        confirmationPassword.initOwner((Stage) DrinkTable.getScene().getWindow());
        confirmationPassword.setTitle("Ingrese su contraseña");
        confirmationPassword.setHeaderText("Ingresar contraseña");
        
        GridPane confirmationPasswordGP = new GridPane();
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");
        confirmationPasswordGP.add(passwordField, 0, 1);
        confirmationPassword.getDialogPane().setContent(confirmationPasswordGP);
        confirmationPassword.showAndWait().ifPresent((ButtonType response) -> {
            if(response == ButtonType.OK){
                if(Integer.parseInt(passwordField.getText()) == Restaurant.getMASTER_KEY()){
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Eliminar Platillo");
                    alert.initOwner((Stage) DrinkTable.getScene().getWindow());
                    alert.setTitle("Eliminar Platillo");
                    alert.initStyle(StageStyle.DECORATED);

                    GridPane deleteDishGP = new GridPane();
                    JFXComboBox deleteDishCmbx = new JFXComboBox();
                    for(Dish dish : Menu.getDISHES())
                        deleteDishCmbx.getItems().add(dish.getDishName());
                    
                    deleteDishCmbx.setPromptText("Platillo");
                    deleteDishGP.add(deleteDishCmbx, 0, 1);

                    alert.getDialogPane().setContent(deleteDishGP);
                    alert.showAndWait().ifPresent(response2 -> {
                        if(response2 == ButtonType.OK && deleteDishCmbx.getSelectionModel().getSelectedItem() != null){
                            Dish dish = Menu.searchDishByName(deleteDishCmbx.getSelectionModel().getSelectedItem().toString());
                            Menu.getDISHES().remove(dish);
                            loadDishes(1);
                            JOptionPane.showMessageDialog(null, "Platillo Eliminado");
                        }
                    });  
                }else{
                    Alert error = new Alert(AlertType.ERROR, "Contraseña incorrecta");
                    error.setTitle("Contraseña incorrecta");
                    error.initOwner((Stage) DrinkTable.getScene().getWindow());
                    error.showAndWait();
                }
                
            }
        });
                  
    }
    
    public void deleteDrink(){
        Alert confirmationPassword = new Alert(AlertType.CONFIRMATION, "Ingrese su contraseña para procesar la solicitud");
        confirmationPassword.initOwner((Stage) DrinkTable.getScene().getWindow());
        confirmationPassword.setTitle("Ingrese su contraseña");
        confirmationPassword.setHeaderText("Ingresar contraseña");
        
        GridPane confirmationPasswordGP = new GridPane();
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Contraseña");
        confirmationPasswordGP.add(passwordField, 0, 1);
        confirmationPassword.getDialogPane().setContent(confirmationPasswordGP);
        confirmationPassword.showAndWait().ifPresent((ButtonType response) -> {
            if(response == ButtonType.OK){
                if(Integer.parseInt(passwordField.getText()) == Restaurant.getMASTER_KEY()){
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Eliminar Bebida");
                    alert.initOwner((Stage) DrinkTable.getScene().getWindow());
                    alert.setTitle("Eliminar Bebida");
                    alert.initStyle(StageStyle.DECORATED);

                    GridPane deleteDrinkGP = new GridPane();
                    JFXComboBox deleteDrinkCmbx = new JFXComboBox();
                    deleteDrinkCmbx.setPromptText("Nombre bebida");
                    for(Drink drink : Menu.getDRINKS()){
                        deleteDrinkCmbx.getItems().add(drink.getName());
                    }
                    deleteDrinkGP.add(deleteDrinkCmbx, 0, 1);

                    alert.getDialogPane().setContent(deleteDrinkGP);
                    alert.showAndWait().ifPresent(response2 -> {
                        if(response2 == ButtonType.OK){                            
                            Drink drink = Menu.searchDrink(deleteDrinkCmbx.getSelectionModel().getSelectedItem().toString());
                            if(drink == null){ //se verifica si existe la bebida ingresada
                                JOptionPane.showMessageDialog(null, "Bebida no encontrada");
                                return;
                            }
                            Menu.getDRINKS().remove(drink);
                            loadDrinks(1);
                            JOptionPane.showMessageDialog(null, "Bebida Eliminada");
                        }
                    });  
                }else{
                    Alert error = new Alert(AlertType.ERROR, "Contraseña incorrecta");
                    error.setTitle("Contraseña incorrecta");
                    error.initOwner((Stage) DrinkTable.getScene().getWindow());
                    error.showAndWait();
                }                
            }
        });
    }
    
    public void openEmergentOrder()throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EmergentTakingOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){}
        loader = 1;
        closeButtonAction();
    }
    
    public void seeOrders(){
        String outString = "";
        if(Restaurant.getOrders().isEmpty() == false){
            for(Order order : Restaurant.getOrders()){
                outString += "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
                outString += "\nOrden numero: "+  order.getOrderNumber() + "\n" + 
                        "Mesa Numero: " + order.getTable().getTableNumber() + "\n" +
                        "Mesero: " + order.getWaiter().getName() + "\n";
            }
            JOptionPane.showMessageDialog(null, outString,"Ordenes Pendientes",JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null,"No hay ordenes pendientes");
    }
    
    public void takeAnotherOrder()throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TakingOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){}
        loader = 1;
        closeButtonAction();
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
        }catch(Exception e){}
    }
    public void OK()throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InWait.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){}
    }
    
    
    public void seeRestaurant(){
        String output = "";
        output += "Nombre: " + Restaurant.getName() + "\n";
        output += "Teléfono: " + Restaurant.getTelephone() + "\n";
        output += "EMail" + Restaurant.getEmail() + "\n";
        output += "Provincia: " + Restaurant.getAddress().getProvince() + "\n";
        output += "Canton: " + Restaurant.getAddress().getCanton() + "\n";
        output += "Distrito: " + Restaurant.getAddress().getDistrict() + "\n";
        output += "Direccion exacta: " + Restaurant.getAddress().getExactAddress() + "\n"; 
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Restaurante");
        alert.setHeaderText(output);
        alert.setContentText("Por favor, elija su opcion");
        alert.initOwner((Stage) DrinkTable.getScene().getWindow());
        Image image = new Image(getClass().getResourceAsStream("images/store.png"));
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        alert.showAndWait();
    }
    
    public void closeButtonAction() throws Exception{
        Stage stage = (Stage) DrinkTable.getScene().getWindow();
        stage.close();
    }
    
    private void confirmClose(DialogEvent dialogEvent) { 
        Dialog<ButtonType> dialog = new Dialog<>(); 
        dialog.setContentText("¿Está seguro que desea cerrar y no tomar la orden?"); 
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.YES, ButtonType.NO); 

        dialog.showAndWait().ifPresent(buttonType -> { 
            if (buttonType != ButtonType.YES)  
                dialogEvent.consume();             
        }); 
    } 
}
