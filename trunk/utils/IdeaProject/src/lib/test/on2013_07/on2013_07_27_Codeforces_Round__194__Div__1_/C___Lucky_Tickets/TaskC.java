package lib.test.on2013_07.on2013_07_27_Codeforces_Round__194__Div__1_.C___Lucky_Tickets;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaskC {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int k = in.nextInt();
        int m = in.nextInt();
        Set<String> set = new HashSet<>();
        if (k == 0) {
            for (int i = 0; i < m; i++) {
                String s = i + "";
                for (int j = s.length(); j < 8; j++) {
                    out.print('0');
                }
                out.print(s);
                out.println();
            }
            return;
        }
//        for (int i = 1; set.size() < m && i < k; i++) {
//            String z = "" + i + (k - i);
//            int toget = 8 - z.length();
//            int ten = 1;
//            for (int j = 0; j < toget; j++) ten *= 10;
//            for (int j = 0; j < ten; j++) {
//                int x = j;
//                boolean ok = false;
//                for (int e = 0; e < toget; e++) {
//                    if (x % 10 == 0) {
//                        ok = true;
//                        break;
//                    }
//                    x /= 10;
//                }
//                if (ok) {
//                    String z0 = "";
//                    for (int e = 0, y = x; e < toget; e++) {
//                        z0 = (y % 10) + z0;
//                        y /= 10;
//                    }
//                    for (int e = 0; e <= toget; e++) {
//                        int id = z0.indexOf('0');
//                        int id2 = z0.indexOf('0', e);
//                        if ((e == 0) || (id >= 0 && id < e)) {
//                            if (e == toget || (id2 >= 0)) {
//                                set.add(z0.substring(0, e) + z + z0.substring(e));
//                            }
//                        }
//                    }
//                }
//            }
//        }
        sb = new StringBuilder();
        List<String>[] ff = new List[9];
        for (int i = 0; i < 9; i++) {
            ff[i] = new ArrayList<>();
            gen(0, i, false, ff[i]);
        }
        all:
        for (int i = 0; i < 1; i++) {
//            String z1 = i + "";
            String z2 = (k - i) + "";
            int toget = 8 - z2.length();
            for (int first = 0; first <= toget; first++) {
                int second = toget - first;
                for (String f1 : ff[first]) {
                    for (String f2 : ff[second]) {
                        set.add(f1 + z2 + f2);
                        if (set.size() >= m) {
                            break all;
                        }
                    }
                }
            }
        }
        all:
        for (int i = 1; set.size() < m && i < k; i++) {
            String z1 = i + "";
            String z2 = (k - i) + "";
            int toget = 8 - z1.length() - z2.length();
            for (int first = 0; first <= toget; first++) {
                for (int second = 0; second + first <= toget; second++) {
                    int third = toget - first - second;
                    for (String f1 : ff[first]) {
                        for (String f2 : ff[second]) {
                            for (String f3 : ff[third]) {
                                set.add(f1 + z1 + f2 + z2 + f3);
                                if (set.size() >= m) {
                                    break all;
                                }
                            }
                        }
                    }
                }
            }
        }
        all:
        for (int i = 1; set.size() < m && i < k; i++) {
            for (int i1 = 1; i1 + i < k; i1++) {
                String z1 = i + "";
                String z2 = i1 + "";
                String z3 = (k - i - i1) + "";
                int toget = 8 - z1.length() - z2.length() - z3.length();
                if (toget < 0) {
                    break;
                }
                for (int first = 0; first <= toget; first++) {
                    for (int second = 0; second + first <= toget; second++) {
                        for (int third = 0; third + first + second <= toget; third++) {
                            int fourth = toget - first - second - third;
                            for (String f1 : ff[first]) {
                                for (String f2 : ff[second]) {
                                    for (String f3 : ff[third]) {
                                        for (String f4 : ff[fourth]) {
                                            set.add(f1 + z1 + f2 + z2 + f3 + z3 + f4);
                                            if (set.size() >= m) {
                                                break all;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        all:
        for (int i = 1; set.size() < m && i < k; i++) {
            for (int i1 = 1; i1 + i < k; i1++) {
                for (int i2 = 1; i2 + i + i1 < k; i2++) {
                    String z1 = i + "";
                    String z2 = i1 + "";
                    String z3 = i2 + "";
                    String z4 = (k - i - i1 - i2) + "";
                    int toget = 8 - z1.length() - z2.length() - z3.length() - z4.length();
                    if (toget < 0) {
                        break;
                    }
                    for (int first = 0; first <= toget; first++) {
                        for (int second = 0; second + first <= toget; second++) {
                            for (int third = 0; third + first + second <= toget; third++) {
                                for (int fourth = 0; fourth + first + second + third <= toget; fourth++) {
                                    int fifth = toget - first - second - third - fourth;
                                    for (String f1 : ff[first]) {
                                        for (String f2 : ff[second]) {
                                            for (String f3 : ff[third]) {
                                                for (String f4 : ff[fourth]) {
                                                    for (String f5 : ff[fifth]) {
                                                        set.add(f1 + z1 + f2 + z2 + f3 + z3 + f4 + z4 + f5);
                                                        if (set.size() >= m) {
                                                            break all;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println(set.size());
    }

    static StringBuilder sb;

    static void gen(int x, int n, boolean wasZero, List<String> addTo) {
        if (x == n) {
            addTo.add(sb.toString());
            return;
        }
        if (addTo.size() > 300000) return;
        sb.append('0');
        gen(x + 1, n, true, addTo);
        sb.setLength(sb.length() - 1);
        if (x + 1 == n && !wasZero) {
            return;
        }
        for (int i = 1; i < 10; i++) {
            sb.append((char) (i + '0'));
            gen(x + 1, n, wasZero, addTo);
            sb.setLength(sb.length() - 1);
        }
    }
}
