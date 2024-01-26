package org.example.views;

import org.example.repository.UserRepository;
import org.example.service.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class GameFrame extends JFrame {

    private Board board;
    private JPanel cardPanel;
    private InfoPanel infoPanel;
    private UserPanel userPanel;
    private MainController mainController;
    private Connection connection;

    public GameFrame(UserRepository userRepository) {
        //this.connection = connection; -> to the Hibernate doesn't need
            initializeUserPanel(userRepository);
            initializeGamePanel();
            setUpInitialLayout();
            }

    private void initializeUserPanel(UserRepository userRepository) {
            cardPanel = new JPanel(new CardLayout());
            userPanel = new UserPanel(userRepository);
            cardPanel.add(userPanel, "UserPanel");
            }

    private void initializeGamePanel() {
            JPanel gamePanel = new JPanel(new BorderLayout());

            // Board:
            board = new Board(800, 800);
            mainController = new MainController(board);
            gamePanel.add(board, BorderLayout.CENTER);

            // InfoPanel:
            infoPanel = new InfoPanel(mainController);
            JPanel infoPanelContainer = new JPanel(new BorderLayout());
            infoPanelContainer.add(infoPanel, BorderLayout.CENTER);
            gamePanel.add(infoPanelContainer, BorderLayout.EAST);

            cardPanel.add(gamePanel, "GamePanel");
            }

    private void setUpInitialLayout() {
            // Initially show the UserPanel
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "UserPanel");

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Wumpus game");
            setSize(500, 500); // Set initial size to UserPanel size
            getContentPane().add(cardPanel);
            setVisible(true);
            setLocationRelativeTo(null);  // Center the frame

            // Add key listener to the board
            board.addKeyListener(mainController);

            // Set focusable to true to receive key events
            board.setFocusable(true);

            Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    updateInfoPanel();
                        }
                    });
                    timer.start();
            }

    // Method to switch to the GamePanel
    public void showGamePanel() {
            // Set the size of the frame to the desired size for the GamePanel
            setSize(900, 900);

            // Switch to the GamePanel
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.show(cardPanel, "GamePanel");
            revalidate();
            repaint();
            //after switching to the GamePanel, request focus explicitly by calling requestFocusInWindow !!!!
            board.requestFocusInWindow();
            }

    // Method to update InfoPanel with the latest information
    private void updateInfoPanel() {
            String welcomeUser = userPanel.getWelcomeUser();
            infoPanel.updateUser(welcomeUser);

            int numberOfMonsters = mainController.getRemainingMonsters();
            infoPanel.updateMonsterInfo(numberOfMonsters);

            String endGameMessage = mainController.getEndMessage();
            infoPanel.endGameInfo(endGameMessage);

            int numberOfSteps = mainController.getStepsOfHero();
            infoPanel.stepsInfo(numberOfSteps);
            }

}
