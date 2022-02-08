package uet.oop.bomberman.entities.still;

import javafx.scene.image.Image;

public class Portal extends StillEntity{
    public Portal(int x, int y, Image image) {
        super(x, y, image);
        setLayer(1); // chỉ số va cham của portal
    }

    @Override
    public void update() {

    }
}
