import static java.lang.Math.max;
import static java.util.Arrays.fill;

public class P8XGraphBuilder {
	public int solve(int[] scores) {
		int n = scores.length + 1;
		int[][] dp = new int[n + 1][2 * n];
		for (int[] d : dp) {
			fill(d, Integer.MIN_VALUE);
		}
		dp[0][0] = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				for (int k = 1; k <= j && k < n; k++) {
					if (dp[i - 1][j - k] == Integer.MIN_VALUE) {
						continue;
					}
					dp[i][j] = max(dp[i][j], dp[i - 1][j - k] + scores[k - 1]);
				}
			}
		}
		return dp[n][2 * n - 2];
	}

	// BEGIN KAWIGIEDIT TESTING
	// Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
	private static boolean KawigiEdit_RunTest(int testNum, int[] p0,
			boolean hasAnswer, int p1) {
		System.out.print("Test " + testNum + ": [" + "{");
		for (int i = 0; p0.length > i; ++i) {
			if (i > 0) {
				System.out.print(",");
			}
			System.out.print(p0[i]);
		}
		System.out.print("}");
		System.out.println("]");
		P8XGraphBuilder obj;
		int answer;
		obj = new P8XGraphBuilder();
		long startTime = System.currentTimeMillis();
		answer = obj.solve(p0);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0
				+ " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + p1);
		}
		System.out.println("Your answer:");
		System.out.println("\t" + answer);
		if (hasAnswer) {
			res = answer == p1;
		}
		if (!res) {
			System.out.println("DOESN'T MATCH!!!!");
		} else if ((endTime - startTime) / 1000.0 >= 2) {
			System.out.println("FAIL the timeout");
			res = false;
		} else if (hasAnswer) {
			System.out.println("Match :-)");
		} else {
			System.out.println("OK, but is it right?");
		}
		System.out.println("");
		return res;
	}

	public static void main(String[] args) {
		boolean all_right;
		all_right = true;

		int[] p0;
		int p1;

		// ----- test 0 -----
		p0 = new int[] { 1, 3, 0 };
		p1 = 8;
		all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
		// ------------------

		// ----- test 1 -----
		p0 = new int[] { 0, 0, 0, 10 };
		p1 = 10;
		all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
		// ------------------

		// ----- test 2 -----
		p0 = new int[] { 1, 2, 3, 4, 5, 6 };
		p1 = 12;
		all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
		// ------------------

		// ----- test 3 -----
		p0 = new int[] { 5, 0, 0 };
		p1 = 15;
		all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
		// ------------------

		// ----- test 4 -----
		p0 = new int[] { 1, 3, 2, 5, 3, 7, 5 };
		p1 = 20;
		all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
		// ------------------

		if (all_right) {
			System.out
					.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
	// END KAWIGIEDIT TESTING
}
// Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
