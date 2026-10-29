
package model;


public class Product {
    
    private int id;
    private int adminId;
    private String code;
    private String medicine;
    private String category;
    private double price;
    private int stock;
    private String unit;
    private String expDate;
    
    
    public Product(
            int adminId,
            String code,           
            String medicine,
            String category,
            double price,
            int stock,
            String unit,
            String expDate) {
        
        this.adminId = adminId;
        this.code = code;
        this.medicine = medicine;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.unit = unit;
        this.expDate = expDate;
    }
    
    public Product(
            int id,
            int adminId,
            String code,
            String medicine,
            String category,
            double price,
            int stock,
            String unit,
            String expDate) {

        this.id = id;
        this.adminId = adminId;
        this.code = code;
        this.medicine = medicine;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.unit = unit;
        this.expDate = expDate;
    }

    public int getId() {
        return id;
    }
    
    public int getAdminId() {
    return adminId;
    }

    public String getCode() {
        return code;
    }

    public String getMedicine() {
        return medicine;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String getUnit() {
        return unit;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setAdminId(int adminId) {
    this.adminId = adminId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }
}
