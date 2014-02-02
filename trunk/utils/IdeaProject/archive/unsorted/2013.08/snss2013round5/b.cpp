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

const int INF = 1 << 30;

int kg[555], pg[555], dd[555], was[555];
int s[555][555], t[555][555];


bool solve() {
  int st = ni() - 1;
  if (st < 0) return false;
  int d = ni();
  int n = ni();
  for (int i = 0; i < n; i++) {
    kg[i] = ni();
    pg[i] = ni();
    for (int j = 0; j < kg[i]; j++) {
      s[i][j] = ni() - 1;
    }
    for (int j = 0; j < kg[i]; j++) {
      t[i][j] = ni();
    }    
  }
  for (int i = 0; i < 500; i++) {
    dd[i] = INF;
    was[i] = false;
  }
  dd[st] = 0;
  while (true) {
    int mi = -1;
    for (int i = 0; i < 500; i++) {
      if (was[i] || dd[i] == INF) continue;
      if (mi < 0 || dd[mi] > dd[i]) mi = i;
    }
    if (mi < 0 || mi == 0) break;
    was[mi] = true;
//    printf("%d\n", mi);
    int avail = dd[mi] + d;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < kg[i]; j++) {
        if (s[i][j] != mi) continue;
        int z1 = avail % pg[i];
        int z2 = t[i][j] % pg[i];
        int go = avail + (z2 - z1 + pg[i]) % pg[i];
        for (int k = j + 1; k < kg[i]; k++) {
          if (dd[s[i][k]] > go + t[i][k] - t[i][j]) {
            dd[s[i][k]] = go + t[i][k] - t[i][j];
//            printf("%d %d\n", s[i][k], dd[s[i][k]]);
          }
        }
      }
    }
  }
  printf("%d\n", dd[0]);
  return true;
}

int main() {
  while (solve());
}
