package uet.oop.bomberman.entities.movable.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.entities.movable.enemy.ai.AI;

public abstract class Enemy extends Character {
    protected AI _ai; //Tự điều khiển
    public int direction; //Đại diện về phương hướng
    public Enemy(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
}
