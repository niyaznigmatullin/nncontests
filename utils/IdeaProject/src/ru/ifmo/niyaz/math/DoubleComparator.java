package ru.ifmo.niyaz.math;

/**
 * Created with IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 11.01.13
 * Time: 22:28
 * To change this template use File | Settings | File Templates.
 */
public abstract class DoubleComparator {

    abstract public int compare(double a, double b);

    public int sign(double x) {
        return compare(x, 0);
    }
}
