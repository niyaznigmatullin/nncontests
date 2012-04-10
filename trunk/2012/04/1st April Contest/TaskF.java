package mypackage;

import math.MathUtils;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.math.BigInteger;
import java.util.Arrays;

public class TaskF {

    static String a = "theinputconsistsofasingleintegermb";
    static String b = "jxuydfkjsediyijievqiydwbuydjuwuhcr";
    static String m = "qd ucyhf yi q fhycu dkcruh mxeiu huluhiu yi q tyvvuhudj fhycu dkcruh. oekh jqia yi je vydt jxu djx ucyhf.";

	public void solve(int testNumber, FastScanner in, FastPrinter out) {
//        char[] map = new char[26];
//        for (int i = 0; i < b.length(); i++) {
//            map[b.charAt(i) - 'a'] = a.charAt(i);
//        }
//        for (int i = 0; i < m.length(); i++) {
//            if (!Character.isLetter(m.charAt(i))) {
//                out.print(m.charAt(i));
//            } else if (map[m.charAt(i) - 'a'] == 0) {
//                out.print("?");
//            } else {
//                out.print(map[m.charAt(i) - 'a']);
//            }
//        }
        int n = in.nextInt();
        int[] primes = MathUtils.genPrimes(2000000);
        for (int i : primes) {
            int z = Integer.parseInt(new StringBuilder(i + "").reverse().toString());
            if (i == z) {
                continue;
            }
            if (Arrays.binarySearch(primes, z) >= 0) {
                if (--n == 0) {
                    out.println(i);
                    return;
                }
            }
        }
	}
}
