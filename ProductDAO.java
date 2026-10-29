
package dao;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

public class ProductDAO {
    
     public boolean insertProduct(Product product) {

        String sql = "INSERT INTO products " + "(id_admin, code, medicine, category, price, " + "stock, unit, exp_date) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    
         try (
            Connection conn = DatabaseConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, product.getAdminId());
            
            stmt.setString(2,product.getCode());

            stmt.setString(3,product.getMedicine());

            stmt.setString(4,product.getCategory());

            stmt.setDouble(5,product.getPrice());

            stmt.setInt(6,product.getStock());

            stmt.setString(7,product.getUnit());
            
            
            java.sql.Date expDate =
            java.sql.Date.valueOf(
                    java.time.LocalDate.parse(
                            product.getExpDate(),
                            java.time.format.DateTimeFormatter.ofPattern("MM/dd/yyyy")
                            
                            
                    )
            );
            
    stmt.setDate(8, expDate);

    stmt.executeUpdate();

                return true;
                
                
} catch (Exception e) {System.out.println("Insert Error: "+ e.getMessage());
    return false;
        }
    }
     
     public List<Product> getAllProducts() {

    List<Product> products = new ArrayList<>();

    String sql = "SELECT * FROM products";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Product product = new Product(
                    rs.getInt("id"),
                    rs.getInt("id_admin"),
                    rs.getString("code"),
                    rs.getString("medicine"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("unit"),
                    rs.getString("exp_date")
            );

            products.add(product);
        }

    } catch (Exception e) {

        System.out.println(
                "Get All Products Error: "
                + e.getMessage()
        );

        e.printStackTrace();
    }

    return products;
}

   public Product findByCode(String code) {

    String sql = "SELECT * FROM products WHERE code = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, code);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            return new Product(
                    rs.getInt("id"),
                     rs.getInt("id_admin"),
                    rs.getString("code"),
                    rs.getString("medicine"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("unit"),
                    rs.getString("exp_date")
            );
        }

    } catch (Exception e) {
        System.out.println("Find Product Error: " + e.getMessage());
    }

    return null;
}
    

    public boolean updateProduct(Product product) {

        String sql =
                "UPDATE products SET "
                + "code = ?, "
                + "medicine = ?, "
                + "category = ?, "
                + "price = ?, "
                + "stock = ?, "
                + "unit = ?, "
                + "exp_date = ? "
                + "WHERE id = ?";

        try (Connection conn =DatabaseConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,product.getCode());

            stmt.setString(2,product.getMedicine());

            stmt.setString(3,product.getCategory());

            stmt.setDouble(4,product.getPrice());

            stmt.setInt(5,product.getStock());

            stmt.setString(6,product.getUnit());

            stmt.setString(7,product.getExpDate());

            stmt.setInt(8, product.getId());

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {System.out.println("Update Error: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteProduct(int id) {

        String sql ="DELETE FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {

            System.out.println("Delete Error: "+ e.getMessage());

            return false;
        }
    }
    
    public boolean reduceStock(String code, int quantity) {

    String sql = "UPDATE products "
               + "SET stock = stock - ? "
               + "WHERE code = ? AND stock >= ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, quantity);
        stmt.setString(2, code);
        stmt.setInt(3, quantity);

        int rows = stmt.executeUpdate();

        return rows > 0;

    } catch (Exception e) {
        System.out.println("Reduce Stock Error: " + e.getMessage());
        e.printStackTrace();
        return false;
    }
    }
    
    public List<Product> searchProducts(String keyword) {

    List<Product> products = new ArrayList<>();

    String sql =
            "SELECT * FROM products "
            + "WHERE code LIKE ? "
            + "OR medicine LIKE ?";

    try (
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)
    ) {

        String search = "%" + keyword + "%";

        stmt.setString(1, search);
        stmt.setString(2, search);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Product product = new Product(
                    rs.getInt("id"),
                    rs.getInt("id_admin"),
                    rs.getString("code"),
                    rs.getString("medicine"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("unit"),
                    rs.getString("exp_date")
            );

            products.add(product);
        }

    } catch (SQLException e) {

        System.out.println(
                "Search Product Error: "
                + e.getMessage()
        );
    }
    
    return products;
}

}
