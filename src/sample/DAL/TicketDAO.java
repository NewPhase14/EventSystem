package sample.DAL;


import com.microsoft.sqlserver.jdbc.SQLServerException;
import sample.BE.Event;
import sample.BE.EventCoordinator;
import sample.BE.Ticket;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private DatabaseConnector databaseConnector;

    public TicketDAO() throws IOException {
        databaseConnector = new DatabaseConnector();
    }

    public Ticket createTicket(Ticket ticket) throws Exception {
        String sql = "INSERT INTO dbo.Ticket (customerFirstName,customerLastName,customerEmail,event,barcode,eventCoordinator) VALUES (?,?,?,?,?,?);";

        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1,ticket.getCustomerFirstName());
            stmt.setString(2, ticket.getCustomerLastName());
            stmt.setString(3,ticket.getCustomerEmail());
            stmt.setInt(4, ticket.getEventId());
            stmt.setString(5, ticket.getBarCode());
            stmt.setInt(6, ticket.getEventCoordinatorId());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;

            if (rs.next()) {
                id = rs.getInt(1);
            }
            Ticket createdTicket = new Ticket(id, ticket.getCustomerFirstName(), ticket.getCustomerLastName(), ticket.getCustomerEmail(), ticket.getEventId(), ticket.getBarCode(), ticket.getEventCoordinatorId());

            return createdTicket;
        }
        catch (SQLException ex) {
            throw new Exception("Couldn't create event", ex);
        }
    }

    public int getSoldTickets(EventCoordinator eventCoordinator) throws Exception {
        String sql = "Select COUNT(eventCoordinator) AS soldTickets from dbo.Ticket where eventCoordinator = (?);";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql))
        {
            stmt.setInt(1, eventCoordinator.getId());
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
               return rs.getInt("soldTickets");
            }
            return 0;
        }
        catch(SQLException ex) {
            throw new Exception("Couldn't get sold tickets from the database", ex);
        }
    }

        public List<File> getFiles() {
        File[] directory = new File("resources/data/tickets").listFiles();
        List<File> fileList = new ArrayList<>();
        for (File file : directory) {
            fileList.add(new File(String.valueOf(file)));
        }
        return fileList;
    }


}
