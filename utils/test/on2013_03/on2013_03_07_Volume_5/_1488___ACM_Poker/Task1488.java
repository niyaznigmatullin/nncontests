package lib.test.on2013_03.on2013_03_07_Volume_5._1488___ACM_Poker;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;

public class Task1488 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        while (true) {
            String first = in.next();
            if (first == null) {
                break;
            }
            Card[] a = new Card[3];
            a[0] = new Card(first);
            a[1] = new Card(in.next());
            a[2] = new Card(in.next());
            Card[] b = new Card[5];
            for (int i = 0; i < 5; i++) b[i] = new Card(in.next());
            int res = -1;
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 5; j++) {
                    all:
                    for (int k = j + 1; k < 5; k++) {
                        Card[] c = new Card[]{b[i], b[j], b[k]};
                        for (Card e : a) {
                            for (Card f : c) {
                                if (e.equals(f)) {
                                    continue all;
                                }
                            }
                        }
                        for (Card e : c) {
                            for (Card f : c) {
                                if (e != f && e.equals(f)) {
                                    continue all;
                                }
                            }
                        }
                        int z = compare(a, c);
                        res = Math.max(res, z);
                    }
                }
            }
            out.println(res > 0 ? "Dima" : res == 0 ? "Artyom" : "Sasha");
        }
    }

    static int compare(Card[] a, Card[] c) {
        Arrays.sort(a);
        Arrays.sort(c);
        int comp = cg(a, c);
        boolean f1 = flush(a);
        boolean f2 = flush(c);
        boolean str1 = str(a);
        boolean str2 = str(c);
        {
            boolean h1 = high(a);
            boolean h2 = high(c);
            if (h1 && h2) return 0;
            if (h1) return -1;
            if (h2) return 1;
        }
        {
            if (f1 && str1 && f2 && str2) return comp;
            if (f1 && str1) return -1;
            if (f2 && str2) return 1;
        }
        {
            boolean t1 = three(a);
            boolean t2 = three(c);
            if (t1 && t2) return comp;
            if (t1) return -1;
            if (t2) return 1;
        }
        {
            if (str1 && str2) return comp;
            if (str1) return -1;
            if (str2) return 1;
        }
        {
            if (f1 && f2) return comp;
            if (f1) return -1;
            if (f2) return 1;
        }
        {
            int p1 = pair(a);
            int p2 = pair(c);
            if (p1 >= 0 && p2 >= 0) {
                if (p1 != p2) return p2 - p1;
                return comp;
            }
            if (p1 >= 0) return -1;
            if (p2 >= 0) return 1;
        }
        return comp;
    }

    static boolean high(Card[] a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].val != 3) return false;
        }
        return true;
    }

    static int pair(Card[] a) {
        for (int i = 0; i < 3; i++) for (int j = i + 1; j < 3; j++) if (a[i].val == a[j].val) return a[i].val;
        return -1;
    }

    static boolean three(Card[] a) {
        return a[1].val == a[0].val && a[2].val == a[0].val;
    }

    static boolean str(Card[] a) {
        for (int i = 1; i < a.length; i++) if (a[i].val != a[i - 1].val - 1) return false;
        return true;
    }

    static boolean flush(Card[] a) {
        for (int i = 1; i < a.length; i++) if (a[i].suit != a[0].suit) return false;
        return true;
    }

    static int cg(Card[] a, Card[] b) {
        for (int i = 0; i < a.length; i++) {
            int c = (a[i].compareTo(b[i]));
            if (c == 0) continue;
            return c;
        }
        return 0;
    }


    static class Card implements Comparable<Card> {
        int suit;
        int val;

        Card(String s) {
            this.suit = getSuit(s.charAt(s.length() - 1));
            s = s.substring(0, s.length() - 1);
            val = getVal(s);
        }

        static int getVal(String s) {
            if (s.equals("A")) return 14;
            else if (s.equals("K")) return 13;
            else if (s.equals("Q")) return 12;
            else if (s.equals("J")) return 11;
            else return Integer.parseInt(s);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Card card = (Card) o;

            if (suit != card.suit) return false;
            if (val != card.val) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = suit;
            result = 31 * result + val;
            return result;
        }

        static int getSuit(char c) {
            return "SCDH".indexOf(c);
        }

        @Override
        public int compareTo(Card o) {
            return o.val - val;
        }
    }
}
