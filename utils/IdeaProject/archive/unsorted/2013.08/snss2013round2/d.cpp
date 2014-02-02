#include <cstdio>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <iostream>
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

int has[444], a[55][55];

int sum(int i1, int j1, int i2, int j2) {
  int ret = a[i2 - 1][j2 - 1];
  if (i1 > 0) ret -= a[i1 - 1][j2 - 1];
  if (j1 > 0) ret -= a[i2 - 1][j1 - 1];
  if (i1 > 0 && j1 > 0) ret += a[i1 - 1][j1 - 1];
  return ret;
}

int main() {
  int n = ni();
  int m = ni();
  int k = ni();
  int c = getchar();
  while (c <= 32) c = getchar();
  while (c > 32) {
    has[c] = true;
    c = getchar();
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      a[i][j] = has[c];
    }
  }
  for (int i = 0; i < n; i++)
    for (int j = 1; j < m; j++) a[i][j] += a[i][j - 1];
    
  for (int i = 1; i < n; i++)
    for (int j = 0; j < m; j++) a[i][j] += a[i - 1][j];
    
  int ansn = 0;
  int ansd = 1;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      for (int len1 = k; len1 + i <= n; len1++) {
        for (int len2 = k; len2 + j <= m; len2++) {
          int curd = len1 * len2;
          int curn = sum(i, j, i + len1, j + len2);
          if (curn * ansd > curd * ansn || (curn * ansd == curd * ansn && curd > ansd)) {
            ansn = curn;
            ansd = curd;
          }
        }
      }
    }
  }
  printf("%d/%d\n", ansn, ansd);
}
