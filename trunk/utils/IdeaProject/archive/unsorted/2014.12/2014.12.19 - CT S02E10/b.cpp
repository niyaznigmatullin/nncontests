#include <bits/stdc++.h>

using namespace std;

int main() {
  string s;
  cin >> s;
  int n = s.length();
  set<string> z;
  for (int s1 = 1; s1 <= n; s1++) {
    for (int s2 = s1 + 1; s2 <= n; s2++) {
      int f1 = s1;
      int f2 = s2;
      string t = "";
      t += s[f1 - 1];
      t += s[f2 - 1];
      while (true) {
        int f3 = f1 + f2;
        if (f3 > n) break;
        t += s[f3 - 1];
        f1 = f2;
        f2 = f3;
      }
      z.insert(t);
    }
  } 
  cout << z.size() << endl;
}
