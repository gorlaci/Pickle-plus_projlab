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
    private static final List<Room> rooms = new ArrayList<>();
    private static final List<Student> players = new ArrayList<>();
    private static final List<Teacher> teachers = new ArrayList<>();
    private static final List<Cleaner> cleaners = new ArrayList<>();

    private static final MenuWindow menuWindow = new MenuWindow();
    private static GameWindow gameWindow;

    private static final HashMap<Room,Color> roomColors = new HashMap<>();
    private static final Random random = new Random();

    private static final String RES = "resources";
    private static final String PNG = ".png";

    private static int turnCounter = 0;
    private static final int MAX_TURNS = 50;
    private static int actionsRemaining = 4;
    private static int playerIdx = 0;

    public static void main(String[] args){
        menuWindow.setVisible(true);
    }

    private static void giveRoomRandomColor( Room room ){
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        roomColors.put( room, new Color(r,g,b) );
    }
    public static Color getRoomColor( Room room ){
        return roomColors.get(room);
    }
    public static int getActionsRemaining() { return actionsRemaining; }
    public static int getTurnsLeft() { return MAX_TURNS-turnCounter; }

    public static String getPersonImage(Person person) {
        if(person instanceof Teacher) return RES+ File.separator+"teacher"+PNG;
        else if(person instanceof Cleaner) return RES+ File.separator+"cleaner"+PNG;
        else {
            int cnt=0;
            for(int i=0; i<players.size(); i++) {
                if(players.get(i)==person) cnt = i+1;
            }
            return RES+ File.separator+"player"+cnt+PNG;
        }
    }
    public static String getItemImage(Item item) {
        if (item instanceof BeerGlass) {
            return RES + File.separator + "beerglass"+PNG;
        } else if (item instanceof AirFresher) {
            return RES + File.separator + "airfresher"+PNG;
        } else if (item instanceof Camembert) {
            return RES + File.separator + "camembert"+PNG;
        } else if (item instanceof Mask) {
            return RES + File.separator + "mask"+PNG;
        } else if (item instanceof SlideRule) {
            return RES + File.separator + "sliderule"+PNG;
        } else if (item instanceof TVSZ) {
            return RES + File.separator + "tvsz"+PNG;
        } else if (item instanceof Rag) {
            return RES + File.separator + "rag"+PNG;
        } else return RES + File.separator + "transistor"+PNG;
    }

    public static ArrayList<String> getItemAttributes(Item item){
        ArrayList<String> attributes = new ArrayList<>();
        if (item instanceof BeerGlass) {
            attributes.add("Beer Glass");
        } else if (item instanceof AirFresher) {
            attributes.add("Air Freshener");
        } else if (item instanceof Camembert) {
            attributes.add("Camembert");
        } else if (item instanceof Mask mask) {
            attributes.add("Mask");
            attributes.add("Next duration: " + mask.getDuration());
        } else if (item instanceof SlideRule) {
            attributes.add("Slide Rule");
        } else if (item instanceof TVSZ tvsz) {
            attributes.add("TVSZ");
            attributes.add("Uses remaining: " + tvsz.getUsesRemaining());
        } else if (item instanceof Rag) {
            attributes.add("Rag");
        } else {
            attributes.add("Transistor");
            attributes.add(((Transistor)item).getPair() == null ? "Not Paired" : "Paired");
        }
        if(item instanceof IntervalItem interval) {
            attributes.add(interval.isActivated() ? "Activated" : "Not activated");
            attributes.add("Remaining time: " + interval.getTimeRemaining());
        }
        return attributes;
    }

    public static void showMenu(){
        menuWindow.setVisible(true);
    }

    public static void startGame( int mapSize, int playerNumber ){

        menuWindow.dispose();

        rooms.clear();
        players.clear();
        teachers.clear();
        cleaners.clear();

        turnCounter = 0;
        if(mapSize == 0) initSmallMap();
        else if(mapSize == 1) initMediumMap();
        else if(mapSize == 2) initLargeMap();
        else initRandomMap();
        initPlayers(playerNumber);

        gameWindow = new GameWindow( players );
        gameWindow.setVisible(true);
    }

    private static void initPlayers(int playerNumber) {
        for (int i = 0; i < playerNumber; i++) {
            Student student = new Student(0, rooms.get(0));
            players.add(student);
            rooms.get(0).addPerson(student);
        }
    }

    private static void nextPlayer(){
        actionsRemaining = 4;
        playerIdx = (playerIdx + 1) % players.size();
        if(playerIdx == 0) endTurn();
        if(players.isEmpty()){
            return;
        }
        gameWindow.showPlayer( players.get(playerIdx) );
    }

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
                players.remove(player);
            }
        }
        if(players.isEmpty()){
            gameOver(false);
        }
        if(turnCounter % 4 == 0) mergeRooms();
        else if(turnCounter % 4 == 2) splitRooms();
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
        if( room.getNeighbours().isEmpty() ) return;
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

    public static void gameOver(boolean win){
        gameWindow.endGame();
        JOptionPane.showMessageDialog(null, win ? "GG" : "BME");
        gameWindow.dispose();
        menuWindow.setVisible(true);
    }

    private static void mergeRooms() {
        ArrayList<Room> merging = getMergingRooms();
        if(merging.isEmpty()) return;
        Room newRoom = merging.get(0).requestMerge(merging.get(1));
        if(newRoom == null) return;
        rooms.remove(merging.get(0));
        rooms.remove(merging.get(1));
        rooms.add(newRoom);
        if(random.nextFloat()>0.8) splitRooms();
    }

    private static ArrayList<Room> getMergingRooms() {
        int tries = 0;
        ArrayList<Room> merging = new ArrayList<>();
        while(tries < rooms.size()) {
            Room room1 = rooms.get(random.nextInt(rooms.size()));
            if(room1.getPeopleInRoom().isEmpty()) {
                for(Room room2 : room1.getNeighbours()) {
                    if(room2.getPeopleInRoom().isEmpty() && room2.getNeighbours().contains(room1) && room2.getNeighbours().size()>1 && room1.getNeighbours().size()>1) {
                        merging.add(room1);
                        merging.add(room2);
                        return merging;
                    }
                }
            }
            tries++;
        }
        return merging;
    }

    private static void splitRooms() {
        int tries = 0;
        while(tries < rooms.size()) {
            Room room = rooms.get(random.nextInt(rooms.size()));
            if(room.getPeopleInRoom().isEmpty()) {
                Room newRoom = room.split();
                if (newRoom != null) rooms.add(newRoom);
                break;
            }
            tries++;
        }
        if(random.nextFloat()>0.8) mergeRooms();
    }

    public static void enterButtonPressed(){
        if( actionsRemaining >= 2 ){
            players.get(playerIdx).enterRoom( gameWindow.getActPlayerPanel().getDoorSelected().getRoom() );
            actionsRemaining -= 2;
        }
        if( isPlayerDead(players.get(playerIdx)) ){
            players.remove(playerIdx);
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
        players.get(playerIdx).addItem( gameWindow.getActPlayerPanel().getItemInRoomSelected().getItem() );
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
        players.get(playerIdx).dropItem(gameWindow.getActPlayerPanel().getItemInHandSelected().getItem());
        actionsRemaining--;
        if( actionsRemaining == 0 ){
            nextPlayer();
        }
        gameWindow.reDraw();
    }

    public static void activateButtonPressed(){
        players.get(playerIdx).activateItem(gameWindow.getActPlayerPanel().getItemInHandSelected().getItem());
        if( isPlayerDead(players.get(playerIdx)) ){
            players.remove(playerIdx);
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

    private static void initMediumMap() {
        for(int i = 0; i < 13; i++) {
            Room room = new Room(3, false, false, 2);
            giveRoomRandomColor(room);
            rooms.add(room);
        }
        rooms.get(0).setCapacity(4);
        rooms.get(1).setGas(true);
        rooms.get(4).setCursed(true);
        rooms.get(6).setCursed(true);
        rooms.get(8).setGas(true);
        rooms.get(9).setGas(true);
        rooms.get(11).setCursed(true);

        rooms.get(0).addNeighbour(rooms.get(1));
        rooms.get(0).addNeighbour(rooms.get(4));
        rooms.get(1).addNeighbour(rooms.get(0));
        rooms.get(1).addNeighbour(rooms.get(2));
        rooms.get(2).addNeighbour(rooms.get(3));
        rooms.get(3).addNeighbour(rooms.get(4));
        rooms.get(4).addNeighbour(rooms.get(0));
        rooms.get(4).addNeighbour(rooms.get(3));
        rooms.get(4).addNeighbour(rooms.get(5));
        rooms.get(5).addNeighbour(rooms.get(4));
        rooms.get(5).addNeighbour(rooms.get(6));
        rooms.get(5).addNeighbour(rooms.get(9));
        rooms.get(5).addNeighbour(rooms.get(10));
        rooms.get(6).addNeighbour(rooms.get(5));
        rooms.get(6).addNeighbour(rooms.get(7));
        rooms.get(6).addNeighbour(rooms.get(8));
        rooms.get(7).addNeighbour(rooms.get(6));
        rooms.get(7).addNeighbour(rooms.get(8));
        rooms.get(8).addNeighbour(rooms.get(6));
        rooms.get(8).addNeighbour(rooms.get(7));
        rooms.get(8).addNeighbour(rooms.get(9));
        rooms.get(8).addNeighbour(rooms.get(10));
        rooms.get(9).addNeighbour(rooms.get(1));
        rooms.get(9).addNeighbour(rooms.get(8));
        rooms.get(10).addNeighbour(rooms.get(5));
        rooms.get(10).addNeighbour(rooms.get(8));
        rooms.get(10).addNeighbour(rooms.get(11));
        rooms.get(11).addNeighbour(rooms.get(10));
        rooms.get(11).addNeighbour(rooms.get(12));
        rooms.get(12).addNeighbour(rooms.get(0));
        rooms.get(12).addNeighbour(rooms.get(11));

        rooms.get(0).initItem(new Rag(rooms.get(0), null, false, 3));
        rooms.get(0).initItem(new Mask(rooms.get(0), null, false, 6));
        rooms.get(0).initItem(new Transistor(rooms.get(0), null));

        rooms.get(3).initItem(new BeerGlass(rooms.get(3), null, false, 4));
        rooms.get(3).initItem(new Transistor(rooms.get(3), null));

        rooms.get(5).initItem(new Transistor(rooms.get(5), null));
        rooms.get(5).initItem(new FalseSlideRule(rooms.get(5), null));

        rooms.get(7).initItem(new AirFresher(rooms.get(7), null));

        rooms.get(8).initItem(new FalseMask(rooms.get(8), null, false, 4));
        rooms.get(8).initItem(new Transistor(rooms.get(8), null));
        rooms.get(8).initItem(new Camembert(rooms.get(8), null));

        rooms.get(9).initItem(new TVSZ(rooms.get(9), null));
        rooms.get(9).initItem(new Mask(rooms.get(9), null, false, 4));

        rooms.get(12).initItem(new SlideRule(rooms.get(12), null));

        Cleaner cleaner = new Cleaner(0, rooms.get(6));
        rooms.get(6).addPerson(cleaner);
        cleaners.add(cleaner);

        Teacher teacher = new Teacher(0, rooms.get(8));
        rooms.get(8).addPerson(teacher);
        teachers.add(teacher);
        teacher = new Teacher(0, rooms.get(9));
        rooms.get(9).addPerson(teacher);
        teachers.add(teacher);
    }

    private static void initLargeMap() {
        initSmallMap();
        initMediumMap();
        rooms.get(4).getItemsInRoom().remove(0);
        rooms.get(0).getNeighbours().add(rooms.get(5));
        rooms.get(1).getNeighbours().add(rooms.get(16));
        rooms.get(16).getNeighbours().add(rooms.get(1));
        rooms.get(2).getNeighbours().add(rooms.get(14));
        rooms.get(14).getNeighbours().add(rooms.get(2));
        rooms.get(3).getNeighbours().add(rooms.get(14));
        rooms.get(14).getNeighbours().add(rooms.get(3));
        rooms.get(4).addItem(new AirFresher(rooms.get(4), null));
        rooms.get(6).initItem(new Rag(rooms.get(6), null, false, 6));
        Cleaner cleaner = new Cleaner(0, rooms.get(15));
        rooms.get(15).addPerson(cleaner);
        cleaners.add(cleaner);
    }

    private static void initRandomMap() {
        int roomCnt=random.nextInt(12,25);
        makeRandomRooms(roomCnt);
        makeRandomNPCs(roomCnt);
        makeRandomItems(roomCnt);
    }

    private static void makeRandomRooms(int roomCnt) {
        for(int i=0; i<roomCnt; i++) {
            boolean gas = false;
            boolean cursed = false;
            if(random.nextFloat() > 0.8) gas = true;
            if(random.nextFloat() > 0.8) cursed = true;
            Room room = new Room(random.nextInt(2, 6), gas, cursed, random.nextInt(0, 4));
            giveRoomRandomColor(room);
            rooms.add(room);
            if(i>0) {
                rooms.get(i).getNeighbours().add(rooms.get(i-1));
                rooms.get(i-1).getNeighbours().add(rooms.get(i));
            }
        }
        int neighbourCnt=random.nextInt(roomCnt, roomCnt*2+1);
        int actCnt=0;
        while (actCnt<neighbourCnt) {
            Room room1 = rooms.get(random.nextInt(roomCnt));
            Room room2 = rooms.get(random.nextInt(roomCnt));
            if(room1!=room2 && ! room1.getNeighbours().contains(room2) && ! room2.getNeighbours().contains(room1) ) {
                room1.addNeighbour(room2);
                actCnt++;
                if(random.nextFloat() > 0.5) {
                    room2.addNeighbour(room1);
                    actCnt++;
                }
            }
        }
    }

    private static void makeRandomNPCs(int roomCnt) {
        Room room;
        int counter = random.nextInt(2, 6);
        for(int i=0; i<counter; i++) {
            room=rooms.get(random.nextInt(1, roomCnt));
            Teacher teacher = new Teacher(0, room);
            room.addPerson(teacher);
            teachers.add(teacher);
        }
        counter = random.nextInt(2, 6);
        for(int i=0; i<counter; i++) {
            room=rooms.get(random.nextInt(0, roomCnt));
            Cleaner cleaner = new Cleaner(0, room);
            room.addPerson(cleaner);
            cleaners.add(cleaner);
        }
    }

    private static void makeRandomItems(int roomCnt) {
        for(Room room: rooms) {
            int itemCnt = random.nextInt(4);
            for(int i=0; i< itemCnt; i++) {
                int itemType = random.nextInt(10);
                int time = random.nextInt(3, 7);
                switch(itemType) {
                    case(0):
                        room.initItem(new AirFresher(room, null));
                        break;
                    case(1):
                        room.initItem(new BeerGlass(room, null, false, time));
                        break;
                    case(2):
                        room.initItem(new Camembert(room, null));
                        break;
                    case(3):
                        room.initItem(new FalseMask(room, null, false, time));
                        break;
                    case(4):
                        room.initItem(new FalseSlideRule(room, null));
                        break;
                    case(5):
                        room.initItem(new FalseTVSZ(room, null));
                        break;
                    case(6):
                        room.initItem(new Mask(room, null, false, time));
                        break;
                    case(7):
                        room.initItem(new Rag(room, null, false, time));
                        break;
                    case(8):
                        room.initItem(new Transistor(room, null));
                        break;
                    case(9):
                        room.initItem(new TVSZ(room, null));
                        break;
                    default:
                }
            }
        }
        Room room = rooms.get(random.nextInt(1, roomCnt));
        room.initItem(new SlideRule(room, null));
    }
}