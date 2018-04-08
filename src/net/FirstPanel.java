package net;

import Statics.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by ABOLFZL on 7/15/2017.
 */
public class FirstPanel extends JPanel implements MouseListener, KeyListener {

    private final JTextField textName;
    private final JLabel lName;
    private final JButton ok;
    private final JButton cancel;
    private Image bgImage = new ImageIcon("images\\backGrounds\\multi.jpg").getImage();
    static Font font = new Font("Times New Roman", Font.BOLD, 55);


    FirstPanel() {
        setBackground(Color.black);

        lName = new JLabel("Name");
        textName = new JTextField();
        ok = new JButton("Ok");
        cancel = new JButton("Cancel");

        lName.setSize(((int) (.1 * Dim.WS)), (int) (Dim.HS * 0.1));
        lName.setLocation((int) (0.075 * Dim.WS), ((int) (0.15 * Dim.HS)));
        lName.setFont(font);
        lName.setForeground(Color.white);

        textName.setSize((int) (Dim.WS * 0.85), (int) (Dim.HS * 0.1));
        textName.setLocation((int) (0.075 * Dim.WS), ((int) (0.24 * Dim.HS)));
        textName.setFont(font);


        ok.setSize(((int) (0.15 * Dim.WS)), (int) (Dim.HS * 0.1));
        ok.setLocation((int) (0.15 * Dim.WS), ((int) (0.65 * Dim.HS)));

        ok.setFont(font);
        cancel.setSize((int) (Dim.WS * 0.15), (int) (Dim.HS * 0.1));
        cancel.setLocation((int) (0.70 * Dim.WS), ((int) (0.65 * Dim.HS)));
        cancel.setFont(font);

        this.add(lName);
        this.add(textName);
        this.add(ok);
        this.add(cancel);

        ok.addMouseListener(this);
        cancel.addMouseListener(this);
        textName.addKeyListener(this);
        // ;
        textName.requestFocus();
        setSize(Dim.WS, Dim.HS);
        setLayout(null);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setLayout(null);
        frame.setSize(Dim.WS, Dim.HS);
        FirstPanel multiplayer = new FirstPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.getContentPane().add(multiplayer);
        frame.setVisible(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println("clickd");

        if (((JButton) e.getSource()).getText().equals("Ok"))
            if (!textName.getText().equals("")) {
                this.removeAll();
                this.revalidate();
                this.repaint();
                this.requestFocusInWindow();
                this.add(new JoinOrCreate(this));
                // this.repaint();
            }
        if (((JButton) e.getSource()).getText().equals("cancel")) {

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
    public void keyTyped(KeyEvent e) {
        //System.out.println(((JTextField) e.getSource()).getText());
        //System.exit(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //  g.drawImage(bgImage, 0, 0, null);
    }

}