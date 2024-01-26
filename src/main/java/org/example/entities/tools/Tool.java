package org.example.entities.tools;

import lombok.Getter;
import org.example.entities.Area;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
public abstract class Tool implements Positionable {
    protected int x;
    protected int y;
    protected Timer visibilityTimer;
    protected JLabel toolLabel; //has a setVisible method to control the visibility of the component on the screen.



    protected Tool(Area area) {
        this.x = getRandomStartPosition(area)[0];
        this.y = getRandomStartPosition(area)[1];
        this.toolLabel = new JLabel("Key");
        this.visibilityTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Hide after 5 sec
                toolLabel.setVisible(false);
                visibilityTimer.stop();
                //Break till 3 sec
                Timer breakTimer = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        x = getRandomStartPosition(area)[0];
                        y = getRandomStartPosition(area)[1];
                        toolLabel.setVisible(true);
                        visibilityTimer.start();
                    }
                });
                breakTimer.setRepeats(false);
                breakTimer.start();
            }
        });
        visibilityTimer.start();
    }
}
