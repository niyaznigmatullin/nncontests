package ru.ifmo.niyaz.geometry;

import ru.ifmo.niyaz.math.DoubleComparator;

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

    public Point2DDouble[] intersect(Circle2DDouble c, DoubleComparator dblComp) {
        double d = p.distance(c.p);
        if (dblComp.compare(d, radius + c.radius) > 0 ||
                dblComp.compare(d + Math.min(radius, c.radius), Math.max(radius, c.radius)) < 0) {
            return new Point2DDouble[0];
        }
        if (dblComp.compare(d, 0) == 0) {
            return null;
        }
        double diff = (radius * radius - c.radius * c.radius) / d;
        double d1 = (d + diff) * .5;
        Point2DDouble v = c.p.subtract(p);
        Point2DDouble v1 = v.multiply(d1 / v.length());
        double z = radius * radius - d1 * d1;
        Point2DDouble p0 = p.add(v1);
        if (dblComp.compare(z, 0) < 0) {
            throw new AssertionError();
        }
        if (dblComp.compare(z, 0) == 0) {
            return new Point2DDouble[]{p0};
        }
        z = Math.sqrt(z);
        Point2DDouble v2 = new Point2DDouble(-v.y, v.x).multiply(z / v.length());
        return new Point2DDouble[]{p0.add(v2), p0.subtract(v2)};
    }

    public Point2DDouble[] getTangents(Point2DDouble q, DoubleComparator comp) {
        double d = p.distance(q);
        if (comp.compare(d, radius) < 0) {
            return null;
        }
        if (comp.compare(d, radius) == 0) {
            return new Point2DDouble[]{p};
        }
        double d1 = radius * radius / d;
        Point2DDouble v = q.subtract(p);
        Point2DDouble vn = new Point2DDouble(-v.y, v.x);
        v = v.multiply(d1 / v.length());
        Point2DDouble c = p.add(v);
        double d2 = radius * radius - d1 * d1;
        if (d2 < 0) d2 = 0;
        d2 = Math.sqrt(d2);
        vn = vn.multiply(d2 / vn.length());
        return new Point2DDouble[]{c.add(vn), c.subtract(vn)};
    }
}
