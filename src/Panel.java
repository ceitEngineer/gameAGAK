
import Statics.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.util.Vector;
import javax.swing.Timer;

/**
 * Created by AK on 04/27/2017.
 */
public class Panel extends JPanel implements Runnable, ActionListener, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {


    static int shiftRate = 30;
    static int wheelFlag = 2;
    static int textureWidth = Images.lawn.getWidth(null);
    static int textureHeight = Images.lawn.getHeight(null);
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    int saveDsiX = 0, saveDisY = 0;
    boolean mouseisDragged = false;
    private Texture saveDragTile;
    private Texture saveSelect;
    int putTextureType = 0;
    private SubMenu SubMenu = new SubMenu(this);
    private MiniMap Minimap;
    boolean delaypassed = false;
    MapInterpreter m;
    private int counter = 0;
    Timer timer;
    static boolean isPlayingStarted = false;

    public PauseMenu pauseMenu = new PauseMenu(this);
    boolean pauseFlag = false;
    MyUndoRedo myUndoRedo;
    /*
    for phase 2
     */
    public Vector<Player> players = new Vector<>();

    Panel(MapInterpreter m) {
        //undoredo initializing
        myUndoRedo = new MyUndoRedo(this);
        this.m = m;

        //todo: adding PauseMenu it must be added first
        add(pauseMenu);

        //todo: adding SubMenu and Minimap
        add(SubMenu);

        Minimap = new MiniMap(this);
        add(Minimap);
        setBackground(Color.BLACK);
        setSize(dimension.width, dimension.height);
        setLocation(0, 0);
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        //new Thread(this).start();
        setLayout(null);
        setFocusable(true);

        SeasonDeterminer();

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
                if (m.map.season == 1) {

                    Images.lawn = Images.lawn_summer;
                    Images.water = Images.water_summer;
                    Images.Deepwater = Images.Deepwater_summer;
                    Images.treeimg = Images.tree_summer;

                    m.map.season = 2;
                } else if (m.map.season == 2) {

                    Images.lawn = Images.lawn_autumn;
                    Images.water = Images.water_autumn;
                    Images.Deepwater = Images.Deepwater_autumn;
                    Images.treeimg = Images.tree_autumn;

                    m.map.season = 3;
                } else if (m.map.season == 3) {

                    Images.lawn = Images.lawn_winter;
                    Images.water = Images.water_winter;
                    Images.Deepwater = Images.Deepwater_winter;
                    Images.treeimg = Images.tree_winter;

                    m.map.season = 4;
                } else if (m.map.season == 4) {

                    Images.lawn = Images.lawn_spring;
                    Images.water = Images.water_spring;
                    Images.Deepwater = Images.Deepwater_spring;
                    Images.treeimg = Images.tree_spring;

                    m.map.season = 1;
                }
            }
        });
        //timer.start();

