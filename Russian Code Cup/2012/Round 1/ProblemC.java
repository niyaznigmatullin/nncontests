package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class ProblemC {

    static class Block {
        NavigableSet<Integer>[] rooms;
        int minimal;
        int maximal;
        int id;

        Block(NavigableSet<Integer>[] rooms, int id) {
            this.rooms = rooms;
            this.id = id;
            update();
        }

        void update() {
            minimal = Integer.MAX_VALUE;
            maximal = Integer.MIN_VALUE;
            for (Set<Integer> e : rooms) {
                int val = e.size();
                minimal = Math.min(minimal, val);
                maximal = Math.max(maximal, val);
            }
        }

        int addMin(int v) {
            int ret = -1;
            for (int i = 0; i < rooms.length; i++) {
                if (rooms[i].size() == minimal) {
                    rooms[i].add(v);
                    ret = i;
                    break;
                }
            }
            update();
            return ret + BLOCK_SIZE * id;
        }

        public void remove(int w, int id) {
            rooms[w].remove(id);
            update();
        }

        int findLeft(int w, int max) {
            for (int i = w - 1; i >= 0; i--) {
                if (rooms[i].size() == max) {
                    return i + id * BLOCK_SIZE;
                }
            }
            return -1;
        }

        int findRight(int w, int max) {
            for (int i = w; i < rooms.length; i++) {
                if (rooms[i].size() == max) {
                    return i + id * BLOCK_SIZE;
                }
            }
            return -1;
        }
    }

    static final int BLOCK_SIZE = 100;

    static int getMin(Block[] b) {
        int minimal = Integer.MAX_VALUE;
        for (Block e : b) {
            minimal = Math.min(minimal, e.minimal);
        }
        return minimal;
    }

    static int getMax(Block[] b) {
        int maximal = Integer.MIN_VALUE;
        for (Block e : b) {
            maximal = Math.max(maximal, e.maximal);
        }
        return maximal;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        NavigableSet<Integer>[] rooms = new NavigableSet[n];
        for (int i = 0; i < n; i++) {
            rooms[i] = new TreeSet<Integer>();
        }
        Block[] blocks = new Block[(n + BLOCK_SIZE - 1) / BLOCK_SIZE];
        for (int i = 0, z = 0; i < n; i += BLOCK_SIZE, ++z) {
            int j = Math.min(n, i + BLOCK_SIZE);
            blocks[z] = new Block(Arrays.copyOfRange(rooms, i, j), z);
        }
        int added = 0;
        int[] allMen = new int[m];
        for (int query = 0; query < m; query++) {
            String op = in.next();
            int minimal = Integer.MAX_VALUE;
            int maximal = Integer.MIN_VALUE;
            for (int i = 0; i < blocks.length; i++) {
                minimal = Math.min(minimal, blocks[i].minimal);
                maximal = Math.max(maximal, blocks[i].maximal);
            }
            if (op.equals("+")) {
                for (int i = 0; i < blocks.length; i++) {
                    if (blocks[i].minimal == minimal) {
                        allMen[added] = blocks[i].addMin(added++);
                        break;
                    }
                }
            } else {
                int id = in.nextInt() - 1;
                int w = allMen[id];
                allMen[id] = -1;
                int block = w / BLOCK_SIZE;
                int w1 = w % BLOCK_SIZE;
                blocks[block].remove(w1, id);
                if (blocks[block].minimal < maximal - 1) {
                    int left = blocks[block].findLeft(w1, maximal);
                    int right = blocks[block].findRight(w1, maximal);
                    if (left < 0) {
                        for (int i = block - 1; i >= 0; i--) {
                            if (blocks[i].maximal == maximal) {
                                left = blocks[i].findLeft(BLOCK_SIZE, maximal);
                                break;
                            }
                        }
                    }
                    if (right < 0) {
                        for (int i = block + 1; i < blocks.length; i++) {
                            if (blocks[i].maximal == maximal) {
                                right = blocks[i].findRight(0, maximal);
                                break;
                            }
                        }
                    }
                    if (left < 0 && right < 0) {
                        throw new AssertionError();
                    }
                    int from = -1;
                    if (left < 0) {
                        from = right;
                    } else if (right < 0) {
                        from = left;
                    } else {
                        if (Math.abs(left - w) <= Math.abs(right - w)) {
                            from = left;
                        } else {
                            from = right;
                        }
                    }
                    int got = rooms[from].pollFirst();
                    blocks[from / BLOCK_SIZE].update();
                    rooms[w].add(got);
                    allMen[got] = w;
                    blocks[block].update();
                }
            }
        }
        for (int i = 0; i < n; i++) {
            out.print(rooms[i].size());
            for (int j : rooms[i]) {
                out.print(" " + (j + 1));
            }
            out.println();
        }
    }
}
