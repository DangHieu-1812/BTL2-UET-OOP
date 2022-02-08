package uet.oop.bomberman.entities.movable.enemy.ai;

// Di chuyển ngẫu nhiên ko có quy luật
public class AILow extends AI{

    @Override
    public int caculateDirection() {
        return random.nextInt(4); // trả về 1 số bất kỳ từ 0 đến 3
    }
}
