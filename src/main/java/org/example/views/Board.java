package org.example.views;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.*;
import org.example.entities.tools.Gold;
import org.example.entities.tools.Key;
import org.example.service.BombServiceImpl;

import javax.swing.*;
import java.awt.*;

import static org.example.views.PositionedImage.*;

@Getter
@Setter
public class Board extends JPanel {
    private final int desiredTileCount = 15;  // Adjust this based on how many tiles you want to fit in the window
    private final int tileSize;
    private Area area;
    private Hero hero;
    private MonsterCollection monsterCollection;
    private Bomb bomb;
    private BombServiceImpl bombServiceImpl;
    private Gold gold;
    private Key key;


    public Board(int windowWidth, int windowHeight, Area area) {
        this.area = area;
        tileSize = Math.min(windowWidth, windowHeight) / desiredTileCount;
        setPreferredSize(new Dimension(tileSize * desiredTileCount, tileSize * desiredTileCount + 40));
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        drawTiles(graphics);
        drawHero(graphics);
        for (Monster monster : monsterCollection.getMonsters()) {
            if (monster.isAlive()) {
                drawMonster(graphics, monster);
            }
        }
        if(bomb.isVisible()) {
            drawBomb(graphics);
        }
        if(!hero.isHasGold()) {
            drawGold(graphics);
        }
        if(!hero.isHasKey()) {
            drawKey(graphics);
        }
        drawStatistics(graphics);
    }


    private void drawTiles(Graphics graphics) {
        if (area != null) {
            String image = "";
            int[][] tiles = area.getTiles();
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[0].length; j++) {
                    int object = tiles[i][j];
                    if (object == area.getFloorValue()) {
                        image = FLOOR_PATH;
                    } else if (object == area.getWallValue()) {
                        image = WALL_PATH;
                    } else if (object == 7) {
                        //image = HOLE_PATH;
                    } else if (object == 2) {
                        image = GRASS_PATH;
                    }
                    new PositionedImage(image, i * tileSize, j * tileSize).draw(graphics);
                }
            }
        }
    }

    private void drawHero(Graphics graphics) {
        String heroImagePath = "";
        if (hero.getHeroDirection() == HeroDirection.UP) {
            heroImagePath = HERO_UP_PATH;
        } else if (hero.getHeroDirection()   == HeroDirection.DOWN) {
            heroImagePath = HERO_DOWN_PATH;
        } else if (hero.getHeroDirection() == HeroDirection.LEFT) {
            heroImagePath = HERO_LEFT_PATH;
        } else if (hero.getHeroDirection() == HeroDirection.RIGHT) {
            heroImagePath = HERO_RIGHT_PATH;
        }
        System.out.println(hero.getHeroDirection() + " from Board.....");

        PositionedImage heroImage = new PositionedImage(heroImagePath, hero.getX() * tileSize, hero.getY() * tileSize);
        heroImage.draw(graphics);
    }

    public void drawBomb(Graphics graphics) {
        String bombPath = BOMB_PATH;
        bombServiceImpl.currentCoordinatesOfBomb();
        System.out.println("coordinate of: " + bomb.getPosX() + " : " + bomb.getPosY()); // ez jo
        PositionedImage bombImage = new PositionedImage(bombPath, bomb.getPosX() * tileSize, bomb.getPosY() * tileSize);
        bombImage.draw(graphics);
    }

    private void drawMonster(Graphics graphics, Monster monster) {
        if (monster.isAlive()) {
            String monsterPath = MONSTER_PATH;
            PositionedImage monsterImage = new PositionedImage(monsterPath, monster.getX() * tileSize, monster.getY() * tileSize);
            monsterImage.draw(graphics);
        }
    }

    private void drawGold(Graphics graphics) {
        String goldPath = GOLD_PATH;
        PositionedImage goldImage = new PositionedImage(goldPath, gold.getX() * tileSize, gold.getY() * tileSize);
        goldImage.draw(graphics);
    }

    private void drawKey(Graphics graphics) {
       String keyPath = KEY_PATH;
       PositionedImage keyImage = new PositionedImage(keyPath, key.getX() * tileSize, key.getY() * tileSize);
       keyImage.draw(graphics);
    }

    private void drawStatistics(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 720, 720, 40);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 16));
        graphics.drawString("HP: 250", 20, 740);
    }

}
