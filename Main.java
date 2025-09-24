import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import gui.HomePanel;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Polygon Area Calculator - Krishika Mehta");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 700);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new HomePanel(frame));
            frame.setVisible(true);
        });
    }
}



