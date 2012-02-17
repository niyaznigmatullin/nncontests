import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.*;
import java.util.*;

public class TaskJ {

static final Random rand = new Random(123123L);
  private static void solve() throws IOException {
  	test();
    // long time = System.currentTimeMillis();
    int tc = nextInt();
    while (tc-- > 0) {
      int a = nextInt(), b = nextInt();
      out.println(Solver.solveTheProblem(a, b));
    }
    // System.err.println(System.currentTimeMillis() - time);
  }
  
  static void test() {
  	int q = 0;
  	while (true) {
  		if (++q == 100) {
  			System.err.println("TEST");
  			q = 0;
  		}
  		int b = rand.nextInt(1000000000) + 1;
  		int a = Math.max(1, b - rand.nextInt(20000));
  		long ans1 = getStupid(a, b);
  		long ans2 = Solver.solveTheProblem(a, b);
  		if (ans1 != ans2) {
  			System.err.println(a + " " + b);
  			System.err.println();
  			System.err.println(ans1);
  			System.err.println(ans2);
  			throw new AssertionError();
  		}
  	}
  }

   static long getStupid(int a, int b) {
   long res = 0;
   for (int i = a, j = b; i <= b; i++, j--) {
   res += (long) digitSum(i) * digitProduct(j);
   }
   return res;
   }
  static long getStupid(int a, int b, int go) {
    long res = 0;
    int product = digitProduct(b);
    int sum = digitSum(a);
    for (int i = 0; i < go; i++) {

      res += (long) product * sum;

      if (++a % 10 == 0) {
        sum = digitSum(a);
      } else {
        sum++;
      }
      int r = --b % 10;
      if (r == 9) {
        product = digitProduct(b);
      } else {
        product /= r + 1;
        product *= r;
      }
    }
    return res;
  }

  static int digitSum(int a) {
    int res = 0;
    while (a > 0) {
      int next = a / 10;
      res += a - 10 * next;
      a = next;
    }
    return res;
  }

  static int digitProduct(int a) {
    if (a == 0) {
      return 0;
    }
    int res = 1;
    while (a > 0) {
      int next = a / 10;
      res *= a - 10 * next;
      a = next;
    }
    return res;
  }

  static class Solver {
    final long[] map1 = new long[] { -1, -1 };
    final long[] map2 = new long[] { -1, -1 };

    static long solveTheProblem(int a, int b) {
      Solver s = new Solver(100000);
      return s.getSmart(a, b, b - a + 1);
    }

    long get(int a, int b, int go) {
      if (go == 0) {
        return 0;
      }
      if (STEP > 10) {
        Solver good = new Solver(STEP / 10);
        return good.getSmart(a, b, go);
      } else {
        return getStupid(a, b, go);
      }
    }

    private Solver(int STEP) {
      this.STEP = STEP;
    }

    final int STEP;

    long getSmart(int a, int b, int goTotal) {
      long answer = 0;
      int key = 0;
      int[] goOk = new int[] { -1, -1 };

      do {
        int next1 = STEP - a % STEP;
        int next2 = b % STEP + 1;
        int go = min(next1, next2);
        if (go > goTotal) {
          break;
        }
        answer += get(a, b, go);
        a += go;
        b -= go;
        goTotal -= go;
      } while (false);

      int aDivStep = a / STEP, bDivStep = b / STEP;
      int add = digitSum(aDivStep), mul = digitProduct(bDivStep);

      // int aWhat = -1;

      while (goTotal > 0) {

        int go = goOk[key];
        if (go < 0) {
          int next1 = STEP - a % STEP;
          int next2 = b % STEP + 1;
          go = min(next1, next2);
          // if (go == next1) {
          // aWhat = key;
          // } else {
          // aWhat = key ^ 1;
          // }
          goOk[key] = go;
        }

        if (go > goTotal) {
          break;
        }

        long stupid;

        if (a >= STEP && b >= STEP) {
          if (map2[key] < 0) {
            stupidAB(key, a % STEP, b % STEP, go);
            stupidBs(key, b % STEP, go);
          }
          stupid = map2[key];
          stupid += add * map1[key];
          // stupid = stupidAB(key, a % STEP, b % STEP, go);
          // stupid += add * stupidBs(key, b % STEP, go);
          stupid *= mul;
        } else {
          stupid = get(a, b, go);
        }
        // if (stupid != getStupid(a, b, go)) {
        // System.err.println("got "+stupid);
        // System.err.println("real "+getStupid(a, b, go));
        // System.err.println(a+" "+b+" "+go);
        // System.err.println(add+" "+mul);
        // throw new AssertionError();
        // }

        answer += stupid;
        a += go;
        b -= go;
        add = digitSum(a / STEP);
        mul = digitProduct(b / STEP);
        // if (key == aWhat) {
        // ++aDivStep;
        // if (aDivStep % 10 == 0) {
        // } else {
        // ++add;
        // }
        // } else {
        // --bDivStep;
        // int r = bDivStep % 10;
        // if (r == 9) {
        // } else {
        // mul /= r + 1;
        // mul *= r;
        // }
        // }
        goTotal -= go;

        key ^= 1;
      }
      answer += get(a, b, goTotal);
      return answer;
    }

    long stupidBs(int key, int bStart, int size) {
      long have = map1[key];
      if (have >= 0) {
        return have;
      }
      long ret = 0;
      int stop = max(bStart - size, STEP / 10 - 1);
      int product = digitProduct(bStart);
      for (int i = bStart; i > stop;) {
        ret += product;
        int r = --i % 10;
        if (r == 9) {
          product = digitProduct(i);
        } else {
          product /= r + 1;
          product *= r;
        }
      }
      map1[key] = ret;
      return ret;
    }

    long stupidAB(int key, int a, int b, int size) {
      long have = map2[key];
      if (have >= 0) {
        return have;
      }
      int stop = min(size, b - STEP / 10 + 1);

      long ret = get(a, b, stop);
      map2[key] = ret;
      return ret;
    }
  }

  public static void main(String[] args) {
    try {
      br = new BufferedReader(new FileReader("input.txt"));
      out = new PrintWriter("output.txt");
      solve();
      out.close();
    } catch (Throwable e) {
      e.printStackTrace();
      System.exit(239);
    }
  }

  static BufferedReader br;
  static StringTokenizer st;
  static PrintWriter out;

  static String nextToken() throws IOException {
    while (st == null || !st.hasMoreTokens()) {
      String line = br.readLine();
      if (line == null) {
        return null;
      }
      st = new StringTokenizer(line);
    }
    return st.nextToken();
  }

  static int nextInt() throws IOException {
    return Integer.parseInt(nextToken());
  }

  static long nextLong() throws IOException {
    return Long.parseLong(nextToken());
  }

  static double nextDouble() throws IOException {
    return Double.parseDouble(nextToken());
  }
}