timerPaint.start();
    }

    Timer timerPaint = new Timer(1, (ActionEvent e) -> {
        repaint();
        if (delaypassed && !pauseFlag) {
            mouseMove();
        }
    });

    public static void SeasonDeterminer() {
        if (Map.season == 1) {

            Images.lawn = Images.lawn_spring;
            Images.water = Images.water_spring;
            Images.Deepwater = Images.Deepwater_spring;
            Images.treeimg = Images.tree_spring;
        } else if (Map.season == 2) {

            Images.lawn = Images.lawn_summer;
            Images.water = Images.water_summer;
            Images.Deepwater = Images.Deepwater_summer;
            Images.treeimg = Images.tree_summer;
        } else if (Map.season == 3) {

            Images.lawn = Images.lawn_autumn;
            Images.water = Images.water_autumn;
            Images.Deepwater = Images.Deepwater_autumn;
            Images.treeimg = Images.tree_autumn;
        } else if (Map.season == 4) {

            Images.lawn = Images.lawn_winter;
            Images.water = Images.water_winter;
            Images.Deepwater = Images.Deepwater_winter;
            Images.treeimg = Images.tree_winter;
        }
    }

    /**
     * to change the season by keyBoard
     */
    public static void SeasonChanger(int mode) {

        if (mode == 1) {
            if (Map.season < 4) {
                Map.season++;
            } else {
                Map.season = 1;
            }
        } else {
            if (Map.season > 1) {
                Map.season--;
            } else {
                Map.season = 4;
            }
        }
        SeasonDeterminer();
    }

    /**
     * todo: initializing game for players in designed map
     */
    public void createPlayer() {

        System.out.println(m.textures.size()+"      get size");
        players.add(0,new Player(
                m.textures.get(9).getX()+Panel.textureWidth/2,
                m.textures.get(9).getY()+textureHeight/2,
                m.textures.get(9).getI(),
                m.textures.get(9).getJ()));
        players.get(0).setImage(new ImageIcon("images\\player\\8.jpg").getImage());
        m.removePanel();
        m.addPanel(new PlayingPanel(m.textures, players));

        isPlayingStarted=true;
        System.out.println("created");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        counter++;


        for (int i = 0; i < m.textures.size(); i++) {
            Texture t = m.textures.get(i);
            int tx = t.getX();
            int ty = t.getY();

            //todo: only screen paint
            if (-150 <= tx && tx <= dimension.getWidth() && -50 <= ty && ty <= dimension.getHeight()) {
                boolean s = t.isSelect();
                int type = t.getType();

                switch (type) {

                    case 1: {
                        //g.drawImage(Images.lawn, tx, ty, null);
                        g.drawImage(SuitablerTiles.showImage(m.textures,t), tx, ty, null);
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


                /**
                 * to show selected region painting
                 */
                if (s && !isPlayingStarted) {
                    int disX = Images.lawn.getWidth(null) / 2;
                    int disY = Images.lawn.getHeight(null) / 2;
                    Color overlay = null;
                    Color green = new Color(135, 199, 50, 80);//alpha component
                    Color red = new Color(199, 63, 34, 80);//alpha component

                    //Water Fish
                    if (putTextureType == 6 && (t.getType() == 2 || t.getType() == 6)) {
                        overlay = green;
                    }
                    if (putTextureType == 6 && t.getType() != 2 && t.getType() != 6) {
                        overlay = red;
                    }
                    //Deep Water Fish
                    if (putTextureType == 7 && (t.getType() == 3 || t.getType() == 7)) {
                        overlay = green;
                    }
                    if (putTextureType == 7 && t.getType() != 3 && t.getType() != 7) {
                        overlay = red;
                    }
                    //Gold Mine
                    if (putTextureType == 5 && t.getType() == 1) {
                        overlay = green;
                    }
                    if (putTextureType == 5 && t.getType() != 1) {
                        overlay = red;
                    }
                    //Tree
                    if (putTextureType == 4 && t.getType() == 1) {
                        overlay = green;
                    }
                    if (putTextureType == 4 && t.getType() != 1) {
                        overlay = red;
                    }

                    //Default (un-selected)
                    if (putTextureType <= 3) {
                        overlay = new Color(194, 192, 199, 90);//alpha component
                    }

                    g.setColor(overlay);
                    int px[] = {tx, tx + disX, tx + (disX * 2), tx + disX, tx};
                    int py[] = {ty + disY, ty, ty + disY, ty + (disY * 2), ty + disY};
                    g.fillPolygon(px, py, 4);

                    g.setColor(overlay.brighter());

                    int px2[] = {tx + 1, tx + disX, tx + (disX * 2) - 1, tx + disX, tx + 1};
                    int py2[] = {ty + disY, ty + 1, ty + disY, ty + (disY * 2) - 1, ty + disY};
                    g.drawPolygon(px2, py2, 4);

                    int px3[] = {tx + 2, tx + disX, tx + (disX * 2) - 2, tx + disX, tx + 2};
                    int py3[] = {ty + disY, ty + 2, ty + disY, ty + (disY * 2) - 2, ty + disY};
                    g.drawPolygon(px3, py3, 4);

                    int px4[] = {tx + 3, tx + disX, tx + (disX * 2) - 3, tx + disX, tx + 3};
                    int py4[] = {ty + disY, ty + 3, ty + disY, ty + (disY * 2) - 3, ty + disY};
                    g.drawPolygon(px4, py4, 4);
                }
            }
        }


    }

    public int[] checkSides(Texture t, int type) {
        int countXright = 0;
        for (int i = t.getJ() + 1; i < Map.width; i++) {
            Texture tt = m.textures.get(t.getI() * Map.width + i);
            if (tt.getType() == type) {
                countXright++;
            } else
                break;
        }
        int countXleft = 0;
        for (int i = t.getJ() - 1; i >= 0; i--) {
            Texture tt = m.textures.get(t.getI() * Map.width + i);
            if (tt.getType() == type) {
                countXleft++;
            } else
                break;
        }
        int countYtop = 0;
        for (int i = t.getI() - 1; i >= 0; i--) {
            Texture tt = m.textures.get(i * Map.width + t.getJ());
            if (tt.getType() == type) {
                countYtop++;
            } else
                break;
        }

        int countYbottom = 0;
        for (int i = t.getI() + 1; i < Map.height; i++) {
            Texture tt = m.textures.get(i * Map.width + t.getJ());
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
        while (i < m.textures.size()) {
            Texture t = m.textures.get(i);
            if (t != null) {
                t.setY(t.getY() - shiftRate);
            }
            i++;
        }
    }

    /**
     * this method is moving up map by mouse moving up in y=0 @
     */
    public void moveTop() {
        int i = 0;
        while (i < m.textures.size()) {
            Texture t = m.textures.get(i);
            if (t != null) {
                t.setY(t.getY() + shiftRate);
            }
            i++;
        }
    }

    public void moveRight() {
        int i = 0;
        while (i < m.textures.size()) {
            Texture t = m.textures.get(i);
            if (t != null) {
                t.setX(t.getX() - shiftRate);
            }
            i++;
        }
    }

    public void moveLeft() {
        int i = 0;
        while (i < m.textures.size()) {
            Texture t = m.textures.get(i);
            if (t != null) {
                t.setX(t.getX() + shiftRate);
            }
            i++;
        }
    }

    public synchronized void moveLeftandBottom() {
        int i = 0;
        while (i < m.textures.size()) {
            Texture t = m.textures.get(i);
            if (t != null) {
                t.setX(t.getX() + shiftRate);
                t.setY(t.getY() - shiftRate);
            }
            i++;
        }
    }

    public void moveLeftandTop() {

        int i = 0;
        while (i < m.textures.size()) {
            Texture t = m.textures.get(i);
            if (t != null) {
                t.setX(t.getX() + shiftRate);
                t.setY(t.getY() + shiftRate);
            }
            i++;
        }
    }

    public void moveRightandTop() {
        int i = 0;
        while (i < m.textures.size()) {
            Texture t = m.textures.get(i);
            if (t != null) {
                t.setX(t.getX() - shiftRate);
                t.setY(t.getY() + shiftRate);
            }
            i++;
        }
    }

    public void moveRightandBottom() {
        int i = 0;
        while (i < m.textures.size()) {
            Texture t = m.textures.get(i);
            if (t != null) {
                t.setX(t.getX() - shiftRate);
                t.setY(t.getY() - shiftRate);
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

    private Texture tileFinder(MouseEvent e) {

        double w = textureWidth;
        double h = textureHeight;

        for (Texture t : m.textures) {
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

    public void setUnSelected() {

        //todo: remove selected houses (do for all)
        for (int i = 0; i < m.textures.size(); i++) {
            Texture t = m.textures.get(i);
            t.setSelect(false);
        }
    }

    public void tileCursor(MouseEvent e) {
        if ((putTextureType >= 0 && putTextureType <= 4) || putTextureType == 6 || putTextureType == 7) {

            //todo: previous option to deselect cursor
            /*/if (flag && saveSelect != null) {
                saveSelect.setSelect(false);
            }*/

            saveSelect = tileFinder(e);
            if (saveSelect != null) {
                saveSelect.setSelect(true);
            }
        }

        //todo: Selection for GoldMine
        if (putTextureType == 5) {
            saveSelect = tileFinder(e);
            if (saveSelect != null) {
                for (int i = saveSelect.getI() - 1; i <= saveSelect.getI(); i++) {
                    for (int j = saveSelect.getJ() - 1; j <= saveSelect.getJ(); j++) {
                        Texture t = m.textures.get(i * m.width + j);
                        t.setSelect(true);
                    }
                }
            }
        }
    }

    public void putTexture(Texture t) {

        if (putTextureType != 0) {
            //todo: put tree
            if (putTextureType == 4 && t.getType() == 1) {
                t.setType(putTextureType);
                m.map.mapType[t.getI()][t.getJ()] = putTextureType;
            }
            //todo: put Gold Mine
            if (putTextureType == 5) {

                //todo: check soround houses
                int count = 0;
                for (int i = t.getI() - 1; i <= t.getI(); i++) {
                    for (int j = t.getJ() - 1; j <= t.getJ(); j++) {
                        Texture tt = m.textures.get(i * m.width + j);
                        if (tt.getType() == 1) {
                            count++;
                            //System.out.println("Count " + count);
                        }
                    }
                }
                //todo: put texture in 4 houses
                if (count == 4) {
                    for (int i = t.getI() - 1; i <= t.getI(); i++) {
                        for (int j = t.getJ() - 1; j <= t.getJ(); j++) {
                            Texture tt = m.textures.get(i * m.width + j);
                            tt.setType(putTextureType);
                            m.map.mapType[i][j] = putTextureType;
                        }
                    }
                }
            }

            //todo: put water fish
            if (putTextureType == 6 && t.getType() == 2) {
                t.setType(putTextureType);
                m.map.mapType[t.getI()][t.getJ()] = putTextureType;
            }

            //todo: put Deep water fish
            if (putTextureType == 7 && t.getType() == 3) {
                t.setType(putTextureType);
                m.map.mapType[t.getI()][t.getJ()] = putTextureType;
            } else if (putTextureType >= 1 && putTextureType <= 3) {

                ////////////////////////to set souround houses to grass in map.txt
                //from Gold Mine to grass
                int[] sides = checkSides(t, 5);
                //System.out.println(Arrays.toString(sides));

                //1
                if (sides[1] % 2 == 0 && sides[2] % 2 == 0 && sides[0] % 2 != 0 && sides[3] % 2 != 0) {
                    int i = t.getI();
                    int j = t.getJ();
                    m.textures.get(i * m.width + j + 1).setType(1);
                    m.map.mapType[i][j + 1] = 1;
                    m.textures.get((i + 1) * m.width + j).setType(1);
                    m.map.mapType[i + 1][j] = 1;
                    m.textures.get((i + 1) * m.width + j + 1).setType(1);
                    m.map.mapType[i + 1][j + 1] = 1;
                }
                //2
                if (sides[1] % 2 != 0 && sides[2] % 2 == 0) {
                    int i = t.getI();
                    int j = t.getJ();
                    m.textures.get(i * m.width + j - 1).setType(1);
                    m.map.mapType[i][j - 1] = 1;
                    m.textures.get((i + 1) * m.width + j).setType(1);
                    m.map.mapType[i + 1][j] = 1;
                    m.textures.get((i + 1) * m.width + j - 1).setType(1);
                    m.map.mapType[i + 1][j - 1] = 1;
                }
                //3
                if (sides[1] % 2 == 0 && sides[2] % 2 != 0) {
                    int i = t.getI();
                    int j = t.getJ();
                    m.textures.get(i * m.width + j + 1).setType(1);
                    m.map.mapType[i][j + 1] = 1;
                    m.textures.get((i - 1) * m.width + j).setType(1);
                    m.map.mapType[i - 1][j] = 1;
                    m.textures.get((i - 1) * m.width + j + 1).setType(1);
                    m.map.mapType[i - 1][j + 1] = 1;
                }
                //4
                if (sides[1] % 2 != 0 && sides[2] % 2 != 0) {
                    int i = t.getI();
                    int j = t.getJ();
                    m.textures.get(i * m.width + j - 1).setType(1);
                    m.map.mapType[i][j - 1] = 1;
                    m.textures.get((i - 1) * m.width + j).setType(1);
                    m.map.mapType[i - 1][j] = 1;
                    m.textures.get((i - 1) * m.width + j - 1).setType(1);
                    m.map.mapType[i - 1][j - 1] = 1;
                }

                t.setType(putTextureType);
                m.map.mapType[t.getI()][t.getJ()] = putTextureType;
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
        System.out.println("key press panel");
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            if (pauseFlag) {
                pauseMenu.setVisible(false);
                pauseFlag = false;
            } else {
                pauseMenu.setVisible(true);
                pauseFlag = true;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            SeasonChanger(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            SeasonChanger(2);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
       System.out.println("mouse pressed");
        saveDragTile = tileFinder(e);

        Texture t = tileFinder(e);
        saveDragTile = t;

        if (t != null && (putTextureType == 4 || putTextureType == 5
                || putTextureType == 6 || putTextureType == 7)) {
            putTexture(t);
            myUndoRedo.save();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        //  System.out.println("mouse released");
        mouseisDragged = false;

        setUnSelected();

        Texture t = tileFinder(e);
        boolean flag = true;

        if (t != null && saveDragTile != null && putTextureType != 0 && putTextureType <= 3) {

            if (flag) {
                for (int i = saveDragTile.getI(); i <= t.getI(); i++) {
                    for (int j = saveDragTile.getJ(); j <= t.getJ(); j++) {
                        int index = i * m.width + j;
                        Texture t2 = m.textures.get(index);
                        putTexture(t2);
                        flag = false;
                    }
                }
            }
            if (flag) {
                for (int i = saveDragTile.getI(); i > t.getI() - 1; i--) {
                    for (int j = t.getJ(); j <= saveDragTile.getJ(); j++) {
                        int index = i * m.width + j;
                        Texture t2 = m.textures.get(index);
                        putTexture(t2);
                        flag = false;
                    }
                }
            }
            if (flag) {
                for (int i = t.getI(); i <= saveDragTile.getI(); i++) {
                    for (int j = saveDragTile.getJ(); j <= t.getJ(); j++) {
                        int index = i * m.width + j;
                        Texture t2 = m.textures.get(index);
                        putTexture(t2);
                        flag = false;
                    }
                }
            }
            if (flag) {
                for (int i = saveDragTile.getI(); i <= t.getI(); i++) {
                    for (int j = t.getJ(); j <= saveDragTile.getJ(); j++) {
                        int index = i * m.width + j;
                        Texture t2 = m.textures.get(index);
                        putTexture(t2);
                        flag = false;
                    }
                }
            }
            myUndoRedo.save();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       // System.exit(0);

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseisDragged = true;

        setUnSelected();

        Texture t = tileFinder(e);
        if (t != null && saveDragTile != null && putTextureType != 0 && putTextureType <= 3) {

            for (int i = saveDragTile.getI(); i <= t.getI(); i++)
                for (int j = saveDragTile.getJ(); j <= t.getJ(); j++) {
                    Texture tt = m.textures.get(i * m.width + j);
                    tt.setSelect(true);
                }

            for (int i = saveDragTile.getI(); i > t.getI() - 1; i--) {
                for (int j = t.getJ(); j <= saveDragTile.getJ(); j++) {
                    Texture tt = m.textures.get(i * m.width + j);
                    tt.setSelect(true);
                }
            }

            for (int i = t.getI(); i <= saveDragTile.getI(); i++) {
                for (int j = saveDragTile.getJ(); j <= t.getJ(); j++) {
                    Texture tt = m.textures.get(i * m.width + j);
                    tt.setSelect(true);
                }
            }

            for (int i = saveDragTile.getI(); i <= t.getI(); i++) {
                for (int j = t.getJ(); j <= saveDragTile.getJ(); j++) {
                    Texture tt = m.textures.get(i * m.width + j);
                    tt.setSelect(true);
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setUnSelected();
        tileCursor(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        int ScaleModel = Image.SCALE_DEFAULT;

        if (wheelFlag == 2 && e.getWheelRotation() == -1) {

            int newWidth = 112;
            int newHeight = 56;
            int disX = 6;
            int disY = 3;

            saveDsiX = disX;
            saveDisY = disY;

            Images.lawn = Images.lawn.getScaledInstance(newWidth, newHeight, ScaleModel);
            Images.water = Images.water.getScaledInstance(newWidth, newHeight, ScaleModel);
            Images.Deepwater = Images.Deepwater.getScaledInstance(newWidth, newHeight, ScaleModel);


            for (int i = 0; i < m.height; i++) {
                for (int j = 0; j < m.width; j++) {
                    int index;
                    index = i * m.width + j;
                    Texture t = m.textures.get(index);
                    t.setX(t.getX() + (index % m.width) * disX - disX * i);
                    t.setY(t.getY() + (index % m.width) * disY + disY * i);
                }
            }
            tileCursor(e);
            wheelFlag++;
        } else if (wheelFlag == 1 && e.getWheelRotation() == -1) {

            int newWidth = 100;
            int newHeight = 50;
            int disX = 6;
            int disY = 3;


            Images.lawn = Images.lawn.getScaledInstance(newWidth, newHeight, ScaleModel);
            Images.water = Images.water.getScaledInstance(newWidth, newHeight, ScaleModel);
            Images.Deepwater = Images.Deepwater.getScaledInstance(newWidth, newHeight, ScaleModel);

            for (int i = 0; i < m.height; i++) {
                for (int j = 0; j < m.width; j++) {
                    int index = i * m.width + j;
                    Texture t = m.textures.get(index);
                    t.setX(t.getX() + (index % m.width) * disX - disX * i);
                    t.setY(t.getY() + (index % m.width) * disY + disY * i);
                }
            }
            tileCursor(e);
            wheelFlag++;
        } else if (wheelFlag == 3 && e.getWheelRotation() == 1) {

            int newWidth = 100;
            int newHeight = 50;
            int disX = 6;
            int disY = 3;


            Images.lawn = Images.lawn.getScaledInstance(newWidth, newHeight, ScaleModel);
            Images.water = Images.water.getScaledInstance(newWidth, newHeight, ScaleModel);
            Images.Deepwater = Images.Deepwater.getScaledInstance(newWidth, newHeight, ScaleModel);

            for (int i = 0; i < m.height; i++) {
                for (int j = 0; j < m.width; j++) {
                    int index = i * m.width + j;
                    Texture t = m.textures.get(index);
                    t.setX(t.getX() - (index % m.width) * disX + disX * i);
                    t.setY(t.getY() - (index % m.width) * disY - disY * i);
                }
            }
            tileCursor(e);
            wheelFlag--;
        } else if (wheelFlag == 2 && e.getWheelRotation() == 1) {

            int newWidth = 88;
            int newHeight = 44;
            int disX = 6;
            int disY = 3;

            saveDsiX = disX;
            saveDisY = disY;

            Images.lawn = Images.lawn.getScaledInstance(newWidth, newHeight, ScaleModel);
            Images.water = Images.water.getScaledInstance(newWidth, newHeight, ScaleModel);
            Images.Deepwater = Images.Deepwater.getScaledInstance(newWidth, newHeight, ScaleModel);


            for (int i = 0; i < m.height; i++) {
                for (int j = 0; j < m.width; j++) {
                    int index = i * m.width + j;
                    Texture t = m.textures.get(index);
                    t.setX(t.getX() - (index % m.width) * disX + disX * i);
                    t.setY(t.getY() - (index % m.width) * disY - disY * i);
                }
            }
            tileCursor(e);
            wheelFlag--;
        }

    }

    @Override
    public synchronized void run() {

        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (!isPlayingStarted) {
            repaint();
            //!mouseisDragged &&
            if (delaypassed && !pauseFlag) {
                mouseMove();
            }
            try {
                //System.out.println("in run panel");
                Thread.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}