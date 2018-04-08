import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by AK on 06/05/2017.
 * this class just gives properties from user and then make a Map
 */
public class MapInitializer extends JFrame implements KeyListener,FocusListener,ActionListener, MouseListener {

    private int width = 400;
    private int height = 270;
    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private int state = 1;
    private JTextField itext , jtext;
    private JComboBox mountOfPlayers;
    private JLabel l1,l2,l3,s1,s2,s3;
    private JRadioButton r1,r2,r3;
    private JButton create,back;

    public static void main(String[] args) {
        new MapInitializer();
    }

    public MapInitializer(){

        //todo make background BLACK
        JDialog background = new JDialog();
        background.setSize(dimension.width,dimension.height);
        background.setLocation(0,0);
        background.getContentPane().setBackground(Color.BLACK);
        background.setUndecorated(true);
        background.setVisible(true);


        itext = new JTextField("100");
        jtext = new JTextField("100");
        l1 = new JLabel("Map Height");
        l2 = new JLabel("Map Width");
        l3 = new JLabel("Number of players");
        s1 = new JLabel("Plain");
        s2 = new JLabel("Sea");
        s3 = new JLabel("Forest");
        r1 = new JRadioButton();
        r2 = new JRadioButton();
        r3 = new JRadioButton();
        create = new JButton("Create");
        back = new JButton("Back");

        String[] list = {"2 players","3 players","4 players"};
        mountOfPlayers = new JComboBox(list);
        mountOfPlayers.setSelectedIndex(0);
        mountOfPlayers.setSize(115,25);
        mountOfPlayers.setLocation(width/2 + 30, height - 145);
        //mountOfPlayers.addFocusListener(this);

        l2.setSize(100,30);
        l2.setLocation(100,22);
        l2.setFont(new Font("Times New Roman", Font.BOLD, 15));

        l1.setSize(100,30);
        l1.setLocation(100,56);
        l1.setFont(new Font("Times New Roman", Font.BOLD, 15));

        l3.setSize(140,30);
        l3.setLocation(width/2 + 30, height - 175);
        l3.setFont(new Font("Times New Roman", Font.BOLD, 14));


        jtext.setSize(100,25);
        jtext.setLocation(width/2,25);
        jtext.setName("jtext");
        jtext.setFont(new Font("Arial", Font.BOLD, 15));
        jtext.addFocusListener(this);
        jtext.addKeyListener(this);

        itext.setSize(100,25);
        itext.setLocation(width/2,60);
        itext.setName("itext");
        itext.setFont(new Font("Arial", Font.BOLD, 15));
        itext.addFocusListener(this);
        itext.addKeyListener(this);


        int xradio = 60;
        int yradio = 110;

        r1.setSize(17,17);
        r1.setLocation(xradio,yradio);
        r1.setSelected(true);
        r1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()== 1){
                    state = 1;
                }
                else
                    state = 0;
            }
        });
        r2.setSize(17,17);
        r2.setLocation(xradio,yradio + 25);
        r2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()== 1){
                    state = 2;
                }
                else
                    state = 0;
            }
        });

        r3.setSize(17,17);
        r3.setLocation(xradio,yradio + 50);
        r3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()== 1){
                    state = 3;
                }
                else
                    state = 0;
            }
        });

        int xlabel = 84,ylabel = 108;
        s1.setSize(50,20);
        s1.setLocation(xlabel,ylabel);
        s1.setFont(new Font("Times New Roman", Font.BOLD, 15));
        s1.setForeground(new Color(0x449036));

        s2.setSize(50,20);
        s2.setLocation(xlabel,ylabel + 25);
        s2.setFont(new Font("Times New Roman", Font.BOLD, 15));
        s2.setForeground(new Color(0x0F7797));

        s3.setSize(50,20);
        s3.setLocation(xlabel,ylabel + 50);
        s3.setFont(new Font("Times New Roman", Font.BOLD, 15));
        s3.setForeground(new Color(0x644F17));


        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);

        create.setSize(110,30);
        create.setLocation(width-110 - 20,height-30 - 20);
        create.addActionListener(this);
        create.setFont(new Font("Palatino Linotype",Font.BOLD,13));


        back.setSize(110,30);
        back.setLocation(0 + 20,height-30 - 20);
        back.addActionListener(this);
        back.setFont(new Font("Palatino Linotype",Font.BOLD,13));

        this.add(mountOfPlayers);
        this.getContentPane().add(itext);
        this.getContentPane().add(jtext);
        this.getContentPane().add(r1);
        this.getContentPane().add(r2);
        this.getContentPane().add(r3);
        this.getContentPane().add(create);
        this.getContentPane().add(back);
        this.getContentPane().add(l1);
        this.getContentPane().add(l2);
        this.getContentPane().add(l3);
        this.getContentPane().add(s1);
        this.getContentPane().add(s2);
        this.getContentPane().add(s3);

        this.addMouseListener(this);

        this.setSize(width,height);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation((dimension.width/2)- width/2 ,(dimension.height)/2 - height/2 );
        this.setUndecorated(true);

        this.getContentPane().setBackground(new Color(249, 244, 162));
        this.setLayout(null);
        this.setVisible(true);
    }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(new Color(0x825120));
            g.drawRect(1,1,width-3,height-3);
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Create") && e.getID() == 1001){
                    if (state == 3){
                        state = 4;
                    }
                    Map map = new Map(Integer.parseInt(jtext.getText()),Integer.parseInt(itext.getText()),mountOfPlayers.getSelectedIndex()+2,state,2);
                    MapInterpreter readMap = new MapInterpreter(map);
                    this.dispose();
            }
            if(e.getActionCommand().equals("Back") && e.getID() == 1001){
                System.exit(0);
            }
            if(e.getActionCommand().equals("Plain") && e.getID() == 1001){
                r1.setSelected(true);
            }
            if(e.getActionCommand().equals("Sea") && e.getID() == 1001){
                r2.setSelected(true);
            }
            if(e.getActionCommand().equals("Forest") && e.getID() == 1001){
                r3.setSelected(true);
            }
        }

        @Override
        public void focusGained(FocusEvent e) {

            Color c = new Color(195, 124,0);
            if("itext".equals( ((JTextField) e.getSource()).getName())){
                l1.setForeground(c);
            }
            if("jtext".equals( ((JTextField) e.getSource()).getName())){
                l2.setForeground(c);
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if("itext".equals( ((JTextField) e.getSource()).getName())){
                l1.setForeground(Color.black);
            }
            if("jtext".equals( ((JTextField) e.getSource()).getName())){
                l2.setForeground(Color.black);
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

            if (!(e.getKeyChar()>='0' && e.getKeyChar()<='9')){
                e.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (e.getKeyCode() == KeyEvent.VK_DOWN){
                if (r1.isSelected())
                    r2.setSelected(true);
                else if (r2.isSelected())
                    r3.setSelected(true);
                else if (r3.isSelected())
                    r1.setSelected(true);
            }
            else if (e.getKeyCode() == KeyEvent.VK_UP){
                if (r1.isSelected())
                    r3.setSelected(true);
                else if (r2.isSelected())
                    r1.setSelected(true);
                else if (r3.isSelected())
                    r2.setSelected(true);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println(e.getX() + " " + e.getY());

            if (e.getX() <  s1.getX() + s1.getWidth() && e.getX() > s1.getX() && e.getY() > s1.getY() && e.getY() < s1.getY() + s1.getHeight() ){
                r1.setSelected(true);
            }
            if (e.getX() <  s2.getX() + s2.getWidth() && e.getX() > s2.getX() && e.getY() > s2.getY() && e.getY() < s2.getY() + s2.getHeight() ){
                r2.setSelected(true);
            }
            if (e.getX() <  s3.getX() + s3.getWidth()  && e.getX() > s3.getX() && e.getY() > s3.getY() && e.getY() < s3.getY() + s3.getHeight() ){
                r3.setSelected(true);
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
