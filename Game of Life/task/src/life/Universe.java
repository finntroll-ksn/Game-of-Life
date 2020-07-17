package life;

import java.util.Random;

public class Universe {
    private boolean[][] currentGen;
    private int generationNumber = 0;

    Universe() {
        int size = 50;

        Random random = new Random();
        currentGen = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currentGen[i][j] = random.nextBoolean();
            }
        }

        generationNumber = 0;
    }

    Universe(int size) {
        Random random = new Random();
        currentGen = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currentGen[i][j] = random.nextBoolean();
            }
        }
    }

    Universe(int size, long seed) {
        Random random = new Random(seed);
        currentGen = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                currentGen[i][j] = random.nextBoolean();
            }
        }
    }

    void setCurrentGen(boolean[][] currentGen) {
        this.generationNumber += 1;
        this.currentGen = currentGen;
    }

    boolean[][] getCurrentGen() {
        return currentGen;
    }

    int getGeneratioNumber() {
        return generationNumber;
    }

    public int countAlive() {
        int alive = 0;

        for (boolean[] row: currentGen) {
            for (boolean column : row) {
                alive += column ? 1 : 0;
            }
        }

        return alive;
    }

    void evolveUniverse() {
        int size = currentGen.length;
        int[][] aliveNeighbors = new int[size][size];

        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                int neighbors = 0;

                for(int i1=-1; i1 <= 1; i1++) {
                    for(int j1=-1; j1 <= 1; j1++) {
                        if (currentGen[(size + i + i1) % size][(size + j + j1) % size] && !(i1 == 0 && j1 == 0)) {
                            neighbors++;
                        }
                    }
                }

                aliveNeighbors[i][j] = neighbors;
            }
        }

        for (int i=0; i < size; i++) {
            for (int j=0; j < size; j++) {
                int neighbors = aliveNeighbors[i][j];

                if (currentGen[i][j] && (neighbors < 2 || neighbors > 3)) {
                    currentGen[i][j] = false;
                } else if (!currentGen[i][j] && neighbors == 3) {
                    currentGen[i][j] = true;
                }
            }
        }

        this.generationNumber += 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Generation #" + generationNumber + "\n");
        sb.append("Alive: " + countAlive() + "\n");

        for(boolean[] row : currentGen) {
            for (boolean column : row) {
                sb.append(column ? 'O' : ' ');
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
