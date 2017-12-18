package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class GameObject implements Controllable {

    protected static boolean shift;
    protected double xPos, yPos;
    protected Polygon area;
    protected float HP;
    protected double timePassed = 0;

    public GameObject() {
        xPos = 0;
        yPos = 0;
    }

    public GameObject(double x, double y) {
        xPos = x;
        yPos = y;
    }
    
    public boolean isWorkable(){
        return false;
    }

    public boolean isUnit() {
        return false;
    }
    
    public float getHP(){
        return HP;
    }
    
    public void addHP(float hp){
        HP+=hp;
    }
    
    public void removeHP(float hp){
        HP-=hp;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public abstract void update(double time);

    public abstract void draw(Graphics g);

    public boolean isInArea(Point p) {
        return area.contains(p);
    }

    public Polygon getShape() {
        return area;
    }

    public void passInMouseClickedEvent(MouseEvent e) {

    }

    public void passInMousePressedEvent(MouseEvent e) {

    }

    public void passInMouseReleasedEvent(MouseEvent e) {

    }

    public void passInMouseEnteredEvent(MouseEvent e) {

    }

    public void passInMouseExitedEvent(MouseEvent e) {

    }

    public void passInKeyboardPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = true;
        }
    }

    public void passInKeyboardTyped(KeyEvent e) {

    }

    public void passInKeyboardReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
            shift = false;
        }
    }
}
