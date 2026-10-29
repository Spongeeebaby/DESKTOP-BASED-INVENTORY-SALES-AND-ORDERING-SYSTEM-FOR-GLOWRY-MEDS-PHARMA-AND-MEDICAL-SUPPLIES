
package dao;
import model.Sales;
import model.SaleItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class SalesDAO {
    
    
    public int addSale(Sales sale) {

        String sql = "INSERT INTO sales "
               + "(id_staff, sale_date, total, money_paid, change_amount) "
               + "VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt =
                 conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        stmt.setInt(1, sale.getStaffId());
        stmt.setTimestamp(2, Timestamp.valueOf(sale.getSaleDate()));
        stmt.setDouble(3, sale.getTotal());
        stmt.setDouble(4, sale.getMoneyPaid());
        stmt.setDouble(5, sale.getChangeAmount());

        stmt.executeUpdate();

        try (ResultSet keys = stmt.getGeneratedKeys()) {
            if (keys.next()) {
                return keys.getInt(1);
            }
        }

    } catch (SQLException e) {
        System.out.println("Add Sale Error: " + e.getMessage());
        e.printStackTrace();
    }

        return -1;
    }

    // ADD SALE ITEM
  
    public boolean addSaleItem(SaleItem item) {

        String sql = "INSERT INTO sales_item "
                   + "(sale_id, product_id, product_code, product_name, "
                   + "quantity, price, subtotal) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,item.getSaleId());
            
            stmt.setInt(2, item.getProductId());

            stmt.setString(3,item.getProductCode());

            stmt.setString(4,item.getProductName());

            stmt.setInt(5,item.getQuantity());

            stmt.setDouble(6,item.getPrice());

            stmt.setDouble(7,item.getSubtotal());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Add Sale Item Error: "+ e.getMessage());

            e.printStackTrace();

            return false;
        }
    }


    // DAILY SALES

    public double getTodaySales() {

        String sql =
                "SELECT COALESCE(SUM(total), 0) "
              + "FROM sales "
              + "WHERE DATE(sale_date) = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(LocalDate.now()));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {

            System.out.println("Get Today Sales Error: " + e.getMessage());

            e.printStackTrace();
        }

        return 0;
    }

    // TOTAL ORDERS

    public int getTodayOrderCount() {

        String sql =
                "SELECT COUNT(*) "
              + "FROM sales "
              + "WHERE DATE(sale_date) = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1,Date.valueOf(LocalDate.now()));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println( "Get Today Order Error: " + e.getMessage());
            e.printStackTrace();
        }

        return 0;
    }

   
    // PRODUCTS SOLD TODAY
   

    public List<SaleItem> getTodayProductsSold() {

        List<SaleItem> list = new ArrayList<>();

        String sql =
            "SELECT CONCAT(st.firstname, ' ', st.lastname) AS staff_name, "
          + "si.product_code, "
          + "si.product_name, "
          + "SUM(si.quantity) AS quantity, "
          + "si.price, "
          + "SUM(si.subtotal) AS subtotal "
          + "FROM sales_item si "
          + "INNER JOIN sales s ON si.sale_id = s.id "
          + "LEFT JOIN staff st ON s.id_staff = st.id_staff "
          + "WHERE DATE(s.sale_date) = ? "
          + "GROUP BY s.id_staff, st.firstname, st.lastname, "
          + "si.product_code, si.product_name, si.price "
          + "ORDER BY MAX(s.sale_date) DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setDate(1, Date.valueOf(LocalDate.now()));

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                SaleItem item = new SaleItem(
                        rs.getString("product_code"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getDouble("subtotal")
                );

                item.setStaffName(rs.getString("staff_name"));
                list.add(item);
            }
        }

    } catch (SQLException e) {
        System.out.println("Get Products Sold Error: " + e.getMessage());
        e.printStackTrace();
    }

    return list;
    }
    
    
     // SALES BY SELECTED DATE
    // ==========================================

    public double getSalesByDate(String date) {

        String sql =
                "SELECT COALESCE(SUM(total), 0) "
              + "FROM sales "
              + "WHERE DATE(sale_date) = "
              + "STR_TO_DATE(?, '%m/%d/%Y')";

        try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    date
            );

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Get Sales By Date Error: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return 0;
    }


    // ==========================================
    // ORDERS BY SELECTED DATE
    // ==========================================

    public int getOrdersByDate(String date) {

        String sql =
                "SELECT COUNT(*) "
              + "FROM sales "
              + "WHERE DATE(sale_date) = "
              + "STR_TO_DATE(?, '%m/%d/%Y')";

        try (
            Connection conn =
                    DatabaseConnection.getConnection();

            PreparedStatement stmt =
                    conn.prepareStatement(sql)
        ) {

            stmt.setString(
                    1,
                    date
            );

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Get Orders By Date Error: "
                    + e.getMessage()
            );

            e.printStackTrace();
        }

        return 0;
    }


    // ==========================================
    // PRODUCTS SOLD BY SELECTED DATE
    // ==========================================

    public List<SaleItem> getProductsByDate(String date) {

        List<SaleItem> list =
                new ArrayList<>();

        String sql =
            "SELECT CONCAT(st.firstname, ' ', st.lastname) AS staff_name, "
          + "si.product_code, "
          + "si.product_name, "
          + "SUM(si.quantity) AS quantity, "
          + "si.price, "
          + "SUM(si.subtotal) AS subtotal "
          + "FROM sales_item si "
          + "INNER JOIN sales s ON si.sale_id = s.id "
          + "LEFT JOIN staff st ON s.id_staff = st.id_staff "
          + "WHERE DATE(s.sale_date) = STR_TO_DATE(?, '%m/%d/%Y') "
          + "GROUP BY s.id_staff, st.firstname, st.lastname, "
          + "si.product_code, si.product_name, si.price "
          + "ORDER BY MAX(s.sale_date) DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, date);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                SaleItem item = new SaleItem(
                        rs.getString("product_code"),
                        rs.getString("product_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getDouble("subtotal")
                );

                item.setStaffName(rs.getString("staff_name"));
                list.add(item);
            }
        }

    } catch (SQLException e) {
        System.out.println("Get Products By Date Error: " + e.getMessage());
        e.printStackTrace();
    }

    return list;
}
}
    
    
    