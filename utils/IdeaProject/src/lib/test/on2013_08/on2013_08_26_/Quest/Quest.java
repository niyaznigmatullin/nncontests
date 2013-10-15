package lib.test.on2013_08.on2013_08_26_.Quest;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Quest {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int roomCount = Integer.parseInt(in.nextLine().trim());
        String[] roomNames = new String[roomCount];
        for (int i = 0; i < roomCount; i++) {
            roomNames[i] = in.nextLine();
        }
        int doorCount = Integer.parseInt(in.nextLine().trim());
        String[] doorKeyNames = new String[doorCount];
        int[] ss = new int[doorCount];
        int[] ff = new int[doorCount];
        for (int i = 0; i < doorCount; i++) {
            int pos = 0;
            String z = in.nextLine();
            while (z.charAt(pos) < '0' || z.charAt(pos) > '9') ++pos;
            int v = 0;
            while (z.charAt(pos) >= '0' && z.charAt(pos) <= '9') {
                v = v * 10 + z.charAt(pos) - '0';
                pos++;
            }
            int u = 0;
            while (z.charAt(pos) < '0' || z.charAt(pos) > '9') ++pos;
            while (z.charAt(pos) >= '0' && z.charAt(pos) <= '9') {
                u = u * 10 + z.charAt(pos) - '0';
                ++pos;
            }
            while (z.charAt(pos) <= 32) ++pos;
            String name = z.substring(pos).trim();
            doorKeyNames[i] = name;
            ss[i] = v - 1;
            ff[i] = u - 1;
        }
        Map<String, String> whoHas = new HashMap<>();
        Map<String, Integer> characters = new HashMap<>();
        Map<String, Integer> objects = new HashMap<>();
        Map<String, Integer> rooms = new HashMap<>();
        Map<String, String[]> need = new HashMap<>();
        Map<String, String[]> give = new HashMap<>();
        for (int i = 0; i < roomCount; i++) {
            rooms.put(roomNames[i], i);
        }
        for (int i = 0; i < roomCount; i++) {
            String[] line = in.nextLine().split(" ");
            int characterCount = Integer.parseInt(line[0]);
            int objectCount = Integer.parseInt(line[1]);
            int itemCount = Integer.parseInt(line[2]);
            for (int j = 0; j < characterCount; j++) {
                String name = in.nextLine().trim();
                characters.put(name, i);
                String[] needs = in.nextLine().split(",");
                for (int k = 0; k < needs.length; k++) needs[k] = needs[k].trim();
                String[] gives = in.nextLine().split(",");
                for (int k = 0; k < gives.length; k++) gives[k] = gives[k].trim();
                need.put(name, needs);
                give.put(name, gives);
                for (String e : gives) {
                    whoHas.put(e, name);
                }
            }
            for (int j = 0; j < objectCount; j++) {
                String name = in.nextLine().trim();
                objects.put(name, i);
                String[] needs = in.nextLine().split(",");
                for (int k = 0; k < needs.length; k++) needs[k] = needs[k].trim();
                String[] gives = in.nextLine().split(",");
                for (int k = 0; k < gives.length; k++) gives[k] = gives[k].trim();
                need.put(name, needs);
                give.put(name, gives);
                for (String e : gives) {
                    whoHas.put(e, name);
                }
            }
            String[] items = new String[itemCount];
            for (int j = 0; j < itemCount; j++) {
                String name = in.nextLine().trim();
                whoHas.put(name, roomNames[i]);
                items[j] = name;
            }
            give.put(roomNames[i], items);
        }
        boolean[] roomOpened = new boolean[roomCount];
        boolean[] doorsOpened = new boolean[doorCount];
        int firstRoom = rooms.get(in.nextLine().trim());
        int lastRoom = rooms.get(in.nextLine().trim());
        roomOpened[firstRoom] = true;
        List<String> answer = new ArrayList<>();
        Set<String> itemsWeHave = new HashSet<>();
        Set<String> used = new HashSet<>();
        for (String e : give.get(roomNames[firstRoom])) {
            itemsWeHave.add(e);
            answer.add("pick " + e);
        }
        int curRoom = firstRoom;
        all:
        while (true) {
            for (int i = 0; i < doorCount; i++) {
                if (roomOpened[ss[i]] && !roomOpened[ff[i]] && itemsWeHave.contains(doorKeyNames[i])) {
                    curRoom = goToRoom(curRoom, ss[i], ss, ff, doorsOpened, answer, roomNames);
                    answer.add("open door to " + roomNames[ff[i]]);
                    doorsOpened[i] = true;
                    roomOpened[ff[i]] = true;
                    curRoom = goToRoom(curRoom, ff[i], ss, ff, doorsOpened, answer, roomNames);
                    for (String e : give.get(roomNames[curRoom])) {
                        answer.add("pick " + e);
                        itemsWeHave.add(e);
                    }
                    if (curRoom == lastRoom) {
                        answer.add("save princess");
                        break all;
                    }
                    continue all;
                }
                if (roomOpened[ff[i]] && !roomOpened[ss[i]] && itemsWeHave.contains(doorKeyNames[i])) {
                    curRoom = goToRoom(curRoom, ff[i], ss, ff, doorsOpened, answer, roomNames);
                    answer.add("open door to " + roomNames[ss[i]]);
                    doorsOpened[i] = true;
                    roomOpened[ss[i]] = true;
                    curRoom = goToRoom(curRoom, ss[i], ss, ff, doorsOpened, answer, roomNames);
                    for (String e : give.get(roomNames[curRoom])) {
                        answer.add("pick " + e);
                        itemsWeHave.add(e);
                    }
                    if (curRoom == lastRoom) {
                        answer.add("save princess");
                        break all;
                    }
                    continue all;
                }
            }
            for (String e : characters.keySet()) {
                if (used.contains(e)) {
                    continue;
                }
                if (!roomOpened[characters.get(e)]) {
                    continue;
                }
                boolean haveAll = true;
                for (String f : need.get(e)) {
                    if (!itemsWeHave.contains(f)) {
                        haveAll = false;
                        break;
                    }
                }
                if (!haveAll) continue;
                curRoom = goToRoom(curRoom, characters.get(e), ss, ff, doorsOpened, answer, roomNames);
                answer.add("talk to " + e);
                used.add(e);
                for (String f : need.get(e)) {
                    itemsWeHave.remove(f);
                }
                answer.add("give " + makeList(need.get(e)) + " to " + e);
                for (String f : give.get(e)) {
                    itemsWeHave.add(f);
                }
                answer.add("take " + makeList(give.get(e)) + " from " + e);
                continue all;
            }
            for (String e : objects.keySet()) {
                if (used.contains(e)) {
                    continue;
                }
                if (!roomOpened[objects.get(e)]) {
                    continue;
                }
                boolean haveAll = true;
                for (String f : need.get(e)) {
                    if (!itemsWeHave.contains(f)) {
                        haveAll = false;
                        break;
                    }
                }
                if (!haveAll) continue;
                curRoom = goToRoom(curRoom, objects.get(e), ss, ff, doorsOpened, answer, roomNames);
                used.add(e);
                answer.add("use " + makeList(need.get(e)) + " on " + e);
                for (String f : give.get(e)) {
                    itemsWeHave.add(f);
                }
                answer.add("take " + makeList(give.get(e)) + " from " + e);
                continue all;
            }
            out.println("dead princess");
            return;
        }
        for (String e : answer) {
            out.println(e);
        }
    }

    static int goToRoom(int curRoom, int target, int[] ss, int[] ff, boolean[] doorsOpened, List<String> answer, String[] roomNames) {
        List<Integer> path = new ArrayList<>();
        boolean[] was = new boolean[roomNames.length];
        if (!dfs(curRoom, target, ss, ff, doorsOpened, path, was)) throw new AssertionError();
        for (int i = 1; i < path.size(); i++) {
            answer.add("go to " + roomNames[path.get(i)]);
            curRoom = path.get(i);
        }
        return curRoom;
    }

    static boolean dfs(int v, int t, int[] ss, int[] ff, boolean[] doorsOpened, List<Integer> rooms, boolean[] was) {
        rooms.add(v);
        if (v == t) {
            return true;
        }
        was[v] = true;
        for (int i = 0; i < ss.length; i++) {
            if (!doorsOpened[i]) continue;
            if (ss[i] != v && ff[i] != v) continue;
            int u = ss[i] ^ ff[i] ^ v;
            if (was[u]) continue;
            if (dfs(u, t, ss, ff, doorsOpened, rooms, was)) return true;
        }
        rooms.remove(rooms.size() - 1);
        return false;
    }

    static String makeList(String[] a) {
        if (a.length == 1) {
            return a[0];
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length - 2; i++) {
            sb.append(a[i]).append(", ");
        }
        sb.append(a[a.length - 2]).append(" and ").append(a[a.length - 1]);
        return sb.toString();
    }
}
