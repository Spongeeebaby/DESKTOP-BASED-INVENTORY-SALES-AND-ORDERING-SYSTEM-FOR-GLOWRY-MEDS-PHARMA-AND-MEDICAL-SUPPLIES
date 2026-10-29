
package model;


public class SaleItem {
    
    private int saleId;
    private int productId;
    private String productCode;
    private String productName;
    private int quantity;
    private double price;
    private double subtotal;
    private String staffName;

    // Constructor used when displaying sale items
    public SaleItem(
            String productCode,
            String productName,
            int quantity,
            double price,
            double subtotal) {

        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    // Constructor used when adding a sale item with FK
    public SaleItem(
            int saleId,
            int productId,
            String productCode,
            String productName,
            int quantity,
            double price,
            double subtotal) {

        this.saleId = saleId;
        this.productId = productId;
        this.productCode = productCode;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }
    
    //public access from other class// void not return a value
    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public int getSaleId() {
        return saleId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getSubtotal() {
        return subtotal;
    }
    
    public String getStaffName(){
        return staffName;
    }

    public void setStaffName(String staffName) {
    this.staffName = staffName;
    }   
}
