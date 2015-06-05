package coding;

import ru.ifmo.niyaz.DataStructures.MaxSegmentTree;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;
import ru.ifmo.niyaz.math.Rational;

import java.math.BigInteger;
import java.util.*;

public class TaskC {
    static class Hiker {
        int startPos;
        int period;
        int id;

        public Hiker(int startPos, int period, int id) {
            this.startPos = startPos;
            this.period = period;
            this.id = id;
        }
    }

    static class Event implements Comparable<Event> {
        long nextTime;
        boolean first;
        Hiker h;

        public Event(long nextTime, boolean first, Hiker h) {
            this.nextTime = nextTime;
            this.first = first;
            this.h = h;
        }

        @Override
        public int compareTo(Event o) {
            if (nextTime != o.nextTime) return Long.compare(nextTime, o.nextTime);
            return Integer.compare(h.id, o.h.id);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        System.err.println("[" + testNumber + "]");
        int dontUseThisN = in.nextInt();
        List<Hiker> hikers = new ArrayList<>();
        for (int i = 0; i < dontUseThisN; i++) {
            int start = in.nextInt();
            int count = in.nextInt();
            int time = in.nextInt();
            for (int j = 0; j < count; j++) hikers.add(new Hiker(start, time + j, hikers.size()));
        }
        PriorityQueue<Event> events = new PriorityQueue<>();
        Hiker[] a = hikers.toArray(new Hiker[hikers.size()]);
        for (int i = 0; i < a.length; i++) {
            events.add(new Event((360L - a[i].startPos) * a[i].period, true, a[i]));
        }
        int ans = Integer.MAX_VALUE;
        long lastTime = Long.MIN_VALUE;
        int balance = a.length;
        for (int i = 0; i < 6 * a.length; i++) {
            Event current = events.poll();
            if (current.nextTime != lastTime) ans = Math.min(ans, balance);
            if (current.first) --balance; else ++balance;
            Event newEvent = new Event(current.nextTime + 360L * current.h.period, false, current.h);
            events.add(newEvent);
            lastTime = current.nextTime;
        }
        out.println("Case #" + testNumber + ": " + ans);
    }

    private Rational getTimeToFinish(Hiker hiker) {
        return new Rational(BigInteger.valueOf((long) (360 - hiker.startPos) * hiker.period), BigInteger.valueOf(360));
    }
}
