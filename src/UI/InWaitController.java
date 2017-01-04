
package UI;

import employees.Chef;
import employees.Employee;
import employees.Waiter;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        int order_Number  = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el numero de orden"));
        
        if(Restaurant.searchTakenOrder(order_Number) == null)
            JOptionPane.showMessageDialog(null, "¡El numero de orden no existe!");
        else{
            int opc = 1;
            int aux = 0;
            while(opc == 1){
                int opcAux = Integer.parseInt(JOptionPane.showInputDialog(null,"Agregar\n1)Platillo\n2)Bebida"));
                if(opcAux == 1){
                    int dishNumber = Integer.parseInt(JOptionPane.showInputDialog(null,"Numero de plato"));
                    Dish dish = Menu.searchDish(dishNumber);
                    if(dish == null)
                        JOptionPane.showMessageDialog(null,"¡El platillo no ha sido encontrado!");
                    else{
                        Restaurant.searchTakenOrder(order_Number).setDishes(dish);
                        aux = 1;
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
                        Restaurant.searchTakenOrder(order_Number).setDrink(drink);
                        aux = 1;
                    }
                }
                
                opc = Integer.parseInt(JOptionPane.showInputDialog(null,"¿Desea agregar otro platillo o bebida?\n"
                        + "1)Si\n2)No"));
            }
            if(aux == 1)
                JOptionPane.showMessageDialog(null, "Orden tomada");
            loadTakenOrders();
        }
        closeButtonAction();
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
    
    public void cancelBill() throws Exception{
        if(Restaurant.getPREPARED_ORDERS().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setContentText("¡Aún no hay órdenes preparadas!");
            alert.showAndWait();
            return;
        }
        int order_Number = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el numero de orden"));
        
        Order order = Restaurant.searchPreparedOrder(order_Number);
        if(order == null)
            JOptionPane.showMessageDialog(null, "La orden no ha sido encontrada");
        else{
            int payForm = Integer.parseInt(JOptionPane.showInputDialog(null,"Forma de pago\n1)Individual\n"
                    + "2)Separado(dos personas)\n3)En grupo"));
            switch(payForm){
                case 1:
                    Customer customer = new Customer();
                    TextInputDialog dialog = new TextInputDialog("ID Cliente");
                    dialog.setTitle("Facturacion");
                    dialog.setHeaderText("Factura Compra");
                    dialog.setContentText("Ingrese el ID del cliente:");
                    Image image = new Image(getClass().getResource("/UI/images/invoice.png").toExternalForm());
                    ImageView imageView = new ImageView(image);
                    dialog.setGraphic(imageView);
                    // Traditional way to get the response value.
                    Optional<String> result = dialog.showAndWait();
                    if (result.isPresent()){
                        customer.setID(Integer.parseInt(result.get()));
                        customer.setRecurrence(1);
                            
                    }
                    
                    TextInputDialog dialog2 = new TextInputDialog("ID Cliente");
                    dialog2.setTitle("Facturacion");
                    dialog2.setHeaderText("Factura Compra");
                    dialog2.setContentText("Ingrese el el nombre del cliente:");
                    Image image2 = new Image(getClass().getResource("/UI/images/invoice.png").toExternalForm());
                    ImageView imageView2 = new ImageView(image2);
                    dialog2.setGraphic(imageView2);
                    // Traditional way to get the response value.
                    Optional<String> result2 = dialog2.showAndWait();
                    if (result2.isPresent()){
                        customer.setName(result2.get());
                    }
                    order.setCustomers(customer);
                    WayToPay individual = new AllPayment((float) 0.13, (float) 0.16, order);
                    JOptionPane.showMessageDialog(null, "Lo correspondiente a pagar es ¢" + individual.CalculateTheCount());
                    Restaurant.setCANCELLED_ORDERS(order);
                    Restaurant.getPREPARED_ORDERS().remove(order);
                    Restaurant.getBUSY_TABLES().remove(order.getTable());
                    break;
                case 2:
                    Customer customer1 = new Customer();
                    TextInputDialog dialog3 = new TextInputDialog("ID Cliente");
                    dialog3.setTitle("Facturacion");
                    dialog3.setHeaderText("Factura Compra");
                    dialog3.setContentText("Ingrese el ID del cliente:");
                    Image image3 = new Image(getClass().getResource("/UI/images/invoice.png").toExternalForm());
                    ImageView imageView3 = new ImageView(image3);
                    dialog3.setGraphic(imageView3);
                    // Traditional way to get the response value.
                    Optional<String> result3 = dialog3.showAndWait();
                    if (result3.isPresent()){
                        customer1.setID(Integer.parseInt(result3.get()));
                        customer1.setRecurrence(1);
                            
                    }
                    order.setCustomers(customer1);
                    
                    Customer customer2 = new Customer();
                    TextInputDialog dialog4 = new TextInputDialog("ID Cliente");
                    dialog4.setTitle("Facturacion");
                    dialog4.setHeaderText("Factura Compra");
                    dialog4.setContentText("Ingrese el ID del cliente:");
                    Image image4 = new Image(getClass().getResource("/UI/images/invoice.png").toExternalForm());
                    ImageView imageView4 = new ImageView(image4);
                    dialog4.setGraphic(imageView4);
                    // Traditional way to get the response value.
                    Optional<String> result4 = dialog4.showAndWait();
                    if (result4.isPresent()){
                        customer2.setID(Integer.parseInt(result4.get()));
                        customer2.setRecurrence(1);
                            
                    }
                    order.setCustomers(customer2);
                    
                    WayToPay separate = new SeparatePayment((float) 0.13, (float) 0.16, order);
                    JOptionPane.showMessageDialog(null, "Lo correspondiente a pagar por persona es ¢" + separate.CalculateTheCount());
                    Restaurant.setCANCELLED_ORDERS(order);
                    Restaurant.getPREPARED_ORDERS().remove(order);
                    Restaurant.getBUSY_TABLES().remove(order.getTable());
                    break;
                case 3:
                    int persons = Integer.parseInt(JOptionPane.showInputDialog(null,"¿Cuántas personas"
                            + " harán el pago?"));
                    WayToPay inGroup = new GroupPayment((float) 0.13, (float) 0.16, order, persons);
                    while(persons > 0){
                        Customer customer3 = new Customer();
                        TextInputDialog dialog5 = new TextInputDialog("ID Cliente");
                        dialog5.setTitle("Facturacion");
                        dialog5.setHeaderText("Factura Compra");
                        dialog5.setContentText("Ingrese el ID del cliente:");
                        Image image5 = new Image(getClass().getResource("/UI/images/invoice.png").toExternalForm());
                        ImageView imageView5 = new ImageView(image5);
                        dialog5.setGraphic(imageView5);
                        // Traditional way to get the response value.
                        Optional<String> result5 = dialog5.showAndWait();
                        if (result5.isPresent()){
                            customer3.setID(Integer.parseInt(result5.get()));
                            customer3.setRecurrence(1);

                        }
                        order.setCustomers(customer3);
                        persons--;
                    }
                    JOptionPane.showMessageDialog(null, "Lo correspondiente a pagar por persona es ¢" + inGroup.CalculateTheCount());
                    Restaurant.setCANCELLED_ORDERS(order);
                    Restaurant.getPREPARED_ORDERS().remove(order);
                    Restaurant.getBUSY_TABLES().remove(order.getTable());
                    break;
            }
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
