/**
 * Created by ABOLFZL on 7/4/2017.
 */
public class Soldier extends Motile {
    private int health;
    private final int distanceForAttack = 25;
    public static final int rateOfConsumingFood = 2; // 5 unit per second
    public Soldier(int x, int y, int i, int j, int health) {
        super(x, y, i, j);
        this.health = health;
    }
}