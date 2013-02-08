package ru.ifmo.niyaz.graphalgorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: niyaznigmatul
 * Date: 14.01.12
 * Time: 17:50
 * To change this template use File | Settings | File Templates.
 */
public class KuhnMatchingGraph {

    int n;
    int m;
    List<Integer>[] edges;
    int[] p1;
    int[] p2;
    int[] was;
    int VER;

    public KuhnMatchingGraph(int n, int m) {
        this.n = n;
        this.m = m;
        edges = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<Integer>(2);
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
    }

    public int[] getPaired1() {
        return p1;
    }

    public int[] getPaired2() {
        return p2;
    }

    public int getMaximalMatching() {
        p1 = new int[n];
        p2 = new int[m];
        was = new int[n];
        VER = 0;
        Arrays.fill(p1, -1);
        Arrays.fill(p2, -1);
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j : edges[i]) {
                if (p2[j] < 0) {
                    p2[j] = i;
                    p1[i] = j;
                    answer++;
                    break;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (p1[i] >= 0) {
                continue;
            }
            VER++;
            if (dfs(i)) {
                answer++;
            }
        }
        return answer;
    }

    public List<Integer>[] getEdges() {
        return edges;
    }

    boolean dfs(int v) {
        if (was[v] == VER) {
            return false;
        }
        was[v] = VER;
        for (int i = 0; i < edges[v].size(); i++) {
            int e = edges[v].get(i);
            if (p2[e] < 0 || dfs(p2[e])) {
                p2[e] = v;
                p1[v] = e;
                return true;
            }
        }
        return false;
    }
}
