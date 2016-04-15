package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LaundroMatt {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int loads = in.nextInt();
        int washers = in.nextInt();
        int dryers = in.nextInt();
        int dryingTime = in.nextInt();
        int[] washingTime = in.readIntArray(washers);
        long[] whenFreeTime = new long[washers];
        int washedLoads = 0;
        PriorityQueue<Event> events = new PriorityQueue<>(loads);
        PriorityQueue<Integer> washersQueue = new PriorityQueue<>(washers, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return Long.compare(washingTime[a] + whenFreeTime[a], washingTime[b] + whenFreeTime[b]);
            }
        });
        for (int i = 0; i < washers; i++) {
            washersQueue.add(i);
        }
        int dryersFree = dryers;
        long currentTime = 0;
        for (int i = 0; i < loads; i++) {
            int f = washersQueue.poll();
            whenFreeTime[f] += washingTime[f];
            events.add(new Event(whenFreeTime[f], 1));
            washersQueue.add(f);
        }
        while (true) {
            if (washedLoads > 0 && dryersFree > 0) {
                events.add(new Event(currentTime + dryingTime, 2));
                washedLoads--;
                dryersFree--;
                continue;
            }
            if (events.isEmpty()) {
                break;
            }
            Event f = events.poll();
            currentTime = f.time;
            if (f.type == 1) {
                washedLoads++;
            } else {
                dryersFree++;
            }
        }
        out.println("Case #" + testNumber + ": " + currentTime);
        System.err.println("Case #" + testNumber + ": " + currentTime);
    }

    static class Event implements Comparable<Event> {
        long time;
        int type;

        public Event(long time, int type) {
            this.time = time;
            this.type = type;
        }

        @Override
        public int compareTo(Event o) {
            return Long.compare(time, o.time);
        }
    }
}
