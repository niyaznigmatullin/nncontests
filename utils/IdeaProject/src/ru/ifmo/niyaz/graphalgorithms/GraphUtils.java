package ru.ifmo.niyaz.graphalgorithms;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 25.12.12
 * Time: 12:18
 * To change this template use File | Settings | File Templates.
 */
public class GraphUtils {
    public static int[][] getEdgesUndirectedUnweighted(int n, int[] v, int[] u) {
        int[][] edges = new int[n][];
        int[] deg = getDegreeUndirected(n, v, u);
        for (int i = 0; i < n; i++) {
            edges[i] = new int[deg[i]];
        }
        int m = v.length;
        Arrays.fill(deg, 0);
        for (int i = 0; i < m; i++) {
            edges[v[i]][deg[v[i]]++] = u[i];
            edges[u[i]][deg[u[i]]++] = v[i];
        }
        return edges;
    }

    public static int[][] getEdgesUndirectedWeighted(int n, int[] v, int[] u) {
        int[][] edges = new int[n][];
        int[] deg = getDegreeUndirected(n, v, u);
        for (int i = 0; i < n; i++) {
            edges[i] = new int[deg[i]];
        }
        int m = v.length;
        Arrays.fill(deg, 0);
        for (int i = 0; i < m; i++) {
            edges[v[i]][deg[v[i]]++] = i;
            edges[u[i]][deg[u[i]]++] = i;
        }
        return edges;
    }

    public static int[] getDegreeUndirected(int n, int[] v, int[] u) {
        int[] deg = new int[n];
        for (int i : v) {
            deg[i]++;
        }
        for (int i : u) {
            deg[i]++;
        }
        return deg;
    }

    public static int[] getOutcomingDegree(int n, int[] v, int[] u) {
        int[] deg = new int[n];
        int m = v.length;
        for (int i = 0; i < m; i++) {
            deg[v[i]]++;
        }
        return deg;
    }


    public static int[] getIncomingDegree(int n, int[] v, int[] u) {
        int[] deg = new int[n];
        int m = v.length;
        for (int i = 0; i < m; i++) {
            deg[u[i]]++;
        }
        return deg;
    }

    public static int[][] getEdgesDirectedUnweighted(int n, int[] v, int[] u) {
        int[][] edges = new int[n][];
        int[] deg = getOutcomingDegree(n, v, u);
        for (int i = 0; i < n; i++) {
            edges[i] = new int[deg[i]];
        }
        int m = v.length;
        Arrays.fill(deg, 0);
        for (int i = 0; i < m; i++) {
            edges[v[i]][deg[v[i]]++] = u[i];
        }
        return edges;
    }

    public static int[][] getEdgesDirectedWeighted(int n, int[] v, int[] u) {
        int[][] edges = new int[n][];
        int[] deg = getOutcomingDegree(n, v, u);
        for (int i = 0; i < n; i++) {
            edges[i] = new int[deg[i]];
        }
        int m = v.length;
        Arrays.fill(deg, 0);
        for (int i = 0; i < m; i++) {
            edges[v[i]][deg[v[i]]++] = i;
        }
        return edges;
    }

}
