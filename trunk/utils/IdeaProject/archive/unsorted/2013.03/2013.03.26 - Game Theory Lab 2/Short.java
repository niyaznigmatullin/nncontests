package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Short {

    static class Game implements Comparable<Game> {
        Set<Game> leftMoves;
        Set<Game> rightMoves;

        Game() {
            leftMoves = new TreeSet<>();
            rightMoves = new TreeSet<>();
        }


        @Override
        public int compareTo(Game o) {
            if (leftMoves.size() != o.leftMoves.size()) {
                return leftMoves.size() - o.leftMoves.size();
            }
            if (rightMoves.size() != o.rightMoves.size()) {
                return rightMoves.size() - o.rightMoves.size();
            }
            for (Iterator<Game> it1 = leftMoves.iterator(), it2 = o.leftMoves.iterator(); it1.hasNext(); ) {
                int z = it1.next().compareTo(it2.next());
                if (z != 0) {
                    return z;
                }
            }
            for (Iterator<Game> it1 = rightMoves.iterator(), it2 = o.rightMoves.iterator(); it1.hasNext(); ) {
                int z = it1.next().compareTo(it2.next());
                if (z != 0) {
                    return z;
                }
            }
            return 0;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append('{');
            for (Game left : leftMoves) {
                sb.append(left).append(',');
            }
            if (!leftMoves.isEmpty()) {
                sb.setLength(sb.length() - 1);
            }
            sb.append('|');
            for (Game right : rightMoves) {
                sb.append(right).append(',');
            }
            if (!rightMoves.isEmpty()) {
                sb.setLength(sb.length() - 1);
            }
            sb.append('}');
            return sb.toString();
        }
    }

    static boolean lessOrEqual(Game a, Game b) {
        for (Game left : a.leftMoves) if (greaterOrEqual(left, b)) return false;
        for (Game right : b.rightMoves) if (greaterOrEqual(a, right)) return false;
        return true;
    }

    static boolean greaterOrEqual(Game a, Game b) {
        for (Game right : a.rightMoves) if (lessOrEqual(right, b)) return false;
        for (Game left : b.leftMoves) if (lessOrEqual(a, left)) return false;
        return true;
    }

    static char[] s;
    static int cur;

    static Game parseGame() {
        if (s[cur] != '{') {
            return new Game();
        }
        Game ret = new Game();
        ++cur;
        while (s[cur] != '|') {
            ret.leftMoves.add(parseGame());
            if (s[cur] == ',') {
                ++cur;
        }
        }
        while (s[cur] == '|')
            ++cur;
        while (s[cur] != '}') {
            ret.rightMoves.add(parseGame());
            if (s[cur] == ',') {
                ++cur;
            }
        }
        ++cur;
        return ret;
    }

    static Game minimize(Game gIn) {
        Game g = new Game();
        for (Game left : gIn.leftMoves) {
            g.leftMoves.add(minimize(left));
        }
        for (Game right : gIn.rightMoves) {
            g.rightMoves.add(minimize(right));
        }
        Queue<Game> queue = new ArrayDeque<>();
        queue.addAll(g.leftMoves);
        loop: while (!queue.isEmpty()) {
            Game v = queue.poll();
            for (Game a : g.leftMoves) {
                if (v.compareTo(a) != 0 && lessOrEqual(v, a)) {
                    g.leftMoves.remove(v);
                    continue loop;
                }
            }
            for (Game b : v.rightMoves) {
                if (lessOrEqual(b, g)) {
                    g.leftMoves.remove(v);
                    queue.addAll(b.leftMoves);
                    g.leftMoves.addAll(b.leftMoves);
                    continue loop;
                }
            }
        }
        queue.addAll(g.rightMoves);
        loop: while (!queue.isEmpty()) {
            Game v = queue.poll();
            for (Game a : g.rightMoves) {
                if (v.compareTo(a) != 0 && greaterOrEqual(v, a)) {
                    g.rightMoves.remove(v);
                    continue loop;
                }
            }
            for (Game b : v.leftMoves) {
                if (greaterOrEqual(b, g)) {
                    g.rightMoves.remove(v);
                    queue.addAll(b.rightMoves);
                    g.rightMoves.addAll(b.rightMoves);
                    continue loop;
                }
            }
        }
        return g;
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        s = in.next().toCharArray();
        cur = 0;
        Game g = parseGame();
        out.println(minimize(g));
    }
}
