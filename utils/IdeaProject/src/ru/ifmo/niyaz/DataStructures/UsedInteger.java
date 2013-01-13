package ru.ifmo.niyaz.DataStructures;

/**
 * Created with IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 31.12.12
 * Time: 15:53
 * To change this template use File | Settings | File Templates.
 */
public class UsedInteger {
    int[] used;
    int version;

    public UsedInteger(int n) {
        used = new int[n];
        clear();
    }

    public void clear() {
        ++version;
    }

    public boolean get(int x) {
        return used[x] == version;
    }

    public void set(int x) {
        used[x] = version;
    }

    public void unSet(int x) {
        used[x] = version - 1;
    }
}
