package uet.oop.bomberman.entities.movable.enemy.ai;

import java.util.Random;

public abstract class AI {

    protected Random random = new Random(); // Đối tượng trả về 1 số bất kỳ do lớp random của Obj
    public abstract int caculateDirection(); // Trả về 1 số -> Sd đế quyết định hướng đi của enemy
}
