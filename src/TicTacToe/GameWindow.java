package TicTacToe;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    static final int WINDOW_POS_X = 400;
    static final int WINDOW_POS_Y = 400;
    static final int WINDOW_WIDTH = 505;
    static final int WINDOW_HEIGHT = 555;

    private SettingsWindow settingsWindow;
    private BattleMap battleMap;

    public GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe");

        settingsWindow = new SettingsWindow(this);
        battleMap = new BattleMap(this);
        add(battleMap, BorderLayout.CENTER);


        JPanel panel = new JPanel(new GridLayout(1, 2));
        JButton buttonStartNewGame = new JButton("Начать игру");
        buttonStartNewGame.setBackground(Color.green);
        panel.add(buttonStartNewGame);

        /*class MyPanel extends JPanel{
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                g.drawString("Hello",55,55);
            }
        }*/

        JButton buttonExit = new JButton("Выход");
        buttonExit.setBackground(Color.RED);
        panel.add(buttonExit);

        buttonExit.addActionListener(e -> {
            System.exit(0);
        });

        buttonStartNewGame.addActionListener(e -> {
            settingsWindow.setVisible(true);
        });

        add(panel, BorderLayout.SOUTH);



        setVisible(true);
    }

    public void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int dotsToWin) {
//        System.out.printf("gameMode: %d, fieldSizeX: %d, fieldSizeY: %d, dotsToWin: %d\n", gameMode, fieldSizeX, fieldSizeY, dotsToWin);
        battleMap.startNewGame(gameMode, fieldSizeX, fieldSizeY, dotsToWin);

    }
}
