package Units;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Marine extends Unit {

    private static BufferedImage[][] image = new BufferedImage[16][14];//WIP
    private static String filePath = "res/MarineSprite.png";
    private int curImageX, curImageY;

    public Marine() {
        super();
    }

    public Marine(double x, double y) {
        super(x, y);
        curImageY = 0;
        curImageX = 0;
        spaceUnits = 1;
        maxVelocity = 20;
        int[] xPoints = {-19, 19, 19, -19};
        int[] yPoints = {-21, -21, 21, 21};
        clickArea = new Polygon(xPoints, yPoints, 4);
        int[] xPoints2 = {-15, 15, 15, -15};
        int[] yPoints2 = {-1, -1, 21, 21};
        area = new Polygon(xPoints2, yPoints2, 4);
        try {
            BufferedImage temp = ImageIO.read(new File(filePath));
            /*image[0][0] = temp.getSubimage(2, 8, 35, 42);
            image[1][0] = temp.getSubimage(38, 8, 38, 42);
            image[2][0] = temp.getSubimage(77, 8, 40, 42);
            image[3][0] = temp.getSubimage(118, 8, 39, 42);
            image[4][0] = temp.getSubimage(158, 8, 33, 42);
            image[5][0] = temp.getSubimage(192, 8, 37, 42);
            image[6][0] = temp.getSubimage(230, 8, 38, 42);
            image[7][0] = temp.getSubimage(269, 8, 36, 42);
            image[8][0] = temp.getSubimage(306, 8, 35, 42);
            image[0][1] = temp.getSubimage(2, 59, 40, 41);
            image[1][1] = temp.getSubimage(43, 59, 44, 41);
            image[2][1] = temp.getSubimage(88, 59, 44, 41);
            image[3][1] = temp.getSubimage(133, 59, 43, 41);
            image[4][1] = temp.getSubimage(177, 59, 40, 41);
            image[5][1] = temp.getSubimage(218, 59, 34, 41);
            image[6][1] = temp.getSubimage(253, 59, 35, 41);
            image[7][1] = temp.getSubimage(289, 59, 37, 41);
            image[8][1] = temp.getSubimage(327, 59, 39, 41); WIP*/
            for (int i = 9; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    AffineTransform flip = AffineTransform.getScaleInstance(-1, 1);
                    flip.translate(-image[i - image.length / 2][j].getWidth(null), 0);
                    AffineTransformOp op = new AffineTransformOp(flip, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                    image[i][j] = op.filter(image[i - image.length / 2][j], null);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getUnitType() {
        return "Marine";
    }

    @Override
    public void update(double time) {

    }

    @Override
    public void draw(Graphics g) {

    }
}
