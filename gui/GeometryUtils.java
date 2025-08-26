package utils;

import java.awt.Point;
import java.util.List;

public class GeometryUtils {
    public static double distance(Point a, Point b) {
        return a.distance(b);
    }

    public static double angleAt(List<Point> pts, int i) {
        Point A = pts.get((i - 1 + pts.size()) % pts.size());
        Point B = pts.get(i);
        Point C = pts.get((i + 1) % pts.size());
        double a = distance(A, B);
        double b = distance(B, C);
        double c = distance(A, C);
        double cos = (a*a + b*b - c*c) / (2 * a * b);
        return Math.toDegrees(Math.acos(Math.max(-1, Math.min(1, cos))));
    }

    public static double area(List<Point> pts) {
        double sum = 0;
        for (int i = 0; i < pts.size(); i++) {
            Point A = pts.get(i);
            Point B = pts.get((i + 1) % pts.size());
            sum += A.x * B.y - B.x * A.y;
        }
        return Math.abs(sum) / 2;
    }

    public static double perimeter(List<Point> pts) {
        double p = 0;
        for (int i = 0; i < pts.size(); i++) {
            p += distance(pts.get(i), pts.get((i + 1) % pts.size()));
        }
        return p;
    }
}

