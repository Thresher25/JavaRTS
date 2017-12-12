package Structures;

import main.Controllable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Structure implements Controllable {

    static boolean shift;
    double xPos, yPos, angle;
    boolean moving;
    Polygon area;

    public Structure() {
        xPos = 0;
        yPos = 0;
        angle = 0;
        shift = false;
    }

    public Structure(double x, double y) {
        xPos = x;
        yPos = y;
        angle = 0;
        shift = false;
    }

    public boolean isInArea(Point p) {
        return area.contains(p);
    }

    public Polygon getShape(){
        return area;
    }
    
    public abstract void update(double time);
    
    public abstract void draw(Graphics g);
    
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

    }

    @Override
    public void passInKeyboardTyped(KeyEvent e) {

    }

    @Override
    public void passInKeyboardReleased(KeyEvent e) {

    }
}
