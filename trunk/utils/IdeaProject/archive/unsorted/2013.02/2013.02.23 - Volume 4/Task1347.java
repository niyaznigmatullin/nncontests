package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class Task1347 {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = Integer.parseInt(in.nextLine().trim());
        String[] name = new String[n];
        Map<String, Integer> idByName = new HashMap<>();
        Set<String>[] friends = new Set[n];
        Set<String>[] inFriends = new Set[n];
        for (int i = 0; i < n; i++) {
            friends[i] = new HashSet<>();
            inFriends[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i++) {
            name[i] = in.nextLine().trim();
            idByName.put(name[i], i);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String s = in.nextLine().trim();
                s = s.replaceAll("[ ]", "");
                sb.append(s);
                if (s.endsWith("</blog>")) {
                    break;
                }
            }
            for (int j = sb.indexOf("<friend>"); j >= 0; j = sb.indexOf("<friend>", j + 1)) {
                int k = sb.indexOf("</friend>", j + 1);
                String friend = sb.substring(j + "<friend>".length(), k);
                if (!friend.equals(name[i])) {
                    friends[i].add(friend);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (String e : friends[i]) {
                if (idByName.containsKey(e)) {
                    inFriends[idByName.get(e)].add(name[i]);
                }
            }
        }
        for (int i = 0; i < n; i++) {
            Set<String> intersection = new HashSet<>(friends[i]);
            intersection.retainAll(inFriends[i]);
            List<String> list = new ArrayList<>(intersection);
            List<String> f1 = new ArrayList<>(friends[i]);
            List<String> f2 = new ArrayList<>(inFriends[i]);
            Collections.sort(list);
            Collections.sort(f1);
            Collections.sort(f2);
            out.println(name[i]);
            out.print("1:");
            printList(out, f1);
            out.print("2:");
            printList(out, f2);
            out.print("3:");
            printList(out, list);
            out.println();
        }
    }

    private void printList(FastPrinter out, List<String> f1) {
        for (int j = 0; j < f1.size(); j++) {
            if (j > 0) {
                out.print(',');
            }
            out.print(' ');
            out.print(f1.get(j));
        }
        out.println();
    }
}
