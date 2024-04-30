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

    PlayerPanel actPlayerPanel;

    public GameWindow(List<Student> players){
        super("Logarléc");
        setMinimumSize(new Dimension(800,500));
        initMenu(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        for( Student player : players ){
            playerPanels.put( player, new PlayerPanel(player) );
        }
        actPlayerPanel = playerPanels.get( players.get(0) );
        setLayout( new BorderLayout() );
        add( actPlayerPanel, BorderLayout.CENTER );
        add( new JLabel("TESZT"), BorderLayout.SOUTH );

        pack();
        setVisible(true);
    }

    private void initMenu(JFrame frame){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuNewGame = new JMenuItem("New Game");
        JMenuItem menuExit = new JMenuItem("Exit");
        menuBar.add(menu);
        menu.add(menuNewGame);
        menu.add(menuExit);
        menuNewGame.addActionListener(this);
        menuExit.addActionListener(this);
        frame.setJMenuBar(menuBar);
    }

    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();
        if(s.equals("Exit")) System.exit(0);
        else if (s.equals("New Game")) {
            
        }
    }

    public void showPlayer( Student player ){
        removeAll();
    }
}
