package ui;

import controller.Controller;
import model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DoorPanel extends JPanel {

    private Room room;
    private Color color;

    public DoorPanel(Room room){
        this.room = room;
        color = Controller.getRoomColor(room);
        setPreferredSize(new Dimension(50,80));
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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            BufferedImage image = ImageIO.read(new File("resources"+ File.separator+"door.png"));
            g.drawImage(image, 0, 0, this);
            g.setColor(color);
            g.fillRect(0, 0, getWidth(), getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
