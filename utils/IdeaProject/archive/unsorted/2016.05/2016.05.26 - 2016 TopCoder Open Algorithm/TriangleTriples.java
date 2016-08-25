package coding;

import ru.ifmo.niyaz.math.MathUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TriangleTriples {

    static final int MOD = 1000000007;

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int modInverse(int a) {
        a %= MOD;
        if (a < 0) a += MOD;
        return MathUtils.modPow(a, MOD - 2, MOD);
    }

//    static int solve(int A, int B, int C) {
//        if (A > B) {
//            int t = A;
//            A = B;
//            B = t;
//        }
//        int first = Math.min(C, A + 1);
//        int second = Math.min(C, B + 1);
//        int third = C;
//        int ans = mul(mul(first, mul(first + 1, first + 2)), modInverse(6));
//        ans = add(ans, )
//    }

    static int stupid(int A, int B, int C) {
        int ans = 0;
        for (int i = 1; i <= A; i++) {
            for (int j = 1; j <= B; j++) {
                for (int k = 1; k <= C; k++) {
                    if (i + j > k && i + k > j && j + k > i) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }

    static class Polynomial {
        Map<Integer, Integer> a;

        public Polynomial(Map<Integer, Integer> a) {
            this.a = a;
        }

        public Polynomial multiply(Polynomial b) {
            Map<Integer, Integer> z = new HashMap<>();
            for (int e : a.keySet()) {
                for (int f : b.a.keySet()) {
                    int q = e + f;
                    int w = mul(a.get(e), b.a.get(f));
                    Integer got = z.get(q);
                    if (got == null) got = 0;
                    got = TriangleTriples.add(got, w);
                    if (got == 0) {
                        z.remove(q);
                    } else {
                        z.put(q, got);
                    }
                }
            }
            return new Polynomial(z);
        }

        public Polynomial add(Polynomial b) {
            Map<Integer, Integer> z = new HashMap<>();
            for (int f : a.keySet()) {
                z.put(f, a.get(f));
            }
            for (int f : b.a.keySet()) {
                int w = b.a.get(f);
                Integer got = z.get(f);
                if (got == null) got = 0;
                got = TriangleTriples.add(got, w);
                if (got == 0) {
                    z.remove(f);
                } else {
                    z.put(f, got);
                }
            }
            return new Polynomial(z);
        }

        public int compute(int x, int y, int z) {
            int ans = 0;
            for (int f : a.keySet()) {
                int cur = a.get(f);
                while (f >= 1000000) {
                    f -= 1000000;
                    cur = mul(cur, z);
                }
                while (f >= 1000) {
                    f -= 1000;
                    cur = mul(cur, y);
                }
                while (f >= 1) {
                    f -= 1;
                    cur = mul(cur, x);
                }
                ans = TriangleTriples.add(ans, cur);
            }
            return ans;
        }

    }

    static Polynomial one(int x) {
        Map<Integer, Integer> a = new HashMap<>();
        a.put(0, x);
        return new Polynomial(a);
    }

    static Polynomial get(int x, int y) {
        Map<Integer, Integer> a = new HashMap<>();
        a.put(x, 1);
        a.put(0, (MOD - y) % MOD);
        return new Polynomial(a);
     }

    public int count(int A, int B, int C) {
        final int MAX = 6;
        Polynomial all = new Polynomial(new HashMap<>());
        List<Polynomial> s = new ArrayList<>();
        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; j <= MAX; j++) {
                for (int k = 1; k <= MAX; k++) {
                    int val = stupid(i, j, k);
                    Polynomial a = one(val);
                    for (int e = 1; e <= MAX; e++) {
                        if (i != e) {
                            a = a.multiply(get(1, e)).multiply(one(modInverse(i - e)));
                        }
                        if (j != e) {
                            a = a.multiply(get(1000, e)).multiply(one(modInverse(j - e)));
                        }
                        if (k != e) {
                            a = a.multiply(get(1000000, e)).multiply(one(modInverse(k - e)));
                        }
                    }
                    if (a.compute(i, j, k) != val) {
                        throw new AssertionError();
                    }
                    for (int x = 1; x <= MAX; x++) {
                        for (int y = 1; y <= MAX; y++) {
                            for (int z = 1; z <= MAX; z++) {
                                if (x != i || y != j || z != k) {
                                    if (a.compute(x, y, z) != 0) throw new AssertionError();
                                }
                            }
                        }
                    }
                    s.add(a);
                    all = all.add(a);
                }
            }
        }
        for (int i = 1; i <= MAX; i++) {
            for (int j = 1; j <= MAX; j++) {
                for (int k = 1; k <= MAX; k++) {
                    if (all.compute(i, j, k) != stupid(i, j, k)) {
                        System.out.println(i + " " + j + " " + k);
                        int sum = 0;
                        for (Polynomial e : s) {
                            sum = add(sum, e.compute(i, j, k));
                        }
                        System.out.println(all.compute(i, j, k) + " " + sum);
                        System.out.println(stupid(i, j, k));
                        throw new AssertionError();
                    }
                }
            }
        }
        return all.compute(A, B, C);
//        System.out.println(all.a);
//        return -1;
    }
}
