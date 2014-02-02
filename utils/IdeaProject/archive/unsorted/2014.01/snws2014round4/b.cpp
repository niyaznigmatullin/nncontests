#include <cstdio>
#include <ctime>
#include <algorithm>
#include <cstdlib>
#include <vector>
#include <iostream>
#include <cmath>
#include <set>
#include <map>
#include <cassert>
#include <memory.h>

using namespace std;

const int N = 12345;

string ans[N];
int p[N], r[N];

void read(string & s) {
  s = "";
  int c = getchar();
  while (c <= 32) c = getchar();
  while (c > 32) {
    s += (char) c;
    c = getchar();
  }
}

void solve() {
  int n;
  scanf("%d", &n);
  map<string, int> f;
  for (int i = 0; i < n; i++) {
    string s;
    read(s);
    f[s] = i;
  }
  for (int i = 0; i < n; i++) {
    string s;
    read(s);
    p[i] = f[s];
  }
  for (int i = 0; i < n; i++) {
    string s;
    read(s);
    ans[p[i]] = s;
  }
  for (int i = 0; i < n; i++) {
    if (i > 0) putchar(' ');
    printf("%s", ans[i].c_str());
  }
  puts("");
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}
