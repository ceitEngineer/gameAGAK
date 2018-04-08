
import Statics.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Created by ABOLFZL on 7/8/2017.
 */
public class PlayingPanel extends JPanel implements Runnable, ActionListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
    Timer timer;
    Vector<Player> players;
    Vector<Texture> textures;
    private MiniMap Minimap;
    static int shiftRate = 50;
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    boolean pauseFlag = false;
    MyUndoRedo myUndoRedo;
    private int counter;
    private boolean delaypassed = false;
    //MapInterpreter m;
    public Player player;

    public PlayingPanel(Vector<Texture> textures, Vector<Player> players) {
        this.textures = textures;
        this.players = players;
//
        MiniMapPlaying MMP = new MiniMapPlaying(this);
        add(MMP);

//        System.out.println("playing panel");
        setBackground(Color.BLACK);
        setSize(dimension.width, dimension.height);
        setLocation(0, 0);

        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        //  new Thread(this).start();
        setLayout(null);
        setFocusable(true);

        Panel.SeasonDeterminer();


        //todo: show frame rate (set it to 60 FPS)
        timer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //   System.out.println(counter);
                counter = 0;
            }
        });
        timer.start();


        //todo: mouse dont move before 1700 milly second
        timer = new Timer(1700, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                delaypassed = true;
            }
        });
        timer.start();


        //todo: change the season
        timer = new Timer(8000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Map.season == 1) {

                    Images.lawn = Images.lawn_summer;
                    Images.water = Images.water_summer;
                    Images.Deepwater = Images.Deepwater_summer;
                    Images.treeimg = Images.tree_summer;

                    Map.season = 2;
                } else if (Map.season == 2) {

                    Images.lawn = Images.lawn_autumn;
                    Images.water = Images.water_autumn;
                    Images.Deepwater = Images.Deepwater_autumn;
                    Images.treeimg = Images.tree_autumn;

                    Map.season = 3;
                } else if (Map.season == 3) {

                    Images.lawn = Images.lawn_winter;
                    Images.water = Images.water_winter;
                    Images.Deepwater = Images.Deepwater_winter;
                    Images.treeimg = Images.tree_winter;

                    Map.season = 4;
                } else if (Map.season == 4) {

                    Images.lawn = Images.lawn_spring;
                    Images.water = Images.water_spring;
                    Images.Deepwater = Images.Deepwater_spring;
                    Images.treeimg = Images.tree_spring;

                    Map.season = 1;
                }
            }
        });
        timer.start();


        this.player = this.players.get(0);


        this.player.move(this).start();
        timerPaint.start();
    }

    Timer timerPaint = new Timer(5, (ActionEvent e) -> {
        repaint();
        if (delaypassed && !pauseFlag) {
            mouseMove();
        }
    });


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        counter++;
        for (int i = 0; i < textures.size(); i++) {
            Texture t = textures.get(i);
            int tx = t.getX();
            int ty = t.getY();

            //todo: only screen paint
            if (-150 <= tx && tx <= dimension.getWidth() && -50 <= ty && ty <= dimension.getHeight()) {
                boolean s = t.isSelect();
                int type = t.getType();

                switch (type) {

                    case 1: {
                        g.drawImage(SuitablerTiles.showImage(textures,t), tx, ty, null);
                        break;
                    }
                    case 2: {
                        g.drawImage(Images.water, tx, ty, null);
                        break;
                    }
                    case 3: {
                        g.drawImage(Images.Deepwater, tx, ty, null);
                        break;
                    }
                    case 4: {
                        g.drawImage(Images.lawn, tx, ty, null);

                        int xTree = t.getX() + 7;
                        int yTree = t.getY() - 140;
                        g.drawImage(Images.treeimg, xTree, yTree, null);
                        break;
                    }
                    case 5: {
                        //Draw lawn for all
                        g.drawImage(Images.lawn, tx, ty, null);

                        int[] sides = checkSides(t, 5);
                        if (sides[1] % 2 != 0 && sides[2] % 2 != 0) {
                            //System.out.println(countX + " " + countY);
                            int xGoldmine = t.getX() - 25;
                            int yGoldmine = t.getY() - 110;
                            //System.out.println(t.getI() + " " + t.getJ());
                            g.drawImage(Images.goldmine, xGoldmine, yGoldmine, null);
                        }
                        break;
                    }
                    case 6: {
                        g.drawImage(Images.water, tx, ty, null);
                        int xWaterfish = t.getX();
                        int yWaterfish = t.getY() - 5;
                        g.drawImage(Images.WaterFish, xWaterfish, yWaterfish, null);
                        break;
                    }
                    case 7: {
                        g.drawImage(Images.Deepwater, tx, ty, null);
                        int xDeepWaterfish = t.getX() - 4;
                        int yDeepWaterfish = t.getY() - 6;
                        g.drawImage(Images.DeepWaterFish, xDeepWaterfish, yDeepWaterfish, null);
                        break;
                    }
                }
            }
        }

        for (int ik = 0; ik < players.size(); ik++) {
            // if (players.get(ik).getImage() == null)
            //   System.out.println("null images for paint");
            //     System.out.println(players.get(ik).getImage().getWidth(null)+"  xaux "+((int) players.get(ik).getxAux()+" x "+((int) players.get(ik).getX())));
            //   System.out.println(players.get(ik).getImage().getHeight(null)+"  yaux "+((int) players.get(ik).getyAux()+" y "+((int) players.get(ik).getY())));
            g.drawImage(players.get(ik).getImage(),
                    ((int) players.get(ik).getX()),
                    ((int) players.get(ik).getY()), null);
        }

    }


    public int[] checkSides(Texture t, int type) {
        int countXright = 0;
        for (int i = t.getJ() + 1; i < Map.width; i++) {
            Texture tt = textures.get(t.getI() * Map.width + i);
            if (tt.getType() == type) {
                countXright++;
            } else
                break;
        }
        int countXleft = 0;
        for (int i = t.getJ() - 1; i >= 0; i--) {
            Texture tt = textures.get(t.getI() * Map.width + i);
            if (tt.getType() == type) {
                countXleft++;
            } else
                break;
        }
        int countYtop = 0;
        for (int i = t.getI() - 1; i >= 0; i--) {
            Texture tt = textures.get(i * Map.width + t.getJ());
            if (tt.getType() == type) {
                countYtop++;
            } else
                break;
        }

        int countYbottom = 0;
        for (int i = t.getI() + 1; i < Map.height; i++) {
            Texture tt = textures.get(i * Map.width + t.getJ());
            if (tt.getType() == type) {
                countYbottom++;
            } else
                break;
        }
        int[] side = {countXright, countXleft, countYtop, countYbottom};
        return side;
    }

    public void moveBottom() {
        int i = 0;
        while (i < textures.size()) {
            Texture t = textures.get(i);
            if (t != null) {
                t.setY(t.getY() - shiftRate);
            }
            i++;
        }
        i = 0;
        while (i < players.size()) {
            Player p = players.get(i);
            if (p != null) {
                p.setY(p.getY() - shiftRate);
                p.setyAux(p.getyAux() - shiftRate);
            }
            i++;
        }
    }

    /**
     * this method is moving up map by mouse moving up in y=0 @
     */
    public void moveTop() {
        int i = 0;
        while (i < textures.size()) {
            Texture t = textures.get(i);
            if (t != null) {
                t.setY(t.getY() + shiftRate);
            }
            i++;
        }
        i = 0;
        while (i < players.size()) {
            Player p = players.get(i);
            if (p != null) {
                p.setY(p.getY() + shiftRate);
                p.setyAux(p.getyAux() + shiftRate);
            }
            i++;
        }

    }

    public void moveRight() {
        int i = 0;
        while (i < textures.size()) {
            Texture t = textures.get(i);
            if (t != null) {
                t.setX(t.getX() - shiftRate);
            }
            i++;
        }
        i = 0;
        while (i < players.size()) {
            Player p = players.get(i);
            if (p != null) {
                p.setX(p.getX() - shiftRate);
                p.setxAux(p.getxAux() - shiftRate);
            }
            i++;
        }
    }


    public void moveLeft() {
        int i = 0;
        while (i < textures.size()) {
            Texture t = textures.get(i);
            if (t != null) {
                t.setX(t.getX() + shiftRate);
            }
            i++;
        }
        i = 0;
        while (i < players.size()) {
            Player p = players.get(i);
            if (p != null) {
                p.setX(p.getX() + shiftRate);
                p.setxAux(p.getxAux() + shiftRate);
            }
            i++;
        }

    }

    public synchronized void moveLeftandBottom() {
        int i = 0;
        while (i < textures.size()) {
            Texture t = textures.get(i);
            if (t != null) {
                t.setX(t.getX() + shiftRate);
                t.setY(t.getY() - shiftRate);
            }
            i++;
        }
        i = 0;
        while (i < players.size()) {
            Player p = players.get(i);
            if (p != null) {
                p.setX(p.getX() + shiftRate);
                p.setxAux(p.getxAux() + shiftRate);
                p.setY(p.getY() - shiftRate);
                p.setyAux(p.getyAux() - shiftRate);
            }
            i++;
        }


    }

    public void moveLeftandTop() {

        int i = 0;
        while (i < textures.size()) {
            Texture t = textures.get(i);
            if (t != null) {
                t.setX(t.getX() + shiftRate);
                t.setY(t.getY() + shiftRate);
            }
            i++;
        }
        i = 0;
        while (i < players.size()) {
            Player p = players.get(i);
            if (p != null) {
                p.setX(p.getX() + shiftRate);
                p.setxAux(p.getxAux() + shiftRate);
                p.setY(p.getY() + shiftRate);
                p.setyAux(p.getyAux() + shiftRate);
            }
            i++;
        }


    }

    public void moveRightandTop() {
        int i = 0;
        while (i < textures.size()) {
            Texture t = textures.get(i);
            if (t != null) {
                t.setX(t.getX() - shiftRate);
                t.setY(t.getY() + shiftRate);
            }
            i++;
        }
        i = 0;
        while (i < players.size()) {
            Player p = players.get(i);
            if (p != null) {
                p.setX(p.getX() - shiftRate);
                p.setxAux(p.getxAux() - shiftRate);
                p.setY(p.getY() + shiftRate);
                p.setyAux(p.getyAux() + shiftRate);
            }
            i++;
        }
    }

    public void moveRightandBottom() {
        int i = 0;
        while (i < textures.size()) {
            Texture t = textures.get(i);
            if (t != null) {
                t.setX(t.getX() - shiftRate);
                t.setY(t.getY() - shiftRate);
            }
            i++;
        }
        i = 0;
        while (i < players.size()) {
            Player p = players.get(i);
            if (p != null) {
                p.setX(p.getX() - shiftRate);
                p.setxAux(p.getxAux() - shiftRate);
                p.setY(p.getY() - shiftRate);
                p.setyAux(p.getyAux() - shiftRate);
            }
            i++;
        }
    }

    public synchronized void mouseMove() {

        int mouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int mouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();

        if (mouseX < 52 && mouseY < 45) {
            //System.out.println("left and top");
            moveLeftandTop();

            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (mouseX < 52 && mouseY > (dimension.getHeight() - 45)) {
            //System.out.println("left and bottom");
            moveLeftandBottom();
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (mouseX >= (dimension.getWidth() - 52) && mouseY < 45) {
            //System.out.println("right and top");
            moveRightandTop();
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (mouseX >= (dimension.getWidth() - 52) && mouseY > (dimension.getHeight() - 45)) {
            //System.out.println("right and bottom");
            moveRightandBottom();
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (mouseX >= (dimension.getWidth() - 52)) {
            //System.out.println("right");
            moveRight();
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (mouseY < 45) {
            //System.out.println("top");
            moveTop();
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (mouseY > dimension.height - 52) {
            //System.out.println("bottom");
            moveBottom();
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (mouseX < 52) {
            //System.out.println("left");
            moveLeft();
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(" key press playing");
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("in esc plying>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }

        if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            Panel.SeasonChanger(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            Panel.SeasonChanger(2);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private Texture tileFinder(MouseEvent e) {

        double w = Panel.textureWidth;
        double h = Panel.textureHeight;

        for (Texture t : textures) {
            double xx = e.getX() - t.getX();
            double yy = e.getY() - t.getY();
            boolean b0 = (xx < w && xx > 0 && yy < h && yy > 0);
            if (b0) {
                boolean b1 = (
                        xx < (w / 2)
                                &&
                                yy < (-1 * (h / w) * xx + h / 2)
                );
                boolean b2 = (
                        xx > (w / 2) && xx < (w)
                                &&
                                yy < ((h / w) * xx - h / 2)
                );
                boolean b3 = (xx < (w / 2)
                        &&
                        yy > ((h / w) * xx + h / 2)
                );
                boolean b4 = ((xx > (w / 2) && xx < (w)) && yy > ((-h / w) * xx + 3 * h / 2));

                if (!(b1 || b2 || b3 || b4)) {
                    return t;
                }
            }
        }
        return null;
    }

    public Texture tileFinder(double xMouse, double yMouse) {

        double w = Panel.textureWidth;
        double h = Panel.textureHeight;

        for (Texture t : textures) {
            double xx = xMouse - t.getX();
            double yy = yMouse - t.getY();
            boolean b0 = (xx < w && xx > 0 && yy < h && yy > 0);
            if (b0) {
                boolean b1 = (
                        xx < (w / 2)
                                &&
                                yy < (-1 * (h / w) * xx + h / 2)
                );
                boolean b2 = (
                        xx > (w / 2) && xx < (w)
                                &&
                                yy < ((h / w) * xx - h / 2)
                );
                boolean b3 = (xx < (w / 2)
                        &&
                        yy > ((h / w) * xx + h / 2)
                );
                boolean b4 = ((xx > (w / 2) && xx < (w)) && yy > ((-h / w) * xx + 3 * h / 2));

                if (!(b1 || b2 || b3 || b4)) {
                    return t;
                }
            }
        }
        return null;
    }


    boolean isNullSelectedTile = false;
    int iDest = -1;
    int jDest = -1;
    double xDest;
    double yDest;
    public Texture destTexture;

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouse clicked playes size" + players.size());

        destTexture = tileFinder(e);
        System.out.println("i,j mousecl " + destTexture.getI() + "  " + destTexture.getJ());

        if (destTexture != null) {
            player.isNewVelcity = true;
            player.isFinished = false;

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {


    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

    }

    @Override
    public void run() {
        //             System.out.println("in run player panel");
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            //  repaint();
            if (delaypassed && !pauseFlag) {
                mouseMove();
            }
            try {
                Thread.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
