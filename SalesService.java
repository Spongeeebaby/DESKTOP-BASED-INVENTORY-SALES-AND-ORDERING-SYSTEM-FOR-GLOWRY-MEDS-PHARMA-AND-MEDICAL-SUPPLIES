
package service;
import dao.SalesDAO;
import model.Sales;
import model.SaleItem;

import java.util.List;

public class SalesService {
    
     private final SalesDAO salesDAO;

    public SalesService() {
        salesDAO = new SalesDAO();
    }

    public int addSale(Sales sale) {
        return salesDAO.addSale(sale);
    }

    public boolean addSaleItem(SaleItem item) {
        return salesDAO.addSaleItem(item);
    }

    public double getTodaySales() {
        return salesDAO.getTodaySales();
    }

    public int getTodayOrderCount() {
        return salesDAO.getTodayOrderCount();
    }

    public List<SaleItem> getTodayProductsSold() {
        return salesDAO.getTodayProductsSold();
    }
    
    public double getSalesByDate(String date) {
        return salesDAO.getSalesByDate(date);
    }
    
    public int getOrdersByDate(String date) {
        return salesDAO.getOrdersByDate(date);
    }
    
    public List<SaleItem> getProductsByDate(String date) {
        return salesDAO.getProductsByDate(date);
}
}