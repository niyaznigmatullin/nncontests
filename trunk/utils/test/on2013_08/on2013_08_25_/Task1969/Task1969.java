package lib.test.on2013_08.on2013_08_25_.Task1969;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.List;

public class Task1969 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int t0 = in.readTimeHMS(':');
        boolean[] has = new boolean[60 * 60 * 24];
        while (true) {
            String s = in.next();
            if (s == null) {
                break;
            }
            int time = readTimeHMS(s, ':', ':');
            has[time] = true;
        }
        int count = 0;
        for (boolean e : has) {
            count += e ? 1 : 0;
        }
        int[] times = new int[count];
        count = 0;
        for (int i = 0; i < has.length; i++) {
            if (has[i]) {
                times[count++] = i;
            }
        }
        int first = times[0];
        int[] was = new int[has.length];
        for (int i = 1; i < count; i++) {
            if ((times[i] - first & 1) == 1) {
                continue;
            }
            int t1 = first - t0;
            int t2 = (times[i] - first) >> 1;
            boolean ok = true;
            int cn = -1;
            int lastGone = -1;
            int firstFinished = -1;
            all:
            for (int j = 0; j < count; j++) {
                if (was[times[j]] == i) {
                    continue;
                }
                int cur = times[j];
                if (cur + 2 * t2 >= has.length || !has[cur + 2 * t2] || was[cur + 2 * t2] == i) {
                    ok = false;
                    break all;
                }
                int curCount = 0;
                lastGone = cur;
                while (true) {
                    was[cur] = i;
                    was[cur + 2 * t2] = i;
                    cur += 2 * t2 + 2 * t1;
                    ++curCount;
                    if (cur + 2 * t2 >= has.length || !has[cur + 2 * t2] || was[cur + 2 * t2] == i) {
                        if (firstFinished < 0) {
                            firstFinished = cur - t1;
                        }
                        break;
                    }
                }
                if (cn >= 0 && cn != curCount) {
                    ok = false;
                    break;
                }
                cn = curCount;
            }
            if (firstFinished <= lastGone) {
                ok = false;
            }
            if (!ok) {
                continue;
            }
            ++i;
            all:
            for (int j = 0; j < count; j++) {
                if (was[times[j]] == i) {
                    continue;
                }
                List<Integer> z = new ArrayList<>();
                int cur = times[j];
                while (true) {
                    was[cur] = i;
                    was[cur + 2 * t2] = i;
                    z.add(cur - t1);
                    z.add(cur + t2);
                    cur += 2 * t2 + 2 * t1;
                    if (cur + 2 * t2 >= has.length || !has[cur + 2 * t2] || was[cur + 2 * t2] == i) {
                        z.add(cur - t1);
                        break;
                    }
                }
                for (int e : z) {
                    out.printf("%02d:%02d:%02d ", e / 3600, e / 60 % 60, e % 60);
                }
                out.println();
            }
            return;
        }
        throw new AssertionError();
    }

    public int readTimeHMS(String s, char delim1, char delim2) {
        int pos = s.indexOf(delim1);
        if (pos < 0) {
            throw new NumberFormatException("no delim found");
        }
        int pos2 = s.indexOf(delim2, pos + 1);
        if (pos2 < 0) {
            throw new NumberFormatException("no second delim found");
        }
        return Integer.parseInt(s.substring(0, pos)) * 3600 + Integer.parseInt(s.substring(pos + 1, pos2)) * 60
                + Integer.parseInt(s.substring(pos2 + 1));
    }
}
