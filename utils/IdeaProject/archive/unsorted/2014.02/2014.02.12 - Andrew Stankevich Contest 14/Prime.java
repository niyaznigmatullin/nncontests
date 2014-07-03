package coding;

import ru.ifmo.niyaz.io.FastScanner;
import ru.ifmo.niyaz.io.FastPrinter;

public class Prime {

    static final int[] ANSWER = {1,
            1,
            2,
            3,
            4,
            6,
            7,
            10,
            12,
            15,
            18,
            23,
            27,
            33,
            38,
            43,
            51,
            60,
            70,
            81,
            92,
            102,
            116,
            134,
            153,
            171,
            191,
            211,
            236,
            266,
            301,
            335,
            367,
            399,
            442,
            485,
            542,
            598,
            649,
            704,
            771,
            849,
            936,
            1023,
            1103,
            1185,
            1282,
            1407,
            1535,
            1662,
            1790,
            1917,
            2063,
            2245,
            2436,
            2621,
            2805,
            2998,
            3211,
            3474,
            3764,
            4043,
            4301,
            4572,
            4884,
            5238,
            5642,
            6044,
            6410,
            6800,
            7258,
            7778,
            8323,
            8888,
            9407,
            9937,
            10554,
            11259,
            12028,
            12801,
            13536,
            14287,
            15116,
            16103,
            17165,
            18171,
            19165,
            20195,
            21328,
            22656,
            24104,
            25475,
            26807,
            28189,
            29716,
            31445,
            33318,
            35196,
            36990,
            38813,
            40890,
            43222,
            45688,
            48173,
            50531,
            52893,
            55581,
            58673,
            61895,
            65130,
            68288,
            71459,
            74970,
            78969,
            83182,
            87316,
            91355,
            95509,
            100055,
            105148,
            110692,
            116108,
            121282,
            126659,
            132518,
            139003,
            146061,
            153018,
            159682,
            166548,
            174182,
            182578,
            191456,
            200277,
            208838,
            217496,
            227063,
            237797,
            249005,
            260233,
            271277,
            282354,
            294408,
            307898,
            322063,
            336054,
            349782,
            363791,
            378983,
            395903,
            413860,};

    public void solve(int testNumber, FastScanner in, FastPrinter out) {
        int n = in.nextInt();
        if (true) {
            out.println(ANSWER[n]);
            return;
        }
        final int N = 150;
        boolean[] isPrime = new boolean[N / 4 + 1];
        int count = 0;
        for (int i = 2; i < isPrime.length; i++) {
            isPrime[i] = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime[i] = false;
                    break;
                }
            }
            if (isPrime[i]) ++count;
        }
        int[] primes = new int[count];
        int[] ids = new int[isPrime.length];
        for (int i = 2, j = 0; i < isPrime.length; i++) {
            if (isPrime[i]) {
                ids[i] = j;
                primes[j] = i;
                j++;
            }
        }
        long[][] dp = new long[1 << count][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            int largest = 0;
            int m = i;
            for (int j = 2; j <= m; j++) {
                while (m % j == 0) {
                    m /= j;
                    largest = j;
                }
            }
            if (largest < isPrime.length || i != largest) {
                continue;
            }
            long[][] next = new long[1 << count][N + 1];
            for (int mask = 0; mask < 1 << count; mask++) {
                for (int got = 0; got <= N; got++) {
                    next[mask][got] += dp[mask][got];
                }
            }
            for (int t = 1; t <= 3; t++) {
                int cMask = t == 1 ? 0 : t == 2 ? 1 : 2;
                for (int mask = 0; mask < 1 << count; mask++) {
                    if ((mask & cMask) != 0) continue;
                    for (int got = 0; got + t * i <= N; got++) {
                        next[mask | cMask][got + t * i] += dp[mask][got];
                    }
                }
            }
            dp = next;
        }
//        long ans = 0;
//        for (long[] d : dp) {
//            for (long e : d) ans += e;
//        }
//        out.println(ans);
        for (int i = 2; i <= N; i++) {
            int largest = 0;
            int m = i;
            int cMask = 0;
            for (int j = 2; j <= m; j++) {
                while (m % j == 0) {
                    m /= j;
                    largest = j;
                    cMask |= j < ids.length ? (1 << ids[j]) : 0;
                }
            }
            if (largest >= isPrime.length) {
                continue;
            }
            long[][] next = new long[1 << count][N + 1];
            for (int mask = 0; mask < 1 << count; mask++) {
                for (int got = 0; got <= N; got++) {
                    next[mask][got] += dp[mask][got];
                }
            }
            for (int mask = 0; mask < 1 << count; mask++) {
                if ((mask & cMask) != 0) continue;
                for (int got = 0; got + i <= N; got++) {
                    next[mask | cMask][got + i] += dp[mask][got];
                }
            }
            dp = next;
        }
        long ans = 0;
        for (int i = 0; i <= N; i++) {
            for (int mask = 0; mask < 1 << count; mask++) {
                ans += dp[mask][i];
            }
            out.println(ans + ",");
        }
    }
}
