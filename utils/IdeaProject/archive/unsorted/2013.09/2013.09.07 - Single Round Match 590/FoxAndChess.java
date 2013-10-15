package coding;

public class FoxAndChess {
    public String ableToMove(String s, String t) {
        int i = 0;
        int j = 0;
        while (true) {
            while (i < s.length() && s.charAt(i) == '.') {
                ++i;
            }
            while (j < t.length() && t.charAt(j) == '.') {
                ++j;
            }
            if ((i >= s.length()) != (j >= t.length())) {
                return "Impossible";
            }
            if (i >= s.length()) break;
            if (s.charAt(i) != t.charAt(j)) {
                return "Impossible";
            }
            if (s.charAt(i) == 'R') {
                if (i > j) return "Impossible";
            } else {
                if (i < j) return "Impossible";
            }
            ++i;
            ++j;
        }
        return "Possible";
    }
}
