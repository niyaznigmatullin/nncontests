package lib.test.on2013_04.on2013_04_13_Croc_Champ_2013___Qualification_Round.B___Command_Line_Arguments;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class TaskB {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String s = in.readLine();
        for (int i = 0; i < s.length();) {
            if (s.charAt(i) == ' ') {
                ++i;
                continue;
            }
            int j = i;
            if (s.charAt(i) == '\"') {
                ++j;
                while (s.charAt(j) != '\"') {
                    ++j;
                }
                ++j;
                out.println("<" + s.substring(i + 1, j - 1) + ">");
            } else {
                while (j < s.length() && s.charAt(j) != ' ') {
                    ++j;
                }
                out.println("<" + s.substring(i, j) + ">");
            }
            i = j;
        }
    }
}
