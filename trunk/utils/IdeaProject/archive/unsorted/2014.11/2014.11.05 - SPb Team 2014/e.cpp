#include <cstdio>

int main() {
  freopen("game.in", "r", stdin);
  freopen("game.out", "w", stdout);
  int n, m;
  scanf("%d%d", &n, &m);
  int l1, r1, l2, r2;
  scanf("%d%d%d%d", &l1, &r1, &l2, &r2);
  int c1 = 0;
  int c2 = 0;
  int c3 = 0;
  for (int i = 0; i < n; i++) {
    bool can1 = false;
    bool can2 = false;
    int x;
    scanf("%d", &x);
    if (x >= l1 && x <= r1) can1 = true;
    if (x >= l2 && x <= r2) can2 = true;
    if (can1 && can2) c3++; else if (can1) ++c1; else if (can2) ++c2;
  }
  int first = 0;
  int second = 0;
  for (int i = 0; i < m; i++) {
    if ((i & 1) == 0) {
      if (c3 > 0) --c3; else if (c1 > 0) --c1; else {
        continue;
      }
      first++;
    } else {
      if (c3 > 0) --c3; else if (c2 > 0) --c2; else {
        continue;
      }
      second++;
    }
  }
  if (first > second) {
    puts("Petya"); 
  } else if (first < second) {
    puts("Vasya");
  } else {
    puts("Draw");
  }
}
