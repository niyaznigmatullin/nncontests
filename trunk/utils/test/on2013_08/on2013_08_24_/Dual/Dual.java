package lib.test.on2013_08.on2013_08_24_.Dual;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;

public class Dual {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        String[] firstLine = in.nextLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        String minmax = in.nextLine();
        boolean min = minmax.startsWith("min");
        minmax = minmax.substring(4);
        int[] c = getCoef(minmax, n);
        in.nextLine();
        int[] gol = new int[n];
        for (int i = 0; i < n; i++) {
            String line = in.nextLine().replaceAll(" ", "");
            int pos = 1;
            while (line.charAt(pos) >= '0' && line.charAt(pos) <= '9') {
                ++pos;
            }
            int x = Integer.parseInt(line.substring(1, pos)) - 1;
            if (line.charAt(pos) == '<') {
                gol[x] = -1;
            } else if (line.charAt(pos) == '>') {
                gol[x] = 1;
            } else {
                gol[x] = 0;
            }
        }
        in.nextLine();
        int[][] a = new int[m][];
        int[] b = new int[m];
        int[] ga = new int[m];
        for (int i = 0; i < m; i++) {
            String line = in.nextLine().replaceAll(" ", "");
            int pos = 0;
            while (line.charAt(pos) != '=' && line.charAt(pos) != '<' && line.charAt(pos) != '>') {
                ++pos;
            }
            a[i] = getCoef(line.substring(0, pos), n);
            line = line.substring(pos);
            if (line.charAt(0) == '=') {
                ga[i] = 0;
                line = line.substring(1);
            } else if (line.charAt(0) == '>') {
                ga[i] = 1;
                line = line.substring(2);
            } else {
                ga[i] = -1;
                line = line.substring(2);
            }
            b[i] = Integer.parseInt(line);
        }
        out.println(m + " " + n);
        out.print(min ? "max " : "min ");
        out.println(printCoef(b));
        out.println("with");
        for (int i = 0; i < m; i++) {
            out.print("y" + (i + 1));
            if (ga[i] == 0) out.println(" arbitary");
            else if (min && ga[i] > 0 || !min && ga[i] < 0) {
                out.println(">=0");
            } else {
                out.println("<=0");
            }
        }
        out.println("under");
        for (int i = 0; i < n; i++) {
            int[] cc = new int[m];
            for (int j = 0; j < m; j++) {
                cc[j] = a[j][i];
            }
            out.print(printCoef(cc));
            if (gol[i] == 0) out.println("=" + c[i]);
            else if (!min && gol[i] > 0 || min && gol[i] < 0) {
                out.println(">=" + c[i]);
            } else {
                out.println("<=" + c[i]);
            }
        }
    }

    static String printCoef(int[] b) {
        int m = b.length;
        StringBuilder sb = new StringBuilder();
        {
            int j = 0;
            for (int i = 0; i < m; i++) {
                if (b[i] == 0) continue;
                if (j > 0 && b[i] > 0) {
                    sb.append('+');
                }
                if (b[i] == -1) sb.append('-');
                if (b[i] != 1 && b[i] != -1)
                    sb.append(b[i]);
                sb.append("y" + (i + 1));
                ++j;
            }
            if (j == 0) sb.append('0');
        }
        return sb.toString();
    }

    static int[] getCoef(String line, int n) {
        line = line.replaceAll(" ", "");
        if (line.equals("0")) {
            return new int[n];
        }
        if (!line.startsWith("-") && !line.startsWith("+")) {
            line = "+" + line;
        }
        int[] ret = new int[n];
        while (!line.isEmpty()) {
            int pos = 1;
            while (pos < line.length() && line.charAt(pos) != '+' && line.charAt(pos) != '-')
            ++pos;
            String nom = line.substring(0, pos);
            line = line.substring(pos);
            pos = nom.indexOf('x');
            int x = 0;
            for (int i = 1; i < pos; i++) {
                x = x * 10 + nom.charAt(i) - '0';
            }
            if (x == 0) x = 1;
            if (nom.charAt(0) == '-') x = -x;
            ret[Integer.parseInt(nom.substring(pos + 1)) - 1] = x;
        }
        return ret;
    }
}
