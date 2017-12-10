package main;


import Tiles.TileMap;
import Units.SovietConscript;
import Units.Unit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainClass extends JPanel implements KeyListener, MouseListener {

    public static final int SCREENWIDTH = 1920;
    public static final int SCREENHEIGHT = 1080;
    boolean quit = false;
    //public Controllable focus = null;
    public ArrayList<Unit> focusedUnits = new ArrayList<Unit>();
    boolean shift = false;
    boolean mousePressed = false;
    public ArrayList<Unit> focusables = new ArrayList<Unit>();
    public JFrame frame;
    public TileMap gameMap;
    //public Rectangle selectedArea = new Rectangle();
    public Point topLeft, bottomRight;

    public MainClass() {
        focusables.add(new SovietConscript(500, 500));
        focusables.add(new SovietConscript(400, 400));
        try {
            gameMap = new TileMap("res/DefaultMap.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setSize(SCREENWIDTH, SCREENHEIGHT);
        this.setVisible(true);
        this.setDoubleBuffered(true);
        this.addMouseListener(this);
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
        double currentTime;
        double deltaTime;
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
        for(int i=0;i<focusables.size();i++){
            focusables.get(i).update(pTimeElapsed);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        gameMap.draw(g);
        for(int i=0;i<focusables.size();i++){
            focusables.get(i).draw(g);
        }
        if (mousePressed) {
            g.setColor(Color.BLACK);
            g.drawLine(topLeft.x, topLeft.y, getMousePosition().x, topLeft.y);
            g.drawLine(topLeft.x, topLeft.y, topLeft.x, getMousePosition().y);
            g.drawLine(topLeft.x, getMousePosition().y, getMousePosition().x, getMousePosition().y);
            g.drawLine(getMousePosition().x, topLeft.y, getMousePosition().x, getMousePosition().y);
            g.setColor(new Color(65, 190, 190, 80));
            g.fillRect(topLeft.x, topLeft.y, getMousePosition().x - topLeft.x, getMousePosition().y - topLeft.y);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            for (int i = 0; i < focusedUnits.size(); i++) {
                focusedUnits.get(i).passInKeyboardPressed(e);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            quit = true;
        }else if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            for (int i = 0; i < focusedUnits.size(); i++) {
                focusedUnits.get(i).passInKeyboardReleased(e);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            topLeft = e.getPoint();
            mousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON3){
            if (focusedUnits.size() > 0) {
                for (int i = 0; i < focusedUnits.size(); i++) {
                    focusedUnits.get(i).passInMouseReleasedEvent(e);
                }
            }
        }else if(e.getButton()==MouseEvent.BUTTON1){
            mousePressed = false;
            bottomRight = e.getPoint();
            boolean changeInFocus = false;
            focusedUnits.clear();
            for(int i=0;i<focusables.size();i++){
                if (focusables.get(i).getShape().intersects(topLeft.x - focusables.get(i).getXPos(), topLeft.y - focusables.get(i).getYPos(), bottomRight.x - topLeft.x, bottomRight.y - topLeft.y)) {
                    if (!changeInFocus) {
                        changeInFocus = true;
                    }
                    focusedUnits.add(focusables.get(i));
                }
            }
            
            for(int i=0;i<focusables.size();i++){
                if (focusables.get(i).isInArea(new Point(e.getX() - (int) focusables.get(i).getXPos(), e.getY() - (int) focusables.get(i).getYPos())) && !changeInFocus) {
                    focusedUnits.clear();
                    focusedUnits.add(focusables.get(i));
                } 
            }
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
