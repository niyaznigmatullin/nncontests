package coding;

import java.util.*;

public class MaterialImplication {

    static abstract class Expression {
        abstract boolean calc(int mask);
    }

    static class Implication extends Expression {
        Expression left;
        Expression right;

        public Implication(Expression left, Expression right) {
            this.left = left;
            this.right = right;
        }

        @Override
        boolean calc(int mask) {
            return (!left.calc(mask) || right.calc(mask));
        }
    }

    static class Variable extends Expression {
        int var;

        public Variable(int var) {
            this.var = var;
        }

        @Override
        boolean calc(int mask) {
            return ((mask >> var) & 1) == 1;
        }
    }

    static char[] s;
    static int cur;

    static Expression parseExpr() {
        if (s[cur] == '(') {
            ++cur;
            Expression left = parseExpr();
            cur += 2;
            Expression right = parseExpr();
            ++cur;
            return new Implication(left, right);
        } else {
            int var = s[cur++] - 'A';
            return new Variable(var);
        }
    }

    static final int ALPHABET = 5;

    static final Random rng = new Random(12323);

    static String generate(int n, int start) {
        if (n == 1) {
            return "" + (char) (start + 'A');
        }
        int left = rng.nextInt(n - 1) + 1;
        int right = n - left;
        return "(" + generate(left, start) + "->" + generate(right, start + left) + ")";
    }

    public String construct(int n, int k) {
        if (k < (1 << n - 1)) return "Impossible";
        if (k == 1 << n) return "(A->A)";
        int need = n;
        while (k % 2 == 0) {
            k /= 2;
            need--;
        }
        List<Answer> all = new ArrayList<>();
        all.add(new Answer("A", 1, 1));
        for (int i = 1; i < need; i++) {
            Set<Answer> set = new HashSet<>();
            for (Answer e : all) {
                set.add(e.addBack());
                set.add(e.addFront());
            }
            all = new ArrayList<>(set);
        }
        for (Answer e : all) {
            if (e.ways == k) {
                char[] c = e.s.toCharArray();
                int cur = 0;
                for (int i = 0; i < c.length; i++) {
                    if (c[i] >= 'A' && c[i] <= 'Z') {
                        c[i] = (char) ('A' + cur);
                        ++cur;
                    }
                }
                return new String(c);
            }
        }
        throw new AssertionError();
    }

    static int getCount(String str, int alpha) {
        s = str.toCharArray();
        cur = 0;
        Expression e = parseExpr();
        int ans = 0;
        for (int mask = 0; mask < 1 << alpha; mask++) {
            if (e.calc(mask)) {
                ++ans;
            }
        }
        return ans;
    }

    static class Answer {
        String s;
        int ways;
        int n;

        public Answer(String s, int ways, int n) {
            this.s = s;
            this.ways = ways;
            this.n = n;
        }

        Answer addBack() {
            return new Answer("(" + s + "->A)", ways + 2 * ((1 << n) - ways), n + 1);
        }

        Answer addFront() {
            return new Answer("(A->" + s + ")", 2 * ways + ((1 << n) - ways), n + 1);
        }

//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//
//            Answer answer = (Answer) o;
//
//            if (ways != answer.ways) return false;
//            if (n != answer.n) return false;
//            return s != null ? s.equals(answer.s) : answer.s == null;
//
//        }
//
//        @Override
//        public int hashCode() {
//            int result = s != null ? s.hashCode() : 0;
//            result = 31 * result + ways;
//            result = 31 * result + n;
//            return result;
//        }
    }
}
