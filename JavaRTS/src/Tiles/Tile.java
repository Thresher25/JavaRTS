package Tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    public BufferedImage image;
    public static BufferedImage bufArray[] = new BufferedImage[9];
    public int xPos, yPos, tileIdentifier;

    public Tile() {
        try {
            BufferedImage img = ImageIO.read(new File("res/GrassTile.png"));
            bufArray[0] = img.getSubimage(0, 0, 32, 32);
            bufArray[1] = img.getSubimage(32, 0, 32, 32);
            bufArray[2] = img.getSubimage(64, 0, 32, 32);
            bufArray[3] = img.getSubimage(0, 32, 32, 32);
            bufArray[4] = img.getSubimage(32, 32, 32, 32);
            bufArray[5] = img.getSubimage(64, 32, 32, 32);
            bufArray[6] = img.getSubimage(0, 64, 32, 32);
            bufArray[7] = img.getSubimage(32, 64, 32, 32);
            bufArray[8] = img.getSubimage(64, 64, 32, 32);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
