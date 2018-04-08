import javax.swing.*;
import javax.xml.bind.ValidationEvent;
import java.awt.*;
import java.util.Vector;

/**
 * Created by ABOLFZL on 7/3/2017.
 */
public class Motile {
    private double x,y;
    private double xAux,yAux;
    private double vx,vy;
    private int i,j;
    private int speed;
    private Image image;


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    protected int health ;
    public static final int speedInSpring=8;
    public static final int speedInSummer=7;
    public static final int speedInFall=5;
    public static final int speedInWinter=3;
    public static final int foodForCreatingPartner=1000;
    public static final int foodForCreatingSoldier=2000;
    public static final int goldForCreatingSoldier=250;
    public static final int woodForCreatingSoldier=600;

     Motile() {
    }
     Motile(double x, double y, int i, int j) {
        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getyAux() {
        return yAux;
    }

    public void setyAux(double yAux) {
        this.yAux = yAux;
    }

    public double getxAux() {
        return xAux;
    }

    public void setxAux(double xAux) {
        this.xAux = xAux;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


    public static void main(String[] args) {
        Motile motile= new Motile();
        motile.setImage(new ImageIcon("images\\player\\8.jpg").getImage());
        System.out.println(motile.getImage().getWidth(null));


    }
}
