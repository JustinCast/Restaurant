/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import restaurant_service.Restaurant;
import restaurant_service.Table;

public final class TableServiesController implements Initializable {
    @FXML private GridPane tablesGrid;
    private static int counter = 0;
    private static int tableNumber = 0;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillGrid();
        pending();
        busy();
    }    
    
    public void verifying(MouseEvent event){
//       //System.out.println(((Control)event.getSource()).getId());
//        if(((ToggleButton)(Control)event.getSource()).isSelected() == true){
//            ((Control)event.getSource()).setStyle("-fx-background-color: #ff0000; ");
////            Table busy = new Table();
////            busy.setTableNumber(Integer.parseInt(((Control)event.getSource()).getId()));
////            Restaurant.setBUSY_TABLES(busy);
//            closeButtonAction();
//        }
//        else
//            ((Control)event.getSource()).setStyle("-fx-background-color:#22D435; ");
//        
        tableNumber =  Integer.parseInt(((Control)event.getSource()).getId());
        closeButtonAction();
    }
    
    private void fillGrid(){
        tablesGrid.getChildren().stream().forEach((Node tmp) -> {
            tmp.setId(String.valueOf(auxiliar()));
            tmp.setStyle("-fx-background-color: #22D435; ");            
            tmp.setOnMouseClicked(this::verifying);
            Table free = new Table();
            free.setTableNumber(Integer.parseInt(tmp.getId()));
            Restaurant.setFREE_TABLES(free);
        });
        counter = 0;
    }
    private int auxiliar(){
        if(counter != 0)
            counter += 1 ;
        else
            counter += 1;
        return counter;
    }

    public static int getTableNumber() {
        return tableNumber;
    }
    
    private void pending(){
        if(!Restaurant.getPENDING_ATTEND().isEmpty()){
            for(Table table : Restaurant.getPENDING_ATTEND()){
                tablesGrid.getChildren().stream().forEach((Node tmp) -> {
                    if(Integer.parseInt(tmp.getId()) == table.getTableNumber()){
                        tmp.setStyle("-fx-background-color:  #FFC500; ");
                        tmp.setDisable(true);
                    }
                });
            }
        }
    }
    private void busy(){
        if(!Restaurant.getBUSY_TABLES().isEmpty()){
            for(Table table : Restaurant.getBUSY_TABLES()){
                tablesGrid.getChildren().stream().forEach((Node tmp) -> {
                    if(Integer.parseInt(tmp.getId()) == table.getTableNumber()){
                        tmp.setStyle("-fx-background-color:  #ff0000; ");
                        tmp.setDisable(true);
                    }
                });
            }
        }
    }
    
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        for (Node node : tablesGrid.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
    
    public void closeButtonAction(){
        Stage stage = (Stage) tablesGrid.getScene().getWindow();
        stage.close();
    }
    
}
