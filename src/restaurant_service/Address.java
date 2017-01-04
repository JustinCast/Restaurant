
package restaurant_service;


/*
*Is contained in the classes of: restaurant and employees
*/
public class Address {
    private String province;
    private String canton;
    private String district;
    private String ExactAddress;
    
    public Address(String province, String canton, String district, String ExactAddress) {
        this.province = province;
        this.canton = canton;
        this.district = district;
        this.ExactAddress = ExactAddress;
    }
    
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getExactAddress() {
        return ExactAddress;
    }

    public void setExactAddress(String ExactAddress) {
        this.ExactAddress = ExactAddress;
    }

    @Override
    public String toString() {
        return "Address{" + "province=" + province + ", canton=" + canton + ", district=" + district + ", ExactAddress=" + ExactAddress + '}';
    }
    
    

}
