package Structures;

import main.MainClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CommandCentre extends Structure {

    private static BufferedImage[] image = new BufferedImage[6];
    public static BufferedImage buySupplyDepot;
    private static String filePath = "res/PATHHERE.png";
    
    public CommandCentre() {
        super();

    }

    public CommandCentre(double x, double y) {
        super(x, y);
        HP = 1;
        hpPerStage = 500;
        stages = 5;
        int[] xPoints2 = {-63, -33, -12, 5, 21, 42, 44, 56, 64, -28, -63};
        int[] yPoints2 = {-6, -32, -43, -52, -46, -32, -25, -17, 30, 47, 8};
        area = new Polygon(xPoints2, yPoints2, 11);
        try {
            image[0] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(0, 0, 107, 117);
            image[1] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(107, 0, 107, 117);
            image[2] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(215, 0, 129, 117);
            image[3] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(344, 0, 129, 117);
            image[4] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(480, 0, 129, 117);
            image[5] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(609, 0, 129, 117);

            buySupplyDepot = ImageIO.read(new File("res/BuySupplyDepot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void update(double time) {
        super.update(time);
//        timePassed += time;
//        if (timePassed > (2.25 / 1 * 1000)) {
//            if (curImage >= 5) {
//                curImage = 0;
//            } else {
//                curImage++;
//            }
//            timePassed = 0;
//        }

    }

    @Override
    public void drawGUI(Graphics g) {
        super.drawGUI(g);
        g.drawImage(buySupplyDepot, 85, 925, null);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image[curImage], (int) xPos - image[curImage].getWidth() / 2, (int) yPos - image[curImage].getHeight() / 2, null);
    }

    @Override
    public void passInMouseReleasedEvent(MouseEvent e) {
        if (MainClass.placingStruct) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                MainClass.focusables.add(new SupplyDepot(e.getX(), e.getY()));
                MainClass.numMinerals -= 300;
                MainClass.passedBackInput = false;
                MainClass.placingStruct = false;
            } else {
                MainClass.gameFocus = null;
                MainClass.passedBackInput = true;
            }
        } else {
            if (new Rectangle(85, 925, 100, 100).contains(e.getPoint())) {
                MainClass.placingStruct = true;
                MainClass.passedBackInput = false;
            } else {
                MainClass.passedBackInput = true;
            }
        }
    }

    @Override
    public void passInMousePressedEvent(MouseEvent e) {
        if (MainClass.placingStruct) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                MainClass.gameFocus = null;
                MainClass.passedBackInput = true;
            } else if (e.getButton() == MouseEvent.BUTTON1) {

            }
        } else {
            if (new Rectangle(85, 925, 100, 100).contains(e.getPoint())) {
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

}
