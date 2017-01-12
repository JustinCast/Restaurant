/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import restaurant_service.Restaurant;

public class InitController implements Initializable {
    @FXML private JFXButton btnTakeOrder;
    @FXML public AnchorPane init;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /**
         * Se agrega la clave Css #init, esto es mas seguro para evitar confusiones cuando se tienen muchos css
         * Se aprovecha la encapsulación de los datos
         */
        init.setId("init");
        init.getStyleClass().add("init");
    } 
    
    public void openRegistration()throws IOException, Exception{
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Home.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setScene(new Scene(root1));
            stage.show();
            closeButtonAction();
        }
        catch(Exception e){}
    }
    public void openOrder()throws IOException, Exception{
        if(Restaurant.class == null){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Restaurante");
            alert.setHeaderText("No hay un Restaurante ingresado");
            alert.initOwner((Stage) btnTakeOrder.getScene().getWindow());
            alert.showAndWait();
            return;
        }            
        try{
            closeButtonAction();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TakingOrder.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(Exception e){}
    }
    
    public void openMenu() throws Exception{
        try{           
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(Exception e){}
        closeButtonAction();
    }
    
    public void developer(){
        Alert alert = new Alert(AlertType.NONE);
        ButtonType buttonTypeOne = new ButtonType("Aceptar",ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(buttonTypeOne);
        alert.initOwner((Stage) btnTakeOrder.getScene().getWindow());
        Image image = new Image(getClass().getResource("/UI/images/computer.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        alert.setTitle("Desarrollador");
        alert.setContentText("Justin Alberto Castillo Valladares\n"
                + "Estudiante Ingeniería en Computación\n"
                + "Sede San Carlos\n"
                + "justincastillovalladares@gmail.com");
        alert.showAndWait();
    }
    
    public void closeButtonAction() throws Exception{
        Stage stage = (Stage) btnTakeOrder.getScene().getWindow();
        stage.close();
    }
    
}
