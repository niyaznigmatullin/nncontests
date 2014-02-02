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

const int dx[] = {0, 1, 0, -1};
const int dy[] = {1, 0, -1, 0};

int main() {
  int n;
  cin >> n;
  int speed = 0;
  int dd = 0;
  int cx = 0;
  int cy = 0;
  for (int i = 0; i < n; i++, 
    cx += speed * 100 * dx[dd],
    cy += speed * 100 * dy[dd]) {
    string s;
    cin >> s;
    if (s == "Fwrd") {
      if (speed != 0) continue;
      speed = 1;
    } else if (s == "Back") {
      if (speed != 0) continue;
      speed = -1;
    } else if (s == "Rght") {
      if (speed != 0) continue;
      dd = (dd + 1) & 3;
    } else if (s == "Left") {
      if (speed != 0) continue;
      dd = (dd - 1) & 3;
    } else if (s == "Pass") {
      
    } else if (s == "More") {
      if (speed <= 0) continue;
      speed++;
      if (speed > 5) speed = 5;
    } else if (s == "Less") {
      if (speed <= 0) continue;
      --speed;
    } else if (s == "Stop") {
      speed = 0;
    }
//    printf("%d\n", speed);
//    printf("%d %d\n", cx, cy);
  }
  printf("%d %d\n", cx, cy);
}
