package Units;

import main.MainClass;
import main.Workable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SCV extends Unit {

    private static BufferedImage[][] image = new BufferedImage[16][2];
    private static String filePath = "res/SCV.png";
    private int curImageX, curImageY;
    private boolean working = false;
    private static double workPerTime = 0.85;
    private Workable objectWorkingOn;

    public SCV() {
        super();
    }

    public SCV(double x, double y) {
        super(x, y);
        HP = 150;
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
            image[0][0] = temp.getSubimage(2, 8, 35, 42);
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
            image[8][1] = temp.getSubimage(327, 59, 39, 41);
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
    public void setFocusPoint(int x, int y) {
        super.setFocusPoint(x, y);
        working = false;
        curImageY = 0;
    }

    public void setObjectWorkingOn(Workable obj) {
        objectWorkingOn = obj;
    }

    public Workable getObjectWorkingOn() {
        return objectWorkingOn;
    }

    @Override
    public void update(double time) {
        moveToFocus();
        calcCurImage();
        if (!moving) {
            if(working){
                curImageY = 1;
                objectWorkingOn.doWork(time / 1000 * workPerTime);
                if (objectWorkingOn.isHarvestable()) {
                    if (objectWorkingOn.resourceType().equals("Mineral")) {
                        MainClass.numMinerals += 5;
                    } else if (objectWorkingOn.resourceType().equals("Vespene")) {
                        MainClass.numVespene += 5;
                    }
                    objectWorkingOn.setHarvestable(false);
                }
            }else{
                curImageY = 0;
            }
            if (angle != Math.PI * 3 / 2 && !working) {
                if (angle > Math.PI * 3 / 2 || angle <= Math.PI / 2) {
                    if (angle < Math.PI * 3 / 2 + Math.PI / 64 && angle > Math.PI * 3 / 2) {
                        angle = Math.PI * 3 / 2;
                    } else {
                        angle -= (time / 1000 * 0.25) * Math.PI * 2;
                    }
                } else {
                    if (angle > Math.PI * 3 / 2 - Math.PI / 64 && angle < Math.PI * 3 / 2) {
                        angle = Math.PI * 3 / 2;
                    } else {
                        angle += (time / 1000 * 0.25) * Math.PI * 2;
                    }
                }
                if (angle < 0) {
                    angle += 2 * Math.PI;
                }
            }
        }
        super.move(time);
    }

    public void calcCurImage() {
        if (angle >= 0.0 / 16.0 * Math.PI * 2.0 && angle < 0.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 4;
        } else if (angle >= 0.5 / 16.0 * Math.PI * 2.0 && angle < 1.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 5;
        } else if (angle >= 1.5 / 16.0 * Math.PI * 2.0 && angle < 2.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 6;
        } else if (angle >= 2.5 / 16.0 * Math.PI * 2.0 && angle < 3.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 7;
        } else if (angle >= 3.5 / 16.0 * Math.PI * 2.0 && angle < 4.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 8;
        } else if (angle >= 4.5 / 16.0 * Math.PI * 2.0 && angle < 5.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 15;
        } else if (angle >= 5.5 / 16.0 * Math.PI * 2.0 && angle < 6.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 14;
        } else if (angle >= 6.5 / 16.0 * Math.PI * 2.0 && angle < 7.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 13;
        } else if (angle >= 7.5 / 16.0 * Math.PI * 2.0 && angle < 8.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 12;
        } else if (angle >= 8.5 / 16.0 * Math.PI * 2.0 && angle < 9.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 11;
        } else if (angle >= 9.5 / 16.0 * Math.PI * 2.0 && angle < 10.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 10;
        } else if (angle >= 10.5 / 16.0 * Math.PI * 2.0 && angle < 11.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 9;
        } else if (angle >= 11.5 / 16.0 * Math.PI * 2.0 && angle < 12.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 0;
        } else if (angle >= 12.5 / 16.0 * Math.PI * 2.0 && angle < 13.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 1;
        } else if (angle >= 13.5 / 16.0 * Math.PI * 2.0 && angle < 14.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 2;
        } else if (angle >= 14.5 / 16.0 * Math.PI * 2.0 && angle < 15.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 3;
        } else if (angle >= 15.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 4;
        } else {
            System.out.println("This shouldn't happen");
        }

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image[curImageX][curImageY], (int) xPos - image[curImageX][curImageY].getWidth() / 2, (int) yPos - image[curImageX][curImageY].getHeight() / 2, null);
    }

    @Override
    public String getUnitType() {
        return "SCV";
    }
    
    public void setWorkable(boolean b){
        working = b;
    }
}
