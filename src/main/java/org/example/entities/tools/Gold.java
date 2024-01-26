package org.example.entities.tools;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.Area;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class Gold extends Tool{


    public Gold(Area area) {
        super(area);
    }

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
