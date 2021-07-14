package hw08;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public static final String HUMAN = "Человек";
    public static final String COMPUTER = "Компьютер";
    final private SettingsWindow sw;
    final private JLabel humanScore;
    final private JLabel aiScore;
    final private XOBoardPanel gamePanel;
    //final private JButton newGameButton;

    public GUI() {
        setTitle("Игра крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setLayout(new BorderLayout());
        sw = new SettingsWindow(this);
        gamePanel=new XOBoardPanel(this);
        add(gamePanel,BorderLayout.CENTER);
        JPanel p= new JPanel();
        p.add(new JLabel("Счет "+HUMAN+"   "));
        humanScore=new JLabel("0");
        p.add(humanScore);
        p.add(new JLabel("   :   "));
        aiScore=new JLabel("0");
        p.add(aiScore);
        p.add(new JLabel("   "+COMPUTER));
        add(p,BorderLayout.NORTH);
        add(gamePanel);
        p=new JPanel();
        JButton newGameButton=new JButton("Играть");
        newGameButton.addActionListener(e -> sw.setVisible(true));

        p.add(newGameButton);
        JButton exitButton=new JButton("Выход");
        exitButton.addActionListener(e -> System.exit(0));
        p.add(exitButton);
        add(p,BorderLayout.SOUTH);
        setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    public void incrementHumanScore(){
        int score=Integer.parseInt(humanScore.getText())+1;
        humanScore.setText(Integer.toString(score));
    }

    public void incrementAIScore(){
        int score=Integer.parseInt(aiScore.getText())+1;
        aiScore.setText(Integer.toString(score));
    }

    public void startNewGame(int boardSize, int dotsToWin,boolean isHumanFirst) {
        TicTacToeObj.startGame(boardSize,dotsToWin,isHumanFirst);
        gamePanel.startNewGame(boardSize);
    }
}
