import java.io.*;

class Test {
    public static void main(String[] args) {
        PrintWriter out = new PrintWriter(System.out);
        out.println(100000);
        for (int i = 0; i < 100000; i++) {
            if (i != 0) {
                out.print(" ");
            }
            out.print(0);
        }
        out.println();
        out.close();
    }
}