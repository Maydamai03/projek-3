/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import koneksi.connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class logincontroller {
    private Connection conn;

    public logincontroller() {
        conn = connector.connection(); // Initialize the connection
    }

    public boolean authenticate(String username, String password) {
        Connection con = connector.connection();

        try {
            String login = "SELECT * FROM login WHERE username = ? AND password = ?";
            PreparedStatement statement = con.prepareStatement(login);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean registerUser(String username, String password) {
        String register = "INSERT INTO login (username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(register)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

