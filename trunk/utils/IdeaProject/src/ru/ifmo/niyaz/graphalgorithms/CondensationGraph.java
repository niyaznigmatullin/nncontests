package ru.ifmo.niyaz.graphalgorithms;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 03.03.12
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class CondensationGraph {
    int n;
    List<Integer>[] edges;
    List<Integer>[] revEdges;
    boolean[] was;
    int[] color;

    public CondensationGraph(int n) {
        this.n = n;
        edges = new List[n];
        revEdges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>();
            revEdges[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
        edges[to].add(from);
    }


    void fillTopSort(int v, List<Integer> topSort) {
        was[v] = true;
        for (int i : edges[v]) {
            if (was[i]) {
                continue;
            }
            fillTopSort(i, topSort);
        }
        topSort.add(v);
    }

    void color(int v, int c) {
        color[v] = c;
        for (int i : revEdges[v]) {
            if (color[i] < 0) {
                color(i, c);
            }
        }
    }

    public CondensationGraph getCondensationNoCopies() {
        was = new boolean[n];
        List<Integer> topSort = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (was[i]) {
                continue;
            }
            fillTopSort(i, topSort);
        }
        Collections.reverse(topSort);
        int colors = 0;
        color = new int[n];
        for (int i : topSort) {
            if (color[i] >= 0) {
                continue;
            }
            color(i, colors++);
        }
        Set<Integer>[] sets = new Set[colors];
        for (int i = 0; i < colors; i++) {
            sets[i] = new HashSet<Integer>();
        }
        for (int i = 0; i < n; i++) {
            for (int j : edges[i]) {
                if (color[i] != color[j]) {
                    edges[color[i]].add(color[j]);
                }
            }
        }
        CondensationGraph g = new CondensationGraph(colors);
        for (int i = 0; i < colors; i++) {
            for (int j : sets[colors]) {
                g.addEdge(i, j);
            }
        }
        return g;
    }

    List<Integer>[] getEdges;
}
