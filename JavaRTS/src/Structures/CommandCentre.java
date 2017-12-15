package Structures;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CommandCentre extends Structure {

    private static BufferedImage[] image = new BufferedImage[6];
    private static String filePath = "res/PATHHERE.png";
    private int curImage = 0;

    public CommandCentre() {
        super();

    }

    public CommandCentre(double x, double y) {
        super(x, y);
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
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void update(double time) {
        timePassed += time;
        if (timePassed > (2.25 / 1 * 1000)) {
            if (curImage >= 5) {
                curImage = 0;
            } else {
                curImage++;
            }
            timePassed = 0;
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image[curImage], (int) xPos - image[curImage].getWidth() / 2, (int) yPos - image[curImage].getHeight() / 2, null);
    }
}
