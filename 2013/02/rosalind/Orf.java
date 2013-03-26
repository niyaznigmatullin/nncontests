import java.io.*;
import java.util.*;

public class Orf {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new File("alpha"));
        Map<String, String> map = new HashMap<>();
        while (in.hasNext()) {
            String s = in.next();
            String t = in.next();
            map.put(s, t);
        }
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        s = s + "#" + rev(s);
        s = s.replace('T', 'U');
        Set<String> answer = new HashSet<>();
        for (int i = 0; i + 3 <= s.length(); i++) {
            String start = s.substring(i, i + 3);
            if (!start.equals("AUG")) {
                continue;
            }
            for (int j = i + 3; j + 3 <= s.length(); j += 3) {
                String end = s.substring(j, j + 3);
                if (!"Stop".equals(map.get(end))) {
                    continue;
                }
                StringBuilder sb = new StringBuilder();
                boolean ok = true;
                for (int k = i; k < j; k += 3) {
                    String t = map.get(s.substring(k, k + 3));
                    if (t == null || t.equals("Stop")) {
                        ok = false;
                        break;
                    }
                    sb.append(t);
                }
                if (ok) {
                    answer.add(sb.toString());
                }
            }
        }
        for (String e : answer) 
            System.out.println(e);
    }
    
    static String rev(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, "ATGC".charAt("TACG".indexOf(sb.charAt(i))));
        }
        return sb.reverse().toString();
    }
}