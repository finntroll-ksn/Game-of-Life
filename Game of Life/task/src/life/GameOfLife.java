package life;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOfLife extends JFrame {
    Grid grid;
    JLabel generationLabel;
    JLabel aliveLabel;
    JToggleButton playToggleButton;
    JButton resetButton;
    boolean isPaused;
    boolean isReset;

    public GameOfLife(int size) {
        super("Game of Life");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);

        playToggleButton = new JToggleButton();
        playToggleButton.setName("PlayToggleButton");
        playToggleButton.setText("PlayPause");
        playToggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                isPaused = !isPaused;
            }
        });

        add(playToggleButton);

        resetButton = new JButton();
        resetButton.setName("ResetButton");
        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                isReset = true;
            }
        });

        add(resetButton);

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("GenerationLabel #0");

        add(generationLabel);

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("AliveLabel: 0");

        add(aliveLabel);

        grid = new Grid(size);

        add(grid);
    }

    public boolean isPaused() {
        return isPaused;
    }

    public boolean isReset() {
        return isReset;
    }

    public void resetToFalse() {
        isReset = false;
    }

    public void fillGrid(boolean[][] generation) {
        int len = generation.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (generation[i][j]) {
                    grid.fillCell(i, j);
                } else {
                    grid.removeCell(i, j);
                }
            }
        }
    }
}
