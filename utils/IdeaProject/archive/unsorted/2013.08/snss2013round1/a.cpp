#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
#include <unordered_map>
#include <unordered_set>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;

int ni() {
  int c = getchar();
  while (c != '-' && (c < '0' || c > '9')) c = getchar();
  int sg = 0;
  if (c == '-') {
    sg = 1;
    c = getchar();
  }
  int x = 0;
  while (c >= '0' && c <= '9') {
    x = x * 10 + c - '0';
    c = getchar();
  }
  return sg ? -x : x;
}

int a[1234567];

int main() {
  int n = ni();
  int k = ni();
  for (int i = 0; i < n; i++) {
    int c = getchar();
    while (c <= 32) c = getchar();
    a[i] = c;
  }
  int cur = 0;
  int cc = 0;
  while (true) {
    while (cc < n && a[cc] != 'v') ++cc;
    if (cc >= n) break;
    int cn = 0;
    for (int i = 0; i < k; i++) if (a[cc + i] == 'v') ++cn;
    if (cn < k) {
      for (int i = 0; i < k; i++) {
        a[cc] = 'v';
      }
      for (int i = 0; i <= k; i++) {
        printf("%d ", cc + i + 1);
      }
      puts("");
      cc += k + 1;
    } else {
      while (cur < n && a[cur] != 'w') cur++;
      for (int i = 0; i < k; i++) printf("%d ", cc + i + 1);
      printf("%d", cur + 1);
      puts("");
      ++cur;
      cc += k;
    }
  }  
}