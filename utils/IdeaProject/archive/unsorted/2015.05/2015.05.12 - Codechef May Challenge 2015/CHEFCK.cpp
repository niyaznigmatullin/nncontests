#include <bits/stdc++.h>

int const MOD = 1000000007;

int mul(int a, int b) {
    return (int) ((long long) a * b % MOD);
}

int const N = 22345678;
int a[N], suf[N], pref[N];

int main() {
    int n, k, q;
    scanf("%d%d%d", &n, &k, &q);
    int A, B, C, D, E, F, R, S, T, M;
    scanf("%d%d%d%d%d%d%d%d%d%d%d", &A, &B, &C, &D, &E, &F, &R, &S, &T, &M, a);
    for (int i = 1, z = T; i < n; i++) {
        z = (int) ((long long) z * T % S);
        if (z <= R) {
            a[i] = (int) (((long long) A * a[i - 1] % M * a[i - 1] + (long long) B * a[i - 1] + C) % M);
        } else {
            a[i] = (int) (((long long) D * a[i - 1] % M * a[i - 1] + (long long) E * a[i - 1] + F) % M);        
        }
    }
    while (n % k != 0) {
        a[n++] = 1 << 30;
    }
    for (int i = 0; i < n; i += k) {
        int mn = 1 << 30;
        for (int j = 0; j < k; j++) {
            if (mn > a[i + j]) mn = a[i + j];
            pref[i + j] = mn;
        }
        mn = 1 << 30;
        for (int j = k - 1; j >= 0; j--) {
            if (mn > a[i + j]) mn = a[i + j];
            suf[i + j] = mn;
        }
    }
    int L1, La, Lc, Lm, D1, Da, Dc, Dm;
    scanf("%d%d%d%d%d%d%d%d", &L1, &La, &Lc, &Lm, &D1, &Da, &Dc, &Dm);
    long long sum = 0;
    int prod = 1;
    for (int i = 0; i < q; i++) {
        L1 = (int) (((long long) La * L1 + Lc) % Lm);
        D1 = (int) (((long long) Da * D1 + Dc) % Dm);
        int L = L1;
        int R = std::min(L + k - 1 + D1, n - 1);
        int bL = L / k;
        int bR = R / k;
        int ans = std::min(suf[L], pref[R]);
        if (bL + 2 == bR) {
            ans = std::min(ans, suf[(bL + 1) * k]);
        }
        sum += ans;
        prod = mul(prod, ans);
    }
    printf("%lld %d\n", sum, prod);
}