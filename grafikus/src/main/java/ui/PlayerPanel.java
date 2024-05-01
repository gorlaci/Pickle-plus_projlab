package ui;

import controller.Controller;
import model.Item;
import model.Person;
import model.Room;
import model.Student;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayerPanel extends JPanel {
    private final Student student;
    private final String name;

    private final JPanel doorsPanel = new JPanel();
    private final JPanel peopleInRoomPanel = new JPanel();
    private final JPanel itemsInRoomPanel = new JPanel();
    private final JPanel itemsInHandPanel = new JPanel();

    public DoorPanel doorSelected = null;
    public ItemPanel itemInRoomSelected = null;
    public ItemPanel itemInHandSelected = null;

    JPanel statusPanel = new JPanel();

    JButton enterButton = new JButton("Enter");
    JButton pickUpButton = new JButton("Pick Up");
    JButton dropButton = new JButton("Drop");
    JButton activateButton = new JButton("Activate");

    public PlayerPanel( Student student, String name){
        this.student = student;
        this.name = name;

        Color roomColor = Controller.getRoomColor( student.getLocation() );

        JPanel buttonsPanel = new JPanel();

        doorsPanel.setBackground(roomColor);
        peopleInRoomPanel.setBackground(roomColor);
        itemsInRoomPanel.setBackground(roomColor);

        itemsInHandPanel.setBackground( Color.lightGray );
        buttonsPanel.setBackground(Color.lightGray);
        statusPanel.setBackground(Color.lightGray);

        enterButton.addActionListener(e -> Controller.enterButtonPressed());
        pickUpButton.addActionListener(e -> Controller.pickUpButtonPressed());
        dropButton.addActionListener(e -> Controller.dropButtonPressed());
        activateButton.addActionListener(e -> Controller.activateButtonPressed());

        enterButton.setEnabled(false);
        pickUpButton.setEnabled(false);
        dropButton.setEnabled(false);
        activateButton.setEnabled(false);

        buttonsPanel.add(enterButton);
        buttonsPanel.add(pickUpButton);
        buttonsPanel.add(dropButton);
        buttonsPanel.add(activateButton);

        Border border = BorderFactory.createLineBorder(Color.BLACK);

        doorsPanel.setBorder(border);
        peopleInRoomPanel.setBorder(border);
        itemsInRoomPanel.setBorder(border);
        itemsInHandPanel.setBorder(border);
        buttonsPanel.setBorder(border);
        statusPanel.setBorder(border);

        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));

        addSmallPanels();

        setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

        JPanel topPanel = new JPanel();
        topPanel.setLayout( new BoxLayout(topPanel,BoxLayout.X_AXIS) );
        topPanel.add(doorsPanel);
        topPanel.add(peopleInRoomPanel);

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout( new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        middlePanel.add(itemsInRoomPanel);
        middlePanel.add(itemsInHandPanel);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.add(buttonsPanel);
        bottomPanel.add(statusPanel);

        add(topPanel);
        add(middlePanel);
        add(bottomPanel);

        doorsPanel.setSize( 400, 200 );
        peopleInRoomPanel.setSize( 400, 200 );
        itemsInRoomPanel.setSize( 400, 200 );
        itemsInHandPanel.setSize(400,200);

    }

    private void addSmallPanels(){
        statusPanel.add( new JLabel(name));
        statusPanel.add(new PersonPanel(student));
        int stun=student.getStunRemaining();
        if(stun > 0 ) statusPanel.add(new JLabel("Stunned for "+stun+" turns"));
        statusPanel.add( new JLabel("Room capacity: "+student.getLocation().getCapacity()));
        statusPanel.add( new JLabel("Room stickiness: "+student.getLocation().getStickiness()));
        if(student.getLocation().isGas()) statusPanel.add( new JLabel("Room is gassed"));
        if(student.getLocation().isCurseActive()) statusPanel.add( new JLabel("Room is cursed right now"));

        JButton endButton = new JButton("End Turn");
        endButton.addActionListener(e -> Controller.endButtonPressed());
        statusPanel.add( endButton );

        statusPanel.add( new JLabel(Controller.getTurnsLeft()+" turns left."));
        statusPanel.add( new JLabel("Actions remaining: "+Controller.getActionsRemaining()));

        for(Room neighbour : student.getLocation().getNeighbours()){
            DoorPanel doorPanel = new DoorPanel(neighbour);
            doorPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    DoorPanel lastSelected = doorSelected;
                    doorSelected = doorPanel;
                    if(doorSelected == lastSelected) {
                        doorSelected = null;
                        lastSelected.setSelected(false);
                        enterButton.setEnabled(false);
                        return;
                    }
                    enterButton.setEnabled(true);
                    doorSelected.setSelected(true);
                    if(lastSelected != null) lastSelected.setSelected(false);
                }
            });
            doorsPanel.add( doorPanel );
        }
        for(Person personInRoom : student.getLocation().getPeopleInRoom()){
            if( personInRoom != student ){
                peopleInRoomPanel.add( new PersonPanel(personInRoom) );
            }
        }
        for(Item itemInRoom : student.getLocation().getItemsInRoom()){
            ItemPanel itemPanel = new ItemPanel(itemInRoom);
            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    ItemPanel lastSelected = itemInRoomSelected;
                    itemInRoomSelected = itemPanel;
                    if(itemInRoomSelected == lastSelected) {
                        itemInRoomSelected = null;
                        lastSelected.setSelected(false);
                        pickUpButton.setEnabled(false);
                        return;
                    }
                    pickUpButton.setEnabled(true);
                    itemInRoomSelected.setSelected(true);
                    if(lastSelected != null) lastSelected.setSelected(false);
                }
            });
            itemsInRoomPanel.add(itemPanel);
        }
        for( Item itemInHand : student.getItemsInHand() ){
            ItemPanel itemPanel = new ItemPanel(itemInHand);
            itemPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    ItemPanel lastSelected = itemInHandSelected;
                    itemInHandSelected = itemPanel;
                    if(itemInHandSelected == lastSelected) {
                        itemInHandSelected = null;
                        lastSelected.setSelected(false);
                        activateButton.setEnabled(false);
                        dropButton.setEnabled(false);
                        return;
                    }
                    activateButton.setEnabled(true);
                    dropButton.setEnabled(true);
                    itemInHandSelected.setSelected(true);
                    if(lastSelected != null) lastSelected.setSelected(false);
                }
            });
            itemsInHandPanel.add(itemPanel);
        }
    }

    public void reDraw(){
        doorsPanel.removeAll();
        peopleInRoomPanel.removeAll();
        itemsInRoomPanel.removeAll();
        itemsInHandPanel.removeAll();
        statusPanel.removeAll();

        doorSelected = null;
        itemInRoomSelected = null;
        itemInHandSelected = null;

        enterButton.setEnabled(false);
        pickUpButton.setEnabled(false);
        dropButton.setEnabled(false);
        activateButton.setEnabled(false);

        addSmallPanels();

        Color roomColor = Controller.getRoomColor(student.getLocation());
        doorsPanel.setBackground(roomColor);
        peopleInRoomPanel.setBackground(roomColor);
        itemsInRoomPanel.setBackground(roomColor);
    }
}
