
package restaurant_service;

//Individual Pay Form
public class AllPayment extends WayToPay {

    public AllPayment(float SaleTax, float Service, Order order) {
        super(SaleTax, Service, order);
    }

    //Implementation of an abstract method in the super class
    @Override
    public float CalculateTheCount() {
        float count = 0;
        for(Dish dish : getOrder().getDISHES())
            count += dish.getPriceWihtoutTax();
        for(Drink drink : getOrder().getDRINKS())
            count += drink.getPrice();
        count += (float) (count * .13);
        count += (float) (count * .6);
        return count;
    }

}
