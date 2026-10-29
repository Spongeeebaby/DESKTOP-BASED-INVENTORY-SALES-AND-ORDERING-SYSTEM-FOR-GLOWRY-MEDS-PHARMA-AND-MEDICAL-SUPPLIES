
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;


public class archiveDAO {
    
    // LOAD MEDICINE ARCHIVE
    // =========================
    public void loadMedicineArchive(DefaultTableModel model) {

        String sql =
                "SELECT id, code, medicine, category, price, "
                + "stock, unit, exp_date, archived_date "
                + "FROM medicine_archive "
                + "ORDER BY archived_date DESC";

        model.setRowCount(0);

        try (
            Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("medicine"),
                    rs.getString("category"),
                    rs.getDouble("price"),
                    rs.getInt("stock"),
                    rs.getString("unit"),
                    rs.getDate("exp_date"),
                    rs.getTimestamp("archived_date")
                });
            }

        } catch (SQLException e) {
            System.out.println("Load Medicine Archive Error: "
                    + e.getMessage());
        }
    }
    
    // LOAD STAFF ARCHIVE
    // =========================
    public void loadStaffArchive(DefaultTableModel model) {

        String sql =
            "SELECT id_staff_archive, fullname, username, archived_date "
            + "FROM staff_archive "
            + "ORDER BY archived_date DESC";

    model.setRowCount(0);

    try (
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()
    ) {

        while (rs.next()) {

            model.addRow(new Object[]{
                rs.getInt("id_staff_archive"),
                rs.getString("fullname"),
                rs.getString("username"),
                rs.getTimestamp("archived_date")
            });
        }

    } catch (SQLException e) {

        System.out.println(
                "Load Staff Archive Error: "
                + e.getMessage()
        );
    }
    }

    // =========================
    // RESTORE MEDICINE
    // =========================
    public boolean restoreMedicine(int id) {

        String selectSQL =
                "SELECT * FROM medicine_archive WHERE id = ?";

        String insertSQL =
                "INSERT INTO products "
                + "(id_admin,id, code, medicine, category, price, stock, unit, exp_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String deleteSQL =
                "DELETE FROM medicine_archive WHERE id = ?";

        Connection conn = null;

        try {

            conn = DatabaseConnection.getConnection();

            // Start transaction
            conn.setAutoCommit(false);

            int adminId;
            int archiveId;
            String code;
            String medicine;
            String category;
            double price;
            int stock;
            String unit;
            java.sql.Date expDate;

            // Get archived medicine
            try (PreparedStatement stmt =
                    conn.prepareStatement(selectSQL)) {

                stmt.setInt(1, id);

                ResultSet rs = stmt.executeQuery();

                if (!rs.next()) {
                    conn.rollback();
                    return false;
                }
                
                adminId = rs.getInt("id_admin");
                archiveId = rs.getInt("id");
                code = rs.getString("code");
                medicine = rs.getString("medicine");
                category = rs.getString("category");
                price = rs.getDouble("price");
                stock = rs.getInt("stock");
                unit = rs.getString("unit");
                expDate = rs.getDate("exp_date");
            }

            // Put medicine back into products
            try (PreparedStatement stmt =
                    conn.prepareStatement(insertSQL)) {
                
                stmt.setInt(1, adminId);
                stmt.setInt(2, archiveId);
                stmt.setString(3, code);
                stmt.setString(4, medicine);
                stmt.setString(5, category);
                stmt.setDouble(6, price);
                stmt.setInt(7, stock);
                stmt.setString(8, unit);
                stmt.setDate(9, expDate);

                stmt.executeUpdate();
            }

            // Remove from archive
            try (PreparedStatement stmt =
                    conn.prepareStatement(deleteSQL)) {

                stmt.setInt(1, id);
                stmt.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {

            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                System.out.println("Rollback Error: "
                        + ex.getMessage());
            }

            System.out.println("Restore Medicine Error: "
                    + e.getMessage());

            return false;

        } finally {

            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Connection Close Error: "
                        + e.getMessage());
            }
        }
    }

    // =========================
    // RESTORE STAFF
    // =========================
    public boolean restoreStaff(int id) {

        String selectSQL =
            "SELECT id_staff_archive, id_admin, fullname, username, "
            + "password, contactnumber "
            + "FROM staff_archive "
            + "WHERE id_staff_archive = ?";

    String insertSQL =
            "INSERT INTO staff "
            + "(id_admin, id_staff, firstname, lastname, username, password, contactnumber) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    String deleteSQL =
            "DELETE FROM staff_archive "
            + "WHERE id_staff_archive = ?";

    Connection conn = null;

    try {

        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);

        int adminId;
        int staffId;
        String fullname;
        String username;
        String password;
        String contactNumber;

        // Get archived staff
        try (PreparedStatement stmt =
                conn.prepareStatement(selectSQL)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                conn.rollback();
                return false;
            }

            adminId = rs.getInt("id_admin");
            staffId = rs.getInt("id_staff_archive");
            fullname = rs.getString("fullname");
            username = rs.getString("username");
            password = rs.getString("password");
            contactNumber = rs.getString("contactnumber");
        }

        // Separate fullname into firstname and lastname
        String[] nameParts =
                fullname.trim().split("\\s+", 2);

        String firstname = nameParts[0];
        String lastname = "";

        if (nameParts.length > 1) {
            lastname = nameParts[1];
        }

        // Put staff back into staff table
        try (PreparedStatement stmt =
                conn.prepareStatement(insertSQL)) {

            stmt.setInt(1, adminId);
            stmt.setInt(2, staffId);
            stmt.setString(3, firstname);
            stmt.setString(4, lastname);
            stmt.setString(5, username);
            stmt.setString(6, password);
            stmt.setString(7, contactNumber);

            stmt.executeUpdate();
        }

        // Remove staff from archive
        try (PreparedStatement stmt =
                conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted == 0) {
                conn.rollback();
                return false;
            }
        }

        conn.commit();

        return true;

    } catch (SQLException e) {

        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            System.out.println(
                    "Rollback Error: " + ex.getMessage()
            );
        }

        System.out.println(
                "Restore Staff Error: " + e.getMessage()
        );

        return false;

    } finally {

        try {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(
                    "Connection Close Error: " + e.getMessage()
            );
        }
    }
}
        
        
    // ARCHIVE MEDICINE
