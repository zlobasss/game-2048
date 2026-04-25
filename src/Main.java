import javax.swing.*;
import models.Grid;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JPanel {

    private static double scale = 2;

    private Grid grid = new Grid(4);
    private boolean gameOver = false;

    public Main() {

        setFocusable(true);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        handleMove(3);
                        break;

                    case KeyEvent.VK_DOWN:
                        handleMove(4);
                        break;

                    case KeyEvent.VK_LEFT:
                        handleMove(1);
                        break;

                    case KeyEvent.VK_RIGHT:
                        handleMove(2);
                        break;

                    case KeyEvent.VK_R:
                        if (gameOver) {
                            grid = new Grid(4);
                            gameOver = false;
                        }
                        break;
                }
            }
        });

        Timer timer = new Timer(16, (ActionEvent e) -> {
            update();
            repaint();
        });
        timer.start();
    }

    private void handleMove(int dir) {

        if (gameOver)
            return;

        grid.move(dir);

        if (!grid.canMove()) {
            gameOver = true;
        }
    }

    private void update() {
        // логика игры
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        grid.draw(g2d, scale);

        if (gameOver) {

            g2d.setColor(new Color(0, 0, 0, 150));
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 30));

            g2d.drawString("GAME OVER", 100, 180);
            g2d.drawString("Press R to restart", 70, 220);
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Main panel = new Main();

        panel.setPreferredSize(new Dimension(400, 400));

        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        SwingUtilities.invokeLater(panel::requestFocusInWindow);
    }
}