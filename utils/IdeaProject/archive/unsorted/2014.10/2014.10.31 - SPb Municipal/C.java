import java.util.Scanner;

public class C {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		int[] b = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			b[i] = sc.nextInt();
		}
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (a[i] > a[j] || (a[i] == a[j] && b[i] < b[j])) {
					int t = a[i];
					a[i] = a[j];
					a[j] = t;
					t = b[i];
					b[i] = b[j];
					b[j] = t;
				}
			}
		}
		int ans = Integer.MIN_VALUE;
		for (int i = 0; i + 1 < n; i++) {
			int dif = Math.abs(b[i] - b[i + 1]);
			if (ans < dif)
				ans = dif;
		}
		System.out.println(ans);
	}
}
