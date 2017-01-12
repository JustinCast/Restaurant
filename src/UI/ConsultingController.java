
package UI;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.util.Callback;
import restaurant_service.Dish;
import restaurant_service.Drink;
import restaurant_service.Order;
import restaurant_service.Person;
import restaurant_service.Restaurant;

public class ConsultingController implements Initializable  {
    @FXML private JFXTextArea sellDishes;
    @FXML private JFXTextArea sellDrinks;
    @FXML private JFXTextArea sellPerMonth;
    @FXML private JFXTextArea sellPerYear;
    @FXML private JFXTextArea customersAtendedPerMonth;
    @FXML private JFXTextArea customersAtendedPerYear;
    @FXML private DatePicker dishesDate;
    @FXML private DatePicker drinksDate;
    @FXML private DatePicker totalSellMonthDate;
    @FXML private DatePicker totalSellYearDate;
    @FXML private DatePicker customersPerMonthDate;
    @FXML private DatePicker customersPerYearDate;
    @FXML private Text totalDishes;
    @FXML private Text totalDrinks;
    @FXML private Text totalPerMonth;
    @FXML private Text totalPerYear;
    @FXML private Text customersPerMonth;
    @FXML private Text customersPerYear;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        final Callback<DatePicker, DateCell> dayCellFactory = (final DatePicker datePicker) -> new DateCell() {
            @Override public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                
                if (MonthDay.from(item).equals(MonthDay.of(9, 25))) {
                    setTooltip(new Tooltip("Happy Birthday!"));
                    setStyle("-fx-background-color: #F44336");
                }
                if (item.equals(LocalDate.now().plusDays(1))) {
                    setStyle("-fx-background-color: #F44336");
                }
            }
        };
    dishesDate.setDayCellFactory(dayCellFactory);
    }
    
    public void loadSellDishes(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String output = "";
        int cont = 0;
        for(Order order : Restaurant.getCANCELLED_ORDERS()){
            if(dtf.format(order.getDate()).equals(dtf.format(dishesDate.getValue()))){
                for(Dish dish : order.getDISHES())
                    output += dish.getDishName() + "\n";                
                cont += order.getDISHES().size();
            }
        }
        if(Restaurant.getCANCELLED_ORDERS().isEmpty())
            output = "NO hay ordenes canceladas";
        sellDishes.setText(output);
        totalDishes.setText("Total: " + String.valueOf(cont));
        
    }
    
    public void loadSellDrinks(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String output = "";
        int cont = 0;
        for(Order order : Restaurant.getCANCELLED_ORDERS()){
            if(dtf.format(order.getDate()).equals(dtf.format(drinksDate.getValue()))){
                for(Drink drink : order.getDRINKS())
                    output += drink.getName() + "\n";
                cont += order.getDRINKS().size();
            }
        }
        if(Restaurant.getCANCELLED_ORDERS().isEmpty())
            output = "NO hay ordenes canceladas";
        sellDrinks.setText(output);
        totalDrinks.setText("Total: " + String.valueOf(cont));
    }
    
    public void totalSellPerMonth(){
        String output = "";
        int cont = 0;
        for(Order order : Restaurant.getCANCELLED_ORDERS()){
            if(order.getDate().getMonth() == totalSellMonthDate.getValue().getMonth()){
                for(Drink drink : order.getDRINKS())
                    output += drink.getName() + "\n";
                
                for(Dish dish : order.getDISHES())
                    output += dish.getDishName() + "\n"; 
                cont += order.getDISHES().size();
                cont += order.getDRINKS().size();
            }
        }
        if(Restaurant.getCANCELLED_ORDERS().isEmpty())
            output = "NO hay ordenes canceladas";
        sellPerMonth.setText(output);
        totalPerMonth.setText("Total: " + String.valueOf(cont));
    }
    
    public void totalSellPerYear(){
        String output = "";
        int cont = 0;
        for(Order order : Restaurant.getCANCELLED_ORDERS()){
            if(order.getDate().getYear() == totalSellYearDate.getValue().getYear()){
                for(Drink drink : order.getDRINKS())
                    output += drink.getName() + "\n";
                
                for(Dish dish : order.getDISHES())
                    output += dish.getDishName() + "\n";  
                cont += order.getDISHES().size();
                cont += order.getDRINKS().size();
            }
        }
        if(Restaurant.getCANCELLED_ORDERS().isEmpty())
            output = "NO hay ordenes canceladas";
        sellPerYear.setText(output);
        totalPerYear.setText("Total: " + String.valueOf(cont));
    }
    
    public void totalAtendedPerMonth(){
        String output = "";
        int cont = 0;
        for(Order order : Restaurant.getCANCELLED_ORDERS()){
            if(order.getDate().getMonth()== customersPerMonthDate.getValue().getMonth()){
                for(Person person : order.getCustomers()){
                    cont ++;
                    output += person.getName() + "\n";
                    output += person.getID() + "\n";
                }
            }
        }
        if(Restaurant.getCANCELLED_ORDERS().isEmpty())
            output = "NO hay ordenes canceladas";
        customersAtendedPerMonth.setText(output);
        customersPerMonth.setText("Total: " + String.valueOf(cont));
    }
    public void totalAtendedPerYear(){
        String output = "";
        int cont = 0;
        for(Order order : Restaurant.getCANCELLED_ORDERS()){
            if(order.getDate().getYear() == customersPerMonthDate.getValue().getYear()){
                for(Person person : order.getCustomers()){
                    cont ++;
                    output += person.getName() + "\n";
                    output += person.getID() + "\n";
                }
            }
        }
        if(Restaurant.getCANCELLED_ORDERS().isEmpty())
            output = "NO hay ordenes canceladas";
        customersAtendedPerYear.setText(output);
        customersPerYear.setText("Total: " + String.valueOf(cont));
    }
    
    

}
