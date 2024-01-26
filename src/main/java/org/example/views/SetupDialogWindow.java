package org.example.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetupDialogWindow extends JDialog {

    private JTextField boardSizeField;
    private JTextField goldCoordinateField;
    private JTextField numberOfEnemiesField;

    public SetupDialogWindow(Frame parent) {
        super(parent, "Game Setup", true);
        setLayout(new GridLayout(4, 2));

        boardSizeField = new JTextField();
        goldCoordinateField = new JTextField();
        numberOfEnemiesField = new JTextField();

        add(new JLabel("Board Size:"));
        add(boardSizeField);
        add(new JLabel("Gold Coordinate:"));
        add(goldCoordinateField);
        add(new JLabel("Number of Enemies:"));
        add(numberOfEnemiesField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the dialog
                dispose();
            }
        });
        add(submitButton);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public int getBoardSizeField() {
        return Integer.parseInt(boardSizeField.getText());
    }

    public String getGoldCoordinateField() {
        return goldCoordinateField.getText();
    }

    public int getNumberOfEnemiesField() {
        return Integer.parseInt(numberOfEnemiesField.getText());
    }

}
