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

    public boolean containsOn(Point2DDouble p, DoubleComparator comp) {
        return comp.compare(p.distanceSquared(this.p), radius * radius) == 0;
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
//        if (dblComp.compare(z, 0) < 0) {
//            System.out.println(this + " " + c);
//            System.out.println(d + " " + (radius + c.radius));
//            throw new AssertionError();
//        }
        if (dblComp.compare(z, 0) <= 0) {
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
            return new Point2DDouble[]{q};
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

    public Point2DDouble[][] getCommonTangents(Circle2DDouble c, DoubleComparator comp) {
        Point2DDouble[][] internalRet = getInternalTangents(c, comp);
        Point2DDouble[][] externalRet = getExternalTangents(c, comp);
        Point2DDouble[][] ret = new Point2DDouble[internalRet.length + externalRet.length][];
        for (int i = 0; i < ret.length; i++) {
            if (i < internalRet.length) ret[i] = internalRet[i];
            else ret[i] = externalRet[i - internalRet.length];
        }
        return ret;
    }

    public Point2DDouble[][] getExternalTangents(Circle2DDouble c, DoubleComparator comp) {
        if (comp.compare(radius, c.radius) < 0) {
            Point2DDouble[][] ret = c.getExternalTangents(this, comp);
            for (Point2DDouble[] e : ret) {
                Point2DDouble t = e[0];
                e[0] = e[1];
                e[1] = t;
            }
            if (comp.compare(ret[0][0].distance(p), radius) != 0) throw new AssertionError();
            if (comp.compare(ret[1][0].distance(p), radius) != 0) throw new AssertionError();
            if (comp.compare(ret[0][1].distance(c.p), c.radius) != 0) throw new AssertionError();
            if (comp.compare(ret[1][1].distance(c.p), c.radius) != 0) throw new AssertionError();
            if (comp.compare(GeometryAlgorithms.distanceToLine(ret[0][0], ret[0][1], p), radius) != 0) throw new AssertionError();
            if (comp.compare(GeometryAlgorithms.distanceToLine(ret[0][0], ret[0][1], c.p), c.radius) != 0) throw new AssertionError();
            if (comp.compare(GeometryAlgorithms.distanceToLine(ret[1][0], ret[1][1], p), radius) != 0) throw new AssertionError();
            if (comp.compare(GeometryAlgorithms.distanceToLine(ret[1][0], ret[1][1], c.p), c.radius) != 0) throw new AssertionError();
            return ret;
        }
        double d = p.distance(c.p);
        if (comp.compare(d + Math.min(radius, c.radius), Math.max(radius, c.radius)) < 0) {
            return new Point2DDouble[0][];
        }
        if (comp.compare(d + Math.min(radius, c.radius), Math.max(radius, c.radius)) == 0) {
            Point2DDouble v = c.p.subtract(p);
            v = v.multiply(radius / v.length());
            v = v.add(p);
            return new Point2DDouble[][]{{v, v}};
        }
        Point2DDouble[] tangentsFromPoint = new Circle2DDouble(p, radius - c.radius).getTangents(c.p, comp);
        Point2DDouble vn1 = tangentsFromPoint[0].subtract(c.p);
        vn1 = new Point2DDouble(vn1.y, -vn1.x);
        vn1 = vn1.multiply(c.radius / vn1.length());
        Point2DDouble vn2 = tangentsFromPoint[1].subtract(c.p);
        vn2 = new Point2DDouble(-vn2.y, vn2.x);
        vn2 = vn2.multiply(c.radius / vn2.length());
        Point2DDouble[][] ret = new Point2DDouble[][]{{tangentsFromPoint[0].add(vn1), c.p.add(vn1)}, {tangentsFromPoint[1].add(vn2), c.p.add(vn2)}};
        if (comp.compare(ret[0][0].distance(p), radius) != 0) throw new AssertionError();
        if (comp.compare(ret[1][0].distance(p), radius) != 0) throw new AssertionError();
        if (comp.compare(ret[0][1].distance(c.p), c.radius) != 0) throw new AssertionError();
        if (comp.compare(ret[1][1].distance(c.p), c.radius) != 0) throw new AssertionError();
        if (comp.compare(GeometryAlgorithms.distanceToLine(ret[0][0], ret[0][1], p), radius) != 0) throw new AssertionError();
        if (comp.compare(GeometryAlgorithms.distanceToLine(ret[0][0], ret[0][1], c.p), c.radius) != 0) throw new AssertionError();
        if (comp.compare(GeometryAlgorithms.distanceToLine(ret[1][0], ret[1][1], p), radius) != 0) throw new AssertionError();
        if (comp.compare(GeometryAlgorithms.distanceToLine(ret[1][0], ret[1][1], c.p), c.radius) != 0) throw new AssertionError();
        return ret;
    }

    public Point2DDouble[][] getInternalTangents(Circle2DDouble c, DoubleComparator comp) {
        double d = p.distance(c.p);
        if (comp.compare(d, radius + c.radius) < 0) return new Point2DDouble[0][];
        if (comp.compare(d, radius + c.radius) == 0) {
            Point2DDouble v = c.p.subtract(p);
            v = v.multiply(radius / v.length());
            v = v.add(p);
            return new Point2DDouble[][]{{v, v}};
        }
        double nRadius = radius + c.radius;
        Point2DDouble[] tangentsFromPoint = new Circle2DDouble(p, nRadius).getTangents(c.p, comp);
        Point2DDouble vn1 = tangentsFromPoint[0].subtract(c.p);
        vn1 = new Point2DDouble(-vn1.y, vn1.x);
        vn1 = vn1.multiply(c.radius / vn1.length());
        Point2DDouble vn2 = tangentsFromPoint[1].subtract(c.p);
        vn2 = new Point2DDouble(vn2.y, -vn2.x);
        vn2 = vn2.multiply(c.radius / vn2.length());
        Point2DDouble[][] ret = new Point2DDouble[][]{{tangentsFromPoint[0].add(vn1), c.p.add(vn1)}, {tangentsFromPoint[1].add(vn2), c.p.add(vn2)}};
        if (comp.compare(ret[0][0].distance(p), radius) != 0) throw new AssertionError();
        if (comp.compare(ret[1][0].distance(p), radius) != 0) throw new AssertionError();
        if (comp.compare(ret[0][1].distance(c.p), c.radius) != 0) throw new AssertionError();
        if (comp.compare(ret[1][1].distance(c.p), c.radius) != 0) throw new AssertionError();
        return ret;
    }

    @Override
    public String toString() {
        return p.toString() + " " + radius;
    }
}
