package Controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Model.DatabaseManager;
import Model.LogInMenu;
import Model.Users;
import Model.Singleton.SingletonUser;

public class VerifyLogin {
    private LogInMenu logIn;

    public VerifyLogin(LogInMenu loginView) {
        this.logIn = loginView;

        this.logIn.addLoginListener(new LoginAction());
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = logIn.getEmail();
            String password = logIn.getPassword();

            try (Connection conn = DatabaseManager.connect()) {
                // Periksa apakah user ada di tabel Users

                String queryEmail = "SELECT * FROM Users WHERE email = ?";
                String queryPass = "SELECT * FROM Users WHERE email = ?";
                var preparedStatementEmail = conn.prepareStatement(queryEmail);
                boolean rowEmailAffected = preparedStatementEmail.execute(queryEmail);

                var preparedStatementPass = conn.prepareStatement(queryPass);
                boolean rowPassAffected = preparedStatementPass.execute(queryPass);

                if (!rowEmailAffected) {
                    JOptionPane.showMessageDialog(logIn, "Invalid email!");
                }else if (!rowPassAffected) {
                    JOptionPane.showMessageDialog(logIn, "Invalid password!");
                }else{
                    String queryUser = "SELECT * FROM Users WHERE email = ? AND password = ?";
                    var preparedStatement = conn.prepareStatement(queryUser);
                    preparedStatement.setString(1, email);
                    preparedStatement.setString(2, password);
    
                    var resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()) {
                        int userId = resultSet.getInt("user_id");
                        String name = resultSet.getString("name");
            
                        SingletonUser.getInstance().setLoggedInUser(new Users(userId, name, email, password));
                        JOptionPane.showMessageDialog(logIn, "Login successful!");
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(logIn, "Database error: " + ex.getMessage());
            }
        }
    }
}
