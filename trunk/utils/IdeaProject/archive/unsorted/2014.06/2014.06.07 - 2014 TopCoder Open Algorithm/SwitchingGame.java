package coding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SwitchingGame {

    static class Event {
        int l;
        int r;

        Event(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public int timeToWin(String[] states) {
        int n = states.length;
        int m = states[0].length();
        List<Event> eventsOn = new ArrayList<>();
        List<Event> eventsOff = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int when = -1;
            char last = '-';
            for (int j = 0; j < n; j++) {
                char cur = states[j].charAt(i);
                if (cur == '?') {
                    continue;
                }
                if (last != cur) {
                    if (cur == '+') {
                        eventsOn.add(new Event(when + 1, j));
                    } else {
                        eventsOff.add(new Event(when + 1, j));
                    }
                }
                last = cur;
                when = j;
            }
        }
        return solve(eventsOn) + solve(eventsOff) + n;
    }

    int solve(List<Event> events) {
        Collections.sort(events, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.r - o2.r;
            }
        });
        int last = Integer.MIN_VALUE;
        int ans = 0;
        for (Event e : events) {
            if (last >= e.l) {
                continue;
            }
            last = e.r;
            ++ans;
        }
        return ans;
    }
}
