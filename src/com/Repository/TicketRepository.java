package com.Repository;

import com.connection.DatabaseConnection;
import com.entities.Ticket;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class TicketRepository implements IRepository<Ticket> {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static TicketRepository instance = null;

    public static TicketRepository getTicketRepositoryInstance() {

        if (instance == null) {
            instance = new TicketRepository();
        }
        return instance;
    }

    @Override
    public Ticket get(String id) {
        Ticket ticket = null;
        String query = "SELECT * FROM tickets WHERE ticket_id = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return ticket;
                }

                System.out.println("User was found!");
                ticket.setId(id);
                ticket.setClient_id(result.getString("client_id"));
                ticket.setEvent_id(result.getString("event_id"));
                ticket.setClient_id(result.getString("client_id"));
                ticket.setPrice(result.getFloat("price"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return ticket;
    }

    @Override
    public Ticket parseElement(ResultSet resultSet) {
        return null;
    }

    @Override
    public Ticket insert(Ticket ticket) {
        String query = "INSERT INTO clients (ticket_id ,client_id,price,event_id) VALUES (?,?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, ticket.getId());
            statement.setString(2, ticket.getClient_id());
            statement.setFloat(3, ticket.getPrice());
            statement.setString(4, ticket.getEvent_id());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return null;
        }
        return ticket;
    }



    @Override
    public Ticket update(Ticket ticket) {

        String query = "UPDATE tickets  set client_id = ?,price=?,event_id=? where ticket_id=? ";


        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, ticket.getClient_id());
            statement.setFloat(2, ticket.getPrice());
            statement.setString(3, ticket.getEvent_id());
            statement.setString(4, ticket.getId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return null;
        }
        return ticket;
    }

    @Override
    public boolean delete(Ticket ticket) {
        String query = String.format("DELETE FROM tickets WHERE ticket_id=?");
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, ticket.getId());

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
