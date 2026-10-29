
package dao;
import model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    
    public boolean registerAdmin(User user) {

        String sql = "INSERT INTO admin "
                   + "(firstname,lastname, username, password) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getFirstname());
            stmt.setString(2, user.getLastname());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getPassword());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Admin Registration Error: "
                    + e.getMessage());

            return false;
        }
    }

    
    public boolean registerStaff(User user, int adminId) {

        String sql = "INSERT INTO staff "
               + "(id_admin, firstname, lastname, username, password, contactnumber) "
               + "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, adminId);
        stmt.setString(2, user.getFirstname());
        stmt.setString(3, user.getLastname());
        stmt.setString(4, user.getUsername());
        stmt.setString(5, user.getPassword());
        stmt.setString(6, user.getContactNumber());

        stmt.executeUpdate();

        return true;

    } catch (SQLException e) {

        System.out.println("Staff Registration Error: "
                + e.getMessage());

        return false;
    }
}
    
    
    public String login(String username, String password) {
    
        
        String adminSQL =
                "SELECT * FROM admin "
              + "WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(adminSQL)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return "admin";
            }

        } catch (SQLException e) {

            System.out.println("Admin Login Error: "
                    + e.getMessage());
        }

        
         String staffSQL =
                "SELECT * FROM staff "
              + "WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(staffSQL)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return "staff";
            }

        } catch (SQLException e) {

            System.out.println("Staff Login Error: "
                    + e.getMessage());
        }


        // --------------------------
        // INVALID LOGIN
        // --------------------------
        return null;
    }
     public List<User> getAllStaff() {

        List<User> staffList = new ArrayList<>();

        String sql =
                "SELECT id_staff, firstname, lastname, username, password, contactnumber "
              + "FROM staff";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                User user = new User(
                        rs.getInt("id_staff"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("contactnumber")
                );

                staffList.add(user);
            }

        } catch (SQLException e) {

            System.out.println(
                    "Get Staff Error: "
                    + e.getMessage()
            );
        }

        return staffList;
    }


    // =========================================================
    // DELETE STAFF
    // =========================================================
    public boolean deleteStaff(int id) {

        String sql =
                "DELETE FROM staff WHERE id_staff = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Delete Staff Error: "
                    + e.getMessage()
            );

            return false;
        }
    }
    
    public int getAdminId(String username, String password) {

    String sql =
            "SELECT id_admin FROM admin "
          + "WHERE username = ? AND password = ?";

    try (
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)
    ) {

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id_admin");
        }

    } catch (Exception e) {
        System.out.println(
                "Get Admin ID Error: " + e.getMessage()
        );
    }

    return -1;
}
    
    public int getStaffId(String username, String password) {

    String sql = "SELECT id_staff FROM staff "
          + "WHERE username = ? AND password = ?";

    try (
        Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)
    ) {

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("id_staff");
        }

    } catch (Exception e) {

        System.out.println(
                "Get Staff ID Error: " + e.getMessage()
        );
    }

    return -1;
    
    } 
}
