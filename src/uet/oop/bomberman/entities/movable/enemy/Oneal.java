package uet.oop.bomberman.entities.movable.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.movable.enemy.ai.AIHigh;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

import static uet.oop.bomberman.BombermanGame.*;

public class Oneal extends Enemy {
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(1); // chỉ số va chạm của Oneal
        setSpeed(2);
        _ai = new AIHigh(bomberman,this);
        direction = _ai.caculateDirection();

        alive = true;
    }

    Rectangle e = this.getBounds(); // tạo bao cho oneal
    int n = 0;

    @Override
    public void update() {
        n++;
        if (isAlive()) { // điều kiện còn sống hay ko
            if (direction == 3) goLeft();
            if (direction == 1) goRight();
            if (direction == 0) goUp();
            if (direction == 2) goDown();
            if ( n == 32) {
                direction = _ai.caculateDirection();
                n = 0;
            }
            if (!bomberman.isAlive()) bomberman.update();

        } else if (animated < 30) { // đại diện tốc độ load ảnh lúc enemy DEAD
            animated++;
            img = Sprite.oneal_dead.getFxImage();
        } else {
            BombermanGame.enemies.remove(this); // dkien còn sồng trả về false thì xóa enemy
            score += 300;
        }
    }

    // Các phương thức đại diện cho HIỂN THỊ SỰ DI CHUYỂN (bản chất là load nhiều ảnh trồng lên nhau thật nhanh để tạo cảm giác chuyển động
    public void goLeft() {
        e = this.getBounds();
        super.goLeft();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, left++, 18).getFxImage();
    }

    public void goRight() {
        e = this.getBounds();
        super.goRight();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, right++, 18).getFxImage();
    }

    public void goUp() {
        e = this.getBounds();
        super.goUp();
        img = Sprite.movingSprite(Sprite.oneal_left1, Sprite.oneal_left2, Sprite.oneal_left3, up++, 18).getFxImage();
    }

    public void goDown() {
        e = this.getBounds();
        super.goDown();
        img = Sprite.movingSprite(Sprite.oneal_right1, Sprite.oneal_right2, Sprite.oneal_right3, down++, 18).getFxImage();
    }

    @Override
    public void stay() {
        super.stay();
        direction = _ai.caculateDirection();
    }


}