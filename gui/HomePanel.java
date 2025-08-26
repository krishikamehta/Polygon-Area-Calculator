package gui;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel title = new JLabel("Polygon Area Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Courier", Font.BOLD, 38));
        title.setForeground(Color.CYAN);
        add(title, BorderLayout.CENTER);

        JLabel author = new JLabel("By Krishika Mehta", SwingConstants.CENTER);
        author.setFont(new Font("SansSerif", Font.BOLD, 20));
        author.setForeground(Color.LIGHT_GRAY);
        add(author, BorderLayout.NORTH);

        JButton startBtn = new JButton("Start");
        startBtn.setFont(new Font("SansSerif", Font.BOLD, 24));
        startBtn.setBackground(Color.GREEN);
        startBtn.setFocusPainted(false);
        startBtn.addActionListener(e -> {
            PlotPanel plotPanel = new PlotPanel();
            JPanel container = new JPanel(new BorderLayout());
            container.add(plotPanel, BorderLayout.CENTER);
            container.add(new ControlPanel(frame, plotPanel), BorderLayout.SOUTH);
            frame.setContentPane(container);
            frame.revalidate();
        });

        JPanel buttonWrapper = new JPanel();
        buttonWrapper.setBackground(Color.BLACK);
        buttonWrapper.add(startBtn);
        add(buttonWrapper, BorderLayout.SOUTH);
    }
}
