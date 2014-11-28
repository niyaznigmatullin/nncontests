package coding;

public class LittleElephantAndBallsAgain {
    public int getNumber(String S) {
        int ans = 0;
        for (int i = 0; i < S.length(); ) {
            int j = i;
            while (j < S.length() && S.charAt(i) == S.charAt(j)) {
                ++j;
            }
            ans = Math.max(ans, j - i);
            i = j;
        }
        return S.length() - ans;
    }
}
