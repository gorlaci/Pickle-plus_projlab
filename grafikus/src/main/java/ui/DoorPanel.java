package ui;

import controller.Controller;
import model.Room;

import javax.swing.*;
import java.awt.*;

public class DoorPanel extends JPanel {

    public DoorPanel( Room room ){
        Color color = Controller.getRoomColor(room);
        setMinimumSize(new Dimension(50,80));
        setBackground(color);
    }
}
