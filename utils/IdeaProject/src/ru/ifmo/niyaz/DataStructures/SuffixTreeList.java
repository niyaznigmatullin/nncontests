package ru.ifmo.niyaz.DataStructures;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 29.08.12
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class SuffixTreeList {
    public int[] head;
    public int[] letter;
    public int[] to;
    public int[] next;
    public int[] suf;
    public int[] start;
    public int[] parent;
    public int[] depth;
    public int[] str;
    public int free;
    int cur;
    int left;
    int alone;
    boolean created;
    public int freeList;

    public SuffixTreeList(int[] a) {
        this.str = a;
        int all = a.length * 2 + 1;
        suf = new int[all];
        parent = new int[all];
        depth = new int[all];
        start = new int[all];
        Arrays.fill(suf, -1);
        head = new int[all];
        Arrays.fill(head, -1);
        next = new int[all];
        letter = new int[all];
        to = new int[all];
        cur = newNode(0, 0, 0);
        freeList = 0;
        free = 1;
        suf[cur] = 0;
        left = 0;
        for (int right = 0; right < a.length; right++) {
            while (left <= right) {
                go(right);
                if (alone >= 0) {
                    suf[alone] = parent[cur];
                    alone = -1;
                }
                if (!created) {
                    break;
                }
                created = false;
                cur = parent[cur];
                ++left;
                if (suf[cur] < 0) {
                    alone = cur;
                    cur = parent[cur];
                }
                cur = suf[cur];
                while (depth[cur] >= 0 && depth[cur] < right - left) {
                    go(left + depth[cur]);
                }
            }
        }
    }

    int newNode(int p, int d, int st) {
        parent[free] = p;
        depth[free] = d;
        start[free] = st;
        return free++;
    }


    void go(int right) {
        if (depth[cur] != right - left) {
            int len = right - left - depth[parent[cur]];
            if (str[right] != str[start[cur] + len]) {
                int u = newNode(parent[cur], right - left, start[cur]);
                setLink(u, str[start[cur] + len], cur);
                setLink(parent[cur], str[start[cur]], u);
                start[cur] += len;
                parent[cur] = u;
                cur = u;
                created = true;
            }
        }
        if (depth[cur] == right - left) {
            int z = getLink(cur, str[right]);
            if (z < 0) {
                setLink(cur, str[right], z = newNode(cur, -1, right));
                created = true;
            }
            cur = z;
        }
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
