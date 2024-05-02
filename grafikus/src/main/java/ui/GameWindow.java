package ui;

import controller.Controller;
import model.Student;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class GameWindow extends JFrame {

    private final HashMap<Student,PlayerPanel> playerPanels = new HashMap<>();

    public PlayerPanel actPlayerPanel;

    public boolean gameEnded = false;

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
        setVisible(true);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuNewGame = new JMenuItem("New Game");
        JMenuItem menuExit = new JMenuItem("Exit");
        menuBar.add(menu);
        menu.add(menuNewGame);
        menu.add(menuExit);
        menuExit.addActionListener( (e) -> System.exit(0));
        menuNewGame.addActionListener((e)->{
            gameEnded = true;
            dispose();
            Controller.showMenu();
        });
        setJMenuBar(menuBar);
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
}
