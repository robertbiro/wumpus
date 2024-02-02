package org.example.service;

import java.awt.event.KeyListener;


public interface HeroService extends KeyListener {

    void moveUp();

    void moveDown();

    void moveLeft();

    void moveRight();

    void lifeChanging();

    void getGold();

    void getKey();

    int getHeroSteps();


}
