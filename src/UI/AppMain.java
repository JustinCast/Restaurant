
package UI;

import employees.Chef;
import employees.Waiter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import restaurant_service.Address;
import restaurant_service.Restaurant;
import static javafx.application.Application.launch;
import javafx.scene.image.Image;

/**
 *
 * @author Justin Cast
 */
public class AppMain extends Application{
    
    @FXML
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        // Inicialización de la ventana de inicio de sesión.
        Parent root = FXMLLoader.load(getClass().getResource("Init.fxml"));
        primaryStage.setTitle("Restaurant");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png"))); 
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        predefinedData();
        setRestaurantData();
    }
    /**
     * Pre-defined data of the waiters and chefs are loaded
     */
    private void predefinedData(){
        ArrayList languages = new ArrayList();
        languages.add("Italiano");
        String telephone = "85781158";
        String Email = "justincastillovalladares@gmail.com";
        String name = "Justin Castillo Valladares";
        Address address = new Address("Alajuela", "San Carlos", "Aguas Zarcas", "25 mts de la iglesia \ncatólica");
        Waiter waiter = new Waiter(languages, address, telephone, Email, name, 207490813);
        Restaurant.setEmployees(waiter);
        
        address = new Address("San Jose", "Moravia", "Los nonos", "500 mts sur de la iglesia \ncatólica");
        telephone = "87935455";
        Email = "marcoUgalde12@gmail.com";
        name = "Marco Ugalde Castro";
        Chef chef = new Chef(address, telephone, Email, name, 204250929);
        Restaurant.setEmployees(chef);
        
        
        address = new Address("Santa Clara", "Centro", "El TEC", "por el TEC");
        telephone = "87935446";
        Email = "marcoUgalde12@gmail.com";
        name = "Franciso Ulate";
        Chef chef2 = new Chef(address, telephone, Email, name, 123456789);
        Restaurant.setEmployees(chef2);
    }
    
    private void setRestaurantData() throws IOException{
        Restaurant.setName("¡Que rico!");
        Restaurant.setTelephone("24619444");
        Restaurant.setEmail("querico@restaurante.com");
        Address address = new Address("Alajuela", "Centro", "Garita", "40 mts oeste de la iglesia");
        Restaurant.setAddress(address);
    }
}