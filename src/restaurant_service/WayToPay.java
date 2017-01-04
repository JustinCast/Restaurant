
package restaurant_service;

/**
 * 
 * Abstract class of payment methods
 */
public abstract class WayToPay {
    private float SaleTax;
    private float Service;
    private Order order;

    /**
     *
     * @param SaleTax
     * @param Service
     * @param order
     */
    public WayToPay(float SaleTax, float Service, Order order) {
        this.SaleTax = SaleTax;
        this.Service = Service;
        this.order = order;
    }

    /**
     *
     * @return
     */
    public float getSaleTax() {
        return SaleTax;
    }

    /**
     *
     * @param SaleTax
     */
    public void setSaleTax(float SaleTax) {
        this.SaleTax = SaleTax;
    }

    /**
     *
     * @return Service
     */
    public float getService() {
        return Service;
    }

    /**
     *
     * @param Service
     */
    public void setService(float Service) {
        this.Service = Service;
    }

    /**
     *
     * @return order
     */
    public Order getOrder() {
        return order;
    }

    /**
     *
     * @param order
     */
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public abstract float CalculateTheCount();

}
