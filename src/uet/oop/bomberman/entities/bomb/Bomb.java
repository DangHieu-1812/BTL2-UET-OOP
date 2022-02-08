package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Character;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;


public class Bomb extends Character {
    private int timeCounter = 0;
    int radius;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        setLayer(2); // chỉ số va chạm của bomb
    }
    public Bomb(int xUnit, int yUnit, Image img, int radius) {
        super(xUnit, yUnit, img);
        setLayer(2); // chỉ số va chạm của bomb
        this.radius = radius; // cài chỉ số bán kính nổ
    }
    @Override
    public void update() {
        if (timeCounter ++ == 120) { // thời gian bom sẽ nổ sau khi đặt
            explodeUpgrade();
        }
        img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, timeCounter, 60).getFxImage(); // load ảnh bom trước khi bom nổ


    }

    public void explodeUpgrade() {
        Flame e = new Flame(x, y); // tạo flame khi bom nổ
        e.setRadius(radius); // bán kính nổ
        e.render_explosion(); // chạy các chức năng của Flame
        alive = false; // tất cả những thực thể có thể bị tiêu diệt bởi flame (những thực thể đó đều có thuộc tính alive), sau khi va chạm sẽ trả về alive = false
        Sound.play("bom_explode"); // âm bom nổ
    }

}