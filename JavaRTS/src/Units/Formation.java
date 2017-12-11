package Units;

import main.Controllable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Formation implements Controllable {

    private ArrayList<Unit> units = new ArrayList<Unit>();
    private Unit leader;

    public Formation() {

    }

    public Formation(ArrayList<Unit> units) {
        this.units = units;
        this.leader = units.get(0);
        setVectors();
    }

    public void setVectors() {
        int i = 0;
        int count = 0;
        int n = 1;
        int numAdds = 0;
        int direction = 0;
        int x = 0;
        int y = 0;
        while (i < units.size()) {

            if (i == 0) {
                units.get(i).setFormationOffset(x, y);
            } else {
                if (direction == 0) {
                    y++;
                } else if (direction == 1) {
                    x++;
                } else if (direction == 2) {
                    y--;
                } else if (direction == 3) {
                    x--;
                }
                numAdds++;
                if (numAdds == n) {
                    if (direction == 3) {
                        direction = 0;
                    } else {
                        direction++;
                    }
                    numAdds = 0;
                    count++;
                    if (count == 2) {
                        count = 0;
                        n++;
                    }
                }
                units.get(i).setFormationOffset(x, y);
            }
            i++;
        }
    }

    //private boolean vectsOverlap(Point2D v1, Point2D v2, int s1, int s2){

    // }

    public void moveToLocation(Point p) {
        for (int i = 0; i < units.size(); i++) {
            units.get(i).setFocusPoint((int) (p.getX() + units.get(i).formationOffset.getX() * Unit.UNIT), (int) (p.getY() + units.get(i).formationOffset.getY() * Unit.UNIT));
        }
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

    }

    @Override
    public void passInKeyboardTyped(KeyEvent e) {

    }

    @Override
    public void passInKeyboardReleased(KeyEvent e) {

    }
}
