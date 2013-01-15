package ru.ifmo.niyaz.geometry;

/**
 * Created with IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 14.01.13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class Circle2DDouble {
    public Point2DDouble p;
    public double radius;

    public Circle2DDouble(Point2DDouble p, double radius) {
        this.p = p;
        this.radius = radius;
    }

    public double length() {
        return 2 * Math.PI * radius;
    }

    public double area() {
        return Math.PI * radius * radius;
    }
}
