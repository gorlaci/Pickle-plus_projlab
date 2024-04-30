package ui;

import controller.Controller;
import model.Item;
import model.Person;
import model.Room;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
    private Student student;

    JPanel doorsPanel = new JPanel();
    JPanel peopleInRoomPanel = new JPanel();
    JPanel itemsInRoomPanel = new JPanel();
    JPanel itemsInHandPanel = new JPanel();

    JPanel statusPanel = new JPanel();

    JButton enterButton = new JButton("Enter");
    JButton pickUpButton = new JButton("Pick Up");
    JButton dropButton = new JButton("Drop");
    JButton activateButton = new JButton("Activate");

    public PlayerPanel( Student student ){
        setBackground(Color.blue);
        this.student = student;

        Color roomColor = Controller.getRoomColor( student.getLocation() );

        JPanel buttonsPanel = new JPanel();

        doorsPanel.setBackground(roomColor);
        peopleInRoomPanel.setBackground(roomColor);
        itemsInRoomPanel.setBackground(roomColor);

        itemsInHandPanel.setBackground( Color.lightGray );
        buttonsPanel.setBackground(Color.lightGray);
        statusPanel.setBackground(Color.lightGray);

        buttonsPanel.add(enterButton);
        buttonsPanel.add(pickUpButton);
        buttonsPanel.add(dropButton);
        buttonsPanel.add(activateButton);

        for(Room neighbour : student.getLocation().getNeighbours()){
            doorsPanel.add( new DoorPanel(neighbour) );
        }
        for(Person personInRoom : student.getLocation().getPeopleInRoom()){
            peopleInRoomPanel.add( new PersonPanel(personInRoom) );
        }
        for(Item itemInRoom : student.getLocation().getItemsInRoom()){
            itemsInRoomPanel.add( new ItemPanel(itemInRoom) );
        }
        for( Item itemInHand : student.getItemsInHand() ){
            itemsInHandPanel.add( new ItemPanel(itemInHand));
        }

        statusPanel.add( new JLabel("Ide kerül majd a státusz"));

        setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

        JPanel topPanel = new JPanel();
        topPanel.setLayout( new BoxLayout(topPanel,BoxLayout.X_AXIS) );
        topPanel.add(doorsPanel);
        topPanel.add(peopleInRoomPanel);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout( new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel.add(itemsInRoomPanel);
        middlePanel.add(itemsInRoomPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(buttonsPanel);
        bottomPanel.add(statusPanel);

        add( topPanel );
        add(middlePanel);
        add(bottomPanel);

    }
}
