package gui;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    public ControlPanel(JFrame frame, PlotPanel plotPanel) {
        setLayout(new FlowLayout());
        setBackground(Color.DARK_GRAY);

        JButton grid = new JButton("Toggle Grid");
        JButton reset = new JButton("Reset");
        JButton back = new JButton("Back to Home");

        for (JButton b : new JButton[]{grid, reset, back}) {
            b.setFont(new Font("SansSerif", Font.BOLD, 14));
            b.setFocusPainted(false);
            b.setBackground(Color.LIGHT_GRAY);
            add(b);
        }

        grid.addActionListener(e -> plotPanel.toggleGrid());
        reset.addActionListener(e -> plotPanel.reset());
        back.addActionListener(e -> {
            frame.setContentPane(new HomePanel(frame));
            frame.revalidate();
        });
    }
}

