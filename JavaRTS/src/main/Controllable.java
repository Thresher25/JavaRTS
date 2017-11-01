package main;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface Controllable {

    void passInMouseClickedEvent(MouseEvent e);

    void passInMousePressedEvent(MouseEvent e);

    void passInMouseReleasedEvent(MouseEvent e);

    void passInMouseEnteredEvent(MouseEvent e);

    void passInMouseExitedEvent(MouseEvent e);

    void passInKeyboardPressed(KeyEvent e);

    void passInKeyboardTyped(KeyEvent e);

    void passInKeyboardReleased(KeyEvent e);

}
