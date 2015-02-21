#include <bits/stdc++.h>
  using namespace std;

template<typename T> void printerr(T a) {
  auto i = begin(a);
  auto j = end(a);
  cerr << "[";
  while (i != j) {
    cerr << *i << ", ";
    i++;
  }
  cerr << "]";
  cerr << endl;
}

ostream_iterator<int> debug(cerr, ", ");

int b[123213];

int main() {
  std::vector<int> a = {1, 2, 3};
  printf("%d\n", a.size());
  printerr(a);
  std::copy(b, b + 10, debug);
  array<tuple<int, int, string>, 10> z;
  for (int i = 0; i < 10; i++) {
    int x, y;
    string name;
    cin >> x >> y >> name;
    z[i] = make_tuple(x, y, name);
  }
  std::sort(z.begin(), z.end());
  for (auto i : z) {
    cerr << get<0>(i) << ' ' << get<1>(i) << ' ' << get<2>(i) << endl;
  }
}
