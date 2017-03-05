package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.*;

public class EasyEquation {

    static class Triple {
        BigInteger a, b, c;

        public Triple(BigInteger a, BigInteger b, BigInteger c) {
            if (a.compareTo(b) > 0) {
                BigInteger t = a;
                a = b;
                b = t;
            }
            if (a.compareTo(c) > 0) {
                BigInteger t = a;
                a = c;
                c = t;
            }
            if (b.compareTo(c) > 0) {
                BigInteger t = b;
                b = c;
                c = t;
            }
            this.a = a;
            this.b = b;
            this.c = c;
        }

        boolean check(int k) {
            BigInteger lhs = a.pow(2).add(b.pow(2)).add(c.pow(2));
            BigInteger rhs = BigInteger.valueOf(k).multiply(a.multiply(b).add(b.multiply(c).add(a.multiply(c)))).add(BigInteger.ONE);
            return lhs.equals(rhs);
        }

        boolean checkModulo(int k, BigInteger mod) {
            BigInteger va = a.mod(mod);
            BigInteger vb = b.mod(mod);
            BigInteger vc = c.mod(mod);
            return va.pow(2).add(vb.pow(2)).add(vc.pow(2)).mod(mod).equals(BigInteger.valueOf(k).multiply(va.multiply(vb).add(vb.multiply(vc).add(va.multiply(vc)))).add(BigInteger.ONE).mod(mod));
        }

        @Override
        public String toString() {
            return "(" + a + " " + b + " " + c + ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Triple triple = (Triple) o;

            if (a != null ? !a.equals(triple.a) : triple.a != null) return false;
            if (b != null ? !b.equals(triple.b) : triple.b != null) return false;
            return c != null ? c.equals(triple.c) : triple.c == null;

        }

        @Override
        public int hashCode() {
            int result = a != null ? a.hashCode() : 0;
            result = 31 * result + (b != null ? b.hashCode() : 0);
            result = 31 * result + (c != null ? c.hashCode() : 0);
            return result;
        }
    }

    static Random rng = new Random(1239392);

    static BigInteger getThird(BigInteger a, BigInteger b, BigInteger c, int k) {
        BigInteger K = BigInteger.valueOf(k);
//        BigInteger v = b.pow(2).add(c.pow(2)).subtract(K.multiply(b.multiply(c))).subtract(BigInteger.ONE);
//        BigInteger B = K.multiply(b.add(c));
//        BigInteger D = B.pow(2).subtract(BigInteger.valueOf(4).multiply(v));
//        BigInteger x = BigInteger.ONE;
//        while (x.add(BigInteger.ONE).pow(2).compareTo(D) <= 0) x = x.add(BigInteger.ONE);
//        return B.negate().add(x).divide(BigInteger.valueOf(2));
        return K.multiply(b.add(c)).subtract(a);
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int k = in.nextInt();
        int n = in.nextInt();
        List<Triple> list = new ArrayList<>();
        BigInteger za = BigInteger.ONE;
        BigInteger zb = BigInteger.valueOf(k);
        for (int x = 1; x < 10 * n; x++) {
            Triple asd = new Triple(BigInteger.ZERO, za, zb);
            list.add(asd);
            BigInteger zc = zb.multiply(BigInteger.valueOf(k)).subtract(za);
            if (zc.compareTo(BigInteger.TEN.pow(100)) > 0) break;
            za = zb;
            zb = zc;
//            list.add(new Triple(BigInteger.ZERO, BigInteger.valueOf(k).pow(x), BigInteger.valueOf(k).pow(x).subtract(BigInteger.ONE)));
        }
//        list.add(new Triple(BigInteger.ONE, BigInteger.valueOf(k), BigInteger.valueOf(k * (k + 1))));
        Set<Triple> all = new HashSet<>();
        for (int i = 0; i < list.size() && all.size() < 10 * n; i++) {
            Triple e = list.get(i);
            BigInteger nOne = getThird(e.a, e.b, e.c, k);
            BigInteger nTwo = getThird(e.b, e.a, e.c, k);
            if (nOne.compareTo(BigInteger.TEN.pow(100)) <= 0) {
                Triple ne = new Triple(e.b, e.c, nOne);
                if (!ne.check(k)) throw new AssertionError();
                if (all.add(ne)) {
                    list.add(ne);
                }
            }
            if (nTwo.compareTo(BigInteger.TEN.pow(100)) <= 0) {
                Triple ne = new Triple(e.a, e.c, nTwo);
                if (nTwo.equals(e.b)) throw new AssertionError();
                if (!ne.check(k)) throw new AssertionError();
                if (all.add(ne)) {
                    list.add(ne);
                }
            }
        }
        List<Triple> answer = new ArrayList<>();
        Set<BigInteger> used = new HashSet<>();
        for (Triple e : all) {
            if (answer.size() == n) break;
            if (e.a.signum() == 0) continue;
            if (!used.contains(e.a) && !used.contains(e.b) && !used.contains(e.c)) {
                used.add(e.a);
                used.add(e.b);
                used.add(e.c);
                answer.add(e);
            }
        }
        answer.forEach(x -> {
            out.println(x.a + " " + x.b + " " + x.c);
        });
//        BigInteger modulo = BigInteger.valueOf(1);
//        List<Triple> answer = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            BigInteger newModulo = modulo.multiply(BigInteger.valueOf(2));
//            List<Triple> next = new ArrayList<>();
//            for (Triple e : list) {
//                for (int a = 0; a < 2; a++) {
//                    BigInteger za = a == 0 ? e.a : e.a.add(modulo);
//                    for (int b = 0; b < 2; b++) {
//                        BigInteger zb = b == 0 ? e.b : e.b.add(modulo);
//                        for (int c = 0; c < 2; c++) {
//                            BigInteger zc = c == 0 ? e.c : e.c.add(modulo);
//                            Triple q = new Triple(za, zb, zc);
//                            if (!q.checkModulo(k, newModulo)) {
//                                continue;
//                            }
//                            if (a + b + c > 0 && za.signum() > 0 && za.compareTo(zb) < 0 && zb.compareTo(zc) < 0 &&
//                                    q.check(k) && a + b + c > 0) {
//                                answer.add(q);
//                            }
//                            next.add(q);
//                        }
//                    }
//                }
//            }
//            Collections.shuffle(next, rng);
//            next = next.subList(0, Math.min(next.size(), 100000));
//            modulo = newModulo;
//            list = next;
//            System.out.println(list.size());
//        }
//        out.println(answer.size());
//        answer.forEach(out::println);
    }
}
