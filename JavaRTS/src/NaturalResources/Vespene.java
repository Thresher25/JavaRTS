package NaturalResources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Vespene extends Resource {

    private BufferedImage image;
    private String filePath = "res/VespeneGeyser.png";

    public Vespene() {
        super();
    }

    public Vespene(double x, double y) {
        super(x, y);
        int[] xPoints = {-56, 56, 56, -56};
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
        return "Vespene";
    }

}
