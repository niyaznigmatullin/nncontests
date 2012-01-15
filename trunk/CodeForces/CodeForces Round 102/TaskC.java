package mypackage;

import com.sun.tools.corba.se.idl.constExpr.Not;
import niyazio.FastScanner;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskC {
    static String[] answers = {".",
"..",
"...",
"....",
".....",
"......",
".......",
"........",
".........",
"..",
"....",
"......",
"........",
"..........",
"............",
"..............",
"................",
"..................",
"...",
"......",
"AAA.A..A.",
".AAA..A...A.",
"AAA.B.ABBB.A..B",
".AAA.B..ABBB..A..B",
"AAABCCC.A.B.C..ABBBC.",
".AAABCCC..A.B.C...ABBBC.",
"AAABCCC.D.A.B.CDDD.ABBBC..D",
"....",
"........",
"...AAA.A..A.",
".AAA.BA..BA.BBB.",
".....AAA.B.ABBB.A..B",
"...AAABBBCA..B.CA..BCCC.",
".AAABBB.CA.DB..CA.DB.CCCDDD.",
".....AAA.BCCCDA..B.C.DA.BBBCDDD.",
"...AAABBBCCCDA.EB..C.DA.EB..CDDDEEE.",
".....",
"..........",
"AAA.A..ABBBB..B",
".....AAA.BA..BA.BBB.",
"AAA.B.ABBBCA.DBCCCD.C.DDD",
"...AAA.BBBA..CB.AD.CBDDDCCC..D",
"..AAA.BCCCABBB.CDA.EB.CDDDE...D.EEE",
"...AAA.B.CCCABBB.DCEA.FB.DCEEEF.DDDE.FFF",
".AAAB.CCC..ADBBBC..EADBF.CG.EDDDFGGGEEE.FFF.G",
"......",
"............",
"...AAA.A..ABBBB..B",
".AAA..A...ABCBBBCCCBC...",
".....AAA.B.ABBBCA.DBCCCD.C.DDD",
"...AAABBBCA..B.CA.DBCCCEDDDEEED....E",
"....AAABBB.CA..BCCCA.DB.EC.FDDDEFFFD.EEE.F",
".......AB.CCCAAABBBCDDDABE.CFDG..EFFFDG.EEE.FGGG",
".....ABBBC.DDDA.B.CCCDAAABECF.DGHEEE.FGGGHHHEFFF.GH...",
".......",
"..............",
"AAA.A..ABBBB.CB.C.CCC",
".AAA.BA..BA.BBBCDCCCDDDCD...",
"..AAA...A.BBBAC.BCCCDB.ECDDDE.D.EEE",
"...AAABBB.A..BC.AD.BCDDDECCCFDEEE.F.E..FFF",
"AAA.BBB.ACCCB..ADCEB.DDDCEEEF.DGE.HFFFGHHHF.GGG.H",
".AAA.BBB.CADDDB..CA.DEB.CCCFDEEEGFFFHE.IGGGFHIIIG..HHH.I",
"..AAA.BBBCCCADDDB..C.AEDFB.GCEEEDFFFGGGHEIF.JGHHH.IJJJ...HIII.J",
"........",
"................",
"...AAA.A..ABBBB.CB.C.CCC",
".....AAA.BA..BA.BBBCDCCCDDDCD...",
"..AAABBBA..B.A.CBDDDCCCD.CE.DF.EFFFEEE.F",
"...AAA.BBBA..CB.AD.CBDDDCCCE.DFEEEG.FFFEG.F..GGG",
"...ABBBCAAADB.CCCADB.CE.DDDF.EEEFFFGEHHHIFGGGH.I.G..HIII",
"...AAA.B.CCCABBB.DCEA.FB.DCEEEF.DDDEGFFFHHHIGGGJ.H.IGJJJ.HIII..J",
"...ABBB.CDAAAEBCCCDDDAEBF.CD.GEEEFFFGGGHHHF.IJ.GKHLIIIJJJKHLLLIJ.KKKL...",
".........",
"..................",
"AAA.A..ABBBBC.BCCCC.DDDD..D",
".AAA..A...ABCBBBCCCBCDDD.ED..ED.EEE.",
"..AAABBBA..B.ACDBCCCDDDECDEEE.FFFEG.FGGG.F..G",
"...AAA.BBBA..CB.AD.CBDDDCCCE.DFFFEEE.FGE.H.FGHHH.GGG.H",
"..AAA.BCCCABBB.C.AD.B.C.EDDDFEEED.GFFFEGGGFHIIIJG.H.I.J.HHHIJJJ",
".AAABCCC.DA.B.C..DABBBCEDDDFGEEEHFFFGGGEHHHFGIIIHJKKKLI..J.K.LI.JJJKLLL.",
"....AAA.BC.DDDABBBCCCDEA.FBC.GDEFFF.GGGEEEHF.I.GJJJHHHIIIKJLH.MIKKKJLMMM...KLLL.M",
};
    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        char[][][][] answer = new char[10][10][][];
        for (int n = 1, cur = 0; n <= 9; n++) {
            for (int m = 1; m <= 9; m++, cur++) {
                String s = answers[cur];
                answer[n][m] = new char[n][m];
                for (int i = 0; i < n; i++) {
                    answer[n][m][i] = s.substring(0, m).toCharArray();
                    s = s.substring(m);
                }
            }
        }
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] output = answer[n][m];
        int ans = 0;
        for (int i = 0; i < n;i++) {
            for (int j = 0; j < m; j++) {
                if (output[i][j] != '.') {
                    ans = Math.max(ans, output[i][j] - 'A' + 1);
                }
            }
        }
        out.println(ans);
        for (char[] e : output) {
            out.println(e);
        }
    }


    static char[][] stupid(int n, int m) {
        BackTracking.n = n;
        BackTracking.m = m;
        return BackTracking.solve();
    }

    static class BackTracking {
        static int n;
        static int m;
        static char[][] current;
        static char[][] best;
        static int ans;
        static final char NOTHING = '.';
        static final int[][] DX = {{0, 0, 0, 1, 2}, {0, 1, 1, 1, 2}, {0, 1, 2, 2, 2}, {0, 1, 1, 1, 2}};
        static final int[][] DY = {{0, 1, 2, 1, 1}, {2, 0, 1, 2, 2}, {1, 1, 0, 1, 2}, {0, 0, 1, 2, 0}};

        static char[][] solve() {
            current = new char[n][m];
            best = new char[n][m];
            for (char[] e : current) {
                Arrays.fill(e, NOTHING);
            }
            for (char[] e : best) {
                Arrays.fill(e, NOTHING);
            }
            ans = 0;
            go(0, 0);
            return best;
        }


        static void go(int v, int got) {
            if ((ans - got) * 5 >= (n * m - v)) {
                return;
            }
            if (v == n * m) {
                if (ans < got) {
                    ans = got;
                    best = current.clone();
                    for (int i = 0; i < best.length; i++) {
                        best[i] = best[i].clone();
                    }
                }
                return;
            }
            int x = v / m;
            int y = v % m;
            go(v + 1, got);
            for (int type = 0; type < DX.length; type++) {
                if (!canPut(x, y, DX[type], DY[type])) {
                    continue;
                }
                put(x, y, DX[type], DY[type], (char) ('A' + got));
                go(v + 1, got + 1);
                put(x, y, DX[type], DY[type], NOTHING);
            }
        }

        static boolean canPut(int x, int y, int[] dx, int[] dy) {
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m || current[nx][ny] != NOTHING) {
                    return false;
                }
            }
            return true;
        }

        static void put(int x, int y, int[] dx, int[] dy, char c) {
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                current[nx][ny] = c;
            }
        }
    }

}
