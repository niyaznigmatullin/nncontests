package ru.ifmo.niyaz.DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 13.02.12
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class DisjointSetUnion {
    int[] p;

    public DisjointSetUnion(int n) {
        p = new int[n];
        clear();
    }

    public void clear() {
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
        }
    }

    public int get(int x) {
        return x != p[x] ? p[x] = get(p[x]) : x;
    }

    public boolean union(int a, int b) {
        a = get(a);
        b = get(b);
        p[a] = b;
        return a == b;
    }
}
