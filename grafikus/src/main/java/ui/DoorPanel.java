package ui;

import controller.Controller;
import model.Room;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DoorPanel extends JPanel {

    private Room room;

    public DoorPanel(Room room){
        this.room = room;
        Color color = Controller.getRoomColor(room);
        setMinimumSize(new Dimension(50,80));
        setBackground(color);
    }

    public void setSelected(boolean selected) {
        if(selected) {
            setBorder(BorderFactory.createLineBorder(Color.WHITE));
        } else {
            setBorder(null);
        }
    }

    public Room getRoom() {
        return room;
    }
}
