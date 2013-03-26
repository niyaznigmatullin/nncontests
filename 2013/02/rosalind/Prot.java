import java.util.*;
import java.io.*;

public class Prot {
    public static void main(String[] args) throws Throwable {
        Scanner sc = new Scanner(System.in);
        Scanner file = new Scanner(new File("alpha"));
        Map<String, String> map = new HashMap<>();
        while (file.hasNext()) {
            String s = file.next();
            String t = file.next();
            map.put(s, t);
        }
        String s = sc.next();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i += 3) {
            String t = map.get(s.substring(i, i + 3));
            if (t.equals("Stop")) break;
            ans.append(t);
        }
        System.out.println(ans);
    }
}