package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.HashMap;
import java.util.Map;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            String s = in.next();
            String type = in.next();
            int cnta = 0;
            int cntb = 0;
            while (type.startsWith("&")) {
                cnta++;
                type = type.substring(1);
            }
            while (type.endsWith("*")) {
                cntb++;
                type = type.substring(0, type.length() - 1);
            }
            if (s.equals("typeof")) {
                Integer got = map.get(type);
                if (type.equals("void")) {
                    got = 0;
                }
                if (got == null || got < 0 || got + cntb - cnta < 0) {
                    out.println("errtype");
                } else {
                    out.print("void");
                    for (int j = 0; j < got + cntb - cnta; j++) {
                        out.print("*");
                    }
                    out.println();
                }
            } else {
                String newType = in.next();
                if (type.equals("errtype")) {
                    map.put(newType, -1);
                } else {
                    if (!type.equals("void") && !map.containsKey(type)) {
                        map.put(newType, -1);
                    } else {
                        int z = type.equals("void") ? 0 : map.get(type);
                        if (z < 0) {
                            map.put(newType, -1);
                        } else {
                            int got = Math.max(-1, z + cntb - cnta);
                            map.put(newType, got);
                        }
                    }
                }
            }
        }
    }
}
