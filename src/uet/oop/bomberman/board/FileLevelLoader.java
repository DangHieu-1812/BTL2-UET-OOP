package uet.oop.bomberman.board;

import uet.oop.bomberman.Layer;
import uet.oop.bomberman.entities.item.FlameItem;
import uet.oop.bomberman.entities.item.SpeedItem;
import uet.oop.bomberman.entities.item.BombItem;
import uet.oop.bomberman.entities.movable.Bomber;
import uet.oop.bomberman.entities.movable.enemy.Balloom;
import uet.oop.bomberman.entities.movable.enemy.Doll;
import uet.oop.bomberman.entities.movable.enemy.Kondoria;
import uet.oop.bomberman.entities.movable.enemy.Oneal;
import uet.oop.bomberman.entities.still.Brick;
import uet.oop.bomberman.entities.still.Grass;
import uet.oop.bomberman.entities.still.Portal;
import uet.oop.bomberman.entities.still.Wall;
import uet.oop.bomberman.graphics.Sprite;
import java.io.*;
import java.util.Scanner;
import static uet.oop.bomberman.BombermanGame.*;


public class FileLevelLoader {
    public static void createMap() {

        try {
            scanner = new Scanner(new FileReader("res/levels/level" + level + ".txt")); // đọc file txt chứa thông tin về lv map
        } catch (FileNotFoundException e) { // bắt ngoại lệ
            e.printStackTrace();
        }
        scanner.nextInt(); // nhận vào số nguyên
        HEIGHT = scanner.nextInt(); // nhận vào chỉ số về chiều cao của map
        WIDTH = scanner.nextInt(); // nhận vào chỉ số về chiều rộng của map
        scanner.nextLine();
        for (int i = 0; i < HEIGHT; i++) { // quét các dòng tiếp rồi nhập hết vào string r để duyệt sau đó xuất ảnh đêt load map
            String r = scanner.nextLine();
            for (int j = 0; j < WIDTH; j++) {
                if (r.charAt(j) == '#') {
                    stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    if (r.charAt(j) == '*') {
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'x') {
                        stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == '1') {
                        enemies.add(new Balloom(j, i, Sprite.balloom_left1.getFxImage()));
                    }
                    if (r.charAt(j) == '2') {
                        enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                    }
                    if (r.charAt(j) == '3') {
                        enemies.add(new Doll(j, i, Sprite.doll_left1.getFxImage()));
                    }
                    if (r.charAt(j) == '4') {
                        enemies.add(new Kondoria(j, i, Sprite.doll_left1.getFxImage()));
                    }
                    if (r.charAt(j) == 'b') {
                        stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'f') {
                        stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 's') {
                        stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'p') {
                        bomberman = new Bomber(j, i, Sprite.player_right.getFxImage());
                        xStart = j;
                        yStart = i;
                    }
                }
            }
        }
        stillObjects.sort(new Layer()); // các thực thể dc tạo chỉ số va chạm
    }
}