package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimilarGraphs {

    static final Random rand = new Random(2131L);

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = in.nextInt();
            }
        }
        int[][] b = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = in.nextInt();
            }
        }
        Answer.a = a;
        Answer.b = b;
        int[] deg1 = getDeg(a);
        int[] deg2 = getDeg(b);
        List<Answer> answers = new ArrayList<Answer>();
        Answer f1 = chooseBestDeg(n, deg1, deg2);
        answers.add(f1);
        Answer f2 = sortByDeg(n, deg1, deg2);
        answers.add(f2);
        answers.add(doBetter(f1, 10000));
        answers.add(doBetter(f2, 10000));
        for (int i = 0; i < 1000; i++) {
            answers.add(random(n));
        }
        for (int i = 0; i < 10; i++) {
            Answer g = answers.get(rand.nextInt(answers.size()));
            answers.add(doBetter(g, 4000));
        }
        for (int i = 0; i < 50; i++) {
            Answer g = answers.get(rand.nextInt(answers.size()));
            answers.add(doBetter(g, 300));
        }
        Answer best = null;
        for (Answer e : answers) {
            if (best == null || best.common < e.common) {
                best = e;
            }
        }
        int[] p = best.p;
        int[] r = best.r;
        for (int i = 0; i < n; i++) {
            ++p[i];
            ++r[i];
        }
        out.printArray(p);
        out.printArray(r);
    }

    Answer doBetter(Answer f, final int ITERS) {
        int[] p = f.p.clone();
        int[] r = f.r.clone();
        int[] revP = rev(p);
        int[] revR = rev(r);
        int n = p.length;
        for (int it = 0; it < ITERS; it++) {
            int i = rand.nextInt(n);
            int j;
            do {
                j = rand.nextInt(n);
            } while (i == j);
            int delta = 0;
            for (int k = 0; k < n; k++) {
                if (Answer.a[i][revP[k]] == Answer.b[revR[p[i]]][revR[k]]) {
                    --delta;
                }
            }
            for (int k = 0; k < n; k++) {
                if (Answer.a[j][revP[k]] == Answer.b[revR[p[j]]][revR[k]]) {
                    --delta;
                }
            }
            {
                int t = p[i];
                p[i] = p[j];
                p[j] = t;
            }
            {
                int t = revP[p[i]];
                revP[p[i]] = revP[p[j]];
                revP[p[j]] = t;
            }
            for (int k = 0; k < n; k++) {
                if (Answer.a[i][revP[k]] == Answer.b[revR[p[i]]][revR[k]]) {
                    ++delta;
                }
            }
            for (int k = 0; k < n; k++) {
                if (Answer.a[j][revP[k]] == Answer.b[revR[p[j]]][revR[k]]) {
                    ++delta;
                }
            }
            if (delta > 0) {
                --it;
                continue;
            }
            {
                int t = p[i];
                p[i] = p[j];
                p[j] = t;
            }
            {
                int t = revP[p[i]];
                revP[p[i]] = revP[p[j]];
                revP[p[j]] = t;
            }
        }
        return new Answer(p, r);
    }

    static Answer firstTranspose(Answer g) {
        int[] p = transpose(g.p);
        int[] r = g.r.clone();
        return new Answer(p, r);
    }

    static Answer secondTranspose(Answer g) {
        int[] r = transpose(g.r);
        int[] p = g.p.clone();
        return new Answer(p, r);
    }

    static Answer twoTransposes(Answer g) {
        int[] p = transpose(g.p);
        int[] r = transpose(g.r);
        return new Answer(p, r);
    }

    static int[] transpose(int[] p) {
        p = p.clone();
        int i = rand.nextInt(p.length - 1);
        int t = p[i];
        p[i] = p[i + 1];
        p[i + 1] = t;
        return p;
    }

    static int[] rev(int[] p) {
        int[] ret = new int[p.length];
        for (int i = 0; i < ret.length; i++) {
            ret[p[i]] = i;
        }
        return ret;
    }

    static int[] tmpP;
    static int[] tmpR;

    static int getCommon(int[][] a, int[][] b, int[] p, int[] r, int n) {
        if (tmpP == null || tmpP.length != p.length) {
            tmpP = rev(p);
            tmpR = rev(r);
        } else {
            for (int i = 0; i < n; i++) {
                tmpP[p[i]] = i;
                tmpR[r[i]] = i;
            }
        }
        p = tmpP;
        r = tmpR;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[p[i]][p[j]] == 1 && b[r[i]][r[j]] == 1) {
                    ++ret;
                }
            }
        }
        return ret;
    }

    static int getCommon(int[][] a, int[][] b, Answer ans, int n) {
        int[] p = rev(ans.p);
        int[] r = rev(ans.r);
        int ret = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[p[i]][p[j]] == 1 && b[r[i]][r[j]] == 1) {
                    ++ret;
                }
            }
        }
        return ret;
    }

    static Answer random(int n) {
        int[] r = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
            r[i] = i;
        }
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int t = p[i];
            p[i] = p[j];
            p[j] = t;
        }
        return new Answer(r, p);
    }

    private Answer chooseBestDeg(int n, int[] deg1, int[] deg2) {
        int[] r = new int[n];
        boolean[] was = new boolean[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            int best = -1;
            for (int j = 0; j < n; j++) {
                if (was[j]) {
                    continue;
                }
                if (best < 0 || Math.abs(deg1[best] - deg2[i]) > Math.abs(deg1[j] - deg2[i])) {
                    best = j;
                }
            }
            was[best] = true;
            p[i] = best;
        }
        for (int i = 0; i < n; i++) {
            r[i] = i;
        }
        return new Answer(r, p);
    }

    static Answer sortByDeg(int n, int[] deg1, int[] deg2) {
        int[] r = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            r[i] = i;
            p[i] = i;
        }
        sortIt(n, deg1, r);
        sortIt(n, deg2, p);
        return new Answer(rev(r), rev(p));
    }

    static void sortIt(int n, int[] deg1, int[] r) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (deg1[r[i]] > deg1[r[j]]) {
                    int t = r[i];
                    r[i] = r[j];
                    r[j] = t;
                }
            }
        }
    }

    static class Answer {
        static int[][] a;
        static int[][] b;
        int[] p;
        int[] r;
        int common;

        Answer(int[] p, int[] r) {
            this.p = p;
            this.r = r;
            common = getCommon(a, b, this, p.length);
        }
    }

    static int[] getDeg(int[][] a) {
        int[] deg = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                deg[i] += a[i][j];
            }
        }
        return deg;
    }
}
