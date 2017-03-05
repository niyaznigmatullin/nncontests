package coding;

public class ClosestRabbit {

    static int hyp2(int dx, int dy) {
        return dx * dx + dy * dy;
    }

    public double getExpected(String[] board, int r) {
        int n = board.length;
        int m = board[0].length();
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = board[i].toCharArray();
        }
        double[][] C = new double[n * m + 1][n * m + 1];
        for (int i = 0; i < C.length; i++) {
            C[i][0] = 1.;
            for (int j = 1; j <= i; j++) {
                C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
            }
        }
        double ans = 0;
        for (int firstX = 0; firstX < n; firstX++) {
            for (int firstY = 0; firstY < m; firstY++) {
                if (c[firstX][firstY] != '.') continue;
                for (int secondX = firstX; secondX < n; secondX++) {
                    for (int secondY = 0; secondY < m; secondY++) {
                        if (c[secondX][secondY] != '.') continue;
                        if (firstX == secondX && secondY <= firstY) continue;
                        int distance = hyp2(firstX - secondX, firstY - secondY);
                        int count = 0;
                        for (int thirdX = 0; thirdX < n; thirdX++) {
                            for (int thirdY = 0; thirdY < m; thirdY++) {
                                if (c[thirdX][thirdY] != '.') continue;
                                int distance1 = hyp2(firstX - thirdX, firstY - thirdY);
                                if (distance1 < distance || distance1 == distance && (thirdX < secondX || thirdX == secondX && thirdY <= secondY)) continue;
                                int distance2 = hyp2(secondX - thirdX, secondY - thirdY);
                                if (distance2 < distance || distance2 == distance && (thirdX < firstX || (thirdX == firstX && thirdY <= firstY))) continue;
                                ++count;
                            }
                        }
                        ans += C[count][r - 2];
                    }
                }
            }
        }
        int number = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == '.') ++number;
            }
        }
        ans /= C[number][r];
        return ans;
    }
}
