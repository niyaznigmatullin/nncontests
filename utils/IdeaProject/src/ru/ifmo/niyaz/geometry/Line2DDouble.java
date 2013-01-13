package ru.ifmo.niyaz.geometry;

import ru.ifmo.niyaz.math.DoubleComparator;

/**
 * Created with IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 11.01.13
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */
public class Line2DDouble {

    public double a;
    public double b;
    public double c;

    public Line2DDouble(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static Line2DDouble getThroughTwoPoints(Point2DDouble first, Point2DDouble second) {
        double a = second.y - first.y;
        double b = first.x - second.x;
        double c = -first.x * a - first.y * b;
        return new Line2DDouble(a, b, c);
    }

    public static Line2DDouble getPerpendicularBisection(Point2DDouble first, Point2DDouble second) {
        double a = second.x - first.x;
        double b = second.y - first.y;
        double c = -a * (second.x + first.x) - b * (second.y + first.y);
        c *= .5;
        return new Line2DDouble(a, b, c);
    }

    public Point2DDouble intersect(Line2DDouble line, DoubleComparator comparator) {
        double z = a * line.b - b * line.a;
        if (comparator.compare(z, 0) == 0) {
            return null;
        }
        return new Point2DDouble((b * line.c - c * line.b) / z, (c * line.a - a * line.c) / z);
    }

}
