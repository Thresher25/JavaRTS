package Units;

import javafx.geometry.Point2D;
import main.Controllable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Unit implements Controllable {

    static boolean shift;
    double xPos, yPos, angle, maxVelocity;
    int spaceUnits;//the smallest square with sidelength n(units) which contains the moveable unit (this is for formations)
    public static final int UNIT = 15;//a unit is 15 pixels;
    boolean moving;
    boolean inFormation = false;
    Polygon area;
    Point2D formationOffset = new Point2D(0, 0);
    ArrayList<Point> focusPoints = new ArrayList<Point>();

    public Unit() {
        xPos = 0;
        yPos = 0;
        angle = 0;
        moving = false;
        shift = false;
    }

    public Unit(double x, double y) {
        xPos = x;
        yPos = y;
        angle = 0;
        moving = false;
        shift = false;
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
    
    public Polygon getShape(){
        return area;
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

    public boolean isInArea(Point p) {
        return area.contains(p);
    }

    public void update(double time) {

    }
    
    public void draw(Graphics g){
        
    }

    public void move(double time) {
        if (moving) {
            xPos += (time / 1000) * maxVelocity * Math.cos(angle);
            yPos += (time / 1000) * maxVelocity * Math.sin(angle);
        }
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public double getAngle() {
        return angle;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    @Override
    public void passInMouseClickedEvent(MouseEvent e) {

    }

    @Override
    public void passInMousePressedEvent(MouseEvent e) {

    }

    @Override
    public void passInMouseReleasedEvent(MouseEvent e) {

    }

    @Override
    public void passInMouseEnteredEvent(MouseEvent e) {

    }

    @Override
    public void passInMouseExitedEvent(MouseEvent e) {

    }

    @Override
    public void passInKeyboardPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = true;
        }
    }

    @Override
    public void passInKeyboardTyped(KeyEvent e) {

    }

    @Override
    public void passInKeyboardReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = false;
        }
    }
}
