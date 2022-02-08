package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public abstract class Character extends Entity {
    protected int desX = x;
    protected int desY = y;
    protected int speed;
    protected int left = 0;
    protected int right = 0;
    protected int up = 0;
    protected int down = 0;

    public Character(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        alive = true;
    }
    //Tạo biến speed
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    //Các phương thức di chuyển của "Character"
    public void goLeft() {
        desX = x - speed;
    }

    public void goRight() {
        desX = x + speed;
    }
    public void goUp() {
        desY = y - speed;
    }

    public void goDown() {
        desY = y + speed;
    }

    // Cho phép di chuyển qua các thực thể trong điều kiện nhất định
    public void move() {
        x = desX;
        y = desY;
    }

    // lệnh dừng -> ngăn ko cho đi qua các vật thể có chỉ số va chạm lớn hơn
    public void stay() {
        desX = x;
        desY = y;
    }
    //Tạo bound cho "Character"
    public Rectangle getBounds() {
        return new Rectangle(desX, desY, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }


}
