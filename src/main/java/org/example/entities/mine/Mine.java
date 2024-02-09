package org.example.entities.mine;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.Area;
import org.example.entities.tools.Positionable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Mine implements Positionable {
    private Area area;
    private int x;
    private int y;

    public Mine(Area area) {
        this.area = area;
        this.x = getRandomStartPosition(area)[0];
        this.y = getRandomStartPosition(area)[1];
    }

    @Override
    public int[] getRandomStartPosition(Area area) {
        List<int[]> allEmptyCoordinates = new ArrayList<>();
        for (int i = 0; i < area.getTiles().length; i++) {
            for (int j = 0; j < area.getTiles()[0].length; j++) {
                if (area.getTiles()[i][j] == area.getFloorValue()) {
                    int[] emptyCoordinate = new int[2];
                    emptyCoordinate[0] = i;
                    emptyCoordinate[1] = j;
                    allEmptyCoordinates.add(emptyCoordinate);
                }
            }
        }
        Random random = new Random();
        return allEmptyCoordinates.get(random.nextInt(0, allEmptyCoordinates.size() -1));
    }
}
