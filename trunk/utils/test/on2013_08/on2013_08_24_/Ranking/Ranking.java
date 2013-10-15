package lib.test.on2013_08.on2013_08_24_.Ranking;



import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

public class Ranking {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = Integer.parseInt(in.nextLine().trim());
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            names[i] = in.nextLine();
        }
        int contestsCount = in.nextInt();
        double[] score = new double[n];
        int[] tookPartIn = new int[n];
        for (int curContest = 0; curContest < contestsCount; curContest++) {
            int teamsTakePartCount = in.nextInt();
            Integer[] teamsTakePart = new Integer[teamsTakePartCount];
            for (int i = 0; i < teamsTakePartCount; i++) {
                teamsTakePart[i] = in.nextInt() - 1;
            }
            int problemsCount = in.nextInt();
            int[][] submittedTime = new int[problemsCount][n];
            int[][] wrongRuns = new int[problemsCount][n];
            int runs = in.nextInt();
            for (int run = 0; run < runs; run++) {
                int team = in.nextInt() - 1;
                int problem = in.next().charAt(0) - 'A';
                int time = in.nextInt();
                String type = in.next();
                if (type.equals("-")) {
                    if (submittedTime[problem][team] == 0) {
                        wrongRuns[problem][team]++;
                    }
                } else {
                    if (submittedTime[problem][team] == 0) {
                        submittedTime[problem][team] = time;
                    }
                }
            }
            final int[] problemsSolved = new int[n];
            final int[] penalty = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < problemsCount; j++) {
                    if (submittedTime[j][i] > 0) {
                        problemsSolved[i]++;
                        penalty[i] += submittedTime[j][i] + wrongRuns[j][i] * 20;
                    }
                }
            }
            int maxProblems = 0;
            for (int i : problemsSolved) {
                maxProblems = Math.max(maxProblems, i);
            }
            double[] curScore = new double[n];
            for (int i = 0; i < n; i++) {
                if (maxProblems == 0) {
                    curScore[i] = 0;
                } else {
                    curScore[i] = 1. * problemsSolved[i] / maxProblems;
                }
            }
            double B = teamsTakePartCount - 2;
            double A = 2 * teamsTakePartCount - 2;
            for (int i = 0; i < teamsTakePartCount; i++) {
                Integer j = teamsTakePart[i];
                int place = 1;
                for (int k : teamsTakePart) {
                    if (problemsSolved[k] > problemsSolved[j] || problemsSolved[k] == problemsSolved[j] && penalty[k] < penalty[j]) {
                        ++place;
                    }
                }
                curScore[j] *= A / (place + B);
                tookPartIn[j]++;
                score[j] += curScore[j];
            }
        }
        final double[] totalScore = new double[n];
        for (int i = 0; i < n; i++) {
            if (tookPartIn[i] == 0) totalScore[i] = 0;
            else totalScore[i] = score[i] / tookPartIn[i];
        }
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        Arrays.sort(order, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Double.compare(totalScore[o1], totalScore[o2]);
            }
        });
        int longest = 0;
        for (String e : names) {
            longest = Math.max(longest, e.length());
        }
        longest++;
        for (int i = 0; i < n; i++) {
            while (names[i].length() < longest) names[i] += " ";
        }
        for (int i = 0; i < n; i++) {
            out.println(names[order[i]] + String.format(Locale.US, "%.4f", totalScore[order[i]]));
        }
    }
}
