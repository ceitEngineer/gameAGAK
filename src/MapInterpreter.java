import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by AK on 04/26/2017.
 * this class gives a @Map and then interprets it ro run
 */
public class MapInterpreter extends JFrame implements KeyListener{

    private Panel panel;
    private ImageIcon lawn = new ImageIcon("Texture\\lawn.png");
    private ImageIcon water = new ImageIcon("Texture\\water.png");
    private Scanner sc;
    static int width, height;
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    Map map;
    Vector<Texture> textures = new Vector<Texture>();
    JPanel parentPanel = new JPanel();


    MapInterpreter(Map map) {
        this.map = map;
        this.width = Map.width;
        this.height = Map.height;
        ///////////////////////////////////
        //for panels setting
        parentPanel.setBackground(Color.darkGray);
        parentPanel.setLayout(new BorderLayout(10, 10));
        panel = new Panel(this);

        parentPanel.add(panel, BorderLayout.CENTER);

      //  panel.addKeyListener(this);

        ///////////////////////////////////
        add2Vector();
        getContentPane().add(parentPanel);
      //  parentPanel.addKeyListener(this);
///////////////////////////////////
        setTitle("Map");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void removePanel() {
        parentPanel.remove(panel);
        //System.out.println("removed");
    }

    public void addPanel(JPanel panel2) {
        parentPanel.add(panel2,BorderLayout.CENTER);
      // getContentPane().add(parentPanel);
        parentPanel.revalidate();
        parentPanel.repaint();


    }


    public void add2Vector() {

        textures.clear();
        //add elements to vectors
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int mode = map.mapType[i][j];
                // 1 is Texture
                if (mode == 1) {
                    int x = ((dimension.width) / 2) - (lawn.getIconWidth() / 2) + (lawn.getIconWidth() / 2) * j - (lawn.getIconWidth() / 2) * i;
                    int y = -1000 + (lawn.getIconHeight() / 2) * j + (lawn.getIconHeight() / 2) * i;
                    textures.add(new Texture(x, y, i, j, 1, false));
                }
                // 2 is Water
                else if (mode == 2) {
                    int x = ((dimension.width) / 2) - (water.getIconWidth() / 2) + (water.getIconWidth() / 2) * j - (water.getIconWidth() / 2) * i;
                    int y = -1000 + (water.getIconHeight() / 2) * j + (water.getIconHeight() / 2) * i;
                    textures.add(new Texture(x, y, i, j, 2, false));
                }
                // 3 is Deep Water
                else if (mode == 3) {
                    int x = ((dimension.width) / 2) - (water.getIconWidth() / 2) + (water.getIconWidth() / 2) * j - (water.getIconWidth() / 2) * i;
                    int y = -1000 + (water.getIconHeight() / 2) * j + (water.getIconHeight() / 2) * i;
                    textures.add(new Texture(x, y, i, j, 3, false));
                }
                // 4 is Tree
                else if (mode == 4) {
                    int x = ((dimension.width) / 2) - (water.getIconWidth() / 2) + (water.getIconWidth() / 2) * j - (water.getIconWidth() / 2) * i;
                    int y = -1000 + (water.getIconHeight() / 2) * j + (water.getIconHeight() / 2) * i;
                    textures.add(new Texture(x, y, i, j, 4, false));
                }
                // 5 is Gold Mine
                else if (mode == 5) {
                    int x = ((dimension.width) / 2) - (water.getIconWidth() / 2) + (water.getIconWidth() / 2) * j - (water.getIconWidth() / 2) * i;
                    int y = -1000 + (water.getIconHeight() / 2) * j + (water.getIconHeight() / 2) * i;
                    textures.add(new Texture(x, y, i, j, 5, false));
                }
            }
        }
         /*
        adding first layout of game in saveVector
         */
        panel.myUndoRedo.save();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("map interprtert key press");
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
