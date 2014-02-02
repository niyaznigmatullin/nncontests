#include <cstdio>
#include <string>
#include <utility>
#include <vector>
#include <set>
#include <map>
#include <iostream>
#include <algorithm>
#include <cassert>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;

int a[1234], b[1234], c[1234], d[1234];

int main() {
  string s, t;
  cin >> s >> t;
  int cn1 = 0;
  int cn2 = 0;
  for (size_t i = 0; i < s.size(); i++) {
    if (s[i] == 'W') a[cn1++] = i; else b[cn2++] = i;
  }
  cn1 = cn2 = 0;
  for (size_t i = 0; i < t.size(); i++) {
    if (t[i] == 'W') c[cn1++] = i; else d[cn2++] = i;
  }
  int ans = 0;
  for (int i = 0; i < cn1; i++) ans = std::max(std::abs(a[i] - c[i]), ans);
  for (int i = 0; i < cn2; i++) ans = std::max(std::abs(b[i] - d[i]), ans);
  cout << ans << "\n";
}
