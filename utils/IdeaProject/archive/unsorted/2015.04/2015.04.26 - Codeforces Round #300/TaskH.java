package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.DataStructures.MinSegmentTree;
import ru.ifmo.niyaz.graphalgorithms.GraphUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskH {

    static int[][] edges;
    static int[] color;
    static int[] vs;
    static int cnv;

    static boolean dfs(int v, int c) {
        color[v] = c;
        vs[cnv++] = v;
        for (int i : edges[v]) {
            if (color[i] >= 0 && color[i] == c) {
                return false;
            }
            if (color[i] < 0) {
                if (!dfs(i, c ^ 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Component {
        int left1;
        int right1;
        int left2;
        int right2;
        List<Integer> first;
        List<Integer> second;

        public Component(int left1, int right1, int left2, int right2, List<Integer> first, List<Integer> second) {
            if (right1 > right2 || right1 == right2 && left1 > left2) {
                int t = left1;
                left1 = left2;
                left2 = t;
                t = right1;
                right1 = right2;
                right2 = t;
                List<Integer> tt = first;
                first = second;
                second = tt;
            }
            this.first = first;
            this.second = second;
            this.left1 = left1;
            this.right1 = right1;
            this.left2 = left2;
            this.right2 = right2;
        }
    }

    static final int INF = 1 << 30;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t1 = in.nextInt();
        int t2 = in.nextInt();
        int n = in.nextInt();
        int m = in.nextInt();
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0; i < n; i++) {
            left[i] = in.nextInt();
            right[i] = in.nextInt();
        }
        int[] from = new int[m];
        int[] to = new int[m];
        for (int i = 0; i < m; i++) {
            from[i] = in.nextInt() - 1;
            to[i] = in.nextInt() - 1;
        }
        vs = new int[n];
        edges = GraphUtils.getEdgesUndirectedUnweighted(n, from, to);
        color = new int[n];
        Arrays.fill(color, -1);
        List<Component> componentList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (color[i] >= 0) continue;
            cnv = 0;
            if (!dfs(i, 0)) {
                out.println("IMPOSSIBLE");
                return;
            }
            int l1 = 0;
            int r1 = INF;
            int l2 = 0;
            int r2 = INF;
            List<Integer> first = new ArrayList<>();
            List<Integer> second = new ArrayList<>();
            for (int j = 0; j < cnv; j++) {
                int v = vs[j];
                if (color[v] == 0) {
                    l1 = Math.max(l1, left[v]);
                    r1 = Math.min(r1, right[v]);
                    first.add(v);
                } else {
                    l2 = Math.max(l2, left[v]);
                    r2 = Math.min(r2, right[v]);
                    second.add(v);
                }
            }
            componentList.add(new Component(l1, r1 + 1, l2, r2 + 1, first, second));
        }
//        int l1 = Integer.MIN_VALUE;
//        int r1 = Integer.MAX_VALUE;
//        int l2 = Integer.MIN_VALUE;
//        int r2 = Integer.MAX_VALUE;
//        for (Component c : componentList) {
//            l1 = Math.max(l1, c.left1);
//            r1 = Math.min(r1, c.right1 - 1);
//            l2 = Math.max(l2, c.left2);
//            r2 = Math.min(r2, c.right2 - 1);
//        }
//        int currentLeft = t1 - r1;
//        int currentRight = t2 - l1;
//        if (Math.max(l2, currentLeft) <= Math.min(r2, currentRight)) {
//            int get1 = t1 - Math.max(l2, currentLeft);
//            int get2 = Math.max(l2, currentLeft);
//            out.println("POSSIBLE");
//            out.println(get1 + " " + get2);
//            char[] c = new char[n];
//            for (int i = 0; i < componentList.size(); i++) {
//                for (int j : componentList.get(i).first) c[j] = '1';
//                for (int j : componentList.get(i).second) c[j] = '2';
//            }
//            out.println(c);
//            return;
//        } else {
//            out.println("IMPOSSIBLE");
//        }
//        if (true) return;
        Event[] events = new Event[componentList.size() * 4];
        for (int i = 0; i < componentList.size(); i++) {
            Component c = componentList.get(i);
            events[i * 4] = new Event(c.left1, i);
            events[i * 4 + 1] = new Event(c.left2, i);
            events[i * 4 + 2] = new Event(c.right1, i);
            events[i * 4 + 3] = new Event(c.right2, i);
        }
        Arrays.sort(events);
        int last = 0;
        MaxSegmentTree treeLeft = new MaxSegmentTree(componentList.size());
        MinSegmentTree treeRight = new MinSegmentTree(componentList.size());
        for (int i = 0; i < componentList.size(); i++) {
            treeLeft.set(i, INF);
            treeRight.set(i, -INF);
        }
        int[] state = new int[n];
        for (int it = 0; it < events.length; ) {
            int globalLeft = treeLeft.getMax(0, componentList.size());
            int globalRight = treeRight.getMin(0, componentList.size());
            int x = events[it].x;
            System.out.println(globalLeft + " " + globalRight + " " + x);
            int currentLeft = t1 - (x - 1);
            int currentRight = t2 - last;
            if (Math.max(globalLeft, currentLeft) <= Math.min(globalRight, currentRight)) {
                int get1 = Math.max(last, t1 - Math.max(globalLeft, currentLeft));
                int get2 = Math.max(globalLeft, currentLeft);
                out.println("POSSIBLE");
                out.println(get1 + " " + get2);
                char[] ans = new char[n];
                for (int i = 0; i < componentList.size(); i++) {
                    for (int j : componentList.get(i).first) if (state[i] == 1) ans[j] = '1'; else ans[j] = '2';
                    for (int j : componentList.get(i).second) if (state[i] == 2) ans[j] = '1'; else ans[j] = '2';
                }
                out.println(ans);
                return;
            }
            while (it < events.length && events[it].x == x) {
                int id = events[it].id;
                Component c = componentList.get(id);
                int count = 0;
                if (c.left1 <= x && x < c.right1) count |= 1;
                if (c.left2 <= x && x < c.right2) count |= 2;
                if (count == 0) {
                    treeLeft.set(id, INF);
                    treeRight.set(id, -INF);
                } else if (count == 1 || count == 3) {
                    state[id] = 1;
                    treeLeft.set(id, c.left2);
                    treeRight.set(id, c.right2 - 1);
                } else {
                    state[id] = 2;
                    treeLeft.set(id, c.left1);
                    treeRight.set(id, c.right1 - 1);
                }
                ++it;
            }
        }
        out.println("IMPOSSIBLE");
    }

    static class Event implements  Comparable<Event> {
        int x;
        int id;

        public Event(int x, int id) {
            this.x = x;
            this.id = id;
        }

        @Override
        public int compareTo(Event o) {
            return Integer.compare(x, o.x);
        }
    }
}
