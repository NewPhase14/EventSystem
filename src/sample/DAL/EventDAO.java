package sample.DAL;

import sample.BE.Event;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {
    private DatabaseConnector databaseConnector;

    public EventDAO() throws IOException {
        databaseConnector = new DatabaseConnector();
    }

    public List<Event> getAllEvents() throws Exception {
        ArrayList<Event> allEvents = new ArrayList<>();

        try (Connection conn = databaseConnector.getConnection();
             Statement stmt = conn.createStatement())
        {
            String sql = "Select * From dbo.Event";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int tickets = rs.getInt("tickets");
                String location = rs.getString("location");
                LocalDate startDate = rs.getDate("startDate").toLocalDate();
                LocalDate endDate = rs.getDate("endDate").toLocalDate();
                String startTime = String.valueOf(rs.getTime("startTime"));
                String endTime = String.valueOf(rs.getTime("endTime"));
                String description = rs.getString("description");
                int coordinator = rs.getInt("coordinator");


                Event event = new Event(id,name,tickets,location,startDate,endDate,startTime,endTime,description, coordinator);
                allEvents.add(event);
            }
            return allEvents;
        }
        catch(SQLException ex) {
            throw new Exception("Couldn't get events from the database", ex);
        }
    }

    public Event createEvent(Event event) throws Exception {
        String sql = "INSERT INTO dbo.Event (name,tickets,location,startDate,endDate,startTime,endTime,description,coordinator) VALUES (?,?,?,?,?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
        stmt.setString(1,event.getName());
        stmt.setInt(2,event.getTickets());
        stmt.setString(3,event.getLocation());
        stmt.setDate(4, Date.valueOf(event.getStartDate()));
        stmt.setDate(5, Date.valueOf(event.getEndDate()));
        stmt.setTime(6, Time.valueOf(event.getStartTime()));
        stmt.setTime(7, Time.valueOf(event.getEndTime()));
        stmt.setString(8,event.getDescription());
        stmt.setInt(9, event.getEventcoordinator());

        stmt.executeUpdate();

        ResultSet rs = stmt.getGeneratedKeys();
        int id = 0;

        if (rs.next()) {
            id = rs.getInt(1);
        }
        Event createdEvent = new Event(id, event.getName(), event.getTickets(), event.getLocation(), event.getStartDate(),event.getEndDate(),event.getStartTime(), event.getEndTime(), event.getDescription(), event.getEventcoordinator());

        return createdEvent;
        }
        catch (SQLException ex) {
            throw new Exception("Couldn't create event", ex);
        }
    }

    public void updateEvent(Event event) throws Exception {
        String sql = "UPDATE dbo.Event SET name = ?, tickets = ?, location = ?, startDate = ?, endDate = ?, startTime = CAST(? AS TIME), endTime = CAST(? AS TIME), description = ?\n" +
                "WHERE id = ?;";

        try (Connection conn = databaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setString(1,event.getName());
            stmt.setInt(2,event.getTickets());
            stmt.setString(3,event.getLocation());
            stmt.setDate(4, Date.valueOf(event.getStartDate()));
            stmt.setDate(5, Date.valueOf(event.getEndDate()));
            stmt.setTime(6, Time.valueOf(event.getStartTime()));
            stmt.setTime(7, Time.valueOf(event.getEndTime()));
            stmt.setString(8,event.getDescription());

            stmt.setInt(9, event.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new Exception("Couldn't update event", ex);
        }
    }

    public void deleteEvent(Event event) throws Exception {
        String sql = "DELETE FROM dbo.Event WHERE id = (?);";
        try (Connection conn = databaseConnector.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setInt(1,event.getId());

            stmt.executeUpdate();
        }
        catch (SQLException ex) {
            throw new Exception("Couldn't delete event", ex);
        }
    }

}
