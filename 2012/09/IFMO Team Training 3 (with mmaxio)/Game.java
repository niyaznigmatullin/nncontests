package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Game {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (n == 0)
            throw new UnknownError();
        String[] names = new String[n];

        HashMap<String, Integer> memo = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            names[i] = in.next();
            memo.put(names[i], i);
        }
        HashSet<Integer>[] g = new HashSet[n];
        for (int i = 0; i < n; i++)
            g[i] = new HashSet<Integer>();
        for (int i = 0; i < n - 1; i++) {
            int v1 = memo.get(in.next());
            int v2 = memo.get(in.next());
            g[v1].add(v2);
            g[v2].add(v1);
        }

        int initN = n;
        int roundNum = 1;

        for (; n != 2; n = (n + 1) / 2, roundNum++) {


            System.out.println(n);

            out.println("Round #" + roundNum);
            int losers = n / 2;
            boolean haveWild = (n & 1) == 1;

            int pair1 = -1;
            int pair2 = -1;

            int[] winnerOpp = new int[initN];
            Arrays.fill(winnerOpp, -1);

            boolean[] tookPart = new boolean[initN];

            int wildCardAdv = -1;

            for (int i = 0; i < initN; i++) {
                if (g[i].size() == 1) {
                    //System.out.println("one match " + i);
                    int opp = g[i].iterator().next();
                    if (winnerOpp[opp] == -1) {
                        winnerOpp[opp] = i;
                        tookPart[i] = true;
                        tookPart[opp] = true;
                    } else {
                        wildCardAdv = i;
                    }
                }
            }

            for (int i = 0; i < initN; i++) {
                if (winnerOpp[i] != -1) {
                    out.println(names[i] + " defeats " + names[winnerOpp[i]]);
                    g[i].remove(winnerOpp[i]);
                    g[winnerOpp[i]].remove(i);
                }
            }
            if (haveWild) {
                if (wildCardAdv == -1) {
                    for (int i = 0; i < initN; i++)
                        if (!g[i].isEmpty() && !tookPart[i])
                            wildCardAdv = i;
                }
                out.println(names[wildCardAdv] + " advances with wildcard");
            }
        }

        out.println("Round #" + roundNum);
        int v1 = -1;
        int v2 = -1;
        for (int i = 0; i < initN; i++) {
            if (!g[i].isEmpty()) {
                v1 = i;
                v2 = g[i].iterator().next();
                break;
            }
        }
        out.println(names[v1] + " defeats " + names[v2]);
        out.println("Winner: " + names[v1]);
        out.println();
    }
}
