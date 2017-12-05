package Units;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SovietConscript extends Unit {

    private static BufferedImage[][] image = new BufferedImage[17][8];
    private int curImageX, curImageY;
    //Possibly add either an AI state, or add an AI class to each unit
    private double timePassed;
    private boolean focused;
    private static String filePath = "res/CivSprite.png";

    public SovietConscript(double x, double y) {
        super(x, y);
        int[] xPoints = {-10, 11, 11, -10};
        int[] yPoints = {-16, -16, 16, 16};
        area = new Polygon(xPoints, yPoints, 4);
        try {
            BufferedImage temp = ImageIO.read(new File(filePath));
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    if (i < 16) {
                        if (i < 9) {
                            image[i][j] = temp.getSubimage(i * 21, j * 32, 21, 32);
                        } else {
                            AffineTransform flip = AffineTransform.getScaleInstance(-1, 1);
                            flip.translate(-image[16 - i][j].getWidth(null), 0);
                            AffineTransformOp op = new AffineTransformOp(flip, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                            image[i][j] = op.filter(image[16 - i][j], null);
                        }
                    } else {
                        if (j < 4) {
                            image[i][j] = temp.getSubimage((j % 4) * 60, temp.getHeight() - 37 * 2, 60, 37);
                        } else {
                            image[i][j] = temp.getSubimage((j % 4) * 60, temp.getHeight() - 37, 60, 37);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        xPos = x;
        yPos = y;
        angle = 0;
        curImageX = 0;
        curImageY = 0;
        timePassed = 0;
        maxVelocity = 15;
        moving = false;
        focused = false;
    }

    public void update(double t) {//where t is time in milliseconds
        super.update(t);
        timePassed += t;
        moveToFocus();
        /*angle+=(t/1000*0.3)/16.0*2*Math.PI;
        if(angle>Math.PI*2){
            angle=0;
        }*/
        calcCurImage();
        if (moving) {//move this into a separate method
            if (timePassed >= (1.0 / 2.0 * 1000)) {
                if (curImageY >= 7) {
                    curImageY = 0;
                } else {
                    curImageY++;
                }
                timePassed -= (1.0 / 2.0 * 1000);
            } else {

            }
        } else {
            curImageY = 2;
            //angle-=(t/1000*0.5)/16.0*2*Math.PI;
        }

        super.move(t);
    }

    public void moveToFocus() {
        if (focusPoints.size()>0) {
            if (-1 <= (xPos - focusPoints.get(0).x) && (xPos - focusPoints.get(0).x) <= 1 && -1 <= (yPos - focusPoints.get(0).y) && (yPos - focusPoints.get(0).y) <= 1) {
                xPos = focusPoints.get(0).x;
                yPos = focusPoints.get(0).y;
                    focusPoints.remove(0);
                    if(focusPoints.size()<=0){
                        moving=false;
                    }
            } else {
                calcAngle(focusPoints.get(0).x, focusPoints.get(0).y);
                moving = true;
            }
        }
    }
    
    public void addFocusPoint(int x, int y){
        focusPoints.add(new Point(x,y));
    }

    public void calcAngle(int x, int y) {
        double dx = (double) x - xPos;
        double dy = -(yPos - (double) y);
        angle = Math.atan2(dy, dx);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
    }

    public boolean getNumFocusPoints() {
        return focusPoints.size() <= 0;
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
            curImageX = 9;
        } else if (angle >= 5.5 / 16.0 * Math.PI * 2.0 && angle < 6.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 10;
        } else if (angle >= 6.5 / 16.0 * Math.PI * 2.0 && angle < 7.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 11;
        } else if (angle >= 7.5 / 16.0 * Math.PI * 2.0 && angle < 8.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 12;
        } else if (angle >= 8.5 / 16.0 * Math.PI * 2.0 && angle < 9.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 13;
        } else if (angle >= 9.5 / 16.0 * Math.PI * 2.0 && angle < 10.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 14;
        } else if (angle >= 10.5 / 16.0 * Math.PI * 2.0 && angle < 11.5 / 16.0 * Math.PI * 2.0) {
            curImageX = 15;
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

    @Override
    public void passInMouseReleasedEvent(MouseEvent e) {
        if (shift) {
            addFocusPoint(e.getX(), e.getY());
        } else {
            setFocusPoint(e.getX(), e.getY());
        }
    }

}
