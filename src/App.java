/*
    This project made by Sait Göktuğ Doğan and Hüseyin Hamad.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame implements ActionListener {

    public static int WIDTH = 1200;
    public static int HEIGHT = 800;

    public GamePanel gamePanel;
    public LevelUPPanel levelPanel;
    public GameoverPanel gameOverPanel;

    public static CardLayout cards;
    public Menu menu;
    public JButton b1;

    public App(){
        SoundEffect.init();
        SoundEffect.volume = SoundEffect.Volume.LOW;
        cards = new CardLayout();

        b1 = new JButton("Start Game");
        b1.setActionCommand("Start Game");
        b1.addActionListener(this);
        b1.setSize(100,100);
        b1.setLocation(500, 300);

        setLayout(cards);

        gamePanel = new GamePanel(this);
        gamePanel.requestFocusInWindow();


        levelPanel = new LevelUPPanel();
        gameOverPanel = new GameoverPanel();

        menu = new Menu();
        menu.add(b1);

        add("Menu", menu);
        add("Game", gamePanel);
        add("LevelUP", levelPanel);
        add("GameOver", gameOverPanel);
    }

    public static void main(String[] args) {
        App app = new App();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(WIDTH, HEIGHT);
        app.setResizable(false);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start Game")){
            cards.show(getContentPane(), "Game");
            gamePanel.start();
        }
    }

    public void changeToLevelUP(){
        cards.show(getContentPane(), "LevelUP");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Level level = gamePanel.level;

        gamePanel.game = new Game(level);
        cards.show(getContentPane(), "Game");
        SoundEffect.SONG.loop();
    }

    public void changeToGameover(){
        SoundEffect.SONG.stop();
        cards.show(getContentPane(), "GameOver");
        SoundEffect.GAMEOVER.play();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }
}
