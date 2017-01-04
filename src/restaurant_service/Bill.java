
package restaurant_service;

import java.util.Date;


//Bill Class | assigned to customer
public class Bill {
    private Date date;
    private String detail;
    private WayToPay payForm;
    private int price;
    private float service;

    public Bill(Date date, String detail, WayToPay payForm, int price, float service) {
        this.date = date;
        this.detail = detail;
        this.payForm = payForm;
        this.price = price;
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public WayToPay getPayForm() {
        return payForm;
    }

    public void setPayForm(WayToPay payForm) {
        this.payForm = payForm;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getService() {
        return service;
    }

    public void setService(float service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Bill{" + "date=" + date + ", detail=" + detail + 
                ", payForm=" + payForm + ", price=" + price + ", service=" + service + '}';
    }
    
    

}
