package mypackage;

import com.sun.tools.corba.se.idl.toJavaPortable.StringGen;
import niyazio.FastScanner;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Special {

    static final int[] MONTHS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static final int[] MONTHS_LEAP = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    static boolean isLeap(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    public void solve(int testNumber, FastScanner in, PrintWriter out) {
        int d = in.nextInt();
        int m = in.nextInt();
        int year = in.nextInt();
        while (!isGood(d, m, year)) {
            d++;
            int[] months = isLeap(year) ? MONTHS_LEAP : MONTHS;
            if (months[m - 1] < d) {
                d = 1;
                m++;
            }
            if (m > 12) {
                m = 1;
                year++;
            }
        }
        out.println(d + " " + m + " " + year);
    }

    static boolean isGood(int day, int month, int year) {
        return check1(day, month, year) || check2(day, month, year);
    }

    static List<String> getAll(int day, int month, int year) {
        List<String> ret = new ArrayList<String>();
        for (int i = 0; i < 2; i++) {
            if (i > 0 && day > 9) {
                continue;
            }
            String s1 = (i == 0 ? "" : "0") + day;
            for (int j = 0; j < 2; j++) {
                if (j > 0 && month > 9) {
                    continue;
                }
                String s2 = (j == 0 ? "" : "0") + month;
                for (int k = 0; k < 2; k++) {
                    String s3 = "" + year;
                    if (k == 1) {
                        s3 = s3.substring(s3.length() - 2);
                    }
                    ret.add(s1 + s2 + s3);
                }
            }
        }
        return ret;
    }

    static boolean check1(int day, int month, int year) {
        List<String> all = getAll(day, month, year);
        for (String e : all) {
            if (isPalindrom(e)) {
                return true;
            }
        }
        return false;
    }

    static boolean check2(int day, int month, int year) {
        List<String> all = getAll(day, month, year);
        for (String e : all) {
            if (isPeriod(e)) {
                return true;
            }
        }
        return false;
    }

    static boolean isPalindrom(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }

    static boolean isPeriod(String s) {
        for (int k = 1; k < s.length(); k++) {
            if (s.length() % k != 0) {
                continue;
            }
            boolean ok = true;
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) != s.charAt(j % k)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }
}
