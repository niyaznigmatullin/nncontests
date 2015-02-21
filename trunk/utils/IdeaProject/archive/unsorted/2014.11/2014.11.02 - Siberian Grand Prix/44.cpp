#include <cstdio>

int s[7][1 << 21];
int ans[1 << 21];

void fft(int * a, int l, int r) {
  if (l + 1 == r) return;
  int half = (r - l) >> 1;
  int mid = l + half;
  fft(a, l, mid);
  fft(a, mid, r);
  for (int i = l; i < mid; i++) {
    int x1 = a[i];
    int x2 = a[i + half];
    a[i] = x1 - x2;
    if (a[i] < 0) a[i] += MOD;
    a[i + half] = x1 + x2;
    if (a[i + half] >= MOD) a[i + half] -= MOD;
  }
}

int main() {
  int n, m, k;
  scanf("%d%d%d", &n, &m, &k);
  for (int i = 0; i < m; i++) {
    int c = getchar();
    while (c <= 32) c = getchar();
    int f = 0;
    while (c > 32) {
      s[i][f++] = c == '1';
      c = getchar();
    }
    fft(s[i], 0, 1 << n);
  }
  for (int q = 0; q < k; q++) {
    int v, u;
    scanf("%d%d", &v, &u);
    int * f1 = s[v];
    int * f2 = s[u];
    for (int i = 0; i < 1 << n; i++) ans[i] = false;
    for (int mask = 0; mask < 1 << n; mask++) {
      if (!f1[mask]) continue;
      int bad = mask;
      for (int i = 0; i < n; i++) {
        if (((mask >> i) & 1) == 1) continue;
        if (f1[mask | (1 << i)]) bad |= 1 << i;
      }
      if (bad != mask) continue;
      int good = ((1 << n) - 1) ^ bad;      
      for (int sub = good; ; sub = (sub - 1) & good) {
        if (f2[sub])
          ans[mask | sub] = true;
        if (sub == 0) break;
      }
    }
    for (int mask = (1 << n) - 1; mask >= 0; mask--) {    
      for (int i = 0; !ans[mask] && i < n; i++) {
        if (((mask >> i) & 1) == 1) continue;
        ans[mask] |= ans[mask | (1 << i)];
      }
    }
    for (int i = 0; i < 1 << n; i++) putchar('0' + (int) ans[i]);
    puts("");
  }
}
