package mypackage;

import niyazio.FastScanner;
import niyazio.FastPrinter;

import java.util.Arrays;
import java.util.Stack;

public class TaskA {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        char[] c = in.next().toCharArray();
        int bestL = -1;
        int bestR = -1;
        int bestCount = 0;
        Stack<Integer> stack = new Stack<Integer>();
        int[] pair = new int[c.length];
        Arrays.fill(pair, -1);
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(' || c[i] == '[') {
                stack.add(i);
            } else {
                char ch = c[i] == ')' ? '(' : '[';
                if (!stack.isEmpty() && c[stack.peek()] == ch) {
                    pair[i] = stack.pop();
                    pair[pair[i]] = i;
                } else {
                    stack.clear();
                }
            }
        }
        for (int i = 0; i < c.length; ) {
            if (pair[i] >= i) {
                int first = i;
                int curCount = 0;
                while (i < c.length && pair[i] >= i) {
                    for (int j = i; j <= pair[i]; j++) {
                        if (c[j] == '[') {
                            ++curCount;
                        }
                    }
                    i = pair[i] + 1;
                }
                if (curCount > bestCount) {
                    bestCount = curCount;
                    bestL = first;
                    bestR = i;
                }
            } else {
                i++;
            }
        }
        if (bestCount == 0) {
            out.println(0);
        } else {
            out.println(bestCount);
            out.println(new String(c, bestL, bestR - bestL));
        }
    }

    static String stupid(String s) {
        int best = 0;
        String answer = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String t = s.substring(i, j);
                if (isCorrect(t) && count(t) > best) {
                    best = count(t);
                    answer = t;
                }
            }
        }
        return answer;
    }

    static int count(String s) {
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                ++ret;
            }
        }
        return ret;
    }

    static boolean isCorrect(String t) {
        Stack<Integer> stack = new Stack<Integer>();
        char[] c = t.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(' || c[i] == '[') {
                stack.add(i);
            } else {
                char ch = c[i] == ')' ? '(' : '[';
                if (!stack.isEmpty() && c[stack.peek()] == ch) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
