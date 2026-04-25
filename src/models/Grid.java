package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

    private int[][] cells;
    private Random random = new Random(System.currentTimeMillis());

    public Grid(int size) {
        cells = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = 0;
            }
        }
    }

    public void move(int direction) {
        switch (direction) {
            case 1: // left
                leftMove();
                break;

            case 2: // right
                rightMove();
                break;

            case 3: // up
                upMove();
                break;

            case 4: // down
                downMove();
                break;

            default:
                break;
        }

        addRandomValue();
    }

    private void addRandomValue() {
        int length = cells.length;
        List<Integer> zeroPositions = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (cells[i][j] == 0) {
                    zeroPositions.add(i * 4 + j);
                }
            }
        }
        int position = random.nextInt(zeroPositions.size() - 1);
        int x = position / length;
        int y = position % length;
        cells[x][y] = 2;
    }

    private void leftMove() {
        int length = cells.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1; j++) {
                for (int k = j + 1; k < length; k++) {
                    if (cells[i][k] == 0) {
                        continue;
                    }
                    if (cells[i][j] == 0) {
                        cells[i][j] = cells[i][k];
                    } else if (cells[i][j] == cells[i][k]) {
                        cells[i][j] += cells[i][k];
                    } else {
                        break;
                    }
                    cells[i][k] = 0;
                }
            }
        }
    }

    private void rightMove() {
        int length = cells.length;
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j > 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (cells[i][k] == 0) {
                        continue;
                    }
                    if (cells[i][j] == 0) {
                        cells[i][j] = cells[i][k];
                    } else if (cells[i][j] == cells[i][k]) {
                        cells[i][j] += cells[i][k];
                    } else {
                        break;
                    }
                    cells[i][k] = 0;
                }
            }
        }
    }

    private void upMove() {
        int length = cells.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j > 0; j++) {
                for (int k = j + 1; k >= 0; k++) {
                    if (cells[k][i] == 0) {
                        continue;
                    }
                    if (cells[j][i] == 0) {
                        cells[j][i] = cells[k][i];
                    } else if (cells[j][i] == cells[k][i]) {
                        cells[j][i] += cells[k][i];
                    } else {
                        break;
                    }
                    cells[k][i] = 0;
                }
            }
        }
    }

    private void downMove() {
        int length = cells.length;
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j > 0; j--) {
                for (int k = j - 1; k >= 0; k--) {
                    if (cells[k][i] == 0) {
                        continue;
                    }
                    if (cells[j][i] == 0) {
                        cells[j][i] = cells[k][i];
                    } else if (cells[j][i] == cells[k][i]) {
                        cells[j][i] += cells[k][i];
                    } else {
                        break;
                    }
                    cells[k][i] = 0;
                }
            }
        }
    }
}
