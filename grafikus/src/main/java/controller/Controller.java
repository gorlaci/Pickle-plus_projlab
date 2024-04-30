package controller;

import model.*;
import ui.GameWindow;
import ui.MenuWindow;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static List<Room> rooms = new ArrayList<>();
    public static List<Student> players = new ArrayList<>();
    public static MenuWindow menuWindow = new MenuWindow();
    public static GameWindow gameWindow;

    public static void main(String[] args){
        menuWindow.setVisible(true);
    }

    public static Color getRoomColor( Room room ){
        return Color.RED;
    }

    public static void startGame( int mapSize, int playerNumber ){
        menuWindow.dispose();
        Room room = new Room(5, false, false, 0);
        rooms.add(room);
        players.add( new Student(0, room));
        gameWindow = new GameWindow( players );
        gameWindow.setVisible(true);
    }
}
