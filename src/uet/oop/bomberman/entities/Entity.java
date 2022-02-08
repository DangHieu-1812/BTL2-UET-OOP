package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;


public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;

    protected Image img;
    protected int animated = 0; // thời gian để thể hiện sự kiện
    protected int layer;
    protected boolean alive;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
    }

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
    }

    //Hiển thị hình ảnh lên game thông qua ảnh, tọa độ x, y
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y+30);
    }

    public abstract void update();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    // Trả về chỉ số va chạm đối tượng
    public int getLayer() {
        return layer;
    }
    // Cài chỉ số va chạm đối tượng (sd để xử lý va chạm)
    public void setLayer(int layer) {
        this.layer = layer;
    }
    //Khởi tạo phương thức về Alive
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    //Tạo bao (hit box) cho đối tượng, sd để tham gia các va chạm
    public Rectangle getBounds() {
        return new Rectangle(x, y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }
    //Trả về kết quả của Alive(hoặc sống hoặc chết)
    public boolean isAlive() {
        return alive;
    }


}
