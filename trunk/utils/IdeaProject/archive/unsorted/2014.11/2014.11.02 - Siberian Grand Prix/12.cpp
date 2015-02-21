#include <bits/stdc++.h>

using namespace std;

int main() {
  long long n, m;
  cin >> n >> m;
  long long cnt = 1;
  double d = 1;
  for (int i = 2; i <= 2 * n; i += 2) {
    cnt *= i;
    d *= i;
  }
  cout << (d >= 2 * m || cnt >= m ? "Harshat Mata\n" : "Nope\n");
  return 0;
}