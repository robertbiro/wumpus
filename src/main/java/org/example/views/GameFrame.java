package org.example.views;

import lombok.Getter;
import lombok.Setter;
import org.example.repository.UserRepository;
import org.example.service.HeroService;
import org.example.service.MainController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

@Getter
@Setter
public class GameFrame extends JFrame {

    private Board board;
    //private DynamicBoard dynamicBoard;
    private JPanel cardPanel;
    private InfoPanel infoPanel;
    private UserPanel userPanel;
    private MainController mainController;
    private Connection connection;
    private HeroService heroService;

    public GameFrame(UserRepository userRepository, MainController mainController,
                     Board board) {
        //this.connection = connection; -> to the Hibernate doesn't need
        this.mainController = mainController;
        this.board = board;
        //this.dynamicBoard = dynamicBoard;
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
        //System.out.println(staticBoard.getArea().getSize());
        gamePanel.add(board, BorderLayout.CENTER);
        //gamePanel.add(dynamicBoard, BorderLayout.CENTER);

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
        //board.addKeyListener();

        // Set focusable to true to receive key events
        board.setFocusable(true);
        //dynamicBoard.setFocusable(true);

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
        //dynamicBoard.requestFocusInWindow();
    }

    // Methods to update InfoPanel with the latest information
    private void updateInfoPanel() {
        System.out.println("sec");
        String welcomeUser = userPanel.getWelcomeUser();
        infoPanel.updateUser(welcomeUser);

        int numberOfMonsters = mainController.getRemainingMonsters();
        infoPanel.updateMonsterInfo(numberOfMonsters);

        String endGameMessage = mainController.getEndMessage();
        infoPanel.endGameInfo(endGameMessage);

        int numberOfSteps = mainController.updateInfoPanelSteps();
        infoPanel.stepsInfo(numberOfSteps);

        String goldMessage = mainController.updateInfoPanelGold();
        infoPanel.goldInfo(goldMessage);

        String keyMessage = mainController.updateInfoPanelKey();
        infoPanel.keyInfo(keyMessage);
    }
}