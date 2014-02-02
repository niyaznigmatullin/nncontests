#include <cstdio>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <map>
#include <cassert>
#include <memory.h>

using namespace std;
int s[1234567], cn[12345];

void solve() {
  int c = getchar();
  while (c <= 32) c = getchar();
  int n = 0;
  while (c > 32) {
    s[n++] = c;
    c = getchar();
  }
  for (int i = 0; i < 256; i++) cn[i] = 0;
  for (int i = 0; i < n; ) {
    cn[s[i]]++;
    if (cn[s[i]] % 3 == 0) {
      if (i + 1 >= n || s[i + 1] != s[i]) {
        puts("FAKE");
        return;
      }
      i += 2;
    } else {
      ++i;
    }
  }
  puts("OK");
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}