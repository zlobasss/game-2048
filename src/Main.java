import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main extends JPanel {

    private static double scale = 5;

    public Main() {
        Timer timer = new Timer(8, (ActionEvent e) -> {
            update();
            repaint();
        });
        timer.start();
    }

    private void update() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLUE);

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int size = 70;

        int x = (int) ((panelWidth / scale - size) / 2);
        int y = (int) ((panelHeight / scale - size) / 2);

        g2d.scale(scale, scale);

        g2d.drawRect(x, y, size, size);
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
    }
}