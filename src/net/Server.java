package net;

import Statics.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by ABOLFZL on 7/15/2017.
 */
public class Server extends JPanel implements Runnable, MouseListener {

    private JLabel title;
    private JLabel disc1;
    private JLabel disc2;
    private JTextField textField = new JTextField();

    Server(FirstPanel firstPanel, String gameName) {
        System.out.println(gameName);
        setBackground(new Color(41, 111, 73));
        Font font = new Font("Times New Roman", Font.BOLD, 55);
        ////////////////////////////
        ////////////////////////////
        title = new JLabel("MultiPlayer Game Starting...");
        disc1 = new JLabel("");
        disc2 = new JLabel("click show Games button... ");

        title.setSize(((int) (0.5 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        title.setLocation(((int) (0.4 * Dim.WS)), ((int) (0.01 * Dim.HS)));
        title.setFont(font);
        title.setForeground(Color.WHITE);

        disc1.setLocation(((int) (0.01 * Dim.WS)), ((int) (0.11 * Dim.HS)));
        disc1.setSize(((int) (0.5 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        disc1.setFont(font);
        disc1.setForeground(Color.white);

        disc2.setSize(((int) (0.5 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        disc2.setLocation(((int) (0.01 * Dim.WS)), ((int) (0.15 * Dim.HS)));
        disc2.setFont(font);
        disc2.setForeground(Color.white);

        ///////////////////////
        this.add(title);
        this.add(disc1);
        this.add(disc2);

        this.setSize(Dim.ScreenDim);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow();

        firstPanel.removeAll();
        firstPanel.add(this);
        firstPanel.validate();
        firstPanel.repaint();
        timerChangeColor.start();
    }

    Timer timerChangeColor = new Timer(7212, (ActionEvent e) -> {
        this.setBackground(new Color((int) (255 * Math.random()),
                ((int) (255 * Math.random())),
                (int) (255 * Math.random())));
    });
    @Override
    public void run() {
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

class ServerNew {

    DatagramSocket socket;
    DatagramPacket packet;

    public ServerNew(int port) {
        try {
            this.socket = new DatagramSocket(port);

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void send() {

    }


}