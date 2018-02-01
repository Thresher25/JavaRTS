package main;


import NaturalResources.Mineral;
import NaturalResources.Vespene;
import Structures.Barracks;
import Structures.CommandCentre;
import Tiles.TileMap;
import Units.Formation;
import Units.SCV;
import Units.SovietConscript;
import Units.Unit;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class MainClass extends JPanel implements KeyListener, MouseListener {

    public static final int SCREENWIDTH = 1920;
    public static final int SCREENHEIGHT = 1080;
    boolean quit = false;
    public int curCount = 0;
    public Vector<Unit> focusedUnits = new Vector<Unit>();
    boolean shift = false;
    public Formation formUnits = new Formation();
    boolean mousePressed = false;
    public static Vector<GameObject> focusables = new Vector<GameObject>();
    public static int numMinerals = 0;
    public JFrame frame;
    public TileMap gameMap;
    public static int numVespene = 0;
    public static int numMaxUnits = 10;
    public static int curUnits = 0;
    public static Controllable gameFocus = null;
    boolean shouldMoveUnits = false;
    Point movePoint = new Point();
    public double cursorFrameChange = 1250.0;
    public Cursor[] c = new Cursor[5];
    public Point topLeft, bottomRight;
    public Barracks eRax = new Barracks(100,900);
    public static boolean passedBackInput = false;
    public static boolean placingStruct = false;
    BufferedImage mineralIcon, Controls, VespeneIcon;
    private boolean gameStarted = false;
    private float eSpawnTime = 3000.0f;
    public static int tStage = 0;
    private double timePassed = 0;
    private int numCC = 1;
    private CommandCentre cCentre;
    private SCV s0, s1, s2, s3, s4, s5;
    private Barracks brax;

    public MainClass() {
        cCentre = new CommandCentre(700, 350);
        cCentre.addHP(2500);
        brax = new Barracks(800, 600);
        brax.addHP(2500);
        s0 = new SCV(1150, 650);
        s1 = new SCV(1150, 750);
        s2 = new SCV(1150, 850);
        s3 = new SCV(1250, 650);
        s4 = new SCV(1250, 750);
        s5 = new SCV(1250, 850);
        eRax.setEnemy();
        //focusables.add(eRax);
        //focusables.get(focusables.size() - 1).addHP(999);
        focusables.add(cCentre);
        focusables.add(new Mineral(1200, 225));
        focusables.add(new Mineral(1270, 200));
        focusables.add(new Mineral(1320, 250));
        focusables.add(new Vespene(1380, 550));
        focusables.add(new Vespene(1470, 500));
        focusables.add(new Vespene(1525, 570));
        focusables.add(s0);
        focusables.add(s1);
        focusables.add(s2);
        focusables.add(s3);
        focusables.add(s4);
        focusables.add(s5);
        focusables.add(brax);

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
        try{
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            c[0] = toolkit.createCustomCursor(ImageIO.read(new File("res/cur1.png")), new Point(frame.getX(), frame.getY()), "CF1");
            c[1] = toolkit.createCustomCursor(ImageIO.read(new File("res/cur2.png")), new Point(frame.getX(), frame.getY()), "CF2");
            c[2] = toolkit.createCustomCursor(ImageIO.read(new File("res/cur3.png")), new Point(frame.getX(), frame.getY()), "CF3");
            c[3] = toolkit.createCustomCursor(ImageIO.read(new File("res/cur4.png")), new Point(frame.getX(), frame.getY()), "CF4");
            c[4] = toolkit.createCustomCursor(ImageIO.read(new File("res/cur5.png")), new Point(frame.getX(), frame.getY()), "CF5");
            frame.setCursor(c[0]);

            Controls = ImageIO.read(new File("res/Controls.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        frame.add(this);
        frame.repaint();
        try {
            gameMap = new TileMap("res/DefaultMap.txt");
            mineralIcon = ImageIO.read(new File("res/mineralIcon.png"));
            VespeneIcon = ImageIO.read(new File("res/VespeneIcon.png"));
            gameStarted = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                for (int i = 0; i < div; i++) {
                    mc.update(deltaTime / (double) div);
                }
            }
            mc.frame.repaint();
        }
        System.exit(0);

    }

    public void tutorialU(double t) {
        switch (tStage) {
            case 0:
                if (gameFocus != null) {
                    if (((Formation) (gameFocus)).getUnit(0).equals(s0)) {
                        tStage++;
                    }
                }
                break;
            case 1:
                if (s0.getObjectWorkingOn() != null) {
                    if (s0.getObjectWorkingOn().resourceType().equals("Mineral")) {
                        tStage++;
                    }
                }
                break;
            case 2:
                if (s0.getObjectWorkingOn() != null && s1.getObjectWorkingOn() != null && s2.getObjectWorkingOn() != null && s3.getObjectWorkingOn() != null && s4.getObjectWorkingOn() != null && s5.getObjectWorkingOn() != null) {
                    if (s0.getObjectWorkingOn().resourceType().equals("Mineral") && s1.getObjectWorkingOn().resourceType().equals("Mineral") && s2.getObjectWorkingOn().resourceType().equals("Mineral") && s3.getObjectWorkingOn().resourceType().equals("Mineral") && s4.getObjectWorkingOn().resourceType().equals("Mineral") && s5.getObjectWorkingOn().resourceType().equals("Mineral")) {
                        tStage++;
                    }
                }
                break;
            case 3:
                if (numMinerals >= 150) {
                    tStage++;
                }
                break;
            case 4:
                if (gameFocus != null) {
                    if (gameFocus == brax && focusables.size() >= 15) {
                        if (((Unit) (focusables.get(focusables.size() - 1))).getUnitType().equals("SCV")) {
                            tStage++;
                        }
                    }
                }
                break;
            case 5:
                if (((SCV) (focusables.get(focusables.size() - 1))).getObjectWorkingOn() != null) {
                    if (((SCV) (focusables.get(focusables.size() - 1))).getObjectWorkingOn().resourceType().equals("Vespene")) {
                        tStage++;
                    }
                }
                break;
            case 6:
                if (gameFocus != null) {
                    if (gameFocus == brax && focusables.size() >= 16) {
                        if (((Unit) (focusables.get(focusables.size() - 1))).getUnitType().equals("Soviet")) {
                            tStage++;
                        }
                    }
                }
                break;
            case 7:
                if (curUnits >= 10) {
                    tStage++;
                }
                break;
            case 8:
                if (gameFocus != null) {
                    if (gameFocus == cCentre) {
                        tStage++;
                    }
                }
                break;
            case 9:
                if (numMaxUnits >= 20) {
                    tStage++;
                }
                break;
            case 10:
                for (int i = 0; i < focusables.size(); i++) {
                    if (focusables.get(i).ally && focusables.get(i).isUnit()) {
                        if (((Unit) (focusables.get(i))).getUnitType().equals("SCV")) {
                            if (((SCV) (focusables.get(i))).getObjectWorkingOn() != null) {
                                if (((SCV) (focusables.get(i))).getObjectWorkingOn().resourceType().equals("SupplyDepot")) {
                                    tStage++;
                                }
                            }
                        }
                    }
                }
                break;
            case 11:
                int pSoldiers = 0;
                for (int i = 0; i < focusables.size(); i++) {
                    if (focusables.get(i).ally && focusables.get(i).isUnit()) {
                        if (((Unit) (focusables.get(i))).getUnitType().equals("Soviet")) {
                            pSoldiers++;
                        }
                    }
                }
                if (pSoldiers >= 5) {
                    tStage++;
                }
                break;
            case 12:
                timePassed += t;
                if (timePassed > 100000) {
                    tStage++;
                    timePassed = 0;
                }
                break;
            default:
                timePassed += t;
                if (timePassed > 950000) {
                    tStage++;
                    timePassed = 0;
                    SovietConscript sov;
                    if (tStage % 2 == 1) {
                        for (int i = 0; i < Math.round(7.55 * Math.pow(1.27, ((double) tStage - 13.72)) - 3.8); i++) {
                            sov = new SovietConscript(Math.random() * 500 + 100, -50);
                            sov.setEnemy();
                            sov.setFocusPoint((int) cCentre.getXPos(), (int) cCentre.getYPos());
                            focusables.add(sov);
                        }
                    } else {
                        for (int i = 0; i < Math.round(7.55 * Math.pow(1.27, ((double) tStage - 13.72)) - 3.8); i++) {
                            sov = new SovietConscript(Math.random() * 500 + 100, SCREENHEIGHT + 50);
                            sov.setEnemy();
                            sov.setFocusPoint((int) cCentre.getXPos(), (int) cCentre.getYPos());
                            focusables.add(sov);
                        }
                    }
                }
                break;
        }
    }

    public void tutorialG(Graphics g) {
        switch (tStage) {
            case 0:
                g.drawString("Press the left-click on the top-left SCV", 500, 55);
                break;
            case 1:
                g.drawString("Great! SCVs are your workers, they mine Minerals and Vespene Gas in order to build things. Why dont you right click on the minerals to get the SCV to work", 300, 55);
                break;
            case 2:
                g.drawString("Good! The SCV mines you minerals now. How about we speed things up by getting all of the SCVs to mine minerals. Left-click and drag to select all the SCVs then right click on the minerals", 50, 55);
                break;
            case 3:
                g.drawString("Nice! Now lets wait until we have 150 minerals", 400, 55);
                break;
            case 4:
                g.drawString("Ok, now left-click on your barracks. This will let you buy units. Then click on the SCV icon in the bottom right", 400, 55);
                break;
            case 5:
                g.drawString("Now get that SCV to mine the Vespene gas, the same way you got the other SCVs to mine minerals", 400, 55);
                break;
            case 6:
                g.drawString("Now save up until you have 250 Minerals and 25 Vespene gas, and then buy the Soldier from the barracks", 400, 55);
                break;
            case 7:
                g.drawString("Soldiers can attack enemies and enemy structures. It is important to have enough military power to defend yourself from enemies. Build two more soldiers", 400, 55);
                break;
            case 8:
                g.drawString("However, we cant keep building units continuously, we have a max unit cap. Click on the Command centre to learn more", 400, 55);
                break;
            case 9:
                g.drawString("In the command centre we can build Supply Depots. Supply Depots increase our max unit cap by 10. Click on the Supply Depot icon, then right click somewhere on the map to begin construction", 50, 55);
                break;
            case 10:
                g.drawString("Buildings dont build themselves! Select an SCV and make it construct the Supply Depot by right clicking", 400, 55);
                break;
            case 11:
                g.drawString("Thats all there is to it! Construct an army of 5 soldiers and prepare to defend your base! If your command centre is destroyed you lose the game!", 300, 55);
                break;
            case 12:
                g.drawString("Nice work! Now that you have mastered the basics, enemies will continually spawn every 60 seconds, survive as long as you can! Good Luck!", 350, 55);
                g.drawString("Remember that your soldiers automatically do damage if they are within range of an enemy!", 475, 80);
                break;
            default:
                g.drawString("Next Wave in " + Math.round(95.0 - (timePassed / 10000.0)) + " Seconds", 450, 55);
                break;
        }
    }

    public void update(double pTimeElapsed) {
        tutorialU(pTimeElapsed);
        /*if(eRax.HP>0){
        eSpawnTime-=pTimeElapsed;
        if(eSpawnTime<=0){
            eSpawnTime = 25000.0f;
            eRax.spawnEUnit();
            }
        }*/
        if (shouldMoveUnits) {
            shouldMoveUnits = false;
            formUnits.moveToLocation(movePoint);
        }
        if (cCentre.getHP() <= 0) {
            numCC--;
        }
        for (int j = focusables.size() - 1; j > 0; j--) {
            if (focusables.get(j).getHP() <= 0) {
                focusables.remove(focusables.get(j));
            }
        }
        for(int i=0;i<focusables.size();i++){
            focusables.get(i).update(pTimeElapsed);
            if (focusables.get(i).isUnit()) {
                Unit u = (Unit) focusables.get(i);

                for (int j = 0; j < focusables.size(); j++) {
                    if (((focusables.get(j).getXPos() - u.getXPos()) * (focusables.get(j).getXPos() - u.getXPos()) + (focusables.get(j).getYPos() - u.getYPos()) * (focusables.get(j).getYPos() - u.getYPos())) <= (u).getAttackRadius() && focusables.get(j).ally != u.ally) {
                        u.attack(focusables.get(j), pTimeElapsed);
                    }
                }
            }
        }

        cursorFrameChange-=pTimeElapsed;
        if(cursorFrameChange<=0){
            if(curCount==4){
                curCount=0;
            }else{
                curCount++;
            }
            frame.setCursor(c[curCount]);
            cursorFrameChange += 1250.0;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameStarted) {
            g.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            g.setColor(Color.MAGENTA);
            g.fillRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
            gameMap.draw(g);
            for (int i = 0; i < focusables.size(); i++) {
                if (!focusables.get(i).isUnit()) {
                    focusables.get(i).draw(g);
                }
            }
            for (int i = 0; i < focusables.size(); i++) {
                if (focusables.get(i).isUnit()) {
                    focusables.get(i).draw(g);
                }
            }
            if (gameFocus != null) {
                gameFocus.drawGUI(g);
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
            g.setColor(Color.WHITE);
            g.setFont(new Font("Times New Roman", 0, 24));
            g.drawImage(mineralIcon.getScaledInstance(32, 32, BufferedImage.SCALE_FAST), 10, 0, null);
            g.drawString("Minerals: " + numMinerals, 50, 25);
            g.drawImage(VespeneIcon, 200, 6, null);
            g.drawString("Vespene Gas: " + numVespene, 225, 25);
            curUnits = 0;
            for (int i = 0; i < focusables.size(); i++) {
                if (focusables.get(i).ally && focusables.get(i).isUnit()) {
                    curUnits += ((Unit) focusables.get(i)).getUnitPoints();
                }
            }
            g.drawString("Max Units: " + curUnits + "/" + numMaxUnits, 930, 30);
            tutorialG(g);
        } else {
            g.drawImage(Controls, 0, 0, null);
        }

    }

    public void mouse1Press(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            topLeft = e.getPoint();
            mousePressed = true;
        }
    }

    public void mouse1Release(MouseEvent e) {
        mousePressed = false;
        bottomRight = e.getPoint();
        boolean changeInFocus = false;
        focusedUnits.clear();
        Point tempTL = new Point(topLeft);
        Point tempBR = new Point(bottomRight);
        if (topLeft.x > bottomRight.x) {
            if (bottomRight.y < topLeft.y) {
                topLeft = new Point(tempBR.x, tempBR.y);
                bottomRight = new Point(tempTL.x, tempTL.y);
            } else {
                topLeft = new Point(tempBR.x, tempTL.y);
                bottomRight = new Point(tempTL.x, tempBR.y);
            }
        } else if (bottomRight.y < topLeft.y) {
            topLeft = new Point(tempTL.x, tempBR.y);
            bottomRight = new Point(tempBR.x, tempTL.y);
        }

        for (int i = 0; i < focusables.size(); i++) {
            if (focusables.get(i).getShape().intersects(topLeft.x - focusables.get(i).getXPos(), topLeft.y - focusables.get(i).getYPos(), bottomRight.x - topLeft.x, bottomRight.y - topLeft.y) && focusables.get(i).isUnit() && focusables.get(i).ally) {
                if (!changeInFocus) {
                    changeInFocus = true;
                }
                focusedUnits.add((Unit) focusables.get(i));
            }
        }

        for (int i = 0; i < focusables.size(); i++) {
            if (focusables.get(i).isInArea(new Point(e.getX() - (int) focusables.get(i).getXPos(), e.getY() - (int) focusables.get(i).getYPos())) && !changeInFocus && focusables.get(i).isUnit() && focusables.get(i).ally) {
                focusedUnits.clear();
                focusedUnits.add((Unit) focusables.get(i));
                changeInFocus = true;
            }
        }
        formUnits.setUnits(focusedUnits);
        if (!changeInFocus) {
            gameFocus = null;
            for (int i = 0; i < focusables.size(); i++) {
                if (focusables.get(i).isInArea(new Point(e.getX() - (int) focusables.get(i).getXPos(), e.getY() - (int) focusables.get(i).getYPos())) && !changeInFocus && !focusables.get(i).isUnit() && focusables.get(i).ally) {
                    gameFocus = focusables.get(i);
                }
            }
        } else {
            gameFocus = formUnits;
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
        if (gameFocus == null) {
            mouse1Press(e);
        } else {
            gameFocus.passInMousePressedEvent(e);
            if (passedBackInput) {
                mouse1Press(e);
                passedBackInput = false;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (gameFocus == null) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                shouldMoveUnits = true;
                movePoint = e.getPoint();
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                mouse1Release(e);
            }
        } else {
            gameFocus.passInMouseReleasedEvent(e);
            if (passedBackInput) {
                passedBackInput = false;
                mouse1Release(e);
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
