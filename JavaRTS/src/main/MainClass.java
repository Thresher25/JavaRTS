package main;


import javax.swing.*;
import java.awt.*;

public class MainClass extends JPanel {

    public static final int SCREENWIDTH = 1920;
    public static final int SCREENHEIGHT = 1080;
    public JFrame frame;

    public MainClass() {
        this.setSize(SCREENWIDTH, SCREENHEIGHT);
        this.setVisible(true);
        this.setDoubleBuffered(true);

        frame = new JFrame("JavaRTS");
        frame.setSize(SCREENWIDTH, SCREENHEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(this);

    }

    public static void main(String[] args){
        MainClass mc = new MainClass();
        mc.frame.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
    }


}
