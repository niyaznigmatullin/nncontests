#include <iostream>
#include <algorithm>

using namespace std;

int main() {
  long long a, b;
  cin >> a >> b;
  int neg = 1;
  if (a < b) {
    std::swap(a, b);
    neg = -1;
  }
  if (b > 0 || a < 0) {
    cout << (a - b) * neg << endl;
  } else {
    cout << (a + b) << endl;
  }
} 