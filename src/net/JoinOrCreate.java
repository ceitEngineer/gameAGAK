package net;

import Statics.Dim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by ABOLFZL on 7/15/2017.
 */
public class JoinOrCreate extends JPanel implements MouseListener {

    private JLabel title;
    private JLabel disc1;
    private JLabel disc2;
    private JTextField textField = new JTextField();
    private JButton create;
    private JButton join;
    private JButton showGames;
    private JButton cancel;
    private FirstPanel firstPanel;

    JoinOrCreate(FirstPanel firstPanel) {
        this.firstPanel = firstPanel;
        setBackground(new Color(111, 111, 76));
        Font font = new Font("Times New Roman", Font.BOLD, 55);
        ////////////////////////////
        ////////////////////////////
        title = new JLabel("MultiPlayer Game Starting...");
        disc1 = new JLabel("select game to join or creatre new Game self...");
        disc2 = new JLabel("click show Games button... ");
        create = new JButton("create");
        join = new JButton("join");
        showGames = new JButton("show Games");
        cancel = new JButton("cancel");
        ////////////////////////////
        ////////////////////////////
        title.setSize(((int) (0.2 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        title.setLocation(((int) (0.4 * Dim.WS)), ((int) (0.01 * Dim.HS)));
        title.setFont(font);
        title.setForeground(Color.WHITE);

        disc1.setLocation(((int) (0.01 * Dim.WS)), ((int) (0.11 * Dim.HS)));
        disc1.setSize(((int) (0.3 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        disc1.setFont(font);
        disc1.setForeground(Color.white);

        disc2.setSize(((int) (0.3 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        disc2.setLocation(((int) (0.01 * Dim.WS)), ((int) (0.15 * Dim.HS)));
        disc2.setFont(font);
        disc2.setForeground(Color.white);

        create.setSize(((int) (0.1 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        create.setLocation(((int) (0.30 * Dim.WS)), ((int) (0.81 * Dim.HS)));
        create.setFont(font);

        join.setSize(((int) (0.1 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        join.setLocation(((int) (0.60 * Dim.WS)), ((int) (0.81 * Dim.HS)));
        join.setFont(font);

        showGames.setSize(((int) (0.1 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        showGames.setLocation(((int) (0.45 * Dim.WS)), ((int) (0.75 * Dim.HS)));
        showGames.setFont(font);

        cancel.setSize(((int) (0.1 * Dim.WS)), ((int) (0.1 * Dim.HS)));
        cancel.setLocation(((int) (0.45 * Dim.WS)), ((int) (0.88 * Dim.HS)));
        cancel.setFont(font);

        this.add(title);
        this.add(disc1);
        this.add(disc2);
        this.add(create);
        this.add(join);
        this.add(showGames);
        this.add(cancel);

        create.addMouseListener(this);
        join.addMouseListener(this);
        cancel.addMouseListener(this);
        showGames.addMouseListener(this);


        this.setSize(Dim.ScreenDim);
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow();

    }

    JDialog getGameName;
    //    JLabel getGameName;;
    JTextField tName;
    JLabel lName;

    private void setGameName() {
        getGameName = new JDialog();
        getGameName.setBackground(new Color(107, 78, 223));
        getGameName.setSize(Dim.WS / 3, Dim.HS / 3);
        getGameName.setLocation(Dim.WS / 2 - Dim.WS / 3 / 2, Dim.HS / 2 - Dim.HS / 3 / 2);
        getGameName.setModal(true);

        lName = new JLabel("What would you like to call your game ? ");
        lName.setSize(((int) (getGameName.getSize().getWidth() )),
                ((int) (getGameName.getSize().getHeight() / 8.0)));
        lName.setLocation(((int) (getGameName.getSize().getWidth() * 0.005)),
                ((int) (getGameName.getSize().getHeight() / 3.98)));
        lName.setFont(FirstPanel.font);
        lName.setOpaque(true);

        tName = new JTextField();
        tName.setSize(((int) (getGameName.getSize().getWidth() * 0.85)),
                ((int) (getGameName.getSize().getHeight() / 8.0)));
        tName.setLocation(((int) (getGameName.getSize().getWidth() * 0.005)),
                ((int) (getGameName.getSize().getHeight() / 3.)));
        tName.setFont(FirstPanel.font);
        //  tName.requestFocus();
        JButton ok = new JButton("Ok");
        ok.setSize(120, 120);
        ok.setLocation(10, (int) (getGameName.getSize().getHeight() / 2));

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getID());
                if (e.getID() == 1001) {
                    if (!tName.getText().equals("")){
                        getGameName.dispose();
                        firstPanel.removeAll();
                        firstPanel.validate();
                        firstPanel.repaint();
                        new Server(firstPanel,tName.getText());
                    }


                }
            }
        });
        JButton cancel = new JButton("Cancel");
        cancel.setSize(120, 120);
        cancel.setLocation((int) (getGameName.getSize().getWidth()*0.85), (int) (getGameName.getSize().getHeight() / 2));

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getID());
                if (e.getID() == 1001) {
                    getGameName.dispose();
                }
            }
        });
        getGameName.add(tName);

        getGameName.add(ok);
        getGameName.add(lName);
        getGameName.add(cancel);


        getGameName.setLayout(null);
        getGameName.setUndecorated(true);
        getGameName.setVisible(true);
        getGameName.setResizable(false);
        getGameName.setAlwaysOnTop(true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("in cl");
        if (((JButton) e.getSource()).getText().equals("create")) {

            //Server server = new Server("k");
//
//            firstPanel.removeAll();
//            firstPanel.validate();
//            firstPanel.repaint();
            setGameName();


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
}
