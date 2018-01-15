package Structures;

import main.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Structure extends GameObject {
    protected int stages;
    protected float hpPerStage;
    static BufferedImage menuPiece;
    protected boolean finishedConstruction;
    protected int curImage = 0;
    public Structure() {
        super();
    }

    public Structure(double x, double y) {
        super(x, y);
        try {
            menuPiece = ImageIO.read(new File("res/PC Computer - StarCraft - General Rip/Menu Pieces/plistmap2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(double time){
        if(!finishedConstruction){
            HP++;
        }
        if(HP==hpPerStage*stages){
            finishedConstruction = true;
        }
        if(finishedConstruction){
            //curImage = stages;
            curImage = (int)(HP/hpPerStage);
        }else{
            curImage = (int)(HP/hpPerStage);
        }
    }
    
    @Override
    public boolean isWorkable(){
        return true;
    }

    @Override
    public void drawGUI(Graphics g) {
        g.drawImage(menuPiece, 0, 787, null);
    }

}
