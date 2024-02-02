package org.example.views;

import org.example.service.MainController;

import javax.swing.*;

public class InfoPanel extends JPanel {

    private JLabel welcomeUserLabel;
    private JLabel numberOfMonstersLabel;
    private JLabel numberOfStepsLabel;
    private JLabel endMessageLabel;
    private JLabel goldMessageLabel;
    private JLabel keyMessageLabel;
    private MainController mainController;

    public InfoPanel(MainController mainController) {
        this.mainController = mainController;
        // Set up the InfoPanel layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        welcomeUserLabel = new JLabel();
        add(welcomeUserLabel);
        add(Box.createVerticalStrut(20));

        numberOfMonstersLabel = new JLabel("Number of Monsters: " + mainController.getRemainingMonsters());
        add(numberOfMonstersLabel);
        add(Box.createVerticalStrut(20));

        numberOfStepsLabel = new JLabel("The hero took " + mainController.getStepsOfHero() +  ((mainController.getStepsOfHero() > 1) ? " steps" : " step"));
        add(numberOfStepsLabel);
        //System.out.println(mainController.getStepsOfHero());
        add(Box.createVerticalStrut(20));

        goldMessageLabel = new JLabel();
        add(goldMessageLabel);
        add(Box.createVerticalStrut(20));

        keyMessageLabel = new JLabel();
        add(keyMessageLabel);
        add(Box.createVerticalStrut(20));

        endMessageLabel = new JLabel();
        add(endMessageLabel);
        add(Box.createVerticalStrut(20));  // Add some spacing
    }

    // Methods to update information on the InfoPanel
    public void updateUser(String welcomeUser) {
        welcomeUserLabel.setText("Hello " + welcomeUser +  " ! Good Luck! ");
    }
    public void updateMonsterInfo(int numberOfMonsters) {
        numberOfMonstersLabel.setText("Number of Monsters: " + numberOfMonsters);
        repaint();
    }

    public void endGameInfo(String message) {
        endMessageLabel.setText(message);
        repaint();
    }

    public  void stepsInfo(int numberOfSteps) {
        numberOfStepsLabel.setText("The hero took " + numberOfSteps + ((numberOfSteps > 1) ? " steps" : " step"));
        repaint();
    }

    public void goldInfo(String goldMessage) {
        goldMessageLabel.setText(goldMessage);
    }

    public void keyInfo(String keyMessage) {
        keyMessageLabel.setText(keyMessage);
    }
}
