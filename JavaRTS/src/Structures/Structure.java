package Structures;

import main.GameObject;

public abstract class Structure extends GameObject {
    protected int stages;
    protected float hpPerStage;
    protected boolean finishedConstruction;
    protected int curImage = 0;
    public Structure() {
        super();
    }

    public Structure(double x, double y) {
        super(x, y);
    }
    
    @Override
    public void update(double time){
        HP++;
        if(HP==hpPerStage*stages){
            finishedConstruction = true;
        }
        if(finishedConstruction){
            curImage = stages;
        }else{
            curImage = (int)(HP/hpPerStage);
        }
    }

}
