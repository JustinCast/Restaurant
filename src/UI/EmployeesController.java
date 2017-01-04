/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.swing.JOptionPane;
import employees.*;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import restaurant_service.Address;
import restaurant_service.Restaurant;
/**
 * FXML Controller class
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class EmployeesController implements Initializable {
    @FXML private JFXButton addEmployee;
    @FXML private TableView chefTable;
    @FXML private TableView waiterTable;
    @FXML private TableColumn chefName;
    @FXML private TableColumn chefID;
    @FXML private TableColumn chefTelephone;
    @FXML private TableColumn chefAddress;
    @FXML private TableColumn chefEmail;
    @FXML private TableColumn waiterName;
    @FXML private TableColumn waiterID;
    @FXML private TableColumn waiterTelephone;
    @FXML private TableColumn waiterAddress;
    @FXML private TableColumn waiterEmail;
    @FXML private TableColumn waiterLanguages; 
    @FXML private AnchorPane employeesPane;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadChefs();
        loadWaiters();
    }    
    
    public void showDialog(){
        int opc = 0;
        while(opc > 2 || opc < 1)
            opc = Integer.parseInt(JOptionPane.showInputDialog(null,"1)Cocinero \n2)Mesero "));
        
        String name = "";
        while(name.length() == 0)
            name = JOptionPane.showInputDialog(null,"Ingrese el nombre completo");
        
        String ID = "";
        while(ID.length() == 0)
            ID = JOptionPane.showInputDialog(null,"Numero de cedula");
        
        String telephone = "";
        while(telephone.length() == 0)
            telephone = JOptionPane.showInputDialog(null,"Numero de telefono");
        
        String province = "";
        while(province.length() == 0)
            province = JOptionPane.showInputDialog(null,"Direccion \nProvincia");
        
        String canton = "";
        while(canton.length() == 0)
            canton = JOptionPane.showInputDialog(null,"Direccion\nCanton");
        
        String district = "";
        while(district.length() == 0)
            district = JOptionPane.showInputDialog(null,"Direccion\nDistrito");
        String exactAddress = "";
        while(exactAddress.length() == 0)
            exactAddress = JOptionPane.showInputDialog(null,"Direccion\nDireccion Exacta");
        
        String email = "";
        while(email.length() == 0)
            email = JOptionPane.showInputDialog(null,"E-mail");
        
        String idioms;
        int otherIdiom = 1;
        ArrayList totalIdioms = new ArrayList();
        Address address = new Address(province,canton,district,exactAddress);
        if(opc == 2){
            while(otherIdiom == 1){
                idioms = JOptionPane.showInputDialog(null,"Ingrese los idiomas separados por comas");
                otherIdiom = Integer.parseInt(JOptionPane.showInputDialog(null,"Desea agregar otro idioma\n1)Si\n2)No"));
                totalIdioms.add(idioms);
            }    
            Waiter waiter = new Waiter(totalIdioms,address,telephone,email,name,Integer.parseInt(ID));
            Restaurant.setEmployees(waiter);
            loadWaiters();
        }
        else
        {
            Chef chef = new Chef(address, telephone, email, name, Integer.parseInt(ID));
            Restaurant.setEmployees(chef);
            loadChefs();
        }
    }
    
    
    private void loadWaiters(){
        ObservableList<Employee> waiters = FXCollections.observableArrayList();
        Restaurant.getEmployees().stream().filter((person) -> (person instanceof Waiter)).forEach((person) -> {
            waiters.add((Waiter)person);
        });
        waiterName.setCellValueFactory(new PropertyValueFactory<>("name"));
        waiterID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        waiterTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        waiterAddress.setCellValueFactory( new PropertyValueFactory<>("address"));
        waiterEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        waiterLanguages.setCellValueFactory(new PropertyValueFactory<>("languages"));
        
        waiterTable.setItems(waiters);
        
        
    }
    private void loadChefs(){
        ObservableList<Employee> chefs = FXCollections.observableArrayList();
        Restaurant.getEmployees().stream().filter((person) -> (person instanceof Chef)).forEach((person) -> {
            chefs.add((Chef)person);
        });
        chefName.setCellValueFactory(new PropertyValueFactory<>("name"));
        chefID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        chefTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        chefAddress.setCellValueFactory( new PropertyValueFactory<>("address"));
        chefEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
        
        chefTable.setItems(chefs);        
    }
    
    public void openMenu(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.getIcons().add(new Image(getClass().getResourceAsStream("images/icono.png")));
            stage.setResizable(false);
            stage.show();
        }catch(Exception e){}
    }
    
    
}
