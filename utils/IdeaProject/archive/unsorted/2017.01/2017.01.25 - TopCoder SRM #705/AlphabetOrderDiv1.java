package coding;


public class AlphabetOrderDiv1 {
    public String isOrdered(String[] words) {
        boolean[] blocked = new boolean[26];
        for (int it = 0; it < 26; it++) {
            boolean found = false;
            for (char c = 'a'; c <= 'z'; c++) {
                if (blocked[c - 'a']) continue;
                boolean can = true;
                all: for (String e : words) {
                    for (int i = 0; i + 1 < e.length(); i++) {
                        if (!blocked[e.charAt(i) - 'a'] && e.charAt(i + 1) == c) {
                            can = false;
                            break all;
                        }
                    }
                }
                if (can) {
                    blocked[c - 'a'] = true;
                    found = true;
                    break;
                }
            }
            if (!found) return "Impossible";
        }
        return "Possible";
    }
}
