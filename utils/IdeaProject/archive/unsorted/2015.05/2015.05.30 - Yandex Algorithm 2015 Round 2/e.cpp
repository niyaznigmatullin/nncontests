#include <bits/stdc++.h>

using namespace std;

int const S = 1001;

int const L = 20;

long long dp[30][L][30][S];

int q[42], w[42], nq[42], d[42], ten[42];
int g[42];

long long get(int mod, long long n) {
    ++n;
    int cn = 0;
    while (n > 0) {
        d[cn++] = (int) (n % 10);
        n /= 10;
    }
    int need = 0;
    ten[0] = 1;
    long long ans = 0;
    for (int i = 1; i <= cn; i++) ten[i] = ten[i - 1] * 10 % mod;
    int cc = 0;
    for (int i = 0; i < 10; i++) g[i] = 0;
    for (int i = cn - 1; i >= 0; i--) {
        for (int cur = 0; cur < d[i]; cur++) {
            int newNeed = (need - cur * ten[i] % mod + mod) % mod;
            for (int q0 = 0; q0 < 10; q0++) {
                for (int q1 = q0; q1 < 10; q1++) {
                    for (int q2 = q1; q2 < 10; q2++) {
                        long long val = dp[mod][i][newNeed][q0 * 100 + q1 * 10 + q2];
                        if (val == 0) continue;
//                        int dd = 0;
//                        for (int e = 0; e < min(cc, 3); e++) w[dd++] = q[e];
//                        for (int e = cn - 1; e > i; e--) w[dd++] = d[e];
                        g[cur]++;
                        g[q0]++;
                        g[q1]++;
                        g[q2]++;
                        //w[dd++] = cur;
                        //w[dd++] = q0;
                        //w[dd++] = q1;
                        //w[dd++] = q2;
                        //sort(w, w + dd);
                        //reverse(w, w + dd);
                        int have = 0;
                        for (int i = 9; i >= 0 && have < 3; i--) {
                            for (int j = 0; j < g[i] && have < 3; j++) {
                                w[have++] = i;
                            }
                        }
                        g[cur]--;
                        g[q0]--;
                        g[q1]--;
                        g[q2]--;
                        if (w[0] + w[1] + w[2] != mod) {
                            continue;
                        }
                        ans += val;
                    }
                }
            }
        }
        g[d[i]]++;
        sort(q, q + cc);
        reverse(q, q + cc);
        need = (need - d[i] * ten[i] % mod + mod) % mod;
    }
    return ans;
}

long long solve(long long n) {
    long long ans = 0;
    for (int i = 1; i <= 9 * 3; i++) {
        ans += get(i, n);
    }
    return ans;
}

void solve() {
    long long a, b;
    scanf("%lld%lld", &a, &b);
    printf("%lld\n", solve(b) - solve(a - 1));
}

int main() {
    for (int mod = 1; mod <= 9 * 3; mod++) {
        dp[mod][0][0][0] = 1;
        for (int len = 0; len + 1 < L; len++) {
            for (int r = 0; r < mod; r++) {
                for (int s = 0; s < S; s++) {
                    long long val = dp[mod][len][r][s];
                    if (val == 0) continue;
                    q[2] = s / 100;
                    q[1] = s / 10 % 10;
                    q[0] = s % 10;
                    for (int d = 0; d < 10; d++) {
                        for (int j = 0; j < 3; j++) nq[j] = q[j];
                        nq[3] = d;
//                        for (int e = 2; e >= 0; e--) if (nq[e] < nq[e + 1]) swap(nq[e], nq[e + 1]);
                        sort(nq, nq + 4);
                        reverse(nq, nq + 4);
                        int ns = nq[0] + nq[1] * 10 + nq[2] * 100;
                        dp[mod][len + 1][(r * 10 + d) % mod][ns] += val;
                    }
                }
            }
        }
    }
//    for (int i = 147; i <= 200; i++) printf("%d %lld\n", i, solve(i));
    int t;
    scanf("%d", &t);
    while (t--) solve();
}
