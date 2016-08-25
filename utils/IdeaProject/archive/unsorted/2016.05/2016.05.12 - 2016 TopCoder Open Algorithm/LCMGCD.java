package coding;

public class LCMGCD {

    public String isPossible(int[] x, int t) {
        int n = x.length;
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            int y = x[i];
            while (y % 2 == 0) {
                y /= 2;
                a[i]++;
            }
            while (y % 3 == 0) {
                y /= 3;
                b[i]++;
            }
        }
        int tA = 0;
        int tB = 0;
        while (t % 2 == 0) {
            tA++;
            t /= 2;
        }
        while (t % 3 == 0) {
            tB++;
            t /= 3;
        }
        if (t != 1) {
            return "Impossible";
        }
        int initMask = 0;
        for (int i = 0; i < n; i++) {
            int first = a[i] < tA ? 0 : a[i] == tA ? 1 : 2;
            int second = b[i] < tB ? 0 : b[i] == tB ? 1 : 2;
            int id = first * 3 + second;
            int got = get(initMask, id);
            if (got < 3) got++;
            initMask |= got << 2 * id;
        }
        int[] q = new int[1 << 18];
        int head = 0;
        int tail = 0;
        q[tail++] = initMask;
        boolean[] was = new boolean[1 << 18];
        was[initMask] = true;
        while (head < tail) {
            int v = q[head++];
            for (int i = 0; i < 9; i++) {
                int countI = get(v, i);
                if (countI == 0) continue;
                int mask1 = add(v, i, -1);
                for (int j = i; j < 9; j++) {
                    int countJ = get(mask1, j);
                    if (countJ == 0) continue;
                    int mask2 = add(mask1, j, -1);
                    for (int w = 0; w < 2; w++) {
                        int first = w == 0 ? Math.min(i / 3, j / 3) : Math.max(i / 3, j / 3);
                        int second = w == 0 ? Math.min(i % 3, j % 3) : Math.max(i % 3, j % 3);
                        int id = first * 3 + second;
                        int nMask = add(mask2, id, 1);
                        if (!was[nMask]) {
                            was[nMask] = true;
                            q[tail++] = nMask;
                        }
                    }
                }
            }
        }
        return was[1 << 2 * 4] ? "Possible" : "Impossible";
    }

    static int add(int mask, int i, int x) {
        int got = get(mask, i);
        got = Math.min(3, got + x);
        return (mask & ~(3 << 2 * i)) | (got << 2 * i);
    }

    private static int get(int mask, int i) {
        return (mask >> 2 * i) & 3;
    }
}
