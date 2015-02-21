#include <random>
#include <cstdio>
#include <algorithm>

double f[123];

int main() {
  int l, d, n;
  scanf("%d%d%d", &l, &d, &n);
  std::uniform_real_distribution<double> unif(0, l);
  std::default_random_engine re;
  int const IT = 1000000;
  int good = 0;
  for (int it = 0; it < IT; it++) {
    for (int i = 0; i < n; i++) f[i] = unif(re);
    std::sort(f, f + n);
    bool ok = true;
    for (int i = 1; i < n; i++) {
      if (f[i] - f[i - 1] > d) ok = false;
    }
    if (ok) {
      good++;
    }
  }
  double ans = 1. * good / IT;
  for (int i = 0; i < n; i++) ans *= l;
  printf("%.17lf\n", ans);
}