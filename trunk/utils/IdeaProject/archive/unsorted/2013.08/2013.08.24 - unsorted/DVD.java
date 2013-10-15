package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.*;

public class DVD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = Integer.parseInt(in.nextLine().trim());
        String[] names = new String[n];
        int[] year = new int[n];
        int[] region = new int[n];
        for (int i = 0; i < n; i++) {
            String line = in.nextLine();
            int pos = line.indexOf('"', 1);
            names[i] = line.substring(0, pos + 1);
            line = line.substring(pos + 1);
            StringTokenizer st = new StringTokenizer(line);
            year[i] = Integer.parseInt(st.nextToken());
            region[i] = Integer.parseInt(st.nextToken()) - 1;
        }
        int[] years = ArrayUtils.sortAndUnique(year);
        for (int i = 0; i < n; i++) {
            year[i] = Arrays.binarySearch(years, year[i]);
        }
        int[][][] dp = new int[6][5][years.length + 1];
        List<Integer>[] filmsByYear = new List[years.length];
        for (int i = 0; i < filmsByYear.length; i++) filmsByYear[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            filmsByYear[year[i]].add(i);
        }
        for (int i = 0; i < years.length; i++) {
            int[] count = new int[5];
            for (int j : filmsByYear[i]) {
                count[region[j]]++;
            }
            for (int prevChanged = 0; prevChanged < 6; prevChanged++) {
                for (int mask = 0; mask < 1 << 5; mask++) {
                    int curChanged = prevChanged + Integer.bitCount(mask) - 1;
                    if (curChanged >= 6) continue;
                    int watched = 0;
                    for (int j = 0; j < 5; j++)
                        if (((mask >> j) & 1) == 1)
                            watched += count[j];
                    for (int first = 0; first < 5; first++) {
                        if (((mask >> first) & 1) == 0) continue;
                        for (int last = 0; last < 5; last++) {
                            if (((mask >> last) & 1) == 0) continue;
                            if ((mask & (mask - 1)) != 0 && first == last)
                                continue;
                            if (dp[curChanged][last][i + 1] < dp[prevChanged][first][i] + watched) {
                                dp[curChanged][last][i + 1] = dp[prevChanged][first][i] + watched;
                            }
                        }
                    }
                }
            }
        }
        int bestLast = -1;
        int bestChanged = -1;
        int ans = 0;
        for (int changed = 0; changed < 6; changed++) {
            for (int i = 0; i < 5; i++) {
                if (dp[changed][i][years.length] > ans) {
                    ans = dp[changed][i][years.length];
                    bestLast = i;
                    bestChanged = changed;
                }
            }
        }
        List<Integer> answer = new ArrayList<>();
        all: for (int i = years.length - 1; i >= 0; i--) {
            int[] count = new int[5];
            for (int j : filmsByYear[i]) {
                count[region[j]]++;
            }
            for (int prevChanged = 0; prevChanged < 6; prevChanged++) {
                for (int mask = 0; mask < 1 << 5; mask++) {
                    int curChanged = prevChanged + Integer.bitCount(mask) - 1;
                    if (curChanged != bestChanged) continue;
                    int watched = 0;
                    for (int j = 0; j < 5; j++)
                        if (((mask >> j) & 1) == 1)
                            watched += count[j];
                    for (int first = 0; first < 5; first++) {
                        if (((mask >> first) & 1) == 0) continue;
                        for (int last = 0; last < 5; last++) {
                            if (last != bestLast || ((mask >> last) & 1) == 0) continue;
                            if ((mask & (mask - 1)) != 0 && first == last)
                                continue;
                            if (dp[curChanged][last][i + 1] == dp[prevChanged][first][i] + watched) {
                                for (int j : filmsByYear[i]) {
                                    if (region[j] == last) {
                                        answer.add(j);
                                    }
                                }
                                for (int c = 0; c < 5; c++) {
                                    if (c == last || c == first || ((mask >> c) & 1) == 0) continue;
                                    for (int j : filmsByYear[i]) {
                                        if (region[j] == c) {
                                            answer.add(j);
                                        }
                                    }
                                }
                                if (first != last)
                                    for (int j : filmsByYear[i]) {
                                        if (region[j] == first) {
                                            answer.add(j);
                                        }
                                    }
                                bestChanged = prevChanged;
                                bestLast = first;
                                continue all;
                            }
                        }
                    }
                }
            }
        }
        Collections.reverse(answer);
        if (answer.size() != ans) throw new AssertionError();
        out.println(answer.size());
        for (int i : answer) {
            out.println(names[i]);
        }
    }
}
