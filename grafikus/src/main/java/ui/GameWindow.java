package ui;

import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class GameWindow extends JFrame {

    private final HashMap<Student,PlayerPanel> playerPanels = new HashMap<>();

    private PlayerPanel actPlayerPanel;

    private boolean gameEnded = false;

    public GameWindow(List<Student> players){
        super("Logarléc");
        setMinimumSize(new Dimension(800,500));
        initMenu();
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for( Student player : players ){
            playerPanels.put( player, new PlayerPanel(player, "Player"+(players.indexOf(player)+1)));
        }
        actPlayerPanel = playerPanels.get( players.get(0) );
        add( actPlayerPanel );

        pack();
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuNewGame = new JMenuItem("New Game");
        JMenuItem menuExit = new JMenuItem("Exit");
        JMenuItem menuInfo = new JMenuItem("Info");
        menuBar.add(menu);
        menu.add(menuNewGame);
        menu.add(menuExit);
        menu.add(menuInfo);
        menuExit.addActionListener( e -> System.exit(0));
        menuNewGame.addActionListener(e->{
            gameEnded = true;
            dispose();
            Controller.showMenu();
        });
        menuInfo.addActionListener( e -> {
            String infoText=readFileToString("resources"+ File.separator+"info.txt");
            JOptionPane.showMessageDialog(null, infoText, "Logarléc: Rules and How-To", JOptionPane.INFORMATION_MESSAGE);
        });
        setJMenuBar(menuBar);
    }

    private String readFileToString(String fileName) {
        File file=new File(fileName);
        if(!file.exists()) {
            return "Unexpected Error: file not found";
        }
        Scanner sc;
        try {
            sc=new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "Unexpected Error: file not found";
        }
        String text="";
        while(sc.hasNext()) {
            text=text.concat(sc.nextLine());
            text=text.concat("\n");
        }
        sc.close();
        return text;
    }

    public void showPlayer( Student player ){
        remove(actPlayerPanel);
        actPlayerPanel = playerPanels.get(player);
        add(actPlayerPanel);
        reDraw();
    }

    public void reDraw(){
        if( gameEnded ) return;
        actPlayerPanel.reDraw();
        pack();
        revalidate();
        repaint();
    }

    public PlayerPanel getActPlayerPanel(){
        return actPlayerPanel;
    }

    public  void endGame(){
        gameEnded = true;
    }
}
