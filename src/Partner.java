/**
 * Created by ABOLFZL on 7/3/2017.
 */
public class Partner extends Motile {
    private int capacityForGoldAndWood;
    private int capacityForFishing;
    private int food;
    public static final int rateOfCatchingFood=50; // 5 unit per second
    public static final int rateOfConsumingFood=1; // 5 unit per second
    public static final int  maxCatchingFish=1200;
    private final int distanceForAttack=25;


    public Partner(int i, int j , int x,int y, int health,int capacityForGoldAndWood ){
        super(x, y, i, j);
        this.capacityForGoldAndWood=capacityForGoldAndWood;
        this.health=health;
    }
public Partner() {
}
    public static void main(String[] args) {
        Partner partner = new Partner(1, 2, 3, 4, 5, 6);
        System.out.println(partner.getHealth());
    }
}


