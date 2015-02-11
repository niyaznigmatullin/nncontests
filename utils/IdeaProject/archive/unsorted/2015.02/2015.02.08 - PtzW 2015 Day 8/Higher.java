package coding;

import ru.ifmo.niyaz.arrayutils.ArrayUtils;
import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

import java.math.BigInteger;
import java.util.*;

public class Higher {

    static List<BigInteger[][]> left, right;
    static List<BigInteger[][]> leftRev, rightRev;

    static int n;

    static BigInteger[][] getMatrix() {
        BigInteger[][] ret = new BigInteger[n][n];
        for (BigInteger[] e : ret) Arrays.fill(e, BigInteger.ZERO);
        return ret;
    }

    static void swapRows(int i, int j) {
        if (i == j) return;
        BigInteger[][] ret = getMatrix();
        ret[i][j] = BigInteger.ONE;
        ret[j][i] = BigInteger.ONE;
        for (int k = 0; k < n; k++) {
            if (k != i && k != j) {
                ret[k][k] = BigInteger.ONE;
            }
        }
        left.add(ret);
        leftRev.add(ret);
    }

    static void swapCols(int i, int j) {
        if (i == j) return;
        BigInteger[][] ret = getMatrix();
        ret[i][j] = BigInteger.ONE;
        ret[j][i] = BigInteger.ONE;
        for (int k = 0; k < n; k++) {
            if (k != i && k != j) {
                ret[k][k] = BigInteger.ONE;
            }
        }
        right.add(ret);
        rightRev.add(ret);
    }

//    static void mulRow(int row, BigInteger value) {
//        if (value.signum() == 0) throw new AssertionError();
//        if (value.equals(BigInteger.ONE)) return;
//        BigInteger[][] ret = getMatrix();
//        for (int i = 0; i < n; i++) ret[i][i] = BigInteger.ONE;
//        ret[row][row] = value;
//        left.add(ret);
//    }
//
//    static void mulCol(int col, BigInteger value) {
//        if (value.signum() == 0) throw new AssertionError();
//        if (value.equals(BigInteger.ONE)) return;
//        BigInteger[][] ret = getMatrix();
//        for (int i = 0; i < n; i++) ret[i][i] = BigInteger.ONE;
//        ret[col][col] = value;
//        right.add(ret);
//    }

    static void subRow(int from, int what, BigInteger c) {
        if (c.signum() == 0) return;
        {
            BigInteger[][] ret = getMatrix();
            for (int i = 0; i < n; i++) ret[i][i] = BigInteger.ONE;
            ret[from][what] = ret[from][what].add(c.negate());
            left.add(ret);
        }
        {
            BigInteger[][] ret = getMatrix();
            for (int i = 0; i < n; i++) ret[i][i] = BigInteger.ONE;
            ret[from][what] = ret[from][what].add(c);
            leftRev.add(ret);
        }
    }

