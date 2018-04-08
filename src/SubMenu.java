
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.AudioInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by AK on 05/20/2017.
 */

public class SubMenu extends JLabel implements ActionListener, KeyListener, MouseListener {
    public JToggleButton bLawn, bWater, bDeepWater, bitem, btexture, bsave, btree, bgoldmine, bwaterfish, bdeepwaterfish;
    public Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public Panel p;
    static int counterUndo = -1;
    ///////////////////////////////////////////////////////////////////////

    public ImageIcon submenu = new ImageIcon("images\\Submenu.png");
    public ImageIcon BLawn = new ImageIcon("images\\Bgrass.png");
    public ImageIcon BWater = new ImageIcon("images\\Bwater.png");
    public ImageIcon BDeepWater = new ImageIcon("images\\BDeepWater.png");

    public ImageIcon BLawnC = new ImageIcon("images\\Bgrass_C.png");
    public ImageIcon BWaterC = new ImageIcon("images\\Bwater_C.png");
    public ImageIcon BDeepWaterC = new ImageIcon("images\\BDeepWater_C.png");

    ////////////////////////////////////////////////////////////////////////

    public ImageIcon Bitem = new ImageIcon("images\\Bitem.png");
    public ImageIcon Btexture = new ImageIcon("images\\Btexture.png");
    public ImageIcon Bsave = new ImageIcon("images\\Bsave.png");

    public ImageIcon BitemC = new ImageIcon("images\\Bitem_C.png");
    public ImageIcon BtextureC = new ImageIcon("images\\Btexture_C.png");

    ////////////////////////////////////////////////////////////////////////

    public ImageIcon Btree = new ImageIcon("images\\Btree.png");
    public ImageIcon BtreeC = new ImageIcon("images\\Btree_C.png");

    public ImageIcon Bgoldmine = new ImageIcon("images\\Bgoldmine.png");
    public ImageIcon BgoldmineC = new ImageIcon("images\\Bgoldmine_C.png");


    public ImageIcon Bwaterfish = new ImageIcon("images\\Bwaterfish.png");
    public ImageIcon BwaterfishC = new ImageIcon("images\\Bwaterfish_C.png");


    public ImageIcon Bdeepwaterfish = new ImageIcon("images\\Bdeepwaterfish.png");
    public ImageIcon BdeepwaterfishC = new ImageIcon("images\\Bdeepwaterfish_C.png");

    public int subY, subWidth, subHeight;
    public JFileChooser jfc;


    SubMenu(Panel p) {

        init();

        bLawn = new JToggleButton("Lawn");
        bLawn.setSize(155, 142);
        bLawn.setLocation(150, 35);
        bLawn.setIcon(BLawn);
        bLawn.setSelectedIcon(BLawnC);
        bLawn.setBorderPainted(false);
        bLawn.addKeyListener(this);
        bLawn.addActionListener(this);
        this.add(bLawn);

        bWater = new JToggleButton("Water");
        bWater.setSize(155, 142);
        bWater.setLocation(310, 35);
        bWater.setIcon(BWater);
        bWater.setSelectedIcon(BWaterC);
        bWater.setBorderPainted(false);
        bWater.addKeyListener(this);
        bWater.addActionListener(this);
        add(bWater);

        bDeepWater = new JToggleButton("DeepWater");
        bDeepWater.setSize(155, 142);
        bDeepWater.setLocation(470, 35);
        bDeepWater.setIcon(BDeepWater);
        bDeepWater.setSelectedIcon(BDeepWaterC);
        bDeepWater.setBorderPainted(false);
        bDeepWater.addKeyListener(this);
        bDeepWater.addActionListener(this);
        bDeepWater.setVisible(true);
        this.add(bDeepWater);

        bitem = new JToggleButton("Items");
        bitem.setSize(44, 51);
        bitem.setLocation(840, 27);
        bitem.setIcon(Bitem);
        bitem.setSelectedIcon(BitemC);
        bitem.setBorderPainted(false);
        bitem.addKeyListener(this);
        bitem.addActionListener(this);
        add(bitem);

        btexture = new JToggleButton("Textures");
        btexture.setSize(44, 51);
        btexture.setLocation(840, 77);
        btexture.setIcon(Btexture);
        btexture.setSelectedIcon(BtextureC);
        btexture.setBorderPainted(false);
        btexture.addKeyListener(this);
        btexture.addActionListener(this);
        add(btexture);
        btexture.setSelected(true);

        bsave = new JToggleButton("Save");
        bsave.setSize(44, 51);
        bsave.setLocation(840, 127);
        bsave.setIcon(Bsave);
        bsave.setSelectedIcon(Bsave);
        bsave.setBorderPainted(false);
        bsave.addKeyListener(this);
        bsave.addActionListener(this);
        add(bsave);

        btree = new JToggleButton("Tree");
        btree.setSize(Btree.getIconWidth() - 30, Btree.getIconHeight());
        btree.setLocation(110, 32);
        btree.setIcon(Btree);
        btree.setSelectedIcon(BtreeC);
        btree.setBorderPainted(false);
        btree.addKeyListener(this);
        btree.addActionListener(this);
        btree.setVisible(false);
        add(btree);


        bgoldmine = new JToggleButton("Gold Mine");
        bgoldmine.setSize(Bgoldmine.getIconWidth() - 30, Bgoldmine.getIconHeight());
        bgoldmine.setLocation(260, 33);
        bgoldmine.setIcon(Bgoldmine);
        bgoldmine.setSelectedIcon(BgoldmineC);
        bgoldmine.setBorderPainted(false);
        bgoldmine.addKeyListener(this);
        bgoldmine.addActionListener(this);
        bgoldmine.setVisible(false);
        add(bgoldmine);

        bwaterfish = new JToggleButton("Water Fish");
        bwaterfish.setSize(Bwaterfish.getIconWidth() - 30, Bwaterfish.getIconHeight());
        bwaterfish.setLocation(410, 57);
        bwaterfish.setIcon(Bwaterfish);
        bwaterfish.setSelectedIcon(BwaterfishC);
        bwaterfish.setBorderPainted(false);
        bwaterfish.addKeyListener(this);
        bwaterfish.addActionListener(this);
        bwaterfish.setVisible(false);
        add(bwaterfish);

        bdeepwaterfish = new JToggleButton("Deep Water Fish");
        bdeepwaterfish.setSize(Bdeepwaterfish.getIconWidth() - 30, Bdeepwaterfish.getIconHeight());
        bdeepwaterfish.setLocation(560, 56);
        bdeepwaterfish.setIcon(Bdeepwaterfish);
        bdeepwaterfish.setSelectedIcon(BdeepwaterfishC);
        bdeepwaterfish.setBorderPainted(false);
        bdeepwaterfish.addKeyListener(this);
        bdeepwaterfish.addActionListener(this);
        bdeepwaterfish.setVisible(false);
        add(bdeepwaterfish);


        this.p = p;
        this.setIcon(submenu);
        this.setSize(submenu.getIconWidth(),  submenu.getIconHeight());
        this.setLocation(0, (int) (dimension.getHeight()- submenu.getIconHeight()));
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setLayout(null);
        this.setVisible(true);
    }

    public void init() {
        subY = dimension.height - submenu.getIconWidth();
        subWidth = submenu.getIconWidth();
        subHeight = submenu.getIconWidth();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z && e.isControlDown() && !(e.isShiftDown()) && counterUndo > 0) {
            p.myUndoRedo.counterUndoCanRedoable++;
            p.myUndoRedo.undo(--counterUndo);
            MyUndoRedo.setRedoable(true);
        }
        if (e.getKeyCode() == KeyEvent.VK_Y && e.isControlDown() && !(e.isShiftDown())
                && MyUndoRedo.isRedoable() && p.myUndoRedo.counterUndoCanRedoable > 0) {
            p.myUndoRedo.undo(++counterUndo);
            p.myUndoRedo.counterUndoCanRedoable--;
        }

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.out.println("in submenu");
            if (p.pauseFlag) {
                p.pauseMenu.setVisible(false);
                p.pauseFlag = false;

            } else {
                p.pauseMenu.setVisible(true);
                p.pauseFlag = true;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_PAGE_UP) {
            p.SeasonChanger(1);
        }
        if (e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
            p.SeasonChanger(2);
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
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Lawn") {

            if (bLawn.isSelected()) {
                p.putTextureType = 1;
                bWater.setSelected(false);
                bDeepWater.setSelected(false);
                p.setUnSelected();
            } else
                p.putTextureType = 0;

        }
        if (e.getActionCommand() == "Water") {

            if (bWater.isSelected()) {
                p.putTextureType = 2;
                bLawn.setSelected(false);
                bDeepWater.setSelected(false);
                p.setUnSelected();
            } else
                p.putTextureType = 0;

        }
        if (e.getActionCommand() == "DeepWater") {

            if (bDeepWater.isSelected()) {
                p.putTextureType = 3;
                bLawn.setSelected(false);
                bWater.setSelected(false);
                p.setUnSelected();
            } else
                p.putTextureType = 0;

        }

        if (e.getActionCommand() == "Items") {

            if (bitem.isSelected()) {

                btexture.setSelected(false);

                bLawn.setVisible(false);
                bWater.setVisible(false);
                bDeepWater.setVisible(false);

                btree.setVisible(true);
                bgoldmine.setVisible(true);
                bwaterfish.setVisible(true);
                bdeepwaterfish.setVisible(true);

                bLawn.setSelected(false);
                bWater.setSelected(false);
                bDeepWater.setSelected(false);

                p.putTextureType = 0;
                p.setUnSelected();
            } else {
                bitem.setSelected(true);
            }
        }

        if (e.getActionCommand() == "Textures") {

            if (btexture.isSelected()) {

                bitem.setSelected(false);

                bLawn.setVisible(true);
                bWater.setVisible(true);
                bDeepWater.setVisible(true);

                btree.setVisible(false);
                btree.setSelected(false);
                bgoldmine.setVisible(false);
                bgoldmine.setSelected(false);
                bwaterfish.setVisible(false);
                bwaterfish.setSelected(false);
                bdeepwaterfish.setVisible(false);
                bdeepwaterfish.setSelected(false);

                bLawn.setSelected(false);
                bWater.setSelected(false);
                bDeepWater.setSelected(false);

                p.putTextureType = 0;
                p.setUnSelected();
            } else {
                btexture.setSelected(true);
            }
        }

        if (e.getActionCommand() == "Save") {

            jfc = new JFileChooser();
            jfc.addKeyListener(this);

            //JFileChooser
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnVal = jfc.showOpenDialog(p);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = jfc.getSelectedFile();
                //System.out.println(file);
                /////todo: process of saving
                FileOutputStream fout = null;
                ObjectOutputStream oos = null;
                try {
                    fout = new FileOutputStream(file + ".ak");
                    oos = new ObjectOutputStream(fout);
                    oos.writeObject(p.m.map);

                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        if (e.getActionCommand() == "Tree") {

            if (btree.isSelected()) {
                p.putTextureType = 4;
                bgoldmine.setSelected(false);
                bwaterfish.setSelected(false);
                bdeepwaterfish.setSelected(false);
                p.setUnSelected();

            } else
                p.putTextureType = 0;

        }

        if (e.getActionCommand() == "Gold Mine") {

            if (bgoldmine.isSelected()) {
                p.putTextureType = 5;
                btree.setSelected(false);
                bwaterfish.setSelected(false);
                bdeepwaterfish.setSelected(false);
                p.setUnSelected();

            } else
                p.putTextureType = 0;

        }

        if (e.getActionCommand() == "Water Fish") {

            if (bwaterfish.isSelected()) {
                p.putTextureType = 6;
                btree.setSelected(false);
                bgoldmine.setSelected(false);
                bdeepwaterfish.setSelected(false);
                p.setUnSelected();

            } else
                p.putTextureType = 0;

        }

        if (e.getActionCommand() == "Deep Water Fish") {

            if (bdeepwaterfish.isSelected()) {
                p.putTextureType = 7;
                btree.setSelected(false);
                bgoldmine.setSelected(false);
                bwaterfish.setSelected(false);
                p.setUnSelected();

            } else
                p.putTextureType = 0;

        }

    }
}