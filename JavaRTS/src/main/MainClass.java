package main;


import javax.swing.*;
import java.awt.*;

public class MainClass extends JPanel {

    public static final int SCREENWIDTH = 1920;
    public static final int SCREENHEIGHT = 1080;
    boolean quit = false;
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
        double previousTime = System.currentTimeMillis();
        double currentTime = System.currentTimeMillis();
        double deltaTime = 0;
        double maxDelta = 1 / 60;
        double minDelta = 1 / 120;
        while (!mc.quit) {
            currentTime = System.currentTimeMillis();
            deltaTime = currentTime - previousTime;
            previousTime = System.currentTimeMillis();
            while (deltaTime < minDelta) {
                mc.frame.repaint();
                deltaTime += System.currentTimeMillis() - previousTime;
            }
            if (deltaTime <= maxDelta) {
                mc.update(deltaTime);
            } else if (deltaTime > maxDelta) {
                int div = (int) deltaTime / (int) maxDelta;
                for (int i = 0; i < div; i++) {
                    mc.update(deltaTime / (double) div);
                }
            }
            mc.frame.repaint();
        }

    }

    public void update(double pTimeElapsed) {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
    }


}
