#include <cstdio>
#include <set>

using namespace std;

int const N = 1234567;
int a[N];
long long s[N];

int main() {
  freopen("seq.in", "r", stdin);
  freopen("seq.out", "w", stdout);
  int n, h;
  scanf("%d%d", &n, &h);
  for (int i = 0; i < n; i++) scanf("%d", a + i);
  for (int i = 0; i < n; i++) {
    s[i] = a[i];
    if (i > 0) s[i] += s[i - 1];
  }
  for (int i = 0; i < n; i++) a[i] -= i;
  multiset<int> b;
  for (int i = 0; i < h; i++) b.insert(a[i]);  
  long long ans = -(1LL << 60);
  for (int i = h - 1; i < n; i++) {
    int first = i - h + 1;
    if (*(b.rbegin()) <= 1 - first) {
      long long sum = s[i];
      if (first > 0) sum -= s[first - 1];
      if (sum > ans) ans = sum;
    }    
    if (i + 1 >= n) break;
    b.erase(b.find(a[first]));
    b.insert(a[i + 1]);
  }
  if (ans == -(1LL << 60)) {
    puts("-1");
    return 0;
  }
  printf("%I64d\n", (long long) h * (h + 1) / 2 - ans);
}