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
        super("Menu");
        setMinimumSize(new Dimension(400,500));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Layout beállítása
        setLayout(new GridLayout(4, 1, 0, 10));

        //BME logo
        JLabel imageLabel = new JLabel(new ImageIcon("resources"+File.separator+"bme.png"));

        //Panelek létrehozása
        JPanel mapPanel = new JPanel();
        JPanel playerPanel = new JPanel();
        JPanel startPanel = new JPanel();

        //Panelek elrendezése
        mapPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        startPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        //Panelek mérete
        mapPanel.setPreferredSize(new Dimension(300, 50));
        playerPanel.setPreferredSize(new Dimension(300, 50));
        startPanel.setPreferredSize(new Dimension(300, 50));

        // Pálya kiválasztása - mapPanel
        JLabel mapLabel = new JLabel("Map:");
        String[] mapOptions = {"Small", "Medium", "Large", "Random"};
        JComboBox<String> mapComboBox = new JComboBox<>(mapOptions);
        mapPanel.add(mapLabel);
        mapPanel.add(mapComboBox);

        // Játékosok számának kiválasztása - playerPanel
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

        // Start gomb - startPanel
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(e -> {
            // Start játék logika
            Controller.startGame(mapComboBox.getSelectedIndex(), numPlayers);
        });
        startPanel.add(startButton);

        // Panelek hozzáadása a frame-hez
        add(imageLabel);
        add(mapPanel);
        add(playerPanel);
        add(startPanel);
    }
}