    static void subCol(int from, int what, BigInteger c) {
        if (c.signum() == 0) return;
        {
            BigInteger[][] ret = getMatrix();
            for (int i = 0; i < n; i++) ret[i][i] = BigInteger.ONE;
            ret[what][from] = ret[what][from].add(c.negate());
            right.add(ret);
        }
        {
            BigInteger[][] ret = getMatrix();
            for (int i = 0; i < n; i++) ret[i][i] = BigInteger.ONE;
            ret[what][from] = ret[what][from].add(c);
            rightRev.add(ret);
        }
    }

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        n = in.nextInt();
        if (n == 0) throw new UnknownError();
        left = new ArrayList<>();
        right = new ArrayList<>();
        leftRev = new ArrayList<>();
        rightRev = new ArrayList<>();
        BigInteger[][] a = new BigInteger[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = new BigInteger(in.next());
            }
        }
        BigInteger[][] A = a.clone();
        for (int i = 0; i < n; i++) A[i] = a[i].clone();
        Random rand = new Random(12323L);
        while (!isOk(a)) {
            for (int i = 1; i < n; i++) {
                int j = rand.nextInt(i);
                swapRows(i, j);
                BigInteger[] t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
            for (int i = 0; i < n; i++) {
                if (a[i][i].signum() == 0) {
                    all:
                    for (int j = i; j < n; j++) {
                        for (int k = i; k < n; k++) {
                            if (a[j][k].signum() != 0) {
                                BigInteger[] t = a[i];
                                a[i] = a[j];
                                a[j] = t;
                                swapRows(i, j);
                                swapCols(i, k);
                                for (int e = 0; e < n; e++) {
                                    BigInteger tmp = a[e][i];
                                    a[e][i] = a[e][k];
                                    a[e][k] = tmp;
                                }
                                break all;
                            }
                        }
                    }
                    if (a[i][i].signum() == 0) break;
                }
                for (int add = i + 1; add <= n; add++) {
                    for (int j = i + 1; j < n; j++) {
                        if (a[j][i].signum() == 0) continue;
                        while (a[j][i].signum() != 0 && a[i][i].signum() != 0) {
                            if (a[j][i].abs().compareTo(a[i][i].abs()) > 0) {
                                BigInteger q = a[j][i].divide(a[i][i]);
                                subRow(j, i, q);
                                for (int k = 0; k < n; k++) {
                                    a[j][k] = a[j][k].subtract(q.multiply(a[i][k]));
                                }
                            } else {
                                BigInteger q = a[i][i].divide(a[j][i]);
                                subRow(i, j, q);
                                for (int k = 0; k < n; k++) {
                                    a[i][k] = a[i][k].subtract(q.multiply(a[j][k]));
                                }
                            }
                        }
                        if (a[i][i].signum() == 0) {
                            swapRows(i, j);
                            BigInteger[] t = a[i];
                            a[i] = a[j];
                            a[j] = t;
                        }
                    }
                    if (add == n) break;
                    {
                        int j = add;
                        if (a[i][j].signum() != 0) {
                            while (a[i][i].signum() != 0 && a[i][j].signum() != 0) {
                                if (a[i][j].abs().compareTo(a[i][i].abs()) > 0) {
                                    BigInteger q = a[i][j].divide(a[i][i]);
                                    subCol(j, i, q);
                                    for (int k = 0; k < n; k++) {
                                        a[k][j] = a[k][j].subtract(q.multiply(a[k][i]));
                                    }
                                } else {
                                    BigInteger q = a[i][i].divide(a[i][j]);
                                    subCol(i, j, q);
                                    for (int k = 0; k < n; k++) {
                                        a[k][i] = a[k][i].subtract(q.multiply(a[k][j]));
                                    }
                                }
                            }
                            if (a[i][i].signum() == 0) {
                                swapCols(i, j);
                                for (int k = 0; k < n; k++) {
                                    BigInteger tmp = a[k][i];
                                    a[k][i] = a[k][j];
                                    a[k][j] = tmp;
                                }
                            }
                        }
                    }
                    subCol(i, add, BigInteger.ONE.negate());
                    for (int k = 0; k < n; k++) {
                        a[k][i] = a[k][i].add(a[k][add]);
                    }
                }
                for (int j = i + 1; j < n; j++) {
                    if (a[j][i].signum() == 0) continue;
                    BigInteger q = a[j][i].divide(a[i][i]);
                    subRow(j, i, q);
                    for (int k = 0; k < n; k++) {
                        a[j][k] = a[j][k].subtract(q.multiply(a[i][k]));
                    }
                }
                for (int j = i + 1; j < n; j++) {
                    if (a[i][j].signum() == 0) continue;
                    BigInteger q = a[i][j].divide(a[i][i]);
                    subCol(j, i, q);
                    for (int k = 0; k < n; k++) {
                        a[k][j] = a[k][j].subtract(q.multiply(a[k][i]));
                    }
                }
//            out.println("here");
//            printMatrix(out, a);
            }
        }
        Collections.reverse(left);
        Collections.reverse(rightRev);
        BigInteger[][] ansLeft = getMatrix();
        for (int i = 0; i < n; i++) ansLeft[i][i] = BigInteger.ONE;
        for (BigInteger[][] e : left) {
            ansLeft = mul(ansLeft, e);
        }
        BigInteger[][] ansLeftRev = getMatrix();
        for (int i = 0; i < n; i++) ansLeftRev[i][i] = BigInteger.ONE;
        for (BigInteger[][] e : leftRev) {
            ansLeftRev = mul(ansLeftRev, e);
        }
        BigInteger[][] ansRight = getMatrix();
        for (int i = 0; i < n; i++) ansRight[i][i] = BigInteger.ONE;
        for (BigInteger[][] e : right) {
            ansRight = mul(ansRight, e);
        }
        BigInteger[][] ansRightRev = getMatrix();
        for (int i = 0; i < n; i++) ansRightRev[i][i] = BigInteger.ONE;
        for (BigInteger[][] e : rightRev) {
            ansRightRev = mul(ansRightRev, e);
        }
        printMatrix(out, ansLeft);
        printMatrix(out, ansLeftRev);
        printMatrix(out, ansRight);
        printMatrix(out, ansRightRev);
        checkOne(mul(ansLeft, ansLeftRev));
        checkOne(mul(ansRight, ansRightRev));
        checkOk(mul(ansLeft, mul(A, ansRight)), A);
//        BigInteger[][] B = mul(ansLeft, mul(A, ansRight));
//        printMatrix(out, B);
//        printMatrix(out, mul(ansLeftRev, mul(B, ansRightRev)));
    }

    static void checkOne(BigInteger[][] ret) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j && !ret[i][j].equals(BigInteger.ONE)) {
                    throw new AssertionError();
                }
                if (i != j && ret[i][j].signum() != 0) {
                    throw new AssertionError();
                }
            }
        }
    }

    static void checkOk(BigInteger[][] a, BigInteger[][] d) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && a[i][j].signum() != 0) {
                    for (BigInteger[] f : d) {
                        System.err.println(Arrays.toString(f));
                    }
                    throw new AssertionError();
                }
            }
        }
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (a[i][i].signum() == 0) {
                firstZero = i;
                break;
            }
        }
        for (int i = 1; i < firstZero; i++) {
            if (a[i][i].mod(a[i - 1][i - 1].abs()).signum() != 0) {
                for (BigInteger[] f : d) {
                    System.err.println(Arrays.toString(f));
                }
                throw new AssertionError();
            }
        }
        for (int i = firstZero; i < n; i++) {
            if (a[i][i].signum() != 0) throw new AssertionError();
        }
    }

    static boolean isOk(BigInteger[][] a) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && a[i][j].signum() != 0) {
                    return false;
                }
            }
        }
        int firstZero = n;
        for (int i = 0; i < n; i++) {
            if (a[i][i].signum() == 0) {
                firstZero = i;
                break;
            }
        }
        for (int i = 1; i < firstZero; i++) {
            if (a[i][i].mod(a[i - 1][i - 1].abs()).signum() != 0) {
                return false;
            }
        }
        for (int i = firstZero; i < n; i++) {
            if (a[i][i].signum() != 0)
                return false;
        }
        return true;
    }

    private void printMatrix(FastPrinter out, BigInteger[][] ansLeft) {
        for (BigInteger[] e : ansLeft) {
            for (BigInteger f : e) {
                out.print(f + " ");
            }
            out.println();
        }
        out.println();
    }

    static BigInteger[][] mul(BigInteger[][] a, BigInteger[][] b) {
        BigInteger[][] ret = getMatrix();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    ret[i][j] = ret[i][j].add(a[i][k].multiply(b[k][j]));
                }
            }
        }
        return ret;
    }

    static BigInteger lcm(BigInteger a, BigInteger b) {
        return a.divide(a.gcd(b)).multiply(b);
    }
}
