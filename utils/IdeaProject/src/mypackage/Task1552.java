package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;

public class Task1552 {
    static final int ALPHABET = 27;

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.nextLine();
        State start = new State(0, 0, 0, 0, 0, 0);
        Queue<State> q = new ArrayDeque<State>();
        HashSet<State> set = new HashSet<State>();
        q.add(start);
        set.add(start);
        State finish = null;
        while (!q.isEmpty()) {
            State state = q.poll();
            if (state.pos == s.length()) {
                finish = state;
                break;
            }
            enQueue(state.movePointer(-1), q, set);
            enQueue(state.movePointer(1), q, set);
            enQueue(state.inc(), q, set);
            enQueue(state.dec(), q, set);
            if (state.getCurrentByte() == s.charAt(state.pos) - 'a' + 1) {
                enQueue(state.incPos(), q, set);
            }
        }
        StringBuilder ret = new StringBuilder();
        while (finish != start) {
            if (finish.last != null) {
                ret.append(finish.last);
            }
            finish = finish.prev;
        }
        out.println(ret.reverse());
    }

    static void enQueue(State newState, Queue<State> q, HashSet<State> set) {
        if (newState == null || set.contains(newState)) {
            return;
        }
        q.add(newState);
        set.add(newState);
    }

    static class State {
        int[] a;
        int where;
        int pos;
        String last;
        State prev;
        static final String incFromZero;
        static final String decToZero;

        static {
            {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 'a'; i++) {
                    sb.append('+');
                }
                incFromZero = sb.toString();
            }
            {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 'a'; i++) {
                    sb.append('-');
                }
                decToZero = sb.toString();
            }
        }

        State(int first, int second, int third, int fourth, int where, int pos, String last, State prev) {
            this(first, second, third, fourth, where, pos);
            this.last = last;
            this.prev = prev;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof State)) return false;

            State state = (State) o;

            if (pos != state.pos) return false;
            if (where != state.where) return false;
            if (!Arrays.equals(a, state.a)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = a != null ? Arrays.hashCode(a) : 0;
            result = 31 * result + where;
            result = 31 * result + pos;
            return result;
        }

        State(int[] a, int where, int pos, String last, State prev) {
            this(a, where, pos);
            this.last = last;
            this.prev = prev;
        }

        State(int first, int second, int third, int fourth, int where, int pos) {
            this(new int[]{first, second, third, fourth}, where, pos);
        }

        State(int[] a, int where, int pos) {
            this.a = a;
            this.where = where;
            this.pos = pos;
        }


        public State movePointer(int d) {
            int nw = where + d;
            if (nw < 0 || nw >= 4) {
                return null;
            }
            return new State(a, nw, pos, d == -1 ? "<" : ">", this);
        }

        public State inc() {
            if (a[where] + 1 >= ALPHABET) {
                return null;
            }
            int[] b = a.clone();
            b[where]++;
            return new State(b, where, pos, a[where] == 0 ? incFromZero : "+", this);
        }

        public State dec() {
            if (a[where] - 1 < 0) {
                return null;
            }
            int[] b = a.clone();
            b[where]--;
            return new State(b, where, pos, b[where] == 0 ? decToZero : "+", this);
        }


        public int getCurrentByte() {
            return a[where];
        }

        public State incPos() {
            return new State(a, where, pos + 1, ".", this);
        }
    }
}
