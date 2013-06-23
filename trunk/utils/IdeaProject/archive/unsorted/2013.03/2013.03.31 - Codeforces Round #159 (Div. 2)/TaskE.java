package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class TaskE {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int menCount = in.nextInt();
        int floors = in.nextInt();
        List<Integer>[] wait = new List[floors];
        List<Integer>[] inside = new List[floors];
        for (int i = 0; i < floors; i++) {
            wait[i] = new ArrayList<>();
            inside[i] = new ArrayList<>();
        }
        Integer[] order = new Integer[menCount];
        final int[] comeTime = new int[menCount];
        int[] start = new int[menCount];
        int[] finish = new int[menCount];
        for (int i = 0; i < menCount; i++) {
            comeTime[i] = in.nextInt();
            start[i] = in.nextInt() - 1;
            finish[i] = in.nextInt() - 1;
            order[i] = i;
        }
        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return comeTime[o1] - comeTime[o2];
            }
        });
        int currentInOrder = 0;
        NavigableSet<Integer> q = new TreeSet<>();
        int currentFloor = 0;
        long currentTime = 0;
        int up = 0;
        int down = 0;
        long[] ans = new long[menCount];
        while (true) {
            int nextTime1 = Integer.MAX_VALUE;
            int nextTime2 = Integer.MAX_VALUE;
            boolean goUp = false;
            boolean goDown = false;
            if (up > 0 || down > 0) {
                if (up >= down) {
                    Integer next = q.higher(currentFloor);
                    if (next != null) {
                        nextTime1 = next - currentFloor;
                        goUp = true;
                    }
                } else {
                    Integer next = q.lower(currentFloor);
                    if (next != null) {
                        nextTime1 = currentFloor - next;
                        goDown = true;
                    }
                }
            }
            if (currentInOrder < menCount) {
                nextTime2 = (int) (comeTime[order[currentInOrder]] - currentTime);
            }
            int deltaTime = Math.min(nextTime1, nextTime2);
            if (deltaTime == Integer.MAX_VALUE) {
                break;
            }
            if (goUp) currentFloor += deltaTime;
            if (goDown) currentFloor -= deltaTime;
            while (currentInOrder < menCount && comeTime[order[currentInOrder]] - currentTime == deltaTime) {
                int i = order[currentInOrder];
                wait[start[i]].add(i);
                if (start[i] == currentFloor) {
                    if (goUp) ++up;
                    if (goDown) ++down;
                } else if (start[i] > currentFloor) ++up;
                else ++down;
                q.add(start[i]);
                ++currentInOrder;
            }
            q.remove(currentFloor);
            while (!wait[currentFloor].isEmpty()) {
                int i = wait[currentFloor].remove(wait[currentFloor].size() - 1);
                inside[finish[i]].add(i);
                q.add(finish[i]);
                if (goUp) --up;
                if (goDown) --down;
                if (finish[i] >= currentFloor) {
                    up++;
                } else {
                    ++down;
                }
            }
            currentTime += deltaTime;
            while (!inside[currentFloor].isEmpty()) {
                int i = inside[currentFloor].remove(inside[currentFloor].size() - 1);
                if (goUp) --up;
                if (goDown) --down;
                ans[i] = currentTime;
            }
        }
        for (long i : ans) {
            out.println(i);
        }
    }
}
