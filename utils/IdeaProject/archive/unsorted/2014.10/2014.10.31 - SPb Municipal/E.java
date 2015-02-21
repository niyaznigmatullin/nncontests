import java.util.Arrays;
import java.util.Scanner;

public class E {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		long[][] dp = new long[n + 1][k + 1];
		for (long[] e : dp) {
			Arrays.fill(e, Long.MIN_VALUE);
		}
		dp[0][0] = 0;
		for (int i = 1; i <= n; i++) {
			int minimum = Integer.MAX_VALUE;
			for (int j = i - 1; j >= 0; j--) {
				minimum = Math.min(minimum, a[j]);
				for (int t = 1; t <= k; t++) {
					long val = dp[j][t - 1];
					if (val == Long.MIN_VALUE)
						continue;
					val += minimum;
					if (dp[i][t] < val) {
						dp[i][t] = val;
					}
				}
			}
		}
		System.out.println(dp[n][k]);
		int[] ans = new int[k - 1];
		all: for (int i = n, t = k; t > 0;) {
			int minimum = Integer.MAX_VALUE;
			for (int j = i - 1; j >= 0; j--) {
				minimum = Math.min(minimum, a[j]);
				long val = dp[j][t - 1];
				if (val == Long.MIN_VALUE)
					continue;
				val += minimum;
				if (dp[i][t] == val) {
					if (j > 0) {
						ans[t - 2] = j - 1;
					}
					i = j;
					--t;
					continue all;
				}
			}
		}
		for (int i = 0; i + 1 < k; i++) {
			if (i > 0)
				System.out.print(' ');
			System.out.print(ans[i] + 1);
		}
		System.out.println();
	}
}
