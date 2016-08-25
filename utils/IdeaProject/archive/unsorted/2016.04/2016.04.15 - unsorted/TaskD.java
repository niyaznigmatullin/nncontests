package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.*;

public class TaskD {
    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        List<Long> start = new ArrayList<>();
        start.add(1989L);
        start.add(1999L);
        start.add(2099L);
        start.add(3099L);
        for (int i = 0; i < 10; i++) {
            start.add(Long.parseLong("1" + start.get(start.size() - 1)));
        }
//        Set<String> q = new HashSet<>();
//        for (int i = 1989; i <= 10000000; i++) {
//            if (i % 2000 == 0) {
//                System.out.println(i + " " + q.size());
//            }
//            String f = i + "";
//            String ans = null;
//            for (int j = 1; j <= f.length(); j++) {
//                String suf = f.substring(f.length() - j);
//                if (q.add(suf)) {
//                    ans = suf;
//                    break;
//                }
//            }
//            if (ans == null) throw new AssertionError();
//            if (i != getAnswer(start, ans)) {
//                System.out.println(i + " " + ans);
//                System.out.println(getAnswer(start, ans));
//                throw new AssertionError();
//            }
//        }
        for (int i = 0; i < n; i++) {
            String s = in.next().substring(4);
            long answer = getAnswer(start, s);
            out.println(answer);
        }
//        BigInteger last = BigInteger.valueOf(1988);
//        final String[] input = new String[n];
//        for (int i = 0; i < n; i++) {
//            input[i] = in.next().substring(4);
//        }
//        Integer[] id = new Integer[n];
//        for (int i = 0; i < n; i++) {
//            id[i] = i;
//        }
//        Arrays.sort(id, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                if (input[o1].length() != input[o2].length()) {
//                    return Integer.compare(input[o1].length(), input[o2].length());
//                }
//                if (input[o1].length() == 1) {
//                    return Integer.compare((Integer.parseInt(input[o1]) + 1) % 10, (Integer.parseInt(input[o2]) + 1) % 10);
//                }
//                return input[o1].compareTo(input[o2]);
//            }
//        });
//        BigInteger[] answer = new BigInteger[n];
//        for (int i = 0; i < n; i++) {
//            String s = input[id[i]];
//            BigInteger cur = new BigInteger(s);
//            BigInteger p = BigInteger.TEN.pow(s.length());
//            BigInteger next = last.divide(p).multiply(p).add(cur);
//            while (next.compareTo(last) <= 0) {
//                next = next.add(p);
//            }
//            last = next;
//            answer[id[i]] = last;
//        }
//        for (BigInteger e : answer) {
//            out.println(e);
//        }
    }

    private long getAnswer(List<Long> start, String s) {
        long a = start.get(s.length() - 1);
        long b = start.get(s.length());
        long answer = -1;
        if (s.length() <= 4) {
            for (long e = a; e < b; e++) {
                if ((e + "").endsWith(s)) {
                    answer = e;
                }
            }
            if (answer == -1) throw new AssertionError();
        } else {
            if (Long.parseLong(s) < a) {
                answer = Long.parseLong("1" + s);
            } else {
                answer = Long.parseLong(s);
            }
        }
        return answer;
    }
}
