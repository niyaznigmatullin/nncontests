package ru.ifmo.niyaz.DataStructures;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 17.02.13
 * Time: 1:44
 * To change this template use File | Settings | File Templates.
 */
public class AhoCorasick {
    int[][] g;
    int[] sufLink;
    int[] terminal;
    boolean[] hasTerminal;
    int free;
    boolean[] was;
    int alphabet;

    public AhoCorasick(int[][] a, int alphabet) {
        this.alphabet = alphabet;
        int len = 3;
        for (int[] d : a) {
            len += d.length;
        }
        g = new int[alphabet][len];
        for (int[] d : g) {
            Arrays.fill(d, -1);
        }
        sufLink = new int[len];
        was = new boolean[len];
        Arrays.fill(sufLink, -1);
        free = 1;
        terminal = new int[len];
        Arrays.fill(terminal, -1);
        hasTerminal = new boolean[len];
        for (int i = 0; i < a.length; i++) {
            add(a[i], i);
        }
        buildSuf();
    }

    int add(int[] s, int id) {
        int p = 0;
        for (int i = 0; i < s.length; i++) {
            int c = s[i];
            if (g[c][p] == -1) {
                g[c][p] = free++;
            }
            p = g[c][p];
        }
        terminal[p] = id;
        return p;
    }

    void buildSuf() {
        Queue<Integer> q = new ArrayDeque<Integer>();
        q.add(0);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int i = 0; i < alphabet; i++) {
                int u = g[i][v];
                int w = sufLink[v] == -1 ? 0 : g[i][sufLink[v]];
                if (u >= 0) {
                    sufLink[u] = w;
                    q.add(u);
                } else {
                    g[i][v] = w;
                }
            }
            hasTerminal[v] = terminal[v] >= 0;
            if (sufLink[v] >= 0 && hasTerminal[sufLink[v]]) {
                hasTerminal[v] = true;
            }
        }
    }

    public int getLink(int v, int c) {
        return g[c][v];
    }

    public int countNodes() {
        return free;
    }

    public boolean hasTerminal(int v) {
        return hasTerminal[v];
    }
}

