package sample.DAL;

import sample.BE.EventCoordinator;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventCoordinatorDAO {

    private DatabaseConnector databaseConnector;

    public EventCoordinatorDAO() throws IOException {
        databaseConnector = new DatabaseConnector();
    }

    public List<EventCoordinator> getAllEventCoordinators() throws Exception {
        ArrayList<EventCoordinator> allEventCoordinators = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "Select * From dbo.EventCoordinator";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");

                EventCoordinator eventCoordinator = new EventCoordinator(id,firstName,lastName,username,password,email);
                allEventCoordinators.add(eventCoordinator);
            }
            return allEventCoordinators;
        }
        catch(SQLException ex) {
            throw new Exception("Couldn't get Event Coordinators from the database", ex);
        }
    }

    public EventCoordinator createEventCoordinator(EventCoordinator eventCoordinator) throws Exception {
        String sql = "INSERT INTO dbo.EventCoordinator (firstName, lastName, username, password, email) VALUES (?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1,eventCoordinator.getFirstName());
            stmt.setString(2,eventCoordinator.getLastName());
            stmt.setString(3,eventCoordinator.getUsername());
            stmt.setString(4,eventCoordinator.getPassword());
            stmt.setString(5,eventCoordinator.getEmail());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }
            EventCoordinator createdEventCoordinator = new EventCoordinator(id,eventCoordinator.getFirstName(), eventCoordinator.getLastName(), eventCoordinator.getUsername(), eventCoordinator.getPassword(), eventCoordinator.getEmail());

            return createdEventCoordinator;
        }
        catch (SQLException ex) {
            throw new Exception("Couldn't create event coordinator", ex);
        }
    }

    public void updateEventCoordinator(EventCoordinator eventCoordinator) throws Exception {
        String sql = "UPDATE dbo.EventCoordinator SET firstName = ?, lastName = ?, username = ?, password = ?, email = ? WHERE id = ?;";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1,eventCoordinator.getFirstName());
            stmt.setString(2,eventCoordinator.getLastName());
            stmt.setString(3,eventCoordinator.getUsername());
            stmt.setString(4,eventCoordinator.getPassword());
            stmt.setString(5,eventCoordinator.getEmail());

            stmt.setInt(6, eventCoordinator.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new Exception("Couldn't update event coordinator", ex);
        }
    }

    public void deleteEventCoordinator(EventCoordinator eventCoordinator) throws Exception {
        String sql = "DELETE FROM dbo.EventCoordinator WHERE id = (?);";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setInt(1,eventCoordinator.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new Exception("Couldn't delete event", ex);
        }
    }
}
