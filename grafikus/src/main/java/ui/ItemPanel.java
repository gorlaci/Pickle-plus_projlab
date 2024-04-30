package ui;

import model.*;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ItemPanel extends JPanel {
    private Item item;
    private BufferedImage image;
    boolean selected;

    public ItemPanel(Item item){
        this.item = item;
        selected = false;

        try {
            if(item instanceof BeerGlass){
                image = ImageIO.read(new File("valami"));
            }
            else if(item instanceof AirFresher){
                //image = ImageIO.read(new File());
            }
            else if(item instanceof Camembert){
                //image = ImageIO.read(new File());
            }
        /*else if(item instanceof FalseMask){
            image = ImageIO.read(new File());
        }*/
            else if(item instanceof Mask){
                //image = ImageIO.read(new File());
            }
        /*else if(item instanceof FalseSlideRule){
            image = ImageIO.read(new File());
        }*/
            else if(item instanceof SlideRule){
                //image = ImageIO.read(new File());
            }
        /*else if(item instanceof FalseTVSZ){
            image = ImageIO.read(new File());
        }*/
            else if(item instanceof TVSZ){
                //image = ImageIO.read(new File());
            }
            else if(item instanceof Rag){
                //image = ImageIO.read(new File());
            }
            else if(item instanceof Transistor){
                //image = ImageIO.read(new File());
            }
        } catch (IIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseListener(new ItemMouseListener());

        setPreferredSize(new Dimension(50,50));
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image != null){
            g.drawImage(image, 0, 0, this);
            if (selected) {
                g.setColor(Color.RED);
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public Item getItem(){
        return item;
    }


    public class ItemMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (isEnabled()) {
                selected = !selected;
                repaint();
            }
        }
    }
}
