package com.Repository;

import com.connection.DatabaseConnection;
import com.users.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepository implements IRepository<Client> {
    private static ClientRepository instance;

    public ClientRepository() {
    }


    public static ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }

        return instance;
    }

    public Client get(int id) {
        Client client = null;
        String query = "SELECT * FROM clients WHERE client_id = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return client;
                }

                System.out.println("User was found!");
                client.setId(result.getString("client_id"));
                client.setName(result.getString("username"));
                client.setPassword(result.getString("password"));
                client.setAge(result.getInt("age"));
                client.setBalance(result.getFloat("balance"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return client;
    }

    public Client get(String name) {
        Client client = null;
        String query = "SELECT * FROM clients WHERE username = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, name);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return client;
                }

                System.out.println("User was found!");
                client = new Client(result.getString("client_id"), result.getString("username"), result.getString("password"), result.getFloat("balance"), result.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return client;
    }

    @Override
    public Client parseElement(ResultSet resultSet) {
        return null;
    }

    @Override
    public Client insert(Client client) {
        var foundClient = get(client.getName());
        if (foundClient != null) {
            System.out.println("User already exists!");
            return null;
        }
        String query = "INSERT INTO clients (client_id, username ,password, balance,age) VALUES (?,?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, client.getId());
            statement.setString(2, client.getName());
            statement.setString(3, client.getPassword());
            statement.setFloat(4, client.getBalance());
            statement.setInt(5, client.getAge());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to insert a new user: " + e.getMessage());
            return null;
        }
        return client;
    }


    @Override
    public Client update(Client client) {
        String query = String.format("update clients set username = ?, password = ?, age = ?, balance = ? where client_id = ?");

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().
                prepareStatement(query)) {

            statement.setString(1, client.getName());
            statement.setString(2, client.getPassword());
            statement.setInt(3, client.getAge());
            statement.setFloat(4, client.getBalance());
            statement.setString(5, String.valueOf(client.getId()));

            int rowsModified = statement.executeUpdate();
            if (rowsModified > 0) {
                System.out.println("User successfully updated!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in update function in Client Repository");
            return null;
        }
        return client;
    }


    @Override
    public boolean delete(Client client) {
        String query = String.format("DELETE FROM clients WHERE client_id=?");
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, client.getId());

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
