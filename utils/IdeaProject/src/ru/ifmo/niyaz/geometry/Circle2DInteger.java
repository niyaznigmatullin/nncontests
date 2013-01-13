package ru.ifmo.niyaz.geometry;

/**
 * Created with IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 06.01.13
 * Time: 0:24
 * To change this template use File | Settings | File Templates.
 */
public class Circle2DInteger {
    public Point2DInteger p;
    public int radius;

    public Circle2DInteger(Point2DInteger p, int radius) {
        this.p = p;
        this.radius = radius;
    }

    public double length() {
        return Math.PI * 2 * radius;
    }

}
