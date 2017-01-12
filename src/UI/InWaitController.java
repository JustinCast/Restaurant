
package UI;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import employees.Chef;
import employees.Employee;
import employees.Waiter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import restaurant_service.AllPayment;
import restaurant_service.Customer;
import restaurant_service.Dish;
import restaurant_service.Drink;
import restaurant_service.GroupPayment;
import restaurant_service.Menu;
import restaurant_service.Order;
import restaurant_service.Restaurant;
import restaurant_service.SeparatePayment;
import restaurant_service.WayToPay;


public class InWaitController implements Initializable {
    @FXML private TableView takenOrders;
    @FXML private TableColumn tableNumber;
    @FXML private TableColumn orderNumber;
    @FXML private TableColumn dishes;
    @FXML private TableColumn drinks;
    @FXML private TableColumn waiterId;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTakenOrders();
    }    
    
    public void loadTakenOrders(){
        tableNumber.setCellValueFactory(new PropertyValueFactory<>("aux"));
        orderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        dishes.setCellValueFactory(new PropertyValueFactory<>("dishes"));
        drinks.setCellValueFactory(new PropertyValueFactory<>("drinks"));
        waiterId.setCellValueFactory(new PropertyValueFactory<>("waiterID"));
        
        ObservableList<Order> orders = FXCollections.observableArrayList();
        Restaurant.getTAKEN_ORDERS().stream().forEach((_item) -> {
            orders.add(_item);
        });
        
        takenOrders.setItems(orders);
    }
    
    public void addToOrder() throws Exception{
        List orderNums = new ArrayList<>();
        for(Order order : Restaurant.getTAKEN_ORDERS())
            orderNums.add(order.getOrderNumber());
        //Se crea el choiceDialog
        ChoiceDialog addToOderDialog = new ChoiceDialog("Numero de orden ", orderNums);
        addToOderDialog.setTitle("Agregar a orden");
        addToOderDialog.setHeaderText("Selección");
        addToOderDialog.initOwner((Stage) takenOrders.getScene().getWindow());
        
        //Se agrega el 'Optional´para poder obtener el resultado
        Optional result = addToOderDialog.showAndWait();
        if(result.isPresent()){
            Order order = Restaurant.searchTakenOrder((int) result.get());
            List<String> dishOrDrink = new ArrayList();
            dishOrDrink.add("Platillo");
            dishOrDrink.add("Bebida");
            ChoiceDialog<String> dishOrDrinkDialog = new ChoiceDialog<>("Elemento a agregar", dishOrDrink);
            dishOrDrinkDialog.setTitle("Agregar");
            dishOrDrinkDialog.initOwner((Stage) takenOrders.getScene().getWindow());
            Optional<String> resultFOD = dishOrDrinkDialog.showAndWait();
            if(resultFOD.isPresent()){
                if(resultFOD.get().equals("Platillo")){
                    List<String> dishNames = new ArrayList<>();
                    for(Dish dish : Menu.getDISHES())
                        dishNames.add(dish.getDishName());
                    ChoiceDialog<String> addNewDish = new ChoiceDialog<>("Platillo", dishNames);
                    addNewDish.setTitle("Agregar");
                    addNewDish.setHeaderText("Agregue un platillo");
                    addNewDish.initOwner((Stage) takenOrders.getScene().getWindow());
                    Optional<String> selectedDish = addNewDish.showAndWait();
                    if(selectedDish.isPresent()){
                        order.setDISHES(Menu.searchDishByName(selectedDish.get()));
                        JOptionPane.showMessageDialog(null, "Platillo agregado con éxito");
                    }
                }               
                else{
                    List<String> drinkNames = new ArrayList<>();
                    for(Drink drink : Menu.getDRINKS())
                        drinkNames.add(drink.getName());
                    ChoiceDialog<String> addNewDrink = new ChoiceDialog<>("Bebida", drinkNames);
                    addNewDrink.setTitle("Agregar");
                    addNewDrink.setHeaderText("Agregue una bebida");
                    addNewDrink.initOwner((Stage) takenOrders.getScene().getWindow());
                    Optional<String> selectedDrink = addNewDrink.showAndWait();
                    if(selectedDrink.isPresent()){
                        order.setDrink(Menu.searchDrink(selectedDrink.get()));
                        JOptionPane.showMessageDialog(null, "Bebida agregada con éxito");
                    }
                }
                    
            }
        }
           
    }
    
    public void seeOrders(){
        String outString = "";
        if(Restaurant.getTAKEN_ORDERS().isEmpty() == false){
            for(Order order : Restaurant.getTAKEN_ORDERS()){
                outString += "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
                outString += "\nOrden numero: "+  order.getOrderNumber() + "\n" + 
                        "Mesa Numero: " + order.getTable().getTableNumber() + "\n" +
                        "Mesero: " + order.getWaiter().getName() + "\n";
            }
            JOptionPane.showMessageDialog(null, outString,"Ordenes",JOptionPane.INFORMATION_MESSAGE);
        }
        else
            JOptionPane.showMessageDialog(null,"No hay ordenes tomadas");
    }
    
    public void cancelBill()throws Exception{
        System.out.println("Entró!");
        if(Restaurant.getPREPARED_ORDERS().isEmpty() == true){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("¡Aún no hay órdenes preparadas!");
            alert.showAndWait();            
        }
        else{
            ComboBox preparedOrdersNums = new ComboBox();
            preparedOrdersNums.setValue("Ordenes por Cancelar");
            for(Order order : Restaurant.getPREPARED_ORDERS())
                preparedOrdersNums.getItems().add(order.getOrderNumber());
            ToggleGroup toggleGroup = new ToggleGroup();
            RadioButton individualRdio = new RadioButton("Individual");
            RadioButton inPairsRdio = new RadioButton("En pareja");
            RadioButton inGroups = new RadioButton("En Grupo");
            toggleGroup.getToggles().addAll(individualRdio, inPairsRdio, inGroups);
            
            GridPane cancelingGP = new GridPane();
            cancelingGP.add(preparedOrdersNums, 0, 0);
            cancelingGP.add(individualRdio, 0, 1);
            cancelingGP.add(inPairsRdio, 1, 1);
            cancelingGP.add(inGroups, 2, 1);
            
            Alert cancellingDialog = new Alert(AlertType.INFORMATION);
            cancellingDialog.initOwner((Stage) takenOrders.getScene().getWindow());
            cancellingDialog.setTitle("Cuenta");
            cancellingDialog.setHeaderText("Cancelación");
            cancellingDialog.getDialogPane().setContent(cancelingGP);
            cancellingDialog.showAndWait();
        }
    }
    
    public void seePreparedOrders() throws Exception{
        String outString = "";
        if(Restaurant.getPREPARED_ORDERS().isEmpty() == false){
            for(Order order : Restaurant.getPREPARED_ORDERS()){
                outString += "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||";
                outString += "\nOrden numero: "+  order.getOrderNumber() + "\n" + 
                        "Mesa Numero: " + order.getTable().getTableNumber() + "\n" +
                        "Mesero: " + order.getWaiter().getName() + "\n";
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"¡No hay Ordenes Preparadas!");
            return;
        }
        Alert alert = new Alert(AlertType.NONE);
        Image image = new Image(getClass().getResource("/UI/images/prepared.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);

        alert.setTitle("Ordenes Preparadas");
        alert.setContentText(outString);
        ButtonType buttonTypeOne = new ButtonType("Aceptar",ButtonData.OK_DONE);
        ButtonType buttonTypeTwo = new ButtonType("Ver Detalle");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One"
        } else if (result.get() == buttonTypeTwo) {
           try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Cancelled.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();
            }catch(Exception e){}
        } else {
            // ... user chose CANCEL or closed the dialog
        }
        
    }
    
    public void especificConsult(){
        Alert alert = new Alert(AlertType.NONE);
        Image image = new Image(getClass().getResource("/UI/images/best.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);

        alert.setTitle("Ordenes Canceladas");
        alert.setContentText("Empleado del Mes");
        ButtonType buttonTypeOne = new ButtonType("Ver Chef");
        ButtonType buttonTypeTwo = new ButtonType("Ver Mesero");
        ButtonType buttonTypeThree = new ButtonType("Ok");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree);
        Optional<ButtonType> result = alert.showAndWait();
        
        if(result.get() == buttonTypeOne)
            consultChefOfTheMonth();
        else if(result.get() == buttonTypeTwo)
            consultWaiterOfTheMonth();
        else{
            alert.close();
        }
    }
    
    
    private void consultWaiterOfTheMonth(){
        if(Restaurant.getCANCELLED_ORDERS().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empleado del mes");
            alert.setContentText("Primero debes cancelar algunas ordenes :)");
            alert.showAndWait();
        }
        else{
            int aux = 0;
            Waiter waiterOfTheMont = new Waiter();
            for(Employee employee : Restaurant.getEmployees()){
                if(employee instanceof Waiter)
                    if(aux < ((Waiter) employee).getAtendedOrders()){
                        aux = ((Waiter) employee).getAtendedOrders();
                        waiterOfTheMont = (Waiter) employee;
                    }

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empleado del mes");
            alert.setContentText("El mesero con mas ordenes atendidas: \n" + waiterOfTheMont.getName());
            alert.showAndWait();
        }
    }
    
    private void consultChefOfTheMonth(){
        if(Restaurant.getCANCELLED_ORDERS().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empleado del mes");
            alert.setContentText("Primero debes cancelar algunas ordenes :)");
            alert.showAndWait();
        }
        else{
            int aux = 0;
            Chef chefOfTheMont = new Chef();
            for(Employee employee : Restaurant.getEmployees()){
                if(employee instanceof Chef)
                    if(aux < ((Chef) employee).getPreparedOrders()){
                        aux = ((Chef) employee).getPreparedOrders();
                        chefOfTheMont = (Chef) employee;
                    }

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Empleado del mes");
            alert.setContentText("El cocinero con más ordenes preparadas es: \n" + chefOfTheMont.getName());
            alert.showAndWait();
        }
    }
    
    public void ordersToPrepare() throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Chef.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();

        }catch(Exception e){}
        closeButtonAction();
    }
    
    public void seeReports() throws Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Consulting.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){}
        closeButtonAction();
    }
    
    public void closeButtonAction() throws Exception{
        Stage stage = (Stage) takenOrders.getScene().getWindow();
        stage.close();
    }
}
