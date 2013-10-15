package lib.test.on2013_04.on2013_04_13_Google_Code_Jam_2013_Qualification.TicTacToe;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TicTacToe {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        out.print("Case #" + testNumber + ": ");
        char[][] c = in.readCharacterFieldTokens(4, 4);
        boolean end = true;
        for (char[] e : c) {
            for (char d : e) {
                if (d == '.') {
                    end = false;
                }
            }
        }
        if (win(c, 'O')) {
            out.println("O won");
            return;
        }
        if (win(c, 'X')) {
            out.println("X won");
            return;
        }
        out.println(end ? "Draw" : "Game has not completed");
    }

    static boolean win(char[][] c, char d) {
        for (int i = 0; i < 4; i++) {
            boolean w = true;
            for (int j = 0; j < 4; j++) {
                if (c[i][j] == d || c[i][j] == 'T') {
                    continue;
                }
                w = false;
                break;
            }
            if (w) return true;
        }
        for (int i = 0; i < 4; i++) {
            boolean w = true;
            for (int j = 0; j < 4; j++) {
                if (c[j][i] == d || c[j][i] == 'T') {
                    continue;
                }
                w = false;
                break;
            }
            if (w) return true;
        }
        {
            boolean w = true;
            for (int i = 0; i < 4; i++) {
                if (c[i][i] == d || c[i][i] == 'T') {
                    continue;
                }
                w = false;
                break;
            }
            if (w) return true;
        }
        {
            boolean w = true;
            for (int i = 0; i < 4; i++) {
                if (c[i][3 - i] == d || c[i][3 - i] == 'T') {
                    continue;
                }
                w = false;
                break;
            }
            if (w) return true;
        }
        return false;
    }
}
