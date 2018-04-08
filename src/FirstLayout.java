/**
 * Created by ABOLFZL on 7/8/2017.
 */
public class FirstLayout {
    public Panel p;
    boolean pauseFlag = false;

    public FirstLayout(Panel p) {
        this.p = p;
        if (p.pauseFlag) {
            p.pauseMenu.setVisible(false);
            p.pauseFlag = false;
        } else {
            p.pauseMenu.setVisible(true);
            p.pauseFlag = true;
        }
    }

    public static void main(String[] args) {
mySound r=new mySound("Online.wav");
r.start();
        try {
            Thread.sleep(12111);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    r.stop();
    }

}
