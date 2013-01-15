package ru.ifmo.niyaz.geometry;

/**
 * Created with IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 14.01.13
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
public class Point3DDouble {
    public double x;
    public double y;
    public double z;

    public Point3DDouble(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3DDouble vmul(Point3DDouble p) {
        return new Point3DDouble(y * p.z - z * p.y, z * p.x - x * p.z, x * p.y - y * p.x);
    }

    public double smul(Point3DDouble p) {
        return x * p.x + y * p.y + z * p.z;
    }

    public Point3DDouble add(Point3DDouble p) {
        return new Point3DDouble(x + p.x, y + p.y, z + p.z);
    }

    public Point3DDouble multiply(double d) {
        return new Point3DDouble(x * d, y * d, z * d);
    }

    public double distance(Point3DDouble p) {
        return Math.sqrt(distanceSquared(p));
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public double lengthSquared() {
        return x * x + y * y + z * z;
    }

    public double distanceSquared(Point3DDouble p) {
        double dx = x - p.x;
        double dy = y - p.y;
        double dz = z - p.z;
        return dx * dx + dy * dy + dz * dz;
    }

    public Point3DDouble subtract(Point3DDouble p) {
        return new Point3DDouble(x - p.x, y - p.y, z - p.z);
    }

    @Override
    public String toString() {
        return "Point3DDouble{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}
