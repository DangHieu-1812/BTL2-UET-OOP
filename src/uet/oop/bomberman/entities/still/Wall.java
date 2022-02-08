package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;

public class Wall extends StillEntity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        setLayer(5); // chỉ số va chạm của wall
    }

    @Override
    public void update() {

    }


}
