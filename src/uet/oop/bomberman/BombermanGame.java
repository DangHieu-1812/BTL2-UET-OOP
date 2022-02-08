package uet.oop.bomberman;

import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uet.oop.bomberman.board.FileLevelLoader;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.movable.Bomber;
import uet.oop.bomberman.entities.movable.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import uet.oop.bomberman.sound.Sound;

public class BombermanGame extends Application {

    public static int WIDTH; // khai báo biến chỉ chỉ số chiều rộng
    public static int HEIGHT; // khai báo biến chỉ chỉ số chiều cao
    public static int level = 1; // khai báo lv
    public static GraphicsContext gc; // gọi class GraphicsContext (về đồ họa 2D)
    private Canvas canvas; // tạo bản đồ, set tọa độ trong canvas
    public static Scanner scanner; // lớp scanner
    public static final List<Enemy> enemies = new ArrayList<>(); // list enenmy
    public static final List<Entity> stillObjects = new ArrayList<>(); // stillObj sẽ là list đại diện cho mọi thực thể trong game
    public static final List<Flame> flameList = new ArrayList<>(); // list đại diện cho fame
    public static int startBomb = 1; // chỉ số tăng thêm bom khi ăn bombitem
    public static int startSpeed = 8; // chỉ số speed đạt dc khi ăn speeditem
    public static int startFlame = 1; // chỉ số bán kinh flame tăng thêm khi ăn flameitem
    public static int time = 400; // cài time
    public static int score =0; // point
    public static int xStart; // tọa độ x ban đầu của bomberman
    public static int yStart; // tọa độ y ban đầu của bomberman
    public static Bomber bomberman; // bomberman được sd đẻ gọi các phương thức lớp Bomber
    public static int cout = 10; // số mạng
    public static int countTime =0;
    public static boolean check = false;
    public static AnchorPane ro = new AnchorPane(); // ro đại diện cho chức năng load các vùng cũng như ảnh text của toàn game
    public static JPANEL jpanel = new JPANEL(); // để gọi các phương thức lớp JPanel (liên quan đến set up hình ảnh)

    public static void main(String[] args) {
        Sound.play("soundtrack"); // nhạc nền
        Application.launch(BombermanGame.class); // chạy giao diện đồ họa
    }

    @Override
    public void start(Stage stage) {
        // Tham khảo mạng từ nhiều nguồn + repo vì không thực sự quá hiểu rõ về load giao diện của canvas
        FileLevelLoader.createMap();
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT+25);
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        ro.getChildren().addAll(new Rectangle(2,3));
        jpanel.setPanel();
        root.getChildren().add(ro);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        AnimationTimer timer = new AnimationTimer() { // set ván mới
            @Override
            public void handle(long l) {
                if (check == true) {
                    FileLevelLoader.createMap();
                    check = false;
                }
                if (cout == 0) this.stop();
                render();
                update();
            }
        };
        timer.start();
        scene.setOnKeyPressed(event -> bomberman.handleKeyPressedEvent(event.getCode())); // sự kiện nhập từ bàn phím
        scene.setOnKeyReleased(event -> bomberman.handleKeyReleasedEvent(event.getCode())); // xử lý sự kiện nhâp bàn phím
  }

    public void update() {
        if(countTime% 60==0){
            time--;
        }
        jpanel.setTimes(time); // set time
        jpanel.setPoint(score); // set điểm
        jpanel.setLives(cout); // set mạng
        for (int i = 0; i < enemies.size(); i++) // duyệt các sự kiện của enemy
            enemies.get(i).update(); // chạy các sự kiện của enemy
        for (int i = 0; i < flameList.size(); i++) // duyệt các sự kiện của flame
            flameList.get(i).update(); // chạy các sự kiện của flame
        bomberman.update(); // chạy các sự kiện của bomber
        List<Bomb> bombs = bomberman.getBombs(); // tạo list bombs
        for (Bomb bomb : bombs) {
            bomb.update(); // chạy các sự kiện của bomb
        }

        for (int i = 0; i < stillObjects.size(); i++) // duyệt all thực thể
            stillObjects.get(i).update(); // chạy các sự kiện các thực thể
        bomberman.handleCollisions(); // chạy các sự kiện va chạm với bomber, thực thể,.. vv (xem lại class Bomber để hiểu rõ)
        bomberman.checkCollisionFlame(); // chạy các sự kiện va chạm với flame

    }

    public void render() { // render, chạy các hình ảnh để hiển thị lên game
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int i = stillObjects.size() - 1; i >= 0; i--) {
            stillObjects.get(i).render(gc);
        }
        enemies.forEach(g -> g.render(gc));
        List<Bomb> bombs = bomberman.getBombs();
        for (Bomb bomb : bombs) {
            bomb.render(gc);
        }
        coutTime();
        bomberman.render(gc);
        flameList.forEach(g -> g.render(gc));
    }
    public void coutTime() {
        if ( countTime<400*60) {
            countTime++;
        }
    }
}