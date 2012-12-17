package ru.ifmo.niyaz.DataStructures;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 29.08.12
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class TrieList {
    public int[] head;
    public int[] letter;
    public int[] to;
    public int[] next;
    public int[] terminal;
    public int free;
    public int freeList;

    public TrieList(int[][] a) {
        int all = 1;
        for (int[] e : a) {
            all += e.length;
        }
        head = new int[all];
        Arrays.fill(head, -1);
        next = new int[all];
        letter = new int[all];
        to = new int[all];
        terminal = new int[all];
        Arrays.fill(terminal, -1);
        freeList = 0;
        free = 1;
        for (int i = 0; i < a.length; i++) {
            int v = 0;
            for (int j : a[i]) {
                v = append(v, j);
            }
            terminal[v] = i;
        }
    }

    public int append(int from, int letter) {
        int z = getLink(from, letter);
        if (z < 0) {
            setLink(from, letter, z = free++);
        }
        return z;
    }

    public int getLink(int from, int c) {
        int e = head[from];
        while (true) {
            int cur = e < 0 ? Integer.MAX_VALUE : letter[e];
            if (cur > c) {
                return -1;
            }
            if (cur == c) {
                return to[e];
            }
            e = next[e];
        }
    }

    public int setLink(int from, int c, int v) {
        int e = head[from];
        int last = -1;
        while (true) {
            int cur = e < 0 ? Integer.MAX_VALUE : letter[e];
            if (cur == c) {
                to[e] = v;
                return e;
            }
            if (cur > c) {
                if (last < 0) {
                    head[from] = freeList;
                } else {
                    next[last] = freeList;
                }
                next[freeList] = e;
                letter[freeList] = c;
                to[freeList] = v;
                return freeList++;
            }
            last = e;
            e = next[e];
        }
    }
}
