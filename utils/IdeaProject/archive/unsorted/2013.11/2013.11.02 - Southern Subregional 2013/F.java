package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.PriorityQueue;

public class F {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int[] time = new int[n];
        double[] prob = new double[n];
        for (int i = 0; i < n; i++) {
            time[i] = in.nextInt();
            prob[i] = in.nextDouble();
        }
        PriorityQueue<Event> q = new PriorityQueue<>();
        for (int i = 0; i < m; i++) {
            q.add(new Event(-1, 0));
        }
        int current = 0;
        int[] endTime = new int[n];
        while (!q.isEmpty()) {
            Event e = q.poll();
            if (e.id >= 0) {
                endTime[e.id] = e.time;
            }
            if (current < n) {
                Event ne = new Event(current, e.time + time[current]);
                ++current;
                q.add(ne);
            }
        }
        for (int i = 1; i < n; i++) {
            endTime[i] = Math.max(endTime[i], endTime[i - 1]);
        }
        double curProb = 1.;
        double ans = 0;
        for (int i = 0; i < n; i++) {
            if (i == n - 1) {
                ans += endTime[i] * curProb;
            } else {
                ans += endTime[i] * curProb * (1 - prob[i]);
            }
            curProb *= prob[i];
        }
        out.println(ans);
    }

    static class Event implements Comparable<Event> {
        int id;
        int time;


        Event(int id, int time) {
            this.id = id;
            this.time = time;
        }

        @Override
        public int compareTo(Event o) {
            return Integer.compare(time, o.time);
        }
    }
}
