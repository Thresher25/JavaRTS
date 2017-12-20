package Structures;

import Units.SCV;
import Units.SovietConscript;
import Units.Unit;
import main.MainClass;

import javax.imageio.ImageIO;
import java.awt.*;
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
            buySCV = ImageIO.read(new File("res/BuildingsSprite.png"));
            buySoviet = ImageIO.read(new File("res/BuildingsSprite.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(double time) {
        super.update(time);
//        timePassed += time;
        spawnTimer += time;
        if (spawnTimer >= 10 / 1 * 1000) {
            spawnTimer = 0;
            Unit sov;
            if (Math.random() > 0.5) {
                sov = new SovietConscript(xPos, yPos + 65);
            } else {
                sov = new SCV(xPos, yPos + 65);
            }
            sov.setFocusPoint((int) (Math.random() * 1000 + 10), (int) (Math.random() * 1000 + 10));
            MainClass.focusables.add(sov);
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image[curImage], (int) xPos - image[curImage].getWidth() / 2, (int) yPos - image[curImage].getHeight() / 2, null);
    }
}
