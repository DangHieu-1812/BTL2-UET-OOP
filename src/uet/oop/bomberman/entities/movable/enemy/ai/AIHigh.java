package uet.oop.bomberman.entities.movable.enemy.ai;

import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.movable.Bomber;
import uet.oop.bomberman.entities.movable.enemy.Enemy;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.bomberman;

public class AIHigh extends AI{
    Bomber _bomber; // Khai báo bomber
    Enemy _e; // Khai báo enemy
    List<Bomb> _bombs; // Tạo 1 list về Bomb (Cho TH sau khi BombItem sẽ có nhiều đối tượng Bomb trên Map)

    public AIHigh(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
        _bombs = bomberman.getBombs(); // Tạo bom
    }

    protected int calculateColDirection() { // điều khiển di chuyển trên hàng của enemy
        if(_bomber.getX() < _e.getX()) //
            return 3;
        else if(_bomber.getX() > _e.getX())
            return 1;

        return -1;
    }

    protected int calculateRowDirection() { // điều khiển di chuyển trên cột của enemy
        if (_bomber.getY() < _e.getY())
            return 0;
        else if (_bomber.getY() > _e.getY())
            return 2;
        return -1;
    }

    @Override
    public int caculateDirection() {
        // Tránh bom
        for (int i = 0; i < _bombs.size(); i++) {
            if (_bombs.get(i).getX() > _e.getX()) return 3;
            else if (_bombs.get(i).getX() < _e.getX()) return 1;
            else if (_bombs.get(i).getY() < _e.getY()) return 2;
            else
                return 0;
        }

        // Đuổi theo bomberman
        int vertical = random.nextInt(2);// trả về 1 số bất kỳ từ 0 đến n-1 (từ 0 đến 1)

        if (vertical == 1) {
            if (calculateColDirection() != -1)
                return calculateColDirection(); // Khi tọa độ x của cả bomber và enemy khác nhau thì trả về giá trị giúp điều khiển trên hàng ngang
            else
                return calculateRowDirection(); // Khi tọa độ x của cả bomber và enemy bằng nhau thì trả về giá trị giúp điều khiển trên hàng dọc

        } else {
            if (calculateRowDirection() != -1)
                return calculateRowDirection(); // Khi tọa độ y của cả bomber và enemy khác nhau thì trả về giá trị giúp điều khiển trên hàng dọc
            else
                return calculateColDirection(); // Khi tọa độ y của cả bomber và enemy bằng nhau thì trả về giá trị giúp điều khiển trên hàng ngang
        }

    }


}
