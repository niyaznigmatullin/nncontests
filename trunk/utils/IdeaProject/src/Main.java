import java.io.InputStreamReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.io.InputStream;

/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * @author Niyaz Nigmatullin
 */
public class Main {
	public static void main(String[] args) {
		InputStream inputStream = System.in;
		OutputStream outputStream = System.out;
		FastScanner in = new FastScanner(inputStream);
		FastPrinter out = new FastPrinter(outputStream);
		Test solver = new Test();
		solver.solve(1, in, out);
		out.close();
	}
}

class Test {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        ArrayList a = new ArrayList();
        a.c();
    }
}

class FastScanner extends BufferedReader {

    public FastScanner(InputStream is) {
        super(new InputStreamReader(is));
    }

    public int read() {
        try {
            int ret = super.read();
//            if (isEOF && ret < 0) {
//                throw new InputMismatchException();
//            }
//            isEOF = ret == -1;
            return ret;
        } catch (IOException e) {
            throw new InputMismatchException();
        }
    }

    public String readLine() {
        try {
            return super.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    }

class FastPrinter extends PrintWriter {

    public FastPrinter(OutputStream out) {
        super(out);
    }

    public FastPrinter(Writer out) {
        super(out);
    }


}

class ArrayList extends BufferedReader {
    int z;

    public ArrayList() {
        super(new InputStreamReader(System.in));
    }

    public ArrayList(int a) {
        super(new InputStreamReader(System.in));
    }

    public ArrayList(double a) {
        super(new InputStreamReader(System.in));
    }

    public String readLine() throws IOException {
        return super.readLine();
    }

    public  void c() throws TooFatException {
        a();
        b();
    }

    public  void a() {
        z = z + 1;
    }

    public  void b() {

    }

}

class TooFatException extends RuntimeException {
}

