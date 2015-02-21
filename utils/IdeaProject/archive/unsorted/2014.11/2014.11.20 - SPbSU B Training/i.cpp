#include <cstdio>

int const N = 1234567;

int from[N], to[N], ans[N], op[N], pv[N];

int get(int x) {
  return x == pv[x] ? x : (pv[x] = get(pv[x]));
}

int main() {
  freopen("cutting.in", "r", stdin);
  freopen("cutting.out", "w", stdout);
  int n, m, k;
  scanf("%d%d%d", &n, &m, &k);
  for (int i = 0; i < m; i++) {
    int x, y;
    scanf("%d%d", &x, &y);
  }
  for (int i = 0; i < n; i++) pv[i] = i;
  for (int i = 0; i < k; i++) {
    char s[5];
    scanf("%s", s);
    if (s[0] == 'a') op[i] = 0; else op[i] = 1;
    scanf("%d%d", from + i, to + i);
    --from[i];
    --to[i];
  }
  for (int i = k - 1; i >= 0; i--) {
    if (op[i] == 0) {
      ans[i] = get(from[i]) == get(to[i]);
    } else {
      pv[get(from[i])] = get(to[i]);
    }
  }
  for (int i = 0; i < k; i++) if (op[i] == 0) {
    puts(ans[i] ? "YES" : "NO");
  }
}