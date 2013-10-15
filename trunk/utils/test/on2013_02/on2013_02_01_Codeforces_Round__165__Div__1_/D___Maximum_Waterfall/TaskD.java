package lib.test.on2013_02.on2013_02_01_Codeforces_Round__165__Div__1_.D___Maximum_Waterfall;



import ru.ifmo.niyaz.graphalgorithms.DijkstraGraph;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskD {
    static class Panel {
        int h;
        int left;
        int right;
        int id;
        Panel ln;
        Panel rn;

        Panel(int h, int left, int right, int id) {
            this.h = h;
            this.left = left;
            this.right = right;
            this.id = id;
            ln = NONE;
            rn = NONE;
        }

        void setLeft(Panel p) {
            if (ln == null) {
                return;
            }
            if (ln == NONE) {
                ln = p;
            } else if (ln != p) {
                ln = null;
            }
        }

        void setRight(Panel p) {
            if (rn == null) {
                return;
            }
            if (rn == NONE) {
                rn = p;
            } else if (rn != p) {
                rn = null;
            }
        }

        int intersect(Panel e) {
            return Math.min(right, e.right) - Math.max(left, e.left);
        }
    }

    static Panel NONE = new Panel(0, 0, 0, -1);

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int t = in.nextInt();
        Panel[] a = new Panel[n + 2];
        for (int i = 0; i < n; i++) {
            a[i] = new Panel(in.nextInt(), in.nextInt(), in.nextInt(), i);
        }
        final int INF = 1000000000;
        a[n] = new Panel(t, -INF, INF, n);
        a[n + 1] = new Panel(0, -INF, INF, n + 1);
        n += 2;
        Panel[] enter = a.clone();
        Panel[] exit = a.clone();
        Arrays.sort(enter, new Comparator<Panel>() {
            @Override
            public int compare(Panel o1, Panel o2) {
                return o1.left - o2.left;
            }
        });
        Arrays.sort(exit, new Comparator<Panel>() {
            @Override
            public int compare(Panel o1, Panel o2) {
                return o1.right - o2.right;
            }
        });
        DijkstraGraph g = new DijkstraGraph(n);
        NavigableSet<Panel> set = new TreeSet<Panel>(new Comparator<Panel>() {
            @Override
            public int compare(Panel o1, Panel o2) {
                return o1.h - o2.h;
            }
        });
        int remove = 0;
        for (Panel e : enter) {
            while (remove < n && exit[remove].right <= e.left) {
                Panel f = exit[remove];
                set.remove(f);
                Panel left = set.higher(f);
                Panel right = set.lower(f);
                if (f.ln == null && left != null && left.rn == f) {
                    g.addEdge(left.id, f.id, left.intersect(f));
                    left.rn = NONE;
                } else if (f.ln != NONE && f.ln != null) {
                    g.addEdge(f.ln.id, f.id, f.ln.intersect(f));
                }
                if (f.rn == null && right != null && right.ln == f) {
                    g.addEdge(f.id, right.id, right.intersect(f));
                    right.ln = NONE;
                } else if (f.rn != NONE && f.rn != null) {
                    g.addEdge(f.id, f.rn.id, f.rn.intersect(f));
                }
//                if (left != null) {
//                    f.setLeft(left);
//                    left.setRight(f);
//
//                    System.out.println("neigh " + f.id + " " + left.id);
//                }
//                if (right != null) {
//                    f.setRight(right);
//                    right.setLeft(f);
//                    System.out.println("neigh " + f.id + " " + right.id);
//                }
//                if (left != null && right != null) {
//                    left.setRight(right);
//                    right.setLeft(left);
//                }
                remove++;
//                System.out.println("remove " + f.id);
            }
            Panel left = set.higher(e);
            Panel right = set.lower(e);
            if (left != null) {
//                System.out.println("neigh " + e.id + " " + left.id);
                e.setLeft(left);
                left.setRight(e);
            }
            if (right != null) {
//                System.out.println("neigh " + e.id + " " + right.id);
                e.setRight(right);
                right.setLeft(e);
            }
            set.add(e);
//            System.out.println("add " + e.id);
        }
//        for (int i = 0; i < n; i++) {
//            Panel e = a[i];
//            if (e.ln != NONE && e.ln != null) {
//                int v = e.intersect(e.ln);
//                g.addEdge(e.ln.id, i, v);
////                System.out.println(e.ln.id + " " + i + " " + v);
//            }
//            if (e.rn != NONE && e.rn != null) {
//                int v = e.intersect(e.rn);
//                g.addEdge(i, e.rn.id, v);
////                System.out.println(i + " " + e.rn.id + " " + v);
//            }
//        }
        while (remove < n) {
            Panel f = exit[remove];
            set.remove(f);
            Panel left = set.higher(f);
            Panel right = set.lower(f);
            if (f.ln == null && left != null && left.rn == f) {
                g.addEdge(left.id, f.id, left.intersect(f));
                left.rn = NONE;
            } else if (f.ln != NONE && f.ln != null) {
                g.addEdge(f.ln.id, f.id, f.ln.intersect(f));
            }
            if (f.rn == null && right != null && right.ln == f) {
                g.addEdge(f.id, right.id, right.intersect(f));
                right.ln = NONE;
            } else if (f.rn != NONE && f.rn != null) {
                g.addEdge(f.id, f.rn.id, f.rn.intersect(f));
            }
//                if (left != null) {
//                    f.setLeft(left);
//                    left.setRight(f);
//
//                    System.out.println("neigh " + f.id + " " + left.id);
//                }
//                if (right != null) {
//                    f.setRight(right);
//                    right.setLeft(f);
//                    System.out.println("neigh " + f.id + " " + right.id);
//                }
//                if (left != null && right != null) {
//                    left.setRight(right);
//                    right.setLeft(left);
//                }
            remove++;
//                System.out.println("remove " + f.id);
        }
        long[] z = g.dijkstraMaxMin(n - 2);
        out.println(z[n - 1]);
    }
}