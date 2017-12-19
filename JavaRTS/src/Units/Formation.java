package Units;

import main.Controllable;
import main.MainClass;
import main.Workable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class Formation implements Controllable {

    private Vector<Unit> units = new Vector<Unit>();
    private Unit leader;

    public Formation() {

    }

    public Formation(Vector<Unit> units) {
        this.units = units;
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
                this.leader = units.get(i);
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
            if(units.get(i).getUnitType().equals("SCV")){
                boolean workable = false;
                for(int j=0;j<MainClass.focusables.size();j++){
                    if(MainClass.focusables.get(j).isInArea(new Point((int)p.getX() - (int) MainClass.focusables.get(j).getXPos(), (int)p.getY() - (int) MainClass.focusables.get(j).getYPos())) && MainClass.focusables.get(j).isWorkable()){
                        SCV temp = (SCV) units.get(i);
                        temp.setWorkable(true);
                        temp.setObjectWorkingOn((Workable) MainClass.focusables.get(j));
                    }
                }
            }
        }
    }

    public void setUnits(Vector<Unit> u) {
        this.units = u;
        setVectors();
    }

    public void removeUnits(Vector<Unit> u) {
        units.removeAll(u);
        setVectors();
    }

    public void addUnits(Vector<Unit> u) {
        units.addAll(u);
        setVectors();
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

    @Override
    public void drawGUI(Graphics g) {

    }
}
