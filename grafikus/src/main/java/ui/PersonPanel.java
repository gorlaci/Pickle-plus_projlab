package ui;

import model.Person;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PersonPanel extends JPanel {
    public PersonPanel(Person person){
        setBackground(Color.YELLOW);
        setBorder(new EmptyBorder(4, 4, 4, 4));
        setSize(50, 50);
        setLayout(new BorderLayout());
        add(new JLabel("almafa"), BorderLayout.SOUTH);
    }
}
