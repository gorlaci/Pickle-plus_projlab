package ui;

import controller.Controller;
import model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ItemPanel extends JComponent {
    private final Item item;
    private BufferedImage image;

    public ItemPanel(Item item){
        this.item = item;
        String imagePath = Controller.getItemImage(item);

        try {
            image = ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setPreferredSize(new Dimension(50,50));
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0, 0, this);
            if(item instanceof IntervalItem){
                if(((IntervalItem) item).isActivated()){
                    g.setColor(new Color(0,255,0,80));
                    g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
                }
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
}
