package org.example.views;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.User;
import org.example.repository.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class UserPanel extends JPanel {
    //based on this: https://www.youtube.com/watch?v=NJjXTzEwU1s

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneNumberField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JButton loginSubmitButton;
    private User newUser;
    private String welcomeUser;
    private UserRepository userRepository;
    private static final Logger logger = Logger.getLogger(UserPanel.class.getName());

    public UserPanel(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.welcomeUser = "";

        // Set insets to create space around components
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Registration label
        JLabel registrationLabel = new JLabel("Registration");
        registrationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across two columns
        add(registrationLabel, gbc);

        // First Name
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Reset gridwidth
        addLabel("First Name:", gbc, 0, 1);
        firstNameField = createTextField();
        addComponent(firstNameField, gbc, 1, 1);

        // Last Name
        addLabel("Last Name:", gbc, 2, 1);
        lastNameField = createTextField();
        addComponent(lastNameField, gbc, 3, 1);

        // Email
        addLabel("Email:", gbc, 0, 2);
        emailField = createTextField();
        addComponent(emailField, gbc, 1, 2);

        // Phone Number
        addLabel("Phone Number:", gbc, 2, 2);
        phoneNumberField = createTextField();
        addComponent(phoneNumberField, gbc, 3, 2);

        // User Name
        addLabel("User Name:", gbc, 0, 3);
        usernameField = createTextField();
        addComponent(usernameField, gbc, 1, 3);

        // Password
        addLabel("Password:", gbc, 2, 3);
        passwordField = createPasswordField();
        addComponent(passwordField, gbc, 3, 3);

        // Submit button
        registerButton = createSubmitButton("Submit");
        addComponent(registerButton, gbc, 0, 4);
        gbc.gridwidth = 2; // Span across two columns
        gbc.gridx = 0;
        gbc.gridy = 4;

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newUser = new User();
                newUser.setFirstName(firstNameField.getText());
                newUser.setLastName(lastNameField.getText());
                newUser.setEmail(emailField.getText());
                newUser.setPhoneNumber(phoneNumberField.getText());
                newUser.setUserName(usernameField.getText());
                // getPassword: get a char array that may be modified,
                // so the password will really not stay in memory!!!
                newUser.setPassword(String.valueOf(passwordField.getPassword()));
                if(!userRepository.isUserNameExists(usernameField.getText())) {
                    userRepository.saveUser(newUser);
                    handleLoginAttempt();
                    welcomeUser = usernameField.getText();
                } else {
                    logger.log(Level.WARNING, "Username " + usernameField.getText() + " already exist.");
                    JOptionPane.showMessageDialog(UserPanel.this, "Username already exists. Please choose a different username.");
                }

            }
        });
        //---------------------------------------------
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Span across two columns
        add(loginLabel, gbc);

        // Login User Name
        gbc.gridwidth = 1; // Reset gridwidth
        addLabel("User Name:", gbc, 0, 6);
        loginUsernameField = createTextField(); // Use a different instance
        addComponent(loginUsernameField, gbc, 1, 6);

        // Login Password
        addLabel("Password:", gbc, 2, 6);
        loginPasswordField = createPasswordField(); // Use a different instance
        addComponent(loginPasswordField, gbc, 3, 6);

        // Login Submit button
        loginSubmitButton = createSubmitButton("Login"); // Use a different instance
        addComponent(loginSubmitButton, gbc, 0, 7);
        gbc.gridwidth = 2; // Span across two columns
        gbc.gridx = 0;
        gbc.gridy = 7;

        loginSubmitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(userRepository.isPasswordCorrect(loginUsernameField.getText(),
                        String.valueOf(loginPasswordField.getPassword()))) {
                    handleLoginAttempt();
                    welcomeUser = loginUsernameField.getText();
                } else {
                    logger.log(Level.WARNING, "Invalid username or password!");
                    JOptionPane.showMessageDialog(UserPanel.this, "Invalid username or password!");
                }
            }
        });
    }

    private void addLabel(String labelText, GridBagConstraints gbc, int gridx, int gridy) {
        JLabel label = new JLabel(labelText);
        label.setHorizontalAlignment(JLabel.RIGHT);
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        add(label, gbc);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(140, 20));
        return textField;
    }

    private JPasswordField createPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(140, 20));
        return passwordField;
    }

    private JButton createSubmitButton(String buttonText) {
        JButton button = new JButton(buttonText);
        return button;
    }

    private void addComponent(Component component, GridBagConstraints gbc, int gridx, int gridy) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        add(component, gbc);
    }

    public void handleLoginAttempt() {
        ((GameFrame) SwingUtilities.getWindowAncestor(this)).showGamePanel();
    }
}