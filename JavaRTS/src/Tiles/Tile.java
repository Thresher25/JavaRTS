package Tiles;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    public BufferedImage image;
    public static BufferedImage bufArray[] = new BufferedImage[256];
    public static int dimension = 32;
    public int xPos, yPos, height, tileID;
    public boolean walkable, constructable;

    public Tile(int x, int y, int h) {
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
        xPos = x;
        yPos = y;
        height = h;
    }

    public void draw(Graphics g) {
        g.drawImage(bufArray[tileID], xPos, yPos, null);
    }

    public void getSpecificTile(int x, int y, int ID) { //given the tile set (x,y) and a direction where the numpad numbers correspond to each tile in the set, set the specific tileID to be used for drawing
        switch (ID) {
            case 1:
                tileID = y * 16 + x + 32;
                break;
            case 2:
                tileID = y * 16 + x + 33;
                break;
            case 3:
                tileID = y * 16 + x + 34;
                break;
            case 4:
                tileID = y * 16 + x + 16;
                break;
            case 5:
                tileID = y * 16 + x + 17;
                break;
            case 6:
                tileID = y * 16 + x + 18;
                break;
            case 7:
                tileID = y * 16 + x;
                break;
            case 8:
                tileID = y * 16 + x + 1;
                break;
            case 9:
                tileID = y * 16 + x + 2;
                break;
            default:
                tileID = 189;
                break;
        }
    }

}
