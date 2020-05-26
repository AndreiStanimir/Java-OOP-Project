package com.Repository;

import com.connection.DatabaseConnection;
import com.users.Administrator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository implements IRepository<Administrator> {
    private static AdminRepository instance;

    public AdminRepository() {
    }


    public static AdminRepository getInstance() {
        if (instance == null) {
            instance = new AdminRepository();
        }

        return instance;
    }

    public Administrator get(int id) {
        Administrator client = null;
        String query = "SELECT * FROM admins WHERE client_id = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return client;
                }

                System.out.println("User was found!");
                client.setId(result.getString("id"));
                client.setName(result.getString("username"));
                client.setPassword(result.getString("password"));

            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return client;
    }
    public Administrator get(String name) {
        Administrator admin = null;
        String query = "SELECT * FROM admins WHERE username = ?";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, name);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    System.out.println("Something went wrong when trying to find user: User was not found!");
                    return admin;
                }

                System.out.println("User was found!");
                admin=new Administrator(result.getString("id"),result.getString("username"),result.getString("password"),result.getInt("root_level"));


            }
        } catch (SQLException e) {
            System.out.println("Something went wrong when trying to find user: " + e.getMessage());
        }
        return admin;
    }

    @Override
    public Administrator parseElement(ResultSet resultSet) {
        return null;
    }

    @Override
    public Administrator insert(Administrator client) {
        var foundClient=get(client.getName());
        if(foundClient!=null){
            System.out.println("User already exists!");
            return null ;
        }
        String query = "INSERT INTO admins (id, username ,password, root_level) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(query)) {
            statement.setString(1, client.getId());
            statement.setString(2, client.getName());
            statement.setString(3, client.getPassword());
            statement.setInt(4, client.getRootLevel());

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
    public Administrator update(Administrator admin){
        String query = String.format("update clients set username = ?, password = ?, root_level = ? where id = ?");

        try (PreparedStatement statement = DatabaseConnection.getInstance().getConnection().
                prepareStatement(query)) {

            statement.setString(1, admin.getName());
            statement.setString(2, admin.getPassword());
            statement.setInt(3, admin.getRootLevel());
            statement.setString(4,admin.getId());

            int rowsModified=statement.executeUpdate();
            if (rowsModified > 0) {
                System.out.println("User successfully updated!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in update function in Administrator Repository");
            return null;
        }
        return admin;
    }


    @Override
    public boolean delete(Administrator client) {
        String query = String.format("DELETE FROM clients WHERE id=?");
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
