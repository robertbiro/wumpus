package org.example.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JMenuBar {

    private ActionListener playActionListener;

    public MainMenu() {
        initializeMenu();
    }

    private void initializeMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        fileMenu.add(exitMenuItem);

        JMenu playMenu = new JMenu("Play");
        JMenuItem playMenuItem = new JMenuItem("Start Game");

        playMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playActionListener != null) {
                    playActionListener.actionPerformed(e);
                }
            }
        });

        playMenu.add(playMenuItem);

        add(fileMenu);
        add(playMenu);
    }

    public void setPlayActionListener(ActionListener playActionListener) {
        this.playActionListener = playActionListener;
    }
}
