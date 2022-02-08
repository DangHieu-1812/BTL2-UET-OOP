package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class StillEntity extends Entity {
    public StillEntity(int x, int y, Image image) {
        super(x,y,image);
    }
}
