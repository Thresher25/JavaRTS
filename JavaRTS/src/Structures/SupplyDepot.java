package Structures;

import main.MainClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SupplyDepot extends Structure {

    private static BufferedImage[] image = new BufferedImage[5];
    private static String filePath = "res/BuildingsSprites.png";

    public SupplyDepot() {
        super();
    }

    public SupplyDepot(double x, double y) {
        super(x, y);
        HP = 1;
        hpPerStage = 100;
        stages = 4;
        MainClass.numMaxUnits += 10;//TODO fix this its ugly
        int[] xPoints2 = {-63, -33, -12, 5, 21, 42, 44, 56, 64, -28, -63};
        int[] yPoints2 = {-6, -32, -43, -52, -46, -32, -25, -17, 30, 47, 8};
        area = new Polygon(xPoints2, yPoints2, 11);
        try {
            image[0] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(19, 267, 63, 50);
            image[1] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(71, 264, 63, 50);
            image[2] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(142, 262, 63, 50);
            image[3] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(213, 251, 94, 75);
            image[4] = ImageIO.read(new File("res/BuildingsSprite.png")).getSubimage(309, 250, 96, 77);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(double time) {
        super.update(time);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image[curImage], (int) xPos - image[curImage].getWidth() / 2, (int) yPos - image[curImage].getHeight() / 2, null);
    }
}
