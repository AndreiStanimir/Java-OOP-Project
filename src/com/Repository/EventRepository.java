package com.Repository;

import com.connection.DatabaseConnection;
import com.entities.Event;
import com.entities.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class EventRepository implements IRepository<Event> {
    private static EventRepository instance = null;

    public static EventRepository getInstance() {

        if (instance == null) {
            instance = new EventRepository();
        }
        return instance;
    }

    @Override
    public List<Event> getData() {
        List<Event> events = new ArrayList<>();
        String query = "SELECT * FROM events";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return events;
                }
                Event event = new Event();
                event.setLocation(result.getString("location"));
                event.setId(result.getString("event_id"));
                event.setDate(result.getTimestamp("date").toLocalDateTime());
                event.setPrice(result.getFloat("price"));
                event.setBaseTicketPrice(result.getFloat("ticket_price"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return events;
    }

    @Override
    public Event get(String index) {
        return null;
    }

    @Override
    public Event parseElement(ResultSet resultSet) {
        return null;
    }

    @Override
    public Event insert(Event event) {
        String query = "INSERT INTO events (event_id ,price,seats,date,ticket_price,location) VALUES (?,?, ?, ?,?,?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, event.getId());
            statement.setFloat(2, event.getPrice());
            statement.setInt(3, event.getSeats());
            statement.setTimestamp(4, Timestamp.valueOf(event.getDate()));
            statement.setFloat(5, event.getBaseTicketPrice());
            statement.setString(6, event.getLocation());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return null;
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        String query = "UPDATE events Set price=?,seats=?,date=?,ticket_price=?,location=? WHERE event_id=?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setFloat(1, event.getPrice());
            statement.setInt(2, event.getSeats());
            statement.setTimestamp(3, Timestamp.valueOf(event.getDate()));
            statement.setFloat(4, event.getBaseTicketPrice());
            statement.setString(5, event.getLocation());
            statement.setString(6, event.getId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return null;
        }
        return event;
    }

    @Override
    public boolean delete(Event event) {
        String query = String.format("DELETE FROM events WHERE event_id=?");
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, event.getId());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("User was deleted successfully!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to delete user: " + e.getMessage());
            return false;
        }

        System.out.println("Something went wrong when trying to delete user: User was not found!");
        return false;

    }

}
