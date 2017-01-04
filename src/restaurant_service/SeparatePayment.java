/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package restaurant_service;

/**
 * 
 * Implementation of WayToPay abstract Class
 */
public class SeparatePayment extends WayToPay {

    public SeparatePayment(float SaleTax, float Service, Order order) {
        super(SaleTax, Service, order);
    }

    /**
     *
     * @return count 
     * Divided in two for separate payment
     */
    @Override
    public float CalculateTheCount() {
        float count = 0;
        for(Dish dish : getOrder().getDISHES())
            count += dish.getPriceWihtoutTax();
        for(Drink drink : getOrder().getDRINKS())
            count += drink.getPrice();
        count += (float) (count * .13);
        count += (float) (count * .6);
        count = count /2;
        return count;
    }
    
}
