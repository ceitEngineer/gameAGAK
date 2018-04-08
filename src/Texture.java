/**
 * Created by AK on 04/27/2017.
 */
public class Texture {

    private int x,y;
    private int i,j;
    private int type;
    private boolean select;
    private int index;
    private boolean passed=false;

public static int indexIJ(int i1,int j1){
    return ((i1*Map.width)+j1);
}
    public Texture() {
    }

    Texture(int x , int y, int i, int j, int type, boolean select) {

        this.x = x;
        this.y = y;
        this.i = i;
        this.j = j;
        this.type = type;
        this.select = select;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isSelect() {
        return select;
    }

    public int getIndex() {
        return (i*Map.width+j);
    }


    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
