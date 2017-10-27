package Units;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SovietConscript {

    private static BufferedImage[][] image = new BufferedImage[20][8];
    private int state, curImageX, curImageY, count;
    //Possibly add either an AI state, or add an AI class to each unit
    private double timePassed, xPos, yPos, angle, maxVelocity;
    private boolean moving;
    private static String filePath = "res/CivSprite.png";

    public SovietConscript(double x, double y) {
        
        try {
            BufferedImage temp = ImageIO.read(new File(filePath));
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    if (i < image.length / 2) {
                        if (i < 9) {
                            image[i][j] = temp.getSubimage(i * 21, j * 32, 21, 32);
                        } else {
                            if (j < 4) {
                                image[i][j] = temp.getSubimage((j % 4) * 60, temp.getHeight() - 37 * 2, 60, 37);
                            } else {
                                image[i][j] = temp.getSubimage((j % 4) * 60, temp.getHeight() - 37, 60, 37);
                            }
                        }
                    } else {
                        AffineTransform flip = AffineTransform.getScaleInstance(-1, 1);
                        flip.translate(-image[i - 10][j].getWidth(null), 0);
                        AffineTransformOp op = new AffineTransformOp(flip, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                        image[i][j] = op.filter(image[i - 10][j], null);
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        state = 0;
        xPos = x;
        yPos = y;
        angle = 0;
        curImageX = 0;
        curImageY = 0;
        count = 0;
        timePassed = 0;
        maxVelocity = 15;
        moving = true;
    }

    public void update(double t) {//where t is time in milliseconds
        timePassed += t;
        calcAngle(600, 100);
        System.out.println(angle);
        //angle+=(t/1000*0.1)/16.0*2*Math.PI;
        if (angle > Math.PI * 2) {
            //    angle = 0;
        }
        calcCurImage();
        if (timePassed >= (1.0 / 2.0 * 1000)) {
            if (curImageY >= 7) {
                curImageY = 0;
            } else {
                curImageY++;
            }
            timePassed -= (1.0 / 2.0 * 1000);
        } else {

        }

        if (moving) {
            xPos += (t / 1000) * maxVelocity * Math.sin(angle);
            yPos += (t / 1000) * maxVelocity * Math.cos(angle);
        }
    }

    public void calcAngle(int x, int y) {//no work for q1,q3
        double a, o;
        if ((x < xPos && y < yPos) || (x > xPos && y > yPos)) {
            a = (double) x - xPos;
            o = (double) y - yPos;
        } else if (x < xPos && y > yPos) {//this no work
            a = (double) x - xPos;
            o = (double) y - yPos;
        } else if (x > xPos && y < yPos) {//this no work
            a = (double) x - xPos;
            o = (double) y - yPos;
        } else {
            a = 0;
            o = 0;
            System.out.println("A and O Problem B0SS");
        }
        angle = Math.atan2(o, a);
    }

    public void calcCurImage() {//make this so that the angle is in between the ranges: eg 15-25 deg = some num instead of 20-30deg
        if (angle >= 0.0 / 16.0 * Math.PI * 2.0 && angle < 1.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 8;
        } else if (angle >= 0.0 / 16.0 * Math.PI * 2.0 && angle < 1.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 7;
        } else if (angle >= 1.0 / 16.0 * Math.PI * 2.0 && angle < 2.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 6;
        } else if (angle >= 2.0 / 16.0 * Math.PI * 2.0 && angle < 3.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 5;
        } else if (angle >= 3.0 / 16.0 * Math.PI * 2.0 && angle < 4.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 4;
        } else if (angle >= 4.0 / 16.0 * Math.PI * 2.0 && angle < 5.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 3;
        } else if (angle >= 5.0 / 16.0 * Math.PI * 2.0 && angle < 6.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 2;
        } else if (angle >= 6.0 / 16.0 * Math.PI * 2.0 && angle < 7.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 1;
        } else if (angle >= 7.0 / 16.0 * Math.PI * 2.0 && angle < 8.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 0;
        } else if (angle >= 8.0 / 16.0 * Math.PI * 2.0 && angle < 9.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 10;
        } else if (angle >= 9.0 / 16.0 * Math.PI * 2.0 && angle < 10.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 11;
        } else if (angle >= 10.0 / 16.0 * Math.PI * 2.0 && angle < 11.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 12;
        } else if (angle >= 11.0 / 16.0 * Math.PI * 2.0 && angle < 12.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 13;
        } else if (angle >= 12.0 / 16.0 * Math.PI * 2.0 && angle < 13.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 14;
        } else if (angle >= 13.0 / 16.0 * Math.PI * 2.0 && angle < 14.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 15;
        } else if (angle >= 14.0 / 16.0 * Math.PI * 2.0 && angle < 15.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 16;
        } else if (angle >= 15.0 / 16.0 * Math.PI * 2.0 && angle < 16.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 17;
        } else if (angle == 16.0 / 16.0 * Math.PI * 2.0) {
            curImageX = 18;
        } else {
            System.out.println("Angle problem b0ss");
        }

    }

    public void draw(Graphics g) {
            g.drawImage(image[curImageX][curImageY], (int) xPos - image[curImageX][curImageY].getWidth() / 2, (int) yPos - image[curImageX][curImageY].getHeight() / 2, null);
    }
    
    public int getCurImageX(){
        return curImageX;
    }
    public int getCurImageY(){
        return curImageY;
    }
    
    public void setCurImageX(int num){
        this.curImageX = num;
    }
    
    public void setCurImageY(int num){
        this.curImageY = num;
    }

}
