package org.example.entities.mine;

import lombok.Getter;
import lombok.Setter;
import org.example.entities.Area;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MineField {

    private final int explosionValueOccurrences = 3;
    private List<Mine> mineField;
    private Area area;
    private Mine mine;

    public MineField(Area area) {
        this.area = area;
        this.mineField = new ArrayList<>();
    }
    public void addMine(Mine mine) {
        mineField.add(mine);
    }
}
