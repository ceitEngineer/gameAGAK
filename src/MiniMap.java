import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by AK on 05/22/2017.
 */
public class MiniMap extends JLabel implements ActionListener,KeyListener,MouseListener {

    public ImageIcon Minimap = new ImageIcon("images\\Minimap.png");

    private Image lawn = new ImageIcon("Texture\\lawn.png").getImage();
    private Image water = new ImageIcon("Texture\\water.png").getImage();
    private Image Deepwater = new ImageIcon("Texture\\deepwater.png").getImage();
    private Image treeimg = new ImageIcon("Texture\\tree.png").getImage();
    private Image goldmine = new ImageIcon("Texture\\GoldMine.png").getImage();
    private Image WaterFish = new ImageIcon("Texture\\waterfish.gif").getImage();
    private Image DeepWaterFish = new ImageIcon("Texture\\deepwaterfish.gif").getImage();
    

    public Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private Panel p;

    int width,height;
    
    MiniMap(Panel p){
        this.p =p;
        this.setSize(Minimap.getIconWidth(),Minimap.getIconHeight());
        this.setLocation(892,dimension.height - Minimap.getIconHeight());
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setIcon(Minimap);
        this.setFocusable(true);
        this.setLayout(null);
        this.setVisible(true);


        lawn = lawn.getScaledInstance(4, 2, Image.SCALE_DEFAULT);
        water = water.getScaledInstance(4, 2, Image.SCALE_DEFAULT);
        Deepwater = Deepwater.getScaledInstance(4, 2, Image.SCALE_DEFAULT);
        goldmine = goldmine.getScaledInstance(6, 5, Image.SCALE_DEFAULT);
        treeimg = treeimg.getScaledInstance(4, 8, Image.SCALE_DEFAULT);

        this.width = this.p.m.map.getWidth();
        this.height = this.p.m.map.getHeight();

    }


    public void mapDevider(Graphics g){

        int terit = p.m.map.mountOfPlayers;

        if (terit == 2){
            int iF = p.m.textures.get( (width*height/2)-width ).getI();
            int jF = p.m.textures.get( (width*height/2)-width ).getJ();

            int iL = p.m.textures.get( (width*height/2)-1 ).getI();
            int jL = p.m.textures.get( (width*height/2)-1 ).getJ();

            int[] r = { ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jF - (water.getWidth(null) / 2) * iF,
                    45 + (water.getHeight(null) / 2) * iF + (water.getHeight(null) / 2) * jF
                    , ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jL - (water.getWidth(null) / 2) * iL,
                    45 + (water.getHeight(null) / 2) * iL + (water.getHeight(null) / 2) * jL };

            g.drawLine(r[0],r[1],r[2],r[3]);

        }

        if (terit == 3){

            ///for left to right
            int iF = p.m.textures.get( (width*height/2)-width ).getI();
            int jF = p.m.textures.get( (width*height/2)-width ).getJ();

            int iL = p.m.textures.get( (width*height/2)-1 ).getI();
            int jL = p.m.textures.get( (width*height/2)-1 ).getJ();

            int[] r = { ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jF - (water.getWidth(null) / 2) * iF,
                    45 + (water.getHeight(null) / 2) * iF + (water.getHeight(null) / 2) * jF
                    , ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jL - (water.getWidth(null) / 2) * iL,
                    45 + (water.getHeight(null) / 2) * iL + (water.getHeight(null) / 2) * jL };

            g.drawLine(r[0],r[1],r[2],r[3]);

            //for up to middle
            int iF2 = p.m.textures.get( width/2 - 1 ).getI();
            int jF2 = p.m.textures.get( width/2 - 1 ).getJ();

            int iL2 = p.m.textures.get( (width*height/2)-width/2 - 1).getI();
            int jL2 = p.m.textures.get( (width*height/2)-width/2 - 1).getJ();

            int[] r2 = { ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jF2 - (water.getWidth(null) / 2) * iF2,
                    45 + (water.getHeight(null) / 2) * iF2 + (water.getHeight(null) / 2) * jF2
                    , ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jL2 - (water.getWidth(null) / 2) * iL2,
                    45 + (water.getHeight(null) / 2) * iL2 + (water.getHeight(null) / 2) * jL2 };

            g.drawLine(r2[0],r2[1],r2[2],r2[3]);

        }

        if (terit == 4){

            ///for left to right
            int iF = p.m.textures.get( (width*height/2)-width ).getI();
            int jF = p.m.textures.get( (width*height/2)-width ).getJ();

            int iL = p.m.textures.get( (width*height/2)-1 ).getI();
            int jL = p.m.textures.get( (width*height/2)-1 ).getJ();

            int[] r = { ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jF - (water.getWidth(null) / 2) * iF,
                    45 + (water.getHeight(null) / 2) * iF + (water.getHeight(null) / 2) * jF
                    , ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jL - (water.getWidth(null) / 2) * iL,
                    45 + (water.getHeight(null) / 2) * iL + (water.getHeight(null) / 2) * jL };

            g.drawLine(r[0],r[1],r[2],r[3]);

            //for up to down
            int iF2 = p.m.textures.get( width/2 - 1 ).getI();
            int jF2 = p.m.textures.get( width/2 - 1 ).getJ();

            int iL2 = p.m.textures.get( (width*height)-width/2 - 1).getI();
            int jL2 = p.m.textures.get( (width*height)-width/2 - 1).getJ();

            int[] r2 = { ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jF2 - (water.getWidth(null) / 2) * iF2,
                    45 + (water.getHeight(null) / 2) * iF2 + (water.getHeight(null) / 2) * jF2
                    , ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * jL2 - (water.getWidth(null) / 2) * iL2,
                    45 + (water.getHeight(null) / 2) * iL2 + (water.getHeight(null) / 2) * jL2 };

            g.drawLine(r2[0],r2[1],r2[2],r2[3]);
        }
    }

    //todo: this paint is repeating allover cause we used paintComponent in Panel - repaint in while
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < p.m.textures.size(); i++) {
            Texture t = p.m.textures.get(i);
            int tx = ((Minimap.getIconWidth()) / 2) - (water.getWidth(null) / 2) + (water.getWidth(null) / 2) * t.getJ() - (water.getWidth(null) / 2) * t.getI();
            int ty = 45 + (water.getHeight(null) / 2) * t.getJ() + (water.getHeight(null) / 2) * t.getI();

            int type = t.getType();

            switch (type) {

                case 1: {
                    g.drawImage(lawn, tx, ty, null);
                    break;
                }
                case 2: {
                    g.drawImage(water, tx, ty, null);
                    break;
                }
                case 3: {
                    g.drawImage(Deepwater, tx, ty, null);
                    break;
                }
                case 4: {
                    g.drawImage(lawn, tx, ty, null);

                    int xTree = tx + 1;
                    int yTree = ty - 4;
                    g.drawImage(treeimg, xTree, yTree, null);
                    break;
                }
                case 5: {
                    //Draw lawn for all
                    g.drawImage(lawn, tx, ty, null);

                    int[] sides = p.checkSides(t, 5);
                    if (sides[1] % 2 != 0 && sides[2] % 2 != 0) {
                        //System.out.println(countX + " " + countY);
                        int xGoldmine = tx - 1;
                        int yGoldmine = ty - 4;
                        //System.out.println(t.getI() + " " + t.getJ());
                        g.drawImage(goldmine, xGoldmine, yGoldmine, null);
                    }
                    break;
                }
                //in the case of 6 and 7 it puts nothing but if you dont want please set water or deep water 4 them
            }
        }

        mapDevider(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
}
