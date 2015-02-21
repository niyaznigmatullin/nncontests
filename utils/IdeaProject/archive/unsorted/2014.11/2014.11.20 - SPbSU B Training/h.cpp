#include <cstdio>
#include <string>
int const N = 155555;
using std::string;

int head[N], tail[N], val[N], next[N], prev[N];

void read(string & s) {
  int c = getchar();
  while (c <= 32) c = getchar();
  s = "";
  while (c > 32) {
    s += (char) c;
    c = getchar();
  }
}

int main() {
  freopen("deques.in", "r", stdin);
  freopen("deques.out", "w", stdout);
  int fr = 0;
  int n;
  scanf("%d", &n);
  for (int i = 0; i < N; i++) head[i] = tail[i] = -1;
  for (int i = 0; i < n; i++) {
    string s;
    read(s);
    if (s == "pushfront") {
      int x, y;
      scanf("%d%d", &x, &y);
      --x;
      int v = fr++;
      val[v] = y;
      next[v] = head[x];
      prev[v] = -1;
      if (head[x] < 0) {
        tail[x] = v;
      } else prev[head[x]] = v;
      head[x] = v;
    } else if (s == "popfront") {
      int x;
      scanf("%d", &x);
      --x;
      printf("%d\n", val[head[x]]);
      if (next[head[x]] >= 0)
        prev[next[head[x]]] = -1;
      head[x] = next[head[x]];
      if (head[x] < 0) tail[x] = -1;
    } else if (s == "pushback") {
      int x, y;
      scanf("%d%d", &x, &y);
      --x;
      int v = fr++;
      val[v] = y;
      prev[v] = tail[x];
      next[v] = -1;
      if (head[x] < 0) {
        head[x] = v;
      } else next[tail[x]] = v;
      tail[x] = v;
    } else if (s == "popback") {
      int x;
      scanf("%d", &x);
      --x;
      printf("%d\n", val[tail[x]]);
      if (prev[tail[x]] >= 0)
        next[prev[tail[x]]] = -1;
      tail[x] = prev[tail[x]];
      if (tail[x] < 0) head[x] = -1;
    }
  }
}