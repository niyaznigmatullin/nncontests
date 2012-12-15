package DataStructures;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: niyaz.nigmatullin
 * Date: 29.08.12
 * Time: 19:30
 * To change this template use File | Settings | File Templates.
 */
public class SuffixTree {
    public int[][] link;
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
    public int alphabet;

    public SuffixTree(int[] a, int alphabet) {
        this.str = a;
        this.alphabet = alphabet;
        int all = a.length * 2 + 1;
        link = new int[alphabet][all];
        suf = new int[all];
        parent = new int[all];
        depth = new int[all];
        start = new int[all];
        Arrays.fill(suf, -1);
        for (int[] d : link) {
            Arrays.fill(d, -1);
        }
        cur = newNode(0, 0, 0);
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
                link[str[start[cur] + len]][u] = cur;
                link[str[start[cur]]][parent[cur]] = u;
                start[cur] += len;
                parent[cur] = u;
                cur = u;
                created = true;
            }
        }
        if (depth[cur] == right - left) {
            if (link[str[right]][cur] < 0) {
                link[str[right]][cur] = newNode(cur, -1, right);
                created = true;
            }
            cur = link[str[right]][cur];
        }
    }
}
