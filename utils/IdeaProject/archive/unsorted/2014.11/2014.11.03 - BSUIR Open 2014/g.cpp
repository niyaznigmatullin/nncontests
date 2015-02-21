#include <cstdio>
#include <algorithm>

using std::swap;

#define N 1000005

unsigned char wh1[N], h1[N], cnt1[N];
unsigned short wh2[N], h2[N], cnt2[N];
int fr;

int geth(int x) {
  return ((int) h1[x] << 16) | h2[x];
}

int getwh(int x) {
  return ((int) wh1[x] << 16) | wh2[x];
}

int getcnt(int x) {
  return ((int) cnt1[x] << 16) | cnt2[x];
}

bool cmp(int x, int y) {
  return getcnt(y) < getcnt(x);
}

void seth(int x, int y) {
  h1[x] = y >> 16;
  h2[x] = y & 0xFFFF;
}

void setwh(int x, int y) {
  wh1[x] = y >> 16;
  wh2[x] = y & 0xFFFF;
}

void setcnt(int x, int y) {
  cnt1[x] = y >> 16;
  cnt2[x] = y & 0xFFFF;
}

void down(int x) {
  while (true) {
    int mx = x;
    if (x * 2 <= fr && cmp(geth(x * 2), geth(mx))) mx = x * 2;
    if (x * 2 + 1 <= fr && cmp(geth(x * 2 + 1), geth(mx))) mx = x * 2 + 1;
    if (mx == x) break;
    swap(h1[mx], h1[x]);
    swap(h2[mx], h2[x]);
    setwh(geth(mx), mx);
    setwh(geth(x), x);
    x = mx;
  }
}

int main() {
  int n;
  scanf("%d", &n);
  for (int x, i = 0; i < n; ++i) {
    scanf("%d", &x);
    setcnt(x, getcnt(x) + 1);
  }
  for (int i = 0; i + 1 < N; i++) seth(i + 1, i), setwh(geth(i + 1), i + 1);
  fr = N - 1;
  for (int i = N - 1; i > 0; i--) down(i);
  if (getcnt(geth(1)) - 1 > n - getcnt(geth(1))) {
    puts("-1");
    return 0;
  }
  int m1 = 0;
  int m2 = 0;
  int have = n;
  int last = -1;
  for (int i = 0; i < n; i++) {
    --have;
    while (m1 < N && getcnt(m1) == 0) ++m1;
    while (m2 <= m1 || (m2 < N && getcnt(m2) == 0)) ++m2;
    int m3 = geth(1);
    int cm3 = getcnt(m3);
    if (cm3 - 1 > have - cm3) {
      setcnt(m3, getcnt(m3) - 1);
      down(getwh(m3));
      printf("%d ", m3);
      last = m3;
    } else if (last == m1) {
      printf("%d ", m2);
      setcnt(m2, getcnt(m2) - 1);
      down(getwh(m2));
      last = m2;
    } else {
      printf("%d ", m1);
      setcnt(m1, getcnt(m1) - 1);
      down(getwh(m1));
      last = m1;
    }
  }
}
