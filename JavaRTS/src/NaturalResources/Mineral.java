package NaturalResources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Mineral extends Resource {

    private BufferedImage image;
    private String filePath = "res/Minerals.png";

    public Mineral() {
        super();
    }

    public Mineral(double x, double y) {
        super(x, y);
        int[] xPoints = {-31, 31, 31, -31};
        int[] yPoints = {-26, -26, 26, 26};
        area = new Polygon(xPoints, yPoints, 4);
        HP = 250;
        try {
            image = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) xPos - image.getWidth() / 2, (int) yPos - image.getHeight() / 2, null);
    }

    @Override
    public String resourceType() {
        return "Mineral";
    }

}
