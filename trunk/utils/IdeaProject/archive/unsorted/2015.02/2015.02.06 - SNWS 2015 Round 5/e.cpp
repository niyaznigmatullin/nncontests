#include <bits/stdc++.h>

using namespace std;

int const N = 1234567;
long long const INF = 1LL << 50;

int s[N], f[N], t[N], id[N];

void solve() {  
  int n;
  scanf("%d", &n);
  for (int i = 0; i <   n; i++) {
    scanf("%d%d%d", s + i, f + i, t + i);
    id[i] = i;
  }
  std::sort(id, id + n, [](int i, int j) {
    return s[i] + f[i] < s[j] + f[j];
  });
  long long last = -INF;
  for (int it = 0; it < n; it++) {
    int i = id[it];
    if (last < s[i]) last = s[i];
    last += t[i];
    if (last > f[i]) {
      puts("0");
      return;
    }
  }
  puts("1");
}


int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
