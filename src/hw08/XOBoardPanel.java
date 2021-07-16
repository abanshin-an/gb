package hw08;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class XOBoardPanel extends JPanel {
    private int boardSize;
    private int cellWidth;
    private int cellHeight;

    XOBoardPanel(GUI gui){
        setBackground(Color.LIGHT_GRAY);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                int cellX=e.getX()/cellWidth;
                int cellY=e.getY()/cellHeight;
                System.out.println(" "+cellX+" "+cellY);
                if (isGameOn()) {
                    TicTacToeObj.moveHuman(cellX, cellY);
                    repaint();
                    if (TicTacToeObj.getStatus()==Status.WIN_AI) {
                        gui.incrementAIScore();
                    } else if (TicTacToeObj.getStatus()==Status.WIN_HUMAN) {
                        gui.incrementHumanScore();
                    }
                }
            }
        });
    }

    private boolean isGameOn() {
        return TicTacToeObj.getStatus()==Status.GAME_IS_ON ;
    }

    private boolean isInit() {
        return TicTacToeObj.getStatus()!= Status.NOT_INIT ;
    }

    public void startNewGame(int boardSize) {
        System.out.println(" "+ boardSize);
        this.boardSize=boardSize;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (!isInit())
            return;
        int panelWidth=getWidth();
        int panelHeigth=getHeight();

        cellWidth=panelWidth/boardSize;
        cellHeight=panelHeigth/boardSize;
        g.setColor(Color.BLACK);
        for (int i = 1; i < boardSize; i++) {
            int y=i*cellHeight;
            g.drawLine(0,y,panelWidth,y);
            int x=i*cellWidth;
            g.drawLine(x,0,x,panelHeigth);
        }
        int r= Math.min(cellWidth,cellHeight)/2;
        int x;
        int y;
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        for (int i = 0; i < boardSize; i++) { // x
            for (int j = 0; j < boardSize; j++) { // y
                switch (TicTacToeObj.cell(i,j)) {
                    case TicTacToeObj.DOT_O:
                        x= cellWidth*i+cellWidth/2-r;
                        y= cellHeight*j+cellHeight/2-r;
                        g.drawOval(x,y,2*r,2*r);
                        break;
                    case TicTacToeObj.DOT_X:
                        x= cellWidth*i+cellWidth/2;
                        y= cellHeight*j+cellHeight/2;
                        g.drawLine(x-r,y-r,x+r,y+r);
                        g.drawLine(x+r,y-r,x-r,y+r);
                        break;
                }
            }
        }
        Status s=TicTacToeObj.getStatus();
        if ( s==Status.WIN_AI || s== Status.WIN_HUMAN ) {
            int[][] winLine = TicTacToeObj.getWinLine();
            int x1= cellWidth*winLine[0][0]+cellWidth/2;
            int y1= cellHeight*winLine[0][1]+cellHeight/2;
            int x2= cellWidth*winLine[1][0]+cellWidth/2;
            int y2= cellHeight*winLine[1][1]+cellHeight/2;
            g2.setStroke(new BasicStroke(3));
            g.setColor(Color.RED);
            g.drawLine(x1,y1,x2,y2);

        }
    }

}
