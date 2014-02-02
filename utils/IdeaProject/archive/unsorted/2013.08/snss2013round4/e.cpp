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

const int N = 1234567;

int a[N], s[N];

int main() {
  int n = 0;
  while (true) {
    int x = ni();
    if (x == 0) break;
    a[n++] = x;
  }
  int j = 0;
  int ansc = 0;
  int ansi = -1;
  int ansj = -1;
  for (int i = 0; i < n; i++) {
    while (j < n) {
      if (s[a[j]] > 0) break;
      s[a[j]]++;
      ++j;
    }
    if (j - i > ansc) {
      ansi = i;
      ansj = j;
      ansc = j - i;
    }
    --s[a[i]];
  }
  for (int i = ansi; i < ansj; i++) printf("%d\n", a[i]);
}
