package ui;

import controller.Controller;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class ItemPanel extends JComponent {
    private final Item item;
    private BufferedImage image;

    private final JPopupMenu attributes;

    public ItemPanel(Item item){
        this.item = item;
        String imagePath = Controller.getItemImage(item);
        attributes = new JPopupMenu();

        ArrayList<String> lines = Controller.getItemAttributes(item);
        attributes.add(lines.get(0));
        if(lines.size() > 1) {
            attributes.addSeparator();
            for(int i = 1; i < lines.size(); i++){
                attributes.add(lines.get(i));
            }
        }

        this.add(attributes);

        addMouseListener(new ItemAttributeMouseListener());

        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(50,50));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0, 0, this);
            if(item instanceof IntervalItem interval && interval.isActivated()){
                g.setColor(new Color(0,255,0,80));
                g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        }
    }

    public Item getItem(){
        return item;
    }

    public void setSelected(boolean selected) {
        if(selected) {
            setBorder(BorderFactory.createLineBorder(Color.BLACK));
        } else {
            setBorder(null);
        }
    }

    class ItemAttributeMouseListener extends MouseAdapter{
        @Override
        public void mouseEntered(MouseEvent e) {
            attributes.show(e.getComponent(), 0, e.getComponent().getHeight());
            attributes.setVisible(true);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            attributes.setVisible(false);
        }
    }
}
