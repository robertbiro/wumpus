package org.example.service;

import org.example.entities.HeroDirection;
import org.example.views.Board;


public interface HeroService {


    void moveUp();

    void moveDown();

    void moveLeft();

    void moveRight();

    void updateDirection(HeroDirection newHeroDirection);

    void lifeChanging();

    void getGold();

    void getKey();

    void triggerBomb(Board board);

}
