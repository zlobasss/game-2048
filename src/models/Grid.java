package models;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {

    private int[][] cells;
    private int size;
    private Random random = new Random(System.currentTimeMillis());

    public Grid(int size) {
        this.size = size;
        cells = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j] = 0;
            }
        }
        addRandomValue();
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

    public boolean canMove() {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == 0) {
                    return true;
                }
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                int current = cells[i][j];

                if (j + 1 < size && cells[i][j + 1] == current) {
                    return true;
                }

                if (i + 1 < size && cells[i + 1][j] == current) {
                    return true;
                }
            }
        }

        return false;
    }

    private void addRandomValue() {
        List<Integer> zeroPositions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (cells[i][j] == 0) {
                    zeroPositions.add(i * 4 + j);
                }
            }
        }

        if (zeroPositions.size() == 0) {
            return;
        }

        int position = zeroPositions.size() != 1 ? zeroPositions.get(random.nextInt(zeroPositions.size() - 1)) : 0;
        int x = position / size;
        int y = position % size;
        cells[x][y] = 2;
    }

    private void leftMove() {
        for (int i = 0; i < size; i++) {

            int[] row = new int[size];

            for (int j = 0; j < size; j++) {
                row[j] = cells[i][j];
            }

            row = process(row);

            for (int j = 0; j < size; j++) {
                cells[i][j] = row[j];
            }
        }
    }

    private void rightMove() {
        for (int i = 0; i < size; i++) {

            int[] row = new int[size];

            for (int j = 0; j < size; j++) {
                row[size - 1 - j] = cells[i][j];
            }

            row = process(row);

            for (int j = 0; j < size; j++) {
                cells[i][j] = row[size - 1 - j];
            }
        }
    }

    private void upMove() {
        for (int j = 0; j < size; j++) {

            int[] col = new int[size];

            for (int i = 0; i < size; i++) {
                col[i] = cells[i][j];
            }

            col = process(col);

            for (int i = 0; i < size; i++) {
                cells[i][j] = col[i];
            }
        }
    }

    private void downMove() {
        for (int j = 0; j < size; j++) {

            int[] col = new int[size];

            for (int i = 0; i < size; i++) {
                col[size - 1 - i] = cells[i][j];
            }

            col = process(col);

            for (int i = 0; i < size; i++) {
                cells[i][j] = col[size - 1 - i];
            }
        }
    }

    private int[] process(int[] line) {

        int n = line.length;
        int[] temp = new int[n];
        int index = 0;

        for (int v : line) {
            if (v != 0) {
                temp[index++] = v;
            }
        }

        for (int i = 0; i < n - 1; i++) {

            if (temp[i] != 0 && temp[i] == temp[i + 1]) {

                temp[i] *= 2;
                temp[i + 1] = 0;

                i++; // ❗ КЛЮЧЕВОЕ: пропускаем следующую плитку
            }
        }

        int[] result = new int[n];
        index = 0;

        for (int v : temp) {
            if (v != 0) {
                result[index++] = v;
            }
        }

        return result;
    }

    public void draw(Graphics2D g2d, double scale) {

        g2d.setColor(Color.BLACK);

        int cellSize = (int) (50);

        g2d.scale(scale, scale);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                int px = x * cellSize;
                int py = y * cellSize;

                g2d.drawRect(px, py, cellSize, cellSize);

                int value = cells[y][x];

                if (value != 0) {
                    drawCenteredText(g2d, String.valueOf(value), px, py, cellSize);
                }
            }
        }

        g2d.scale(1 / scale, 1 / scale);
    }

    private void drawCenteredText(Graphics2D g2d, String text, int x, int y, int size) {

        Font font = new Font("Arial", Font.BOLD, 16);
        g2d.setFont(font);

        FontMetrics fm = g2d.getFontMetrics();

        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int tx = x + (size - textWidth) / 2;
        int ty = y + (size + textHeight) / 2 - 4;

        g2d.drawString(text, tx, ty);
    }
}
