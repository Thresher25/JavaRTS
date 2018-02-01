package Structures;

import Units.SCV;
import Units.SovietConscript;
import Units.Unit;
import main.MainClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Barracks extends Structure {

    private static BufferedImage[] image = new BufferedImage[7];
    private static String filePath = "res/PATHHERE.png";
    private int spawnTimer = 0;
    private static BufferedImage buySCV, buySoviet;

    public Barracks() {
        super();
    }

    public Barracks(double x, double y) {
        super(x, y);
        HP = 1;
        hpPerStage = 500;
        stages = 6;
        int[] xPoints2 = {-62, -16, 52, 62, 35, -29};
        int[] yPoints2 = {18, -54, -29, 25, 53, 50};
        area = new Polygon(xPoints2, yPoints2, 6);
        try {
            //image[0] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(0, 108, 96, 108);
            //image[1] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(96, 108, 96, 108);
            //image[2] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(215, 0, 129, 117);

            image[0] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(0, 0, 107, 117);
            image[1] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(107, 0, 107, 117);
            image[2] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(215, 0, 129, 117);
            image[3] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(362, 135, 138, 114);
            image[4] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(500, 135, 138, 114);
            image[5] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(638, 135, 138, 114);
            image[6] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(776, 135, 138, 114);
            buySCV = ImageIO.read(new File("res/buySCV.png"));
            buySoviet = ImageIO.read(new File("res/buySoviet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(double time) {
        super.update(time);

    }

    private void spawnUnit(String name) {
        Unit sov;
        if (name.equals("SCV")) {
            MainClass.numMinerals -= 150;
            sov = new SCV(xPos, yPos + 65);
        } else if (name.equals("Soviet")) {
            MainClass.numMinerals -= 250;
            MainClass.numVespene -= 25;
            sov = new SovietConscript(xPos, yPos + 65);
        } else {
            sov = new SCV(xPos, yPos + 65);
        }
        MainClass.focusables.add(sov);
    }
    
    public void spawnEUnit(){
        Unit sov;
        sov = new SovietConscript(xPos, yPos + 65);
        sov.setFocusPoint((int)(Math.random()*1000), (int)(Math.random()*1000));
        sov.setEnemy();
        MainClass.focusables.add(sov);
    }

    @Override
    public void drawGUI(Graphics g) {
        super.drawGUI(g);
        g.drawImage(buySCV, 85, 925, null);
        g.drawImage(buySoviet, 222, 925, null);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image[curImage], (int) xPos - image[curImage].getWidth() / 2, (int) yPos - image[curImage].getHeight() / 2, null);
    }

    @Override
    public void passInMouseReleasedEvent(MouseEvent e) {
        if (new Rectangle(85, 925, 100, 100).contains(e.getPoint())) {
            if (MainClass.curUnits < MainClass.numMaxUnits) {
                if (MainClass.numMinerals >= 150 && (MainClass.tStage == 4 || MainClass.tStage >= 11)) {
                    spawnUnit("SCV");
                }
            }
            MainClass.passedBackInput = false;
        } else if (new Rectangle(222, 925, 100, 100).contains(e.getPoint())) {
            if (MainClass.curUnits < MainClass.numMaxUnits && MainClass.numMinerals >= 250 && MainClass.numVespene >= 25 && (MainClass.tStage == 6 || MainClass.tStage == 7 || MainClass.tStage >= 11)) {
                spawnUnit("Soviet");
            }
            MainClass.passedBackInput = false;
        } else {
            MainClass.passedBackInput = true;
        }
    }

    @Override
    public void passInMousePressedEvent(MouseEvent e) {
        if (new Rectangle(85, 925, 100, 100).contains(e.getPoint())) {
            MainClass.passedBackInput = false;
        } else if (new Rectangle(222, 925, 100, 100).contains(e.getPoint())) {
            MainClass.passedBackInput = false;
        } else {
            if (this.isInArea(new Point(e.getX() - (int) this.getXPos(), e.getY() - (int) this.getYPos()))) {
                MainClass.passedBackInput = false;
            } else {
                MainClass.gameFocus = null;
                MainClass.passedBackInput = true;
            }

        }
    }

}
