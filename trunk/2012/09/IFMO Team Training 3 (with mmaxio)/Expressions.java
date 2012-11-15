package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.*;

public class Expressions {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        s = in.next();
        pos = s.length() - 1;
        Element root = go(s.charAt(s.length() - 1));
        char[] ret = new char[s.length()];
        Queue<Element> q = new ArrayDeque<Element>();
        q.add(root);
        int count = ret.length;
        while (!q.isEmpty()) {
            Element e = q.poll();
            ret[--count] = e.var;
            if (e instanceof Operation) {
                q.add(((Operation) e).right);
                q.add(((Operation) e).left);
            }
        }
        out.println(ret);
    }

    static Element go(char c) {
        pos--;
        if (Character.isUpperCase(c)) {
            Element left = go(s.charAt(pos));
            Element right = go(s.charAt(pos));
            return new Operation(left, right, c);
        } else {
            return new Variable(c);
        }
    }

    static abstract class Element {
        char var;
    }

    static class Operation extends Element {
        Element left;
        Element right;

        Operation(Element left, Element right, char op) {
            this.left = left;
            this.right = right;
            this.var = op;
        }
    }

    static class Variable extends Element {

        Variable(char var) {
            this.var = var;
        }
    }

    static String s;
    static int pos;
    static int[] pair1;
    static int[] pair2;
}
