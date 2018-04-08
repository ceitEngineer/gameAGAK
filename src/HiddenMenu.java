import Statics.Dim;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ABOLFZL on 7/9/2017.
 */
public class HiddenMenu extends JLabel implements KeyListener,MouseListener {

    public HiddenMenu() {



        this.setSize(Dim.WS,Dim.HS);
        this.setLocation(0,0);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        this.setLayout(null);
        this.setVisible(false);
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
