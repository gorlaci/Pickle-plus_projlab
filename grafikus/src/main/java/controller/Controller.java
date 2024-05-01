package controller;

import model.*;
import ui.GameWindow;
import ui.MenuWindow;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Controller {
    public static List<Room> rooms = new ArrayList<>();
    public static List<Student> players = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();
    public static List<Cleaner> cleaners = new ArrayList<>();
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

    public static ArrayList<String> getItemAttributes(Item item){
        ArrayList<String> attributes = new ArrayList<>();
        if (item instanceof BeerGlass) {
            attributes.add("Beer Glass");
            attributes.add(((BeerGlass)item).isActivated() ? "Activated" : "Not activated");
            attributes.add("Remaining time: " + ((BeerGlass)item).getTimeRemaining());
        } else if (item instanceof AirFresher) {
            attributes.add("Air Freshener");
        } else if (item instanceof Camembert) {
            attributes.add("Camembert");
        } else if (item instanceof Mask) {
            attributes.add("Mask");
            attributes.add(((Mask)item).isActivated() ? "Activated" : "Not activated");
            attributes.add("Remaining time: " + ((Mask)item).getTimeRemaining());
            attributes.add("Next duration: " + ((Mask)item).getDuration());
        } else if (item instanceof SlideRule) {
            attributes.add("Slide Rule");
        } else if (item instanceof TVSZ) {
            attributes.add("TVSZ");
            attributes.add("Uses remaining: " + ((TVSZ)item).getUsesRemaining());
        } else if (item instanceof Rag) {
            attributes.add("Rag");
            attributes.add(((Rag)item).isActivated() ? "Activated" : "Not activated");
            attributes.add("Remaining time: " + ((Rag)item).getTimeRemaining());
        } else{
            attributes.add("Transistor");
            attributes.add(((Transistor)item).getPair() == null ? "Not Paired" : "Paired");
        }
        return attributes;
    }


    public static void startGame( int mapSize, int playerNumber ){

        menuWindow.dispose();

        rooms.clear();
        players.clear();
        teachers.clear();
        cleaners.clear();

        turnCounter = 0;
        if(mapSize == 0) initSmallMap();
        //else if(mapSize == 1) initMediumMap();
        //else initLargeMap();
        initPlayers(playerNumber);

        gameWindow = new GameWindow( players );
        gameWindow.setVisible(true);
    }

    private static void initSmallMap() {
        Room room = new Room(4, false, false, 1);
        giveRoomRandomColor(room);
        rooms.add(room);
        room = new Room(3, false, true, 0);
        giveRoomRandomColor(room);
        rooms.add(room);
        room = new Room(3, false, false, 0);
        giveRoomRandomColor(room);
        rooms.add(room);
        room = new Room(2, true, false, 0);
        giveRoomRandomColor(room);
        rooms.add(room);
        room = new Room(3, false, false, 0);
        giveRoomRandomColor(room);
        rooms.add(room);

        rooms.get(0).addNeighbour(rooms.get(1));
        rooms.get(0).addNeighbour(rooms.get(2));
        rooms.get(1).addNeighbour(rooms.get(0));
        rooms.get(1).addNeighbour(rooms.get(4));
        rooms.get(2).addNeighbour(rooms.get(0));
        rooms.get(2).addNeighbour(rooms.get(3));
        rooms.get(3).addNeighbour(rooms.get(1));
        rooms.get(3).addNeighbour(rooms.get(2));
        rooms.get(3).addNeighbour(rooms.get(4));
        rooms.get(4).addNeighbour(rooms.get(2));

        rooms.get(0).initItem(new BeerGlass(rooms.get(0), null, false, 3));
        rooms.get(1).initItem(new Transistor(rooms.get(1), null));
        rooms.get(2).initItem(new Rag(rooms.get(2), null, false, 3));
        rooms.get(2).initItem(new FalseTVSZ(rooms.get(2), null));
        rooms.get(2).initItem(new Camembert(rooms.get(2), null));
        rooms.get(3).initItem(new Transistor(rooms.get(3), null));
        rooms.get(4).initItem(new SlideRule(rooms.get(4), null));
        rooms.get(4).initItem(new Mask(rooms.get(4), null, false, 5) );

        Cleaner cleaner = new Cleaner(0, rooms.get(3));
        rooms.get(3).addPerson(cleaner);
        cleaners.add(cleaner);

        Teacher teacher = new Teacher(0, rooms.get(1));
        rooms.get(1).addPerson(teacher);
        teachers.add(teacher);
    }

    private static void initPlayers(int playerNumber) {
        for( int i = 0 ; i < playerNumber ; i++ ) {
            Student student = new Student(0, rooms.get(0));
            players.add(student);
            rooms.get(0).addPerson(student);
        }
    }


    private static int actionsRemaining = 3;
    private static int playerIdx = 0;

    public static int getActionsRemaining() { return actionsRemaining; }

    private static void nextPlayer(){
        actionsRemaining = 3;
        playerIdx = (playerIdx + 1) % players.size();
        if(playerIdx == 0) endTurn();
        if(players.isEmpty()){
            return;
        }
        gameWindow.showPlayer( players.get(playerIdx) );
    }

    private static int turnCounter = 0;
    private static final int MAX_TURNS = 50;

    private static void endTurn() {
        turnCounter++;
        if( turnCounter == MAX_TURNS ){
            gameOver(false);
        }
        for(Teacher teacher: teachers) {
            pickUpRandomItem(teacher);
            moveToRandomNeighbour(teacher);
        }
        for(Cleaner cleaner: cleaners) {
            moveToRandomNeighbour(cleaner);
        }
        for(Room room : rooms) room.timeElapsed(1);
        List<Student> playersCopy = new ArrayList<>(players);
        for( Student player : playersCopy ){
            if( isPlayerDead(player) ){
                System.out.println( "Dead: " + players.indexOf(player) );
                players.remove(player);
            }
        }
        if(players.isEmpty()){
            gameOver(false);
        }
    }

    public static void enterButtonPressed(){
        if( actionsRemaining >= 2 ){
            players.get(playerIdx).enterRoom( gameWindow.actPlayerPanel.doorSelected.getRoom() );
            actionsRemaining -= 2;
        }
        if( isPlayerDead(players.get(playerIdx)) ){
            players.remove(playerIdx);
            System.out.println("Dead: "  + playerIdx);
            if( players.isEmpty() ){
                gameOver(false);
                return;
            }
            playerIdx--;
            nextPlayer();
            gameWindow.reDraw();
            return;
        }
        if( actionsRemaining == 0 ){
            nextPlayer();
        }
        gameWindow.reDraw();
    }

    public static void pickUpButtonPressed(){
        players.get(playerIdx).addItem( gameWindow.actPlayerPanel.itemInRoomSelected.getItem() );
        actionsRemaining--;
        for( Item item : players.get(playerIdx).getItemsInHand() ){
            if( item instanceof SlideRule && !(item instanceof FalseSlideRule) ){
                gameOver(true);
            }
        }
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
        }
        gameWindow.reDraw();
    }

    public static void activateButtonPressed(){
        players.get(playerIdx).activateItem(gameWindow.actPlayerPanel.itemInHandSelected.getItem());
        if( isPlayerDead(players.get(playerIdx)) ){
            players.remove(playerIdx);
            System.out.println( "Dead: " + playerIdx );
            if( players.isEmpty() ){
                gameOver(false);
                return;
            }
            playerIdx--;
            nextPlayer();
            gameWindow.reDraw();
            return;
        }
        actionsRemaining--;
        if( actionsRemaining == 0 ){
            nextPlayer();
        }
        gameWindow.reDraw();
    }

    public static void endButtonPressed() {
        nextPlayer();
    }

    private static void gameOver(boolean win){
        gameWindow.gameEnded = true;
        JOptionPane.showMessageDialog(null, win ? "GG" : "BME");
        gameWindow.dispose();
        menuWindow.setVisible(true);
    }

    private static void pickUpRandomItem(Person person) {
        Room room = person.getLocation();
        if( !room.getItemsInRoom().isEmpty() ) {
            int idx = random.nextInt(room.getItemsInRoom().size());
            person.addItem(room.getItemsInRoom().get(idx));
        }
    }

    private static void moveToRandomNeighbour( Person person ){
        Room room = person.getLocation();
        int idx = random.nextInt( room.getNeighbours().size() );
        person.enterRoom( room.getNeighbours().get(idx) );
    }

    private static boolean isPlayerDead( Student player ){
        for( Person person : player.getLocation().getPeopleInRoom() ){
            if( person == player ){
                return false;
            }
        }
        return true;
    }
}
