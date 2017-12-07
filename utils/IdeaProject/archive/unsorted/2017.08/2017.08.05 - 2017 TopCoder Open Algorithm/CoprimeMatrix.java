package coding;

import ru.ifmo.niyaz.math.MathUtils;

import java.util.Arrays;

public class CoprimeMatrix {

    static final String BAD = "Impossible";
    static final String GOOD = "Possible";

    public String isPossible(String[] coprime) {
        int n = coprime.length;
        char[][] c = new char[n][];
        for (int i = 0; i < n; i++) {
            c[i] = coprime[i].toCharArray();
        }
        if (c[0][0] == 'Y') {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if ((c[i - 1][j - 1] == 'Y') != (MathUtils.gcd(i, j) == 1)) {
                        return BAD;
                    }
                }
            }
            return GOOD;
        }
        for (int i = 0; i < n; i++) {
            if (c[i][i] == 'Y') {
                return BAD;
            }
            if (i > 0 && c[i - 1][i] == 'N') return BAD;
            if (i + 1 < n && c[i + 1][i] == 'N') return BAD;
            for (int j = 0; j < n; j++) {
                if (c[i][j] != c[j][i]) return BAD;
            }
        }
        boolean[][] proven = new boolean[n][n];
        for (int i = 0; i < n; i++) proven[i][i] = true;
        for (int prime = 2; prime < n; prime++) {
            boolean isPrime = true;
            for (int i = 2; i < prime; i++) {
                if (prime % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (!isPrime) continue;
            int mod = 0;
            while (mod + prime < n && c[mod][mod + prime] == 'Y') {
                ++mod;
            }
            if (mod >= prime) {
                return BAD;
            }
            for (int i = mod; i < n; i += prime) {
//                char z = i % prime == mod ? 'N' : 'Y';
                for (int j = mod; j < n; j += prime) {
//                    if (c[i][j] != z) {
//                        System.out.println(mod + " " + z + " " + i + " " + j);
//                        return BAD;
//                    }
                    if (c[i][j] != 'N') {
                        return BAD;
                    }
                    proven[i][j] = true;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (c[i][j] == 'N' && !proven[i][j]) return BAD;
            }
        }
        return GOOD;
    }
}
