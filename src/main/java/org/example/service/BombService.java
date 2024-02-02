package org.example.service;

import org.example.entities.Area;
import org.example.views.Board;
import org.w3c.dom.events.MouseEvent;

import java.awt.event.MouseListener;

public interface BombService extends MouseListener {

    void mouseClicked(MouseEvent e);
    void currentCoordinatesOfBomb();
    boolean validDetonation(Area area, int bombXCoordinate, int bombYCoordinate);

    boolean isValidCoordinate(Area area, int bombXCoordinate, int bombYCoordinate);
    void triggerBomb(Board board);
}
