package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import utils.GeometryUtils;

public class PlotPanel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener {
    private ArrayList<Point> points = new ArrayList<>();
    private int draggedIndex = -1;
    private double scale = 1.0;
    private boolean showGrid = true;

    public PlotPanel() {
        setBackground(Color.WHITE);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
    }

    public void toggleGrid() {
        showGrid = !showGrid;
        repaint();
    }

    public void reset() {
        points.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth(), h = getHeight();
        Graphics2D g2 = (Graphics2D) g.create();

        // Transform coordinate system to Cartesian
        g2.translate(w / 2.0, h / 2.0);
        g2.scale(scale, -scale);

        if (showGrid) drawGrid(g2, w, h);

        drawPolygon(g2, w, h);

        g2.dispose();
    }

    private void drawGrid(Graphics2D g2, int w, int h) {
        g2.setColor(Color.LIGHT_GRAY);
        int step = 25;
        int maxX = (int)(w / (2 * scale));
        int maxY = (int)(h / (2 * scale));
        for (int x = -maxX; x <= maxX; x++) g2.drawLine(x*step, -maxY*step, x*step, maxY*step);
        for (int y = -maxY; y <= maxY; y++) g2.drawLine(-maxX*step, y*step, maxX*step, y*step);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(-maxX*step, 0, maxX*step, 0);
        g2.drawLine(0, -maxY*step, 0, maxY*step);
    }

    private void drawPolygon(Graphics2D g2, int w, int h) {
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLUE);
        for (int i = 0; i < points.size(); i++) {
            Point a = points.get(i);
            Point b = points.get((i + 1) % points.size());
            if (points.size() > 2 || i < points.size() - 1)
                g2.drawLine(a.x, a.y, b.x, b.y);
        }

        for (int i = 0; i < points.size(); i++) {
            Point p = points.get(i);
            g2.setColor(Color.BLACK);
            g2.fillOval(p.x - 4, p.y - 4, 8, 8);
            g2.scale(1, -1);
            g2.drawString("(" + p.x + "," + p.y + ")", p.x + 6, -(p.y) - 6);
            g2.scale(1, -1);
        }

        if (points.size() >= 3) {
            double area = GeometryUtils.area(points);
            double perimeter = GeometryUtils.perimeter(points);
            g2.scale(1, -1);
            g2.setColor(Color.RED);
            g2.setFont(new Font("Courier", Font.BOLD, 14));
            g2.drawString("Area: " + String.format("%.2f units²", area), -w/2+20, -h/2+40);
            g2.drawString("Peri: " + String.format("%.2f units", perimeter), -w/2+20, -h/2+60);
            g2.scale(1, -1);

            g2.setColor(Color.GREEN.darker());
            g2.setFont(new Font("SansSerif", Font.BOLD, 12));
            for (int i = 0; i < points.size(); i++) {
                double angle = GeometryUtils.angleAt(points, i);
                Point p = points.get(i);
                g2.scale(1, -1);
                g2.drawString(String.format("%.1f°", angle), p.x + 10, -(p.y) + 20);
                g2.scale(1, -1);
            }

            g2.setColor(Color.MAGENTA.darker());
            for (int i = 0; i < points.size(); i++) {
                Point a = points.get(i);
                Point b = points.get((i + 1) % points.size());
                double len = GeometryUtils.distance(a, b);
                int mx = (a.x + b.x)/2, my = (a.y + b.y)/2;
                g2.scale(1, -1);
                g2.drawString(String.format("%.1f", len), mx + 5, -(my) - 5);
                g2.scale(1, -1);
            }
        }
    }

    private Point screenToWorld(Point e) {
        int w = getWidth(), h = getHeight();
        double wx = (e.x - w/2.0) * (1/scale);
        double wy = -(e.y - h/2.0) * (1/scale);
        return new Point((int)wx, (int)wy);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point wp = screenToWorld(e.getPoint());
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).distance(wp) <= 5 / scale) {
                draggedIndex = i;
                return;
            }
        }
        points.add(wp);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (draggedIndex >= 0) {
            points.set(draggedIndex, screenToWorld(e.getPoint()));
            repaint();
        }
    }

    @Override public void mouseReleased(MouseEvent e) { draggedIndex = -1; }
    @Override public void mouseWheelMoved(MouseWheelEvent e) {
        scale *= e.getPreciseWheelRotation() < 0 ? 1.1 : 0.9; repaint();
    }
    // Unused:
    @Override public void mouseMoved(MouseEvent e) {}
    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}

