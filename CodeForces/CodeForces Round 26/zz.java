import java.io.*;
import java.util.*;

class zz {
    public static void main(String[] args) throws Throwable {
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(new File("in.txt"));
        String s = sc.nextLine();
        System.err.println(s.length());
        sc.close();
        System.err.println(System.currentTimeMillis() - time);
    }
}