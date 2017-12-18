package NaturalResources;

import main.GameObject;

import java.awt.*;

public abstract class Resource extends GameObject {

    int resourceLeft, maxResource;

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
    
}
