package org.example.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Area {
        //to create only the static item of the game:
        // area with its not changing objects (wall, floor, etc)
        private int size = 12;
        private int[][] tiles;
        private int width = 900;
        private int height = 900;
        private final int floorValue = 1;
        private final int grassValueOccurrences = 5;
        private final int wallValue = 4;
        private final int wallValueOccurrences = 8;


        private int numberOfMonster;
        Random random = new Random();

        public Area(int size) {
                initializeArea(size);
                generateRandomGrass();
                generateRandomWall();
                randomlyPopulateArea(size);
        }


        private void initializeArea(int size) {
                tiles = new int[size][size];
        }

        public void generateRandomGrass() {
                int occurrences = grassValueOccurrences;
                while (occurrences > 0) {
                        int grassValue = 2;
                        tiles[generateOneCoordinate()][generateOneCoordinate()] = grassValue;
                        occurrences--;
                }
        }

        public void generateRandomWall() {
                int occurrences = wallValueOccurrences;
                while (occurrences > 0) {
                        tiles[generateOneCoordinate()][generateOneCoordinate()] = wallValue;
                        occurrences--;
                }
        }

        private void printTiles() {
                for (int i = 0; i < tiles.length; i++) {
                        for (int j = 0; j < tiles[0].length; j++) {
                                System.out.print(tiles[i][j] + " ");
                        }
                        System.out.println();
                }
        }

        private void randomlyPopulateArea(int size) {
                for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                                int object = tiles[i][j];
                                if (object == 0) {
                                        tiles[i][j] = floorValue;
                                }
                        }
                }
        }



        private int generateOneCoordinate() {
                return random.nextInt( 0,size -1);
        }



        public int[][] getTiles() {
                return tiles;
        }
}


