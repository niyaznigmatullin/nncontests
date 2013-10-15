package ru.ifmo.niyaz.DataStructures;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 13.02.12
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class DisjointSetUnionFast {
    int[] p;
    int[] r;

    public DisjointSetUnionFast(int n) {
        p = new int[n];
        r = new int[n];
        clear();
    }

    public void clear() {
        for (int i = 0; i < p.length; i++) {
            p[i] = i;
            r[i] = 0;
        }
    }

    public int get(int x) {
        return x != p[x] ? p[x] = get(p[x]) : x;
    }

    public boolean union(int a, int b) {
        a = get(a);
        b = get(b);
        if (a == b) {
            return false;
        }
        if (r[a] >= r[b]) {
            if (r[a] == r[b]) r[a]++;
            p[b] = a;
        } else {
            p[a] = b;
        }
        return true;
    }
}
