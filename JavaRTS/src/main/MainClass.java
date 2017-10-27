package main;


import Tiles.TileMap;
import Units.SovietConscript;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MainClass extends JPanel implements KeyListener {

    public static final int SCREENWIDTH = 1920;
    public static final int SCREENHEIGHT = 1080;
    boolean quit = false;
    public JFrame frame;
    public TileMap gameMap;
    public SovietConscript guy1 = new SovietConscript(400, 400);
   /* public SovietConscript guy2 = new SovietConscript(80, 20);
    public SovietConscript guy3 = new SovietConscript(130, 20);
    public SovietConscript guy4 = new SovietConscript(30, 70);
    public SovietConscript guy5 = new SovietConscript(80, 70);
    public SovietConscript guy6 = new SovietConscript(130, 70);
    public SovietConscript guy7 = new SovietConscript(30, 120);
    public SovietConscript guy8 = new SovietConscript(80, 120);
    public SovietConscript guy9 = new SovietConscript(130, 120);
    public SovietConscript guy10 = new SovietConscript(80, 170);*/

    public MainClass() {
        guy1.setCurImageX(guy1.getCurImageX() + 0);
       /* guy2.setCurImageX(guy2.getCurImageX()+8);
        guy3.setCurImageX(guy3.getCurImageX()+7);
        guy4.setCurImageX(guy4.getCurImageX()+6);
        guy5.setCurImageX(guy5.getCurImageX()+5);
        guy6.setCurImageX(guy6.getCurImageX()+4);
        guy7.setCurImageX(guy7.getCurImageX()+3);
        guy8.setCurImageX(guy8.getCurImageX()+2);
        guy9.setCurImageX(guy9.getCurImageX()+1);
        guy10.setCurImageX(guy10.getCurImageX()+0);*/
        try {
            gameMap = new TileMap("res/DefaultMap.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setSize(SCREENWIDTH, SCREENHEIGHT);
        this.setVisible(true);
        this.setDoubleBuffered(true);

        frame = new JFrame("JavaRTS");
        frame.setSize(SCREENWIDTH, SCREENHEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.addKeyListener(this);
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
        System.exit(0);

    }

    public void update(double pTimeElapsed) {
        guy1.update(pTimeElapsed);
       /* guy2.update(pTimeElapsed);
        guy3.update(pTimeElapsed);
        guy4.update(pTimeElapsed);
        guy5.update(pTimeElapsed);
        guy6.update(pTimeElapsed);
        guy7.update(pTimeElapsed);
        guy8.update(pTimeElapsed);
        guy9.update(pTimeElapsed);
        guy10.update(pTimeElapsed);*/
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
       /* for (int i = 0; i < Math.sqrt(Tile.bufArray.length); i++) {
            for (int j = 0; j < Math.sqrt(Tile.bufArray.length); j++) {
                g.drawImage(Tile.bufArray[i * 16 + j], j * 32, i * 32, null);
            }
        }
        til.draw(g);
        til1.draw(g);
        til2.draw(g);*/
        gameMap.draw(g);
        guy1.draw(g);
       /* guy2.draw(g);
        guy3.draw(g);
        guy4.draw(g);
        guy5.draw(g);
        guy6.draw(g);
        guy7.draw(g);
        guy8.draw(g);
        guy9.draw(g);
        guy10.draw(g);*/
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            quit = true;
        }
    }
}
