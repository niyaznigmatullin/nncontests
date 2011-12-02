import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class Test {
	static Random rand = new Random();

	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter("c.in");
		int q = 0;
		while (true) {
			if (++q % 100 == 0) {
				System.err.println(q);
			}
			for (int i = 0; i < 4; i++) {
				out.println(rand.nextInt(1000000000) + 1);
			}
			out.close();
			C.main(null);
			BufferedReader br = new BufferedReader(new FileReader("c.out"));
			String s = br.readLine();
			if (s.equals("-1")) {
				throw new AssertionError();
			}
		}
	}
}
