package mypackage;

import com.sun.tools.corba.se.idl.toJavaPortable.StringGen;
import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.io.File;
import java.util.Stack;

public class TaskC {
	public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        Stack<String> stack = new Stack<String>();
        String current = "/";
        for (int i = 0; i < n; i++) {
            String command = in.next();
            if (command.equals("cd")) {
                String arg = in.next();
                if (arg.startsWith("/")) {
                    stack.clear();
                    arg = arg.substring(1);
                }
                String[] t = arg.split("/");
                for (String e : t) {
                    if (e.equals("..")) {
                        stack.pop();
                    } else {
                        stack.add(e);
                    }
                }
                StringBuilder sb = new StringBuilder("/");
                for (String e : stack) {
                    sb.append(e).append("/");
                }
                current = sb.toString();
            } else {
                out.println(current);
            }
        }
	}
}
