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

const int N = 222;
const int dx[] = {1, 0, -1, 0};
const int dy[] = {0, 1, 0, -1};

int q[N * N], was[N][N], a[N][N], ql[42][N * N], cl[42], has[42], n, m;

void enqueue(int & head, int & tail, int i, int j, int & ans) {
  if (a[i][j] == '.' || a[i][j] == '$') {
    q[tail++] = i * m + j;
    was[i][j] = true;
    if (a[i][j] == '$') ++ans;
  } else if (a[i][j] >= 'a' && a[i][j] <= 'z') {
    q[tail++] = i * m + j;
    has[a[i][j] - 'a'] = true;
    was[i][j] = true;
    int letter = a[i][j] - 'a';
    while (cl[letter] > 0) {
      --cl[letter];
      int v = ql[letter][cl[letter]];
      q[tail++] = v;
      int cx = v / m;
      int cy = v % m;
      was[cx][cy] = true;
    }
  } else if (a[i][j] >= 'A' && a[i][j] <= 'Z') {
    if (has[a[i][j] - 'A']) {
      q[tail++] = i * m + j;
      was[i][j] = true;
    } else {
      int letter = a[i][j] - 'A';
      ql[letter][cl[letter]++] = i * m + j;
    }
  }
}

void solve() {
  scanf("%d%d", &n, &m);
  for (int i = 0; i < n; i++) 
    for (int j = 0; j < m; j++) {
      int c = getchar();
      while (c <= 32) c = getchar();
      a[i][j] = c;
    }
  for (int i = 0; i < 26; i++) {
    has[i] = false;
    cl[i] = 0;
  }
  {
    int c = getchar();
    while (c <= 32) c = getchar();
    while (c > 32) {
      if (c != '0')
        has[c - 'a'] = true;
      c = getchar();
    }
  }
  for (int i = 0; i < n; i++)
    for (int j = 0; j < m; j++) was[i][j] = false;
  int head = 0;
  int tail = 0;
  int ans = 0;
  for (int i = 0; i < n; i++) 
    for (int j = 0; j < m; j++)
      if (i == 0 || i == n - 1 || j == 0 || j == m - 1)
        if (a[i][j] != '*') {
          enqueue(head, tail, i, j, ans);
        }
  while (head < tail) {
    int v = q[head++];
    int cx = v / m;
    int cy = v % m;
    for (int dir = 0; dir < 4; dir++) {
      int nx = cx + dx[dir];
      int ny = cy + dy[dir];
      if (nx < 0 || ny < 0 || nx >= n || ny >= m || was[nx][ny] || a[nx][ny] == '*') continue;
      enqueue(head, tail, nx, ny, ans);
    }
  }
  printf("%d\n", ans);
}

int main() {
  int t;
  scanf("%d", &t);
  for (int i = 0; i < t; i++) solve();
}