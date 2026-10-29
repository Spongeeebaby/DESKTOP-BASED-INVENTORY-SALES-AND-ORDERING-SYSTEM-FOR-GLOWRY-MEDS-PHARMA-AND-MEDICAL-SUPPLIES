
package service;

import dao.ProductDAO;
import model.Product;

import java.util.List;

public class InventoryService {
    
    private final ProductDAO productDAO;

    public InventoryService() {

        productDAO = new ProductDAO();
    }

    public boolean addProduct(Product product) {
        return productDAO.insertProduct(product);
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public boolean updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }

    public boolean deleteProduct(int id) {
        return productDAO.deleteProduct(id);
    }
    
    public boolean reduceStock(String code, int quantity) {
    return productDAO.reduceStock(code, quantity);
    }
    
    public Product findByCode(String code) {
    return productDAO.findByCode(code);
    }
    
    public List<Product> searchProducts(String keyword) {
    return productDAO.searchProducts(keyword);

}
}   
    
    
    
    