// =========================
public boolean archiveMedicine(int id) {

    String selectSQL =
            "SELECT id_admin, id, code, medicine, category, price, "
            + "stock, unit, exp_date "
            + "FROM products WHERE id = ?";

    String insertSQL =
            "INSERT INTO medicine_archive "
            + "(id_admin, id, code, medicine, category, price, stock, unit, exp_date) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    String deleteSQL =
            "DELETE FROM products WHERE id = ?";

    Connection conn = null;

    try {

        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);

        // Get medicine from products
        try (PreparedStatement stmt =
                conn.prepareStatement(selectSQL)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                conn.rollback();
                return false;
            }

            // Copy medicine to archive
            try (PreparedStatement insert =
                    conn.prepareStatement(insertSQL)) {

                insert.setInt(1, rs.getInt("id_admin"));
                insert.setInt(2, rs.getInt("id"));
                insert.setString(3, rs.getString("code"));
                insert.setString(4, rs.getString("medicine"));
                insert.setString(5, rs.getString("category"));
                insert.setDouble(6, rs.getDouble("price"));
                insert.setInt(7, rs.getInt("stock"));
                insert.setString(8, rs.getString("unit"));
                insert.setDate(9, rs.getDate("exp_date"));

                insert.executeUpdate();
            }
        }

        // Delete medicine from products
        try (PreparedStatement stmt =
                conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        conn.commit();

        return true;

    } catch (SQLException e) {

        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            System.out.println(
                    "Rollback Error: " + ex.getMessage()
            );
        }

        System.out.println(
                "Archive Medicine Error: " + e.getMessage()
        );

        return false;

    } finally {

        try {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(
                    "Connection Close Error: " + e.getMessage()
            );
        }
    }
}
    

    public void archiveExpiredMedicines() {

    String sql =
            "SELECT id FROM products "
          + "WHERE exp_date <= CURDATE()";

    try (
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()
    ) {

        while (rs.next()) {

            int id = rs.getInt("id");

            archiveMedicine(id);
        }

    } catch (Exception e) {

        System.out.println(
                "Expired Medicine Error: "
                + e.getMessage()
        );
    }
}
public boolean archiveStaff(int id) {

   String selectSQL = "SELECT id_admin, id_staff, firstname, lastname, username, password, contactnumber "
            + "FROM staff WHERE id_staff = ?";

    String insertSQL =
            "INSERT INTO staff_archive "
            + "(id_staff_archive, id_admin, fullname, username, password, contactnumber) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    String deleteSQL =
            "DELETE FROM staff WHERE id_staff = ?";

    Connection conn = null;

    try {

        conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false);

        // Get staff information
        try (PreparedStatement stmt =
                conn.prepareStatement(selectSQL)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {
                conn.rollback();
                return false;
            }

            String fullname =
                    rs.getString("firstname") + " "
                    + rs.getString("lastname");

            // Insert into archive
            try (PreparedStatement insert =
                    conn.prepareStatement(insertSQL)) {

                insert.setInt(1, rs.getInt("id_staff"));
                insert.setInt(2, rs.getInt("id_admin"));
                insert.setString(3, fullname);
                insert.setString(4, rs.getString("username"));
                insert.setString(5, rs.getString("password"));
                insert.setString(6, rs.getString("contactnumber"));

                insert.executeUpdate();
            }
        }

        // Delete from active staff table
        try (PreparedStatement stmt =
                conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }

        conn.commit();

        return true;

    } catch (SQLException e) {

        try {
            if (conn != null) {
                conn.rollback();
            }
        } catch (SQLException ex) {
            System.out.println(
                    "Rollback Error: " + ex.getMessage()
            );
        }

        System.out.println(
                "Archive Staff Error: " + e.getMessage()
        );

        return false;

    } finally {

        try {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(
                    "Connection Close Error: " + e.getMessage()
            );
        }
    }
}
}
    
    
    
    
