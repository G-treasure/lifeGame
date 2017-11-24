import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//������Ϸ
public class LifeGame {
    // GUI���
    JFrame liFrame;    // ���ϸ��״̬
    boolean[][] cell;
    // ��ʾϸ��״̬
    JPanel[][] cellP;
    JPanel map;//��ͼ���
    
   
    public LifeGame(int rows, int cols) {
        // GUI�����ʼ��
        liFrame = new JFrame("Life Game");
        cell = new boolean[rows][cols];
        cellP = new JPanel[rows][cols];
        liFrame.setLayout(new GridLayout(1, 2, 2, 2));
        map.setLayout(new GridLayout(rows, cols, 2, 2));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cellP[i][j] = new JPanel();
                cellP[i][j].setBackground(Color.WHITE);
                map.add(cellP[i][j]);
            }
        }
        liFrame.add(map);
        liFrame.setSize(500, 600);
        liFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        randomStart();
        reColor();
        liFrame.setVisible(true);
    }
   
    public void randomStart() {
        for (int i = 1; i < cell.length - 1; i++) {
            for (int j = 1; j < cell[i].length - 1; j++) {
                if (Math.random() > 0.5) {
                    cell[i][j] = true;
                } else {
                    cell[i][j] = false;
                }
            }
        }
    }
   
    public void generation() {
        for (int i = 1; i < cell.length - 1; i++) {
            for (int j = 1; j < cell[i].length - 1; j++) {
                int counter = 0;
                if (cellP[i - 1][j - 1].getBackground() == Color.BLUE) {
                    counter++;
                }
                if (cellP[i - 1][j].getBackground() == Color.BLUE) {
                    counter++;
                }
                if (cellP[i - 1][j + 1].getBackground() == Color.BLUE) {
                    counter++;
                }
                if (cellP[i][j - 1].getBackground() == Color.BLUE) {
                    counter++;
                }
                if (cellP[i][j + 1].getBackground() == Color.BLUE) {
                    counter++;
                }
                if (cellP[i + 1][j - 1].getBackground() == Color.BLUE) {
                    counter++;
                }
                if (cellP[i + 1][j].getBackground() == Color.BLUE) {
                    counter++;
                }
                if (cellP[i + 1][j + 1].getBackground() == Color.BLUE) {
                    counter++;
                }
                if (cellP[i][j].getBackground() == Color.BLUE) {
                    if (counter <= 1) {
                        cell[i][j] = false;
                    } else if (counter > 3) {
                        cell[i][j] = false;
                    }
                } else {
                    if (counter == 3) {
                        cell[i][j] = true;
                    }
                }
            }
        }
    }
   
    public void reColor() {
        for (int i = 1; i < cell.length - 1; i++) {
            for (int j = 1; j < cell[i].length - 1; j++) {
                if (cell[i][j]) {
                    cellP[i][j].setBackground(Color.BLUE);
                } else {
                    cellP[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }
   
    public static void main(String[] args) {
        // ������Ϸ����
        LifeGame lg = new LifeGame(80, 80);
        // ���г�ʼ��
        lg.randomStart();
        // ������ʾ
        lg.reColor();
        while (true) {
            // ȡ����һ��
            lg.generation();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // ������ʾ
            lg.reColor();
        }
    }
}