package org.example.service;

import javax.swing.*;

public class LoginController extends JPasswordField{

    JPasswordField passwordField;

    public LoginController() {
        passwordField = new JPasswordField();
        this.add(passwordField);
        passwordField.setBounds(1200,1250,1200,100);

    }
}
