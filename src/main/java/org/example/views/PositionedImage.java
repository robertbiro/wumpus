package org.example.views;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PositionedImage {

    private BufferedImage image;
    public static final String WALL_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\wall.png";
    public static final String FLOOR_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\floor.png";
    public static final String HERO_UP_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\hero-up.png";
    public static final String HERO_DOWN_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\hero.png";
    public static final String HERO_LEFT_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\hero-left.png";
    public static final String HERO_RIGHT_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\hero-right.png";
    public static final String HOLE_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\sarlacc.png";
    public static final String GRASS_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\grass.png";
    public static final String MONSTER_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\wumpus.gif";
    public static final String BOMB_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\bomb.png";
    public static final String GOLD_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\gold_pile.png";
    public static final String KEY_PATH = "C:\\Users\\user\\Documents\\after_greenfox\\wumpus\\img\\key.png";
    private int posX, posY;

    public PositionedImage(String filename, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        try {
            BufferedImage originalImage = ImageIO.read(new File(filename));
            // Resize the image to the target size (60x60)
            this.image = resizeImage(originalImage, 60, 60);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics graphics) {
        if (image != null) {
            graphics.drawImage(image, posX, posY, null);
        }
    }
    // Helper method to resize the image
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(resultingImage, 0, 0, null);
        g2d.dispose();
        return resizedImage;
    }

}
