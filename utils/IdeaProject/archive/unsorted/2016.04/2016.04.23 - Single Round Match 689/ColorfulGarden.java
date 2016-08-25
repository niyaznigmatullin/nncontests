package coding;

public class ColorfulGarden {
    public String[] rearrange(String[] garden) {
        String[] ans = new String[]{"", ""};
        int n = garden[0].length();
        int[] count = new int[26];
        for (String e : garden) {
            for (int j = 0; j < n; j++) {
                count[e.charAt(j) - 'a']++;
            }
        }
        int all = n * 2;
        for (int i : count) {
            if (2 * i > all) {
                return new String[0];
            }
        }
        int last1 = -1;
        int last2 = -1;
        loop: for (int i = 0; i < n; i++) {
            for (int c1 = 0; c1 < 26; c1++) {
                if (c1 == last1 || count[c1] == 0) continue;
                for (int c2 = 0; c2 < 26; c2++) {
                    if (c2 == last2 || c1 == c2 || count[c2] == 0) continue;
                    --count[c1];
                    --count[c2];
                    boolean ok = true;
                    for (int f = 0; f < 26; f++) {
                        if (2 * count[f] > all - 2) {
                            ok = false;
                            break;
                        }
                    }
                    if (ok) {
                        ans[0] += (char) (c1 + 'a');
                        ans[1] += (char) (c2 + 'a');
                        all -= 2;
                        last1 = c1;
                        last2 = c2;
                        continue loop;
                    }
                    count[c1]++;
                    count[c2]++;
                }
            }
        }
        return ans;
    }
}
