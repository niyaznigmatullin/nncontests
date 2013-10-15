package ru.ifmo.niyaz.DataStructures;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz
 * Date: 12.06.13
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class SuffixAutomaton {
    public int[][] link;
    public int[] sufLink;
    public int[] length;
    public int free;

    public SuffixAutomaton(int[][] a, int alphabet) {
        int all = 1;
        for (int[] i : a) {
            all += i.length;
        }
        all = all * 2 + 1;
        link = new int[alphabet][all];
        sufLink = new int[all];
        length = new int[all];
        for (int[] d : link) {
            Arrays.fill(d, -1);
        }
        Arrays.fill(sufLink, -1);
        free = 0;
        int root = newNode(0);
        for (int[] d : a) {
            int v = root;
            for (int i : d) {
                v = append(v, i);
            }
        }
    }

    int newNode(int len) {
        length[free] = len;
        return free++;
    }

    int copyNode(int v, int len) {
        length[free] = len;
        for (int i = 0; i < link.length; i++) {
            link[i][free] = link[i][v];
        }
        sufLink[free] = sufLink[v];
        return free++;
    }

    int append(int v, int c) {
        if (link[c][v] >= 0) {
            int q = link[c][v];
            if (length[q] == length[v] + 1) {
                return q;
            }
            int copy = copyNode(q, length[v] + 1);
            while (v >= 0 && link[c][v] == q) {
                link[c][v] = copy;
                v = sufLink[v];
            }
            sufLink[q] = copy;
            return copy;
        }
        int u = newNode(length[v] + 1);
        while (v >= 0 && link[c][v] < 0) {
            link[c][v] = u;
            v = sufLink[v];
        }
        if (v < 0) {
            sufLink[u] = 0;
            return u;
        }
        int q = link[c][v];
        if (length[q] == length[v] + 1) {
            sufLink[u] = q;
            return u;
        }
        int copy = copyNode(q, length[v] + 1);
        while (v >= 0 && link[c][v] == q) {
            link[c][v] = copy;
            v = sufLink[v];
        }
        sufLink[q] = sufLink[u] = copy;
        return u;
    }
}
