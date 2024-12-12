package Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LogInMenu extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;

    // Constructor
    public LogInMenu() {
        setTitle("Dimasukin Keranjang");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel buat form
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);

        loginButton = new JButton("Login");

        add(formPanel, BorderLayout.CENTER);
        add(loginButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }
}
