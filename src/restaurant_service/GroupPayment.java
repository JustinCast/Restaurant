
package restaurant_service;


//Class that inherits from 'form of payment' and implements its abstract methods
public class GroupPayment extends WayToPay {
    private int persons;
    public GroupPayment(float SaleTax, float Service, Order order,int persons) {
        super(SaleTax, Service,order);
        this.persons = persons;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }
    
    @Override
    public float CalculateTheCount() {
        float count = 0;
        for(Dish dish : getOrder().getDISHES())
            count += dish.getPriceWihtoutTax();
        for(Drink drink : getOrder().getDRINKS())
            count += drink.getPrice();
        count += (float) (count * .13);
        count += (float) (count * .6);
        count = count / persons; //The total of the account is divided among the total of people
        return count;
    }

}
