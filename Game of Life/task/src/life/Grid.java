package life;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Grid extends JPanel {

    private ArrayList<Point> fillCells;
    private int size = 20;

    public Grid() {
        fillCells = new ArrayList<>();
    }

    public Grid(int size) {
        fillCells = new ArrayList<>();
        this.size = size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < fillCells.size(); i++) {
            int cellX = 10 + (fillCells.get(i).x * 10);
            int cellY = 10 + (fillCells.get(i).y * 10);

            g.setColor(Color.BLACK);
            g.fillRect(cellX, cellY, 10, 10);
        }

        g.setColor(Color.BLUE);
        g.drawRect(10, 10, size * 10, size * 10);

        for (int i = 10; i <= size * 10; i += 10) {
            g.drawLine(i, 10, i, size * 10 + 10);
        }

        for (int i = 10; i <= size * 10; i += 10) {
            g.drawLine(10, i, size * 10 + 10, i);
        }
    }

    public void fillCell(int x, int y) {
        fillCells.add(new Point(x, y));

        repaint();
    }

    public void removeCell(int x, int y) {
        fillCells.remove(new Point(x, y));

        repaint();
    }

}