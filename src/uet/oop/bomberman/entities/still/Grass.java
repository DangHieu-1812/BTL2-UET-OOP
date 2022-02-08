package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public class Grass extends StillEntity {

    public Grass(int x, int y, Image img) {
        super(x, y, img);
        setLayer(0); // chỉ số va chạm của grass
    }

    @Override
    public void update() {

    }


}
