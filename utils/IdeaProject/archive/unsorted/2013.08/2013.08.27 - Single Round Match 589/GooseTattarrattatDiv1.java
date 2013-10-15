package coding;

import ru.ifmo.niyaz.DataStructures.DisjointSetUnion;

public class GooseTattarrattatDiv1 {
    public int getmin(String s) {
        int[] have = new int[26];
        for (char c : s.toCharArray()) {
            have[c - 'a']++;
        }
        DisjointSetUnion dsu = new DisjointSetUnion(26);
        for (int i = 0; i < s.length(); i++) {
            dsu.union(s.charAt(i) - 'a', s.charAt(s.length() - i - 1) - 'a');
        }
        int[] maximal = new int[26];
        for (int i = 0; i < 26; i++) {
            maximal[dsu.get(i)] = Math.max(maximal[dsu.get(i)], have[i]);
        }
        int ans = s.length();
        for (int i = 0; i < 26; i++) {
            if (have[i] == 0) continue;
            if (dsu.get(i) == i) {
                ans -= maximal[i];
            }
        }
        return ans;
    }
}
