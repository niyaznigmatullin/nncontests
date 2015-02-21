import java.util.Scanner;

public class D {

	static int n;
	static int[] a;

	static void go(int got, int last, int x) {
		if (got == n) {
			System.out.print(n + "=");
			System.out.print(a[0]);
			for (int i = 1; i < x; i++) {
				System.out.print("+");
				System.out.print(a[i]);
			}
			System.out.println();
			return;
		}
		for (int next = last + 2; next + got <= n; next++) {
			a[x] = next;
			go(got + next, next, x + 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		a = new int[n];
		go(0, -1, 0);
	}
}
