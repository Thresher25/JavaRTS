package Structures;

import main.GameObject;
import main.Workable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Structure extends GameObject implements Workable {
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
        if (HP > hpPerStage * (stages)) {
            HP = hpPerStage * stages;
        }
        if(!finishedConstruction){
            //HP++;
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

    public boolean isFinishedConstruction() {
        return finishedConstruction;
    }

    @Override
    public boolean isWorkable(){
        return true;
    }

    @Override
    public void drawGUI(Graphics g) {
        g.drawImage(menuPiece, 0, 787, null);
    }

    @Override
    public void doWork(double workDone) {
        if(HP<(stages)*hpPerStage){
            HP+=workDone*10;
        }
    }

    @Override
    public boolean isHarvestable() {
        return false;
    }

    @Override
    public void setHarvestable(boolean set) {
        
    }

    @Override
    public String resourceType() {
        return null;
    }

}
