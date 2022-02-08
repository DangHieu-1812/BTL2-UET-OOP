package uet.oop.bomberman.entities.movable.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.movable.enemy.ai.AILow;
import uet.oop.bomberman.graphics.Sprite;

public class Kondoria extends Enemy {

    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(4); // chỉ số va chạm của Kondoria
        setSpeed(2);
        _ai = new AILow();
        direction = _ai.caculateDirection();
        alive = true;
    }

    @Override
    public void update() {
        if(isAlive()) { // điều kiện còn sống hay ko
            if (direction == 0) goLeft();
            if (direction == 1) goRight();
            if (direction == 2) goUp();
            if (direction == 3) goDown();

        }else if(animated < 30){ // đại diện cho tốc độ load ảnh enemy lúc DEAD
            animated ++;
            img = Sprite.kondoria_dead.getFxImage();
        }else {
            BombermanGame.enemies.remove(this); // dkien còn sồng trả về false thì xóa enemy
            BombermanGame.score += 400;
        }

    }
    // Các phương thức đại diện cho HIỂN THỊ SỰ DI CHUYỂN (bản chất là load nhiều ảnh trồng lên nhau thật nhanh để tạo cảm giác chuyển động
    public void goLeft() {
        super.goLeft();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, left++, 18).getFxImage();
    }

    public void goRight() {
        super.goRight();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, right++, 18).getFxImage();
    }

    public void goUp() {
        super.goUp();
        img = Sprite.movingSprite(Sprite.kondoria_left1, Sprite.kondoria_left2, Sprite.kondoria_left3, up++, 18).getFxImage();
    }

    public void goDown() {
        super.goDown();
        img = Sprite.movingSprite(Sprite.kondoria_right1, Sprite.kondoria_right2, Sprite.kondoria_right3, down++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        direction = _ai.caculateDirection();
    }
}
