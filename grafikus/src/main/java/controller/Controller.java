package controller;

import model.*;
import ui.GameWindow;
import ui.MenuWindow;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Controller {
    public static List<Room> rooms = new ArrayList<>();
    public static List<Student> players = new ArrayList<>();
    public static MenuWindow menuWindow = new MenuWindow();
    public static GameWindow gameWindow;

    public static void main(String[] args){
        menuWindow.setVisible(true);
    }

    private static final HashMap<Room,Color> roomColors = new HashMap<>();
    public static Color getRoomColor( Room room ){
        return roomColors.get(room);
    }

    private static final Random random = new Random();
    private static void giveRoomRandomColor( Room room ){
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        roomColors.put( room, new Color(r,g,b) );
    }

    public static String getPersonImage(Person person) {
        if(person instanceof Teacher) return "resources"+ File.separator+"teacher.png";
        else if(person instanceof Cleaner) return "resources"+ File.separator+"cleaner.png";
        else {
            return "resources"+ File.separator+"player"+(players.indexOf((Student)person)+1)+".png";
        }
    }
    public static String getItemImage(Item item) {
        if (item instanceof BeerGlass) {
            return "resources" + File.separator + "beerglass.png";
        } else if (item instanceof AirFresher) {
            return "resources" + File.separator + "airfresher.png";
        } else if (item instanceof Camembert) {
            return "resources" + File.separator + "camembert.png";
        } else if (item instanceof Mask) {
            return "resources" + File.separator + "mask.png";
        } else if (item instanceof SlideRule) {
            return "resources" + File.separator + "sliderule.png";
        } else if (item instanceof TVSZ) {
            return "resources" + File.separator + "tvsz.png";
        } else if (item instanceof Rag) {
            return "resources" + File.separator + "rag.png";
        } else return "resources" + File.separator + "transistor.png";
    }


    public static void startGame( int mapSize, int playerNumber ){
        menuWindow.dispose();
        Room room = new Room(5, false, false, 0);
        rooms.add(room);
        giveRoomRandomColor(room);


        Room r2 = new Room(5,false,false, 0);
        rooms.add(r2);
        giveRoomRandomColor(r2);

        room.addNeighbour(r2);


        for( int i = 0 ; i < playerNumber ; i++ ) {
            Student student = new Student(0, room);
            players.add(student);
            room.addPerson(student);
        }

        Cleaner cleaner = new Cleaner(0, room);
        room.addPerson(cleaner);

        room.initItem( new SlideRule(room, null));
        room.initItem( new BeerGlass(room, null, false, 5) );

        players.get(0).initItem( new Mask(room, players.get(0), false, 5) );

        gameWindow = new GameWindow( players );
        gameWindow.setVisible(true);
    }

    private static int actionsRemaining = 3;
    private static int playerIdx = 0;

    private static void nextPlayer(){
        actionsRemaining = 3;
        playerIdx = (playerIdx + 1) % players.size();
        gameWindow.showPlayer( players.get(playerIdx) );
    }

    public static void enterButtonPressed(){
        if( actionsRemaining >= 2 ){
            players.get(playerIdx).enterRoom( gameWindow.actPlayerPanel.doorSelected.getRoom() );
            actionsRemaining -= 2;
        }
        if( actionsRemaining == 0 ){
            nextPlayer();
        }
        gameWindow.reDraw();
    }

    public static void pickUpButtonPressed(){
        players.get(playerIdx).addItem( gameWindow.actPlayerPanel.itemInRoomSelected.getItem() );
        actionsRemaining--;
        if( actionsRemaining == 0 ){
            nextPlayer();
        }
        gameWindow.reDraw();
    }

    public static void dropButtonPressed(){
        players.get(playerIdx).dropItem(gameWindow.actPlayerPanel.itemInHandSelected.getItem());
        actionsRemaining--;
        if( actionsRemaining == 0 ){
            nextPlayer();
            System.out.println("drop endturn");
        }
        gameWindow.reDraw();
    }

    public static void activateButtonPressed(){
        players.get(playerIdx).activateItem(gameWindow.actPlayerPanel.itemInHandSelected.getItem());
        actionsRemaining--;
        if( actionsRemaining == 0 ){
            nextPlayer();
        }
        gameWindow.reDraw();
    }

    public static void endButtonPressed() {
        nextPlayer();
    }
}
