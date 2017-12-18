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

    @Override
    public boolean isUnit() {
        return true;
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
    
    public abstract String getUnitType();
    
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

    public void addFocusPoint(int x, int y) {
        focusPoints.add(new Point(x, y));
    }

    public void calcAngle(int x, int y) {
        double dx = (double) x - xPos;
        double dy = -(yPos - (double) y);
        angle = Math.atan2(dy, dx);
        if (angle < 0) {
            angle += 2 * Math.PI;
        }
    }

    public void moveToFocus() throws ArrayIndexOutOfBoundsException {
        if (focusPoints.size() > 0) {
            if (-1 <= (xPos - focusPoints.get(0).x) && (xPos - focusPoints.get(0).x) <= 1 && -1 <= (yPos - focusPoints.get(0).y) && (yPos - focusPoints.get(0).y) <= 1) {
                xPos = focusPoints.get(0).x;
                yPos = focusPoints.get(0).y;
                focusPoints.remove(0);
                if (focusPoints.size() <= 0) {
                    moving = false;
                }
            } else {
                calcAngle(focusPoints.get(0).x, focusPoints.get(0).y);
                moving = true;
            }
        }
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
