import java.util.Arrays;
import java.util.Scanner;


public class B {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int h = sc.nextInt();
		int w = sc.nextInt();
		int n = sc.nextInt();
		char[][] ans = new char[h][w];
		for (char[] d : ans) {
			Arrays.fill(d, '.');
		}
		for (int i = 0; i < n; i++) {
			int x1 = sc.nextInt() - 1;
			int y1 = sc.nextInt() - 1;
			int x2 = sc.nextInt() - 1;
			int y2 = sc.nextInt() - 1;
			for (int j = x1; j <= x2; j++) {
				ans[j][y1] = ans[j][y2] = (char) (i + 'a');
			}
			for (int j = y1; j <= y2; j++) {
				ans[x1][j] = ans[x2][j] = (char) (i + 'a');
			}
		}
		for (char[] d : ans) {
			System.out.println(d);
		}
	}
}
