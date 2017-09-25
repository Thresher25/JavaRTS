package Tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    public BufferedImage image;
    public static BufferedImage bufArray[] = new BufferedImage[256];
    public int xPos, yPos, height;
    public boolean walkable, constructable;

    public Tile() {
        try {
            BufferedImage img = ImageIO.read(new File("res/PathAndObjects.png"));
            for (int i = 0; i < Math.sqrt(bufArray.length); i++) {
                for (int j = 0; j < Math.sqrt(bufArray.length); j++) {
                    bufArray[i * 16 + j] = img.getSubimage(j * 32, i * 32, 32, 32);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
