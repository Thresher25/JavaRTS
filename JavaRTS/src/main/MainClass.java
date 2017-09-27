package main;


import Tiles.Grass;
import Tiles.Tile;

import javax.swing.*;
import java.awt.*;

public class MainClass extends JPanel {

    public static final int SCREENWIDTH = 1920;
    public static final int SCREENHEIGHT = 1080;
    boolean quit = false;
    public JFrame frame;
    public Grass til = new Grass(600, 600, 0, 5);
    public Grass til1 = new Grass(632, 600, 0, 5);
    public Grass til2 = new Grass(664, 600, 0, 5);

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
        // mc.frame.repaint();
        double currentTime = System.currentTimeMillis();
        double deltaTime = 0;
        double maxDelta = 1000 / 30;
        double minDelta = 1000 / 120;
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
                long div = Math.round(deltaTime / maxDelta) + 1;
                System.out.println(div);
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
        for (int i = 0; i < Math.sqrt(Tile.bufArray.length); i++) {
            for (int j = 0; j < Math.sqrt(Tile.bufArray.length); j++) {
                g.drawImage(Tile.bufArray[i * 16 + j], j * 32, i * 32, null);
            }
        }
        til.draw(g);
        til1.draw(g);
        til2.draw(g);
    }


}
