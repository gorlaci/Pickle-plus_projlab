package ui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MenuWindow extends JFrame {
    private final JLabel playerLabel;
    private int numPlayers = 2;

    public MenuWindow() {

        // Ablak inicializálása
        setTitle("Menu");
        setMinimumSize(new Dimension(600,600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Layout beállítása
        setLayout(new GridLayout(4, 1, 0, 10));
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Pálya kiválasztása
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.X_AXIS));
        JLabel mapLabel = new JLabel("Map:");
        String[] mapOptions = {"Small", "Medium", "Large"};
        JComboBox<String> mapComboBox = new JComboBox<>(mapOptions);
        mapPanel.add(mapLabel);
        mapPanel.add(mapComboBox);

        // Játékosok számának kiválasztása
        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.X_AXIS));
        JLabel playerTextLabel = new JLabel("Players:");
        playerLabel = new JLabel(String.valueOf(numPlayers));
        JButton plusButton = new JButton("+");
        JButton minusButton = new JButton("-");
        plusButton.addActionListener(e -> {
            if(numPlayers == 1) minusButton.setEnabled(true);
            numPlayers++;
            playerLabel.setText(String.valueOf(numPlayers));
            if(numPlayers == 4) plusButton.setEnabled(false);
        });
        minusButton.addActionListener(e -> {
            if(numPlayers == 4) plusButton.setEnabled(true);
            numPlayers--;
            playerLabel.setText(String.valueOf(numPlayers));
            if(numPlayers == 1) minusButton.setEnabled(false);
        });
        playerPanel.add(playerTextLabel);

        playerPanel.add(minusButton);
        playerPanel.add(playerLabel);
        playerPanel.add(plusButton);

        // Start gomb
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            // Start játék logika
            //JOptionPane.showMessageDialog(null, "Game started!");
            Controller.startGame(0,numPlayers);
        });
        buttonPanel.add(startButton);

        //BME kép
        JLabel imageLabel = new JLabel(new ImageIcon("resources"+File.separator+"bme.png"));
        setContentPane(imageLabel);

        // Panelek hozzáadása a frame-hez
        mapPanel.setPreferredSize(new Dimension(300, 50)); // Például 300 pixel széles
        playerPanel.setPreferredSize(new Dimension(300, 50));
        buttonPanel.setPreferredSize(new Dimension(300, 50));
        menuPanel.add(imageLabel);
        menuPanel.add(mapPanel);
        menuPanel.add(playerPanel);
        menuPanel.add(buttonPanel);
        add(menuPanel, BorderLayout.CENTER);

        /*setContentPane(new JLabel(new ImageIcon("resources"+File.separator+"bme.png")));
        mapPanel.setOpaque(false);
        playerPanel.setOpaque(false);
        buttonPanel.setOpaque(false);
        mapPanel.setBackground(new Color(0, 0, 0, 0));
        playerPanel.setBackground(new Color(0, 0, 0, 0));
        buttonPanel.setBackground(new Color(0, 0, 0, 0));*/

        setVisible(true);
    }
}
