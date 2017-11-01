package Units;

import main.Controllable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Unit implements Controllable {

    static boolean shift;
    double xPos, yPos, angle, maxVelocity;
    boolean moving;
    Polygon area;

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
    
    public Polygon getShape(){
        return area;
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
