#include <cstdio>
#include <cassert>

int const K = 444;

int const N = 222222;
int const INF = 1 << 30;
int const B = N / K + 1;
int a[N];
int b[B][2 * K];
int sz[B];
int mn[B];

void rebuild() {
  int cn = 0;
  for (int i = 0; i < B; i++) {
    if (sz[i] == 0) break;
    for (int j = 0; j < sz[i]; j++) a[cn++] = b[i][j];
    sz[i] = 0;
    mn[i] = INF;
  }
  for (int i = 0; i < cn; i++) {
    int q = i / K;
    b[q][sz[q]++] = a[i];
    if (mn[q] > a[i]) mn[q] = a[i];
  }
}

void add(int x, int y) {
  for (int i = 0; i < B; i++) {
    if (sz[i] == 0) {
      int j = i == 0 ? 0 : i - 1;
      b[j][sz[j]++] = y;
      if (mn[j] > y) mn[j] = y;
      return;
    }
    if (x < sz[i]) {
      for (int j = sz[i]; j > x; j--) b[i][j] = b[i][j - 1];
      b[i][x] = y;
      if (mn[i] > y) mn[i] = y;
      ++sz[i];
      return;
    } else {
      x -= sz[i];
    }
  }
  assert(false);
}

int getmin(int l, int r) {
  --l;
  --r;
  int bl = 0;
  int br = 0;
  int ans = INF;
  while (true) {
    if (l < sz[bl]) {
      break;
    } else {
      l -= sz[bl];
      bl++;
    }
  }
  while (true) {
    if (r < sz[br]) {
      break;
    } else {
      r -= sz[br];
      br++;
    }
  }
  if (bl == br) {
    while (l <= r) {
      if (b[bl][l] < ans) ans = b[bl][l];
      ++l;
    }
  } else {
    while (l < sz[bl]) {
      if (b[bl][l] < ans) ans = b[bl][l];
      ++l;
    }
    while (r >= 0) {
      if (b[br][r] < ans) ans = b[br][r];
      --r;
    }
    ++bl;
    --br;
    while (bl <= br) {
      if (mn[bl] < ans) ans = mn[bl];
      bl++;
    }
  }
  return ans;
}

int main() {
  freopen("rmq.in", "r", stdin);
  freopen("rmq.out", "w", stdout);
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    int c = getchar();
    while (c <= 32) c = getchar();
    int x, y;
    scanf("%d%d", &x, &y);
    if (c == '+') {
      add(x, y);
    } else {
      printf("%d\n", getmin(x, y)); 
    }/*
    for (int j = 0; j < B; j++) {
      if (sz[j] == 0) break;
      for (int k = 0; k < sz[j]; k++) printf("%d ", b[j][k]);
      puts("");
    }
    puts("");
    puts("");*/
    if (i % K == 0)
      rebuild();
  }
}
