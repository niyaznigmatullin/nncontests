package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class OperationWithSets {

    static class AntiAntiHashSetInteger {
        int value;

        AntiAntiHashSetInteger(int value) {
            this.value = value;
        }


        @Override
        public String toString() {
            return value + "";
        }

        @Override
        public boolean equals(Object o) {
            AntiAntiHashSetInteger that = (AntiAntiHashSetInteger) o;

            if (value != that.value) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return (value >> 16) * 239 ^ (value << 16) * 3434;
        }
    }

    static class MySet {
        HashSet<AntiAntiHashSetInteger> set;
        boolean r;

        MySet(HashSet<AntiAntiHashSetInteger> set, boolean r) {
            this.set = set;
            this.r = r;
        }

        MySet r() {
            r = !r;
            return this;
        }

        @Override
        public String toString() {
            return "MySet{" +
                    "set=" + set +
                    ", r=" + r +
                    '}';
        }

        MySet and(MySet b) {
            MySet a = this;
            if (a.set.size() < b.set.size()) {
                MySet t = a;
                a = b;
                b = t;
            }
            if (!a.r && !b.r) {
                for (Iterator<AntiAntiHashSetInteger> e = b.set.iterator(); e.hasNext(); ) {
                    if (!a.set.contains(e.next())) {
                        e.remove();
                    }
                }
                a.set.clear();
                return b;
            } else if (!a.r && b.r) {
                for (AntiAntiHashSetInteger e : b.set) {
                    a.set.remove(e);
                }
            } else if (a.r && b.r) {
                for (AntiAntiHashSetInteger e : b.set) {
                    a.set.add(e);
                }
            } else {
                for (AntiAntiHashSetInteger e : a.set) {
                    b.set.remove(e);
                }
                a.set.clear();
                return b;
            }
            b.set.clear();
            return a;
        }

        MySet or(MySet b) {
            MySet a = this;
            if (a.set.size() < b.set.size()) {
                MySet t = a;
                a = b;
                b = t;
            }
            if (!a.r && !b.r) {
                for (AntiAntiHashSetInteger e : b.set) {
                    a.set.add(e);
                }
            } else if (a.r && b.r) {
                for (Iterator<AntiAntiHashSetInteger> e = b.set.iterator(); e.hasNext(); ) {
                    if (!a.set.contains(e.next())) {
                        e.remove();
                    }
                }
                a.set.clear();
                return b;
            } else if (!a.r && b.r) {
                for (AntiAntiHashSetInteger e : a.set) {
                    b.set.remove(e);
                }
                a.set.clear();
                return b;
            } else {
                for (AntiAntiHashSetInteger e : b.set) {
                    a.set.remove(e);
                }
            }
            b.set.clear();
            return a;
        }

        public int size() {
            return r ? 1000000000 - set.size() : set.size();
        }
    }

    static int[] polish = new int[2000010];
    static int[] stack = new int[10000010];
    static MySet[] st = new MySet[1000010];
    static int[] curSet = new int[10000000];

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int curPolish = 0;
        int curStack = 0;
        String OPEN = "(";
        String CLOSE = ")";
        String PIPE = "|";
        String AND = "&";
        String NOT = "!";
        int c = in.read();
        int setCount = 0;
        while (c >= 0 && c <= 32) {
            c = in.read();
        }
        if (c < 0) {
            throw new UnknownError();
        }
        for (; c > 32; c = in.read()) {
            if (c == '(') {
                stack[curStack++] = -1;
            } else if (c == ')') {
                while (true) {
                    int element = stack[--curStack];
                    if (element == -1) {
                        break;
                    }
                    polish[curPolish++] = element;
                }
            } else if (c == '{') {
                int cnt = 0;
                c = in.read();
                while (c != '}') {
                    while (c < '0' || c > '9') {
                        c = in.read();
                    }
                    int x = 0;
                    while (c >= '0' && c <= '9') {
                        x = x * 10 + c - '0';
                        c = in.read();
                    }
                    curSet[cnt++] = x;
                }
                HashSet<AntiAntiHashSetInteger> set = new HashSet<AntiAntiHashSetInteger>(cnt * 2);
                for (int i = 0; i < cnt; i++) {
                    set.add(new AntiAntiHashSetInteger(curSet[i]));
                }
                st[setCount++] = (new MySet(set, false));
                polish[curPolish++] = setCount - 1;
            } else if (c == '|') {
                while (curStack > 0) {
                    int e = stack[curStack - 1];
                    if (e == -3 || e == -4) {
                        polish[curPolish++] = stack[--curStack];
                    } else break;
                }
                stack[curStack++] = -2;
            } else if (c == '&') {
                while (curStack > 0) {
                    int e = stack[curStack - 1];
                    if (e == -4) {
                        polish[curPolish++] = stack[--curStack];
                    } else break;
                }
                stack[curStack++] = -3;
            } else if (c == '!') {
                stack[curStack++] = -4;
            } else {
                throw new AssertionError();
//                System.out.println("bad " + c);
            }
        }
        while (curStack > 0) {
            polish[curPolish++] = stack[--curStack];
        }
        for (int i = 0; i < curPolish; i++) {
            int e = polish[i];
//            System.out.println(e);
            if (e == -4) {
                st[stack[curStack - 1]] = st[stack[curStack - 1]].r();
            } else if (e == -2 || e == -3) {
                int place = stack[--curStack];
                MySet b = st[place];
                st[setCount] = null;
                MySet a = st[stack[--curStack]];
                st[setCount] = null;
                if (e == -2) {
                    st[place] = a.or(b);
                } else if (e == -3) {
                    st[place] = a.and(b);
                }
                stack[curStack++] = place;
            } else {
                stack[curStack++] = e;
            }
        }
//        System.out.println(st);
        MySet answer = st[stack[0]];
//        System.out.println(answer);
        out.println("Case " + testNumber + ": " + answer.size());
        while (setCount > 0) {
            st[--setCount] = null;
        }
    }
}
