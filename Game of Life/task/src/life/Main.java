package life;

public class Main {
    public static void main(String[] args) {
        Universe universe = new Universe();
        GameOfLife gol = new GameOfLife(universe.getCurrentGen().length);

        while (true) {
            if (gol.isReset()) {
                universe = new Universe();
                gol.resetToFalse();
            }

            if (gol.isPaused()) {
                try {
                    Thread.sleep(1000);

                    continue;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            gol.fillGrid(universe.getCurrentGen());

            String gen = "Generation #" + universe.getGeneratioNumber();
            String alive = "Alive: " + universe.countAlive();

            gol.generationLabel.setText(gen);
            gol.aliveLabel.setText(alive);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            universe.evolveUniverse();
        }
    }
}
