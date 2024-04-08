package sample.DAL;

import sample.BE.Admin;
import sample.BE.EventCoordinator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {

    private DatabaseConnector databaseConnector;

    public AdminDAO() throws IOException {
        databaseConnector = new DatabaseConnector();
    }
    public List<Admin> getAllAdmins() throws Exception {
        ArrayList<Admin> allAdmins = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "Select * From dbo.Admin";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");

                Admin admin = new Admin(id,firstName,lastName,username,password,email);
                allAdmins.add(admin);
            }
            return allAdmins;
        }
        catch(SQLException ex) {
            throw new Exception("Couldn't get Admins from the database", ex);
        }
    }
}
