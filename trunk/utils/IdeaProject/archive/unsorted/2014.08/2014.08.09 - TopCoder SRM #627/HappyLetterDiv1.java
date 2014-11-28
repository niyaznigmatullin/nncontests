package coding;

public class HappyLetterDiv1 {
    public String getHappyLetters(String letters) {
        int[] count = new int[26];
        for (char c : letters.toCharArray()) {
            count[c - 'a']++;
        }
        String ans = "";
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= count[i]; j++) {
                int all = 0;
                int maximum = 0;
                for (int k = 0; k < 26; k++) {
                    int cur = i == k ? count[i] - j : count[k];
                    all += cur;
                    maximum = Math.max(maximum, cur);
                }
                if ((all & 1) == 0 && maximum * 2 <= all) {
                    ans += (char) (i + 'a');
                    break;
                }
            }
        }
        return ans;
    }
}
