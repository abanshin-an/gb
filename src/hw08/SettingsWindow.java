package hw08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SettingsWindow extends JFrame {
    final private JSlider boardSize;
    final private JSlider winningLineLength;

    SettingsWindow(GUI gui) {
        final int MIN_BOARD_SIZE = 3;
        final int MAX_BOARD_SIZE = 10;
        setBounds(300, 300, 400, 400);
        setLayout(new GridLayout(7, 1));
        JLabel l = new JLabel("Размер доски (клеток)");
        l.setHorizontalAlignment(SwingConstants.CENTER);
        add(l, 0, 0);
        boardSize = new JSlider(MIN_BOARD_SIZE, MAX_BOARD_SIZE, MAX_BOARD_SIZE);
        boardSize.addChangeListener(e -> setBoardSize(boardSize.getValue()));
        boardSize.setMajorTickSpacing(1);
        boardSize.setPaintLabels(true);
        boardSize.setPaintLabels(true);
        add(boardSize);
        l = new JLabel("Победная линия (точек)");
        l.setHorizontalAlignment(SwingConstants.CENTER);
        add(l);
        winningLineLength = new JSlider(MIN_BOARD_SIZE, boardSize.getValue(), MIN_BOARD_SIZE);
        winningLineLength.setMajorTickSpacing(1);
        winningLineLength.setPaintLabels(true);
        winningLineLength.setPaintLabels(true);
        add(winningLineLength);
        l = new JLabel("Кто делает первый ход");
        l.setHorizontalAlignment(SwingConstants.CENTER);
        add(l);
        JRadioButton humanButton = new JRadioButton(GUI.HUMAN);
        humanButton.setMnemonic(KeyEvent.VK_H);
        humanButton.setActionCommand(GUI.HUMAN);

        JRadioButton aiButton = new JRadioButton(GUI.COMPUTER);
        aiButton.setMnemonic(KeyEvent.VK_C);
        aiButton.setActionCommand(GUI.COMPUTER);

        JPanel radioPanel = new JPanel(new GridLayout(1, 2));
        radioPanel.add(humanButton);
        radioPanel.add(aiButton);
        add(radioPanel);

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(humanButton);
        group.add(aiButton);
        humanButton.setSelected(true);
        JPanel p = new JPanel();
        JButton b = new JButton("Ок");
        b.addActionListener(e -> {
            setVisible(false);
            gui.startNewGame(boardSize.getValue(), winningLineLength.getValue(), aiButton.isSelected());
        });
        p.add(b);
        b = new JButton("Отказ");
        b.addActionListener(e -> setVisible(false) );
        p.add(b);
        add(p);
        setVisible(false);
    }

    private void setBoardSize(int size) {
        winningLineLength.setMaximum(size);
    }

}
