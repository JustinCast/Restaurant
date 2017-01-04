/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import restaurant_service.*;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class HomeController implements Initializable {
    @FXML private JFXTextField restName;
    @FXML private JFXTextField logoDir;
    @FXML private JFXTextField telephone;
    @FXML private JFXTextField province;
    @FXML private JFXTextField canton;
    @FXML private JFXTextField district;
    @FXML private JFXTextField email;
    @FXML private JFXTextArea exactAddress;
    @FXML private AnchorPane homePane;
    @FXML private JFXButton btnOk;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        homePane.setId("registerPane");
        homePane.getStyleClass().add("Register");
        restName.setStyle("-fx-text-inner-color: white;");
        logoDir.setStyle("-fx-text-inner-color: white;");
        telephone.setStyle("-fx-text-inner-color: white;");
        province.setStyle("-fx-text-inner-color: white;");
        canton.setStyle("-fx-text-inner-color: white;");
        district.setStyle("-fx-text-inner-color: white;");
        email.setStyle("-fx-text-inner-color: white;");
        exactAddress.setStyle("-fx-text-inner-color: black;");
    }   
    
    private void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("¡No debes dejar campos vacíos!");
        alert.showAndWait();
    }
    
    public void Accept() throws Exception{
        if(restName.getText().length() == 0){
            showAlert();
        }
        else if(logoDir.getText().length() == 0){
            showAlert();
        }
        else if(telephone.getText().length() == 0){
            showAlert();
        }
        else if(province.getText().length() == 0){
            showAlert();
        }
        else if(canton.getText().length() == 0){
            showAlert();
        }
        else if(district.getText().length() == 0){
            showAlert();
        }
        else if(email.getText().length() == 0){
            showAlert();
        }
        else if(exactAddress.getText().length() == 0){
            showAlert();
        }
        else {
            Restaurant.setLogo(ImageIO.read(new File(logoDir.getText())));
            Restaurant.setName(restName.getText());
            Restaurant.setTelephone(telephone.getText());
            Address address = new Address(province.getText(), canton.getText(), district.getText(), exactAddress.getText());
            Restaurant.setAddress(address);
            Restaurant.setEmail(email.getText());
            newWindow();
            closeButtonAction();
        }
    }
    
    private void newWindow() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Employees.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void btnShowPictureChooser_Click() {
        JFileChooser dlg = new JFileChooser();
        dlg.showOpenDialog(null);
        logoDir.setText(dlg.getSelectedFile().getAbsolutePath());
    }
    
    public void closeButtonAction(){
        Stage stage = (Stage) exactAddress.getScene().getWindow();
        stage.close();
    }
}
