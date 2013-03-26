import java.io.*;
import java.util.*;

public class LONG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            sc.next();
            s[i] = sc.next();
        }
        List<Integer>[] edges = new List[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
        }
        int[][] f = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                int maximal = 0;
                for (int k = 1; k < s[i].length() && k < s[j].length(); k++) {
                    if (s[i].endsWith(s[j].substring(0, k))) {
                        maximal = k;
                    }
                }
                f[i][j] = maximal;
                if (maximal * 2 > s[i].length()) edges[i].add(j);
            }            
        }
        Random rand = new Random(58L);
        all: while (true) {
            int last = rand.nextInt(n);
            boolean[] was = new boolean[n];
            was[last] = true;
            StringBuilder ans = new StringBuilder(s[last]);
            for (int i = 1; i < n; i++) {
                Collections.shuffle(edges[last], rand);
                int next = -1;
                for (int j : edges[last]) {
                    if (was[j]) continue;
                    next = j;
                    break;
                }
                if (next < 0) continue all;
                ans.append(s[next].substring(f[last][next]));
                last = next;                
                was[last] = true;
            }
            System.out.println(ans);
            break;
        }
    }
}

