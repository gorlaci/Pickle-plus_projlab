package ui;

import model.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

public class GameWindow extends JFrame implements ActionListener {

    HashMap<Student,PlayerPanel> playerPanels = new HashMap<>();

    public PlayerPanel actPlayerPanel;

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
        menuNewGame.addActionListener(this);
        menuExit.addActionListener(this);
        setJMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if(s.equals("Exit")) System.exit(0);
        else if (s.equals("New Game")) {
            
        }
    }

    public void showPlayer( Student player ){
        remove(actPlayerPanel);
        actPlayerPanel = playerPanels.get(player);
        add(actPlayerPanel);
        reDraw();
    }

    public void reDraw(){
        actPlayerPanel.reDraw();
        pack();
        revalidate();
        repaint();
    }
}
