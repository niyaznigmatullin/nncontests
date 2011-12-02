public class GenE {
	public static void main(String[] args) {
		System.out.println(100000);
		for (int i = 0; i < 50000; i++) {
			System.out.print(1000000000 + " ");
		}
		for (int i = 0; i < 50000; i++) {
			System.out.print(1);
			if (i != 49999) {
				System.out.print(" ");
			}
		}
		System.out.println();
		for (int i = 0; i < 50000; i++) {
			System.out.print(1 + " ");
		}
		for (int i = 0; i < 50000; i++) {
			System.out.print(1000000000);
			if (i != 49999) {
				System.out.print(" ");
			}
		}
		System.out.println();
	}
}
