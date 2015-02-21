#include <cstdio>
#include <algorithm>

double const EPS = 5e-9;

int const N = 123456;

int g[N], a[N], b[N], id[N];
double s[N];

bool bydif(int i, int j) {
  return (long long) a[i] * b[j] > (long long) a[j] * b[i];
}

int main() {
  freopen("burrito.in", "r", stdin);
  freopen("burrito.out", "w", stdout);
  int n, A, B;
  scanf("%d%d%d", &n, &A, &B);
  for (int i = 0; i < n; i++) scanf("%d%d%d", g + i, a + i, b + i);
  for (int i = 0; i < n; i++) id[i] = i;
  std::sort(id, id + n, bydif);
  double curA = 0;
  int curB = 0;
  for (int it = 0; it < n; it++) {
    int i = id[it];
    int left = B - curB;
    if ((long long) b[i] * g[i] <= left) {
      s[i] = g[i];
      curA += a[i] * g[i];
      curB += b[i] * g[i];
    } else {
      double need = 1. * left / b[i];
      s[i] = need;
      curA += need * a[i];
      curB = B;
      break;
    }
  }
  if (curA < A - EPS) {
    puts("-1 -1");
    return 0;
  }
  printf("%.17f %d\n", curA, curB);
  for (int i = 0; i < n; i++) {
    printf("%.17f ", s[i]);
  }
  puts("");
}
