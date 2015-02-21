#include <iostream>
#include <cstdio>
#include <algorithm>
#include <map>
#include <string>
using namespace std;

int const N = 123456;

string a[N], b[N], c[N], z[N];

int main() {
  freopen("names.in", "r", stdin);
  freopen("names.out", "w", stdout);
  int n;
  cin >> n;
  map<string, int> names;
  for (int i = 0; i < n; i++) {
    cin >> a[i] >> b[i] >> c[i];
    names[a[i]]++;
    names[b[i]]++;
    names[c[i]]++;
  }
  for (int i = 0; i < n; i++) {
    if (names[a[i]] > 1) {
      string t = c[i];
      c[i] = b[i];
      b[i] = a[i];
      a[i] = t;
    }
    z[i] = a[i] + " " + b[i] + " " + c[i];    
  }
  std::sort(z, z + n);
  for (int i = 0; i < n; i++) cout << z[i] << "\n";
  
}