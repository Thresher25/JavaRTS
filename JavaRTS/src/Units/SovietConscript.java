package Units;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SovietConscript {

    private static BufferedImage[][] image = new BufferedImage[10][8];
    private int state, curImageX, curImageY, count;
    //Possibly add either an AI state, or add an AI class to each unit
    private AffineTransform flip = AffineTransform.getScaleInstance(-1, 1);
    AffineTransformOp op = new AffineTransformOp(flip,AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    private float xPos, yPos, angle;
    private double timePassed;
    private boolean movingRight = true;
    private static String filePath = "res/CivSprite.png";

    public SovietConscript(float x, float y) {
        
        try {
            BufferedImage temp = ImageIO.read(new File(filePath));
            for (int i = 0; i < image.length; i++) {
                for (int j = 0; j < image[0].length; j++) {
                    if (i < 9) {
                        image[i][j] = temp.getSubimage(i * 21, j * 32, 21, 32);
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
        state = 0;
        xPos = x;
        yPos = y;
        angle = 0;
        curImageX = 0;
        curImageY = 0;
        count = 0;
        timePassed = 0;
    }

    public void update(double t) {
        timePassed += t;
        if (timePassed >= (1.0 / 2.0 * 1000)) {
            if (curImageY >= 7) {
                curImageY = 0;
                if (curImageX >= 9) {
                    curImageX = 0;
                    movingRight = !movingRight;
                } else {
                    curImageX++;
                }
            } else {
                curImageY++;
            }
            timePassed -= (1.0 / 2.0 * 1000);
        } else {

        }

    }

    public void draw(Graphics g) {
        if(movingRight){
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image[curImageX][curImageY].getWidth(null), 0);
             op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
            image[curImageX][curImageY] = op.filter(image[curImageX][curImageY], null);
           
            g.drawImage(image[curImageX][curImageY], (int) xPos - image[curImageX][curImageY].getWidth() / 2, (int) yPos - image[curImageX][curImageY].getHeight() / 2, null);
        image[curImageX][curImageY] = op.filter(image[curImageX][curImageY], null);
        }else{
            
            g.drawImage(image[curImageX][curImageY], (int) xPos - image[curImageX][curImageY].getWidth() / 2, (int) yPos - image[curImageX][curImageY].getHeight() / 2, null);
        }
            
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
