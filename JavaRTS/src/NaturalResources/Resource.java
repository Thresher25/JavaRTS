package NaturalResources;

import main.GameObject;
import main.Workable;

import java.awt.*;

public abstract class Resource extends GameObject implements Workable {

    int resourceLeft, maxResource;

    double workPerUnit = 25.0;
    boolean harvestable = false;

    public Resource() {
        super();
    }

    public Resource(double x, double y) {
        super(x, y);

    }

    @Override
    public void update(double time) {

    }


    @Override
    public void draw(Graphics g) {

    }
    
    @Override
    public boolean isWorkable(){
        return true;
    }

    @Override
    public boolean isHarvestable() {
        return harvestable;
    }

    @Override
    public void setHarvestable(boolean set) {
        harvestable = set;
    }

    @Override
    public void doWork(double workDone) {
        workPerUnit -= workDone;
        if (workPerUnit <= 0) {
            workPerUnit += 25.0;
            harvestable = true;
        }
    }

    @Override
    public String resourceType() {
        return "Res";
    }
    
}
