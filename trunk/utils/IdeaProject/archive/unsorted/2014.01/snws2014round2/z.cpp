#include <vector>
#include <iostream>

using namespace std;

int main() {
  vector<int> a;
  a.push_back(1);
  a.push_back(1);
  a.push_back(1);
  a.push_back(1);
  a.push_back(1);
  a.push_back(1);
  a.push_back(1);
  a.push_back(1);
  a.push_back(1);
  cout << a.capacity() << endl;
}