package Units;

import javafx.geometry.Point2D;
import main.GameObject;

import java.awt.*;
import java.util.Vector;

public abstract class Unit extends GameObject {

    double angle, maxVelocity;
    int spaceUnits;//the smallest square with sidelength n(units) which contains the moveable unit (this is for formations)
    public static final int UNIT = 15;//a unit is 15 pixels;
    boolean moving;
    boolean inFormation = false;
    Polygon clickArea;
    Point2D formationOffset = new Point2D(0, 0);
    Vector<Point> focusPoints = new Vector<Point>();

    public Unit() {
        super();
        angle = 0;
        moving = false;
    }

    public Unit(double x, double y) {
        super(x, y);
        angle = 0;
        moving = false;
    }

    public Point2D getFormationOffset() {
        return formationOffset;
    }

    public void setFormationOffset(double x, double y) {
        formationOffset = new Point2D(x, y);
    }

    public boolean isInFormation() {
        return inFormation;
    }

    public void setInFormation(boolean b) {
        inFormation = b;
    }

    public int getSpaceUnits(){
        return spaceUnits*UNIT;
    }
    
    public Point getFocusPoint(){
        if(focusPoints.size()==0){
            return new Point(10000,10000);
        }
        return focusPoints.get(0);
    }
    
    public void setFocusPoint(int x, int y) {
        focusPoints.clear();
        focusPoints.add(new Point(x, y));
    }

    @Override
    public boolean isInArea(Point p) {
        return clickArea.contains(p);
    }

    public void move(double time) {
        if (moving) {
            xPos += (time / 1000) * maxVelocity * Math.cos(angle);
            yPos += (time / 1000) * maxVelocity * Math.sin(angle);
        }
    }

    public double getAngle() {
        return angle;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

}
