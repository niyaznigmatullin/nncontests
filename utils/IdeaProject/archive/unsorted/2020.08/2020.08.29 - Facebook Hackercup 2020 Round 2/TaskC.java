package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import sun.reflect.generics.tree.Tree;

import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskC {

    static int[] readArray(FastScanner in, int n, int k, int mod) {
        int[] ret = new int[n];
        for (int i = 0; i < k; i++) {
            ret[i] = in.nextInt();
        }
        long a = in.nextInt();
        long b = in.nextInt();
        int c = in.nextInt();
        for (int i = k; i < n; i++) {
            ret[i] = (int) ((ret[i - 2] * a + ret[i - 1] * b + c) % mod);
        }
        return ret;
    }

    static class Item implements Comparable<Item> {
        final int weight;
        final int id;

        public Item(int weight, int id) {
            this.weight = weight;
            this.id = id;
        }

        @Override
        public int compareTo(Item o) {
            int c = Integer.compare(weight, o.weight);
            if (c != 0) return c;
            return Integer.compare(id, o.id);
        }
    }

    static class ItemLong implements Comparable<ItemLong> {
        final long weight;
        final int id;

        public ItemLong(long weight, int id) {
            this.weight = weight;
            this.id = id;
        }

        @Override
        public int compareTo(ItemLong o) {
            int c = Long.compare(weight, o.weight);
            if (c != 0) return c;
            return Integer.compare(id, o.id);
        }
    }

    static class Circle {
        int first;
        int second;
        int len;
        NavigableSet<Item> edges;
        NavigableSet<Item> firstEdges;
        NavigableSet<Item> secondEdges;
        long bestMinusOne;
        long bestOther;
        long sum;
        int[] weights;

        public Circle(int first, int second, int len) {
            if (first > second) {
                int t = first;
                first = second;
                second = t;
            }
            this.first = first;
            this.second = second;
            this.len = len;
            edges = new TreeSet<>();
            firstEdges = new TreeSet<>();
            secondEdges = new TreeSet<>();
            weights = new int[len];
            for (int i = 0; i < len; i++) {
                weights[i] = 1;
                Item item = new Item(1, i);
                edges.add(item);
                if (first <= i && i < second) {
                    secondEdges.add(item);
                } else {
                    firstEdges.add(item);
                }
            }
            sum = len;
            bestMinusOne = len - 1;
            if (first == second) {
                bestOther = Long.MAX_VALUE;
            } else {
                bestOther = -1;
            }
        }

        public void changeEdge(int id, int newWeight) {
            int oldWeight = weights[id];
            sum -= oldWeight;
            Item oldItem = new Item(oldWeight, id);
            edges.remove(oldItem);
            weights[id] = newWeight;
            Item newItem = new Item(newWeight, id);
            edges.add(newItem);
            sum += newWeight;
            bestMinusOne = sum - edges.last().weight;
            if (first != second) {
                if (first <= id && id < second) {
                    secondEdges.remove(oldItem);
                    secondEdges.add(newItem);
                } else {
                    firstEdges.remove(oldItem);
                    firstEdges.add(newItem);
                }
                bestOther = sum - firstEdges.last().weight - secondEdges.last().weight - bestMinusOne;
            }
        }

    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("Test #" + testNumber);
        int n = in.nextInt();
        int m = in.nextInt();
        int events = in.nextInt();
        int k = in.nextInt();
        int[] x = readArray(in, n, k, m);
        int[] y = readArray(in, n, k, m);
        int[] vx = readArray(in, events, k, n * m + n);
        int[] w = readArray(in, events, k, 1000000000);
        Circle mainCircle = new Circle(0, 0, n);
        Circle[] smallCircles = new Circle[n];
        NavigableSet<ItemLong> otherValues = new TreeSet<>();
        long sumMinusOne = 0;
        for (int i = 0; i < n; i++) {
            smallCircles[i] = new Circle(x[i], y[i], m);
            otherValues.add(new ItemLong(smallCircles[i].bestOther, i));
            sumMinusOne += smallCircles[i].bestMinusOne;
        }
        final int MOD = 1000000007;
        long ans = 1;
        for (int cq = 0; cq < events; cq++) {
            int edge = vx[cq];
            if (edge < n * m) {
                int cur = edge / m;
                sumMinusOne -= smallCircles[cur].bestMinusOne;
                otherValues.remove(new ItemLong(smallCircles[cur].bestOther, cur));
                smallCircles[cur].changeEdge(edge % m, w[cq]);
                sumMinusOne += smallCircles[cur].bestMinusOne;
                otherValues.add(new ItemLong(smallCircles[cur].bestOther, cur));
            } else {
                mainCircle.changeEdge(edge - n * m, w[cq]);
            }
            long currentAns = mainCircle.bestMinusOne + sumMinusOne;
            if (otherValues.first().weight != Long.MAX_VALUE) {
                currentAns = Math.min(currentAns, mainCircle.sum + sumMinusOne + otherValues.first().weight);
            }
            currentAns %= MOD;
            ans = ans * currentAns % MOD;
        }
        out.println("Case #" + testNumber + ": " + ans);
    }
}
