import com.sun.jmx.snmp.SnmpOpaque;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * Created by AK on 06/07/2017.
 */
public class PauseMenu extends JLabel implements Runnable,KeyListener,MouseListener,ActionListener {


    private JButton b1,b2,b3,b4;
    private JLabel j1,j2;
    private ImageIcon pmButton_newMap,pmButtonC_newMap,pmButtonP_newMap,pmButton_loadMap,pmButtonC_loadMap,pmButtonP_loadMap,pmButton_play,pmButtonC_play,
            pmButtonP_play,pmButton_quit,pmButtonC_quit,pmButtonP_quit,Border;
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private int x;
    private int y = dimension.height/2 - 130;
    private int dis = 70;

    private Panel p;

    private MapReader mapReader;
    public JFileChooser jfc;
    public PauseMenu(Panel p){
        this.p =  p;

        pmButton_newMap = new ImageIcon("images\\menuImages\\pmButton_newMap.png");
        pmButtonC_newMap = new ImageIcon("images\\menuImages\\pmButtonC_newMap.png");
        pmButtonP_newMap = new ImageIcon("images\\menuImages\\pmButtonP_newMap.png");
        pmButton_loadMap = new ImageIcon("images\\menuImages\\pmButton_loadMap.png");
        pmButtonC_loadMap = new ImageIcon("images\\menuImages\\pmButtonC_loadMap.png");
        pmButtonP_loadMap = new ImageIcon("images\\menuImages\\pmButtonP_loadMap.png");
        pmButton_play = new ImageIcon("images\\menuImages\\pmButton_play.png");
        pmButtonC_play = new ImageIcon("images\\menuImages\\pmButtonC_play.png");
        pmButtonP_play = new ImageIcon("images\\menuImages\\pmButtonP_play.png");
        pmButton_quit = new ImageIcon("images\\menuImages\\pmButton_quit.png");
        pmButtonC_quit = new ImageIcon("images\\menuImages\\pmButtonC_quit.png");
        pmButtonP_quit = new ImageIcon("images\\menuImages\\pmButtonP_quit.png");

        Border = new ImageIcon("images\\menuImages\\pauseMenuBorder.png");


        x = (dimension.width)/2 - pmButton_newMap.getIconWidth()/2 + 5;


        b1 = new JButton("New Map", pmButton_newMap);
        b1.setSize(pmButton_newMap.getIconWidth() - 10, pmButton_newMap.getIconHeight());
        b1.setBorder(BorderFactory.createEtchedBorder(new Color(0x6A6A2D), new Color(0xEDF3ED)));
        b1.setRolloverIcon(pmButtonC_newMap);
        b1.setPressedIcon(pmButtonP_newMap);
        b1.setLocation(x, y);
        b1.addKeyListener(this);
        add(b1);
        b1.setActionCommand("New Map");
        b1.addActionListener(this);

        b2 = new JButton("Load Map", pmButton_loadMap);
        b2.setSize(pmButton_newMap.getIconWidth() - 10, pmButton_newMap.getIconHeight());
        b2.setBorder(BorderFactory.createEtchedBorder(new Color(0x6A6A2D), new Color(0xEDF3ED)));
        b2.setRolloverIcon(pmButtonC_loadMap);
        b2.setPressedIcon(pmButtonP_loadMap);
        b2.setLocation(x, y + dis * 1);
        b2.addKeyListener(this);
        add(b2);
        b2.setActionCommand("Load Map");
        b2.addActionListener(this);

        b3 = new JButton("Play", pmButton_play);
        b3.setSize(pmButton_newMap.getIconWidth() - 10, pmButton_newMap.getIconHeight());
        b3.setBorder(BorderFactory.createEtchedBorder(new Color(0x6A6A2D), new Color(0xEDF3ED)));
        b3.setRolloverIcon(pmButtonC_play);
        b3.setPressedIcon(pmButtonP_play);
        b3.setLocation(x, y+ dis * 2);
        b3.addKeyListener(this);
        add(b3);
        b3.setActionCommand("Play");
        b3.addActionListener(this);

        b4 = new JButton("Quit", pmButton_quit);
        b4.setSize(pmButton_newMap.getIconWidth() - 10, pmButton_newMap.getIconHeight());
        b4.setBorder(BorderFactory.createEtchedBorder(new Color(0x6A6A2D), new Color(0xEDF3ED)));
        b4.setRolloverIcon(pmButtonC_quit);
        b4.setPressedIcon(pmButtonP_quit);
        b4.setLocation(x, y+ dis * 3);
        b4.addKeyListener(this);
        add(b4);
        b4.setActionCommand("Quit");
        b4.addActionListener(this);


        //Border
        j2 = new JLabel(Border);
        j2.setSize(Border.getIconWidth(), Border.getIconHeight());
        j2.setLocation((dimension.width)/2 - Border.getIconWidth()/2, (dimension.height)/2 - Border.getIconHeight()/2);
        j2.setBorder(BorderFactory.createEtchedBorder(new Color(0x6A6A2D), new Color(0xEDF3ED)));
        add(j2);

        //todo: dont set BckGround Color cause the parent dont paint its self, thus add a new JLabel.
        //this.setOpaque(true);
        //this.setBackground(new Color(30, 30, 30, 181));
        j1 = new JLabel();
        j1.setOpaque(true);
        j1.setSize(dimension.width,dimension.height);
        j1.setLocation(0,0);
        j1.setBackground(new Color(0, 0, 0, 180));
        add(j1);



        this.setSize(dimension.width,dimension.height);
        this.setLocation(0,0);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setLayout(null);
        this.setVisible(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        InputStream in = null;
        try {
            in = new FileInputStream("sounds\\click.wav");
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (FileNotFoundException eq) {
            eq.printStackTrace();
        } catch (IOException eq) {
            eq.printStackTrace();
        }

        if (e.getActionCommand() == "Load Map") {
            jfc = new JFileChooser();
            jfc.addKeyListener(this);
            //JFileChooser
            jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int returnVal = jfc.showOpenDialog(p);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //todo: process of Loading
                File file = jfc.getSelectedFile();
                mapReader = new MapReader(file.toString());
            }
            if (returnVal == 0) {
                //todo: close the last map
                p.m.dispose();
            }
        }
        else if (e.getActionCommand() == "Quit") {
            System.exit(0);
        }

        else if (e.getActionCommand() == "New Map") {
            new MapInitializer();
            p.m.dispose();
        }

        if (e.getActionCommand().equals("Play")){
            setVisible(false);
            p.pauseFlag = false;
            p.createPlayer();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

            if (p.pauseFlag) {
                setVisible(false);
                p.pauseFlag = false;
            }
            else {
                setVisible(true);
                p.pauseFlag = true;
            }
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
    public void run() {

    }
}
