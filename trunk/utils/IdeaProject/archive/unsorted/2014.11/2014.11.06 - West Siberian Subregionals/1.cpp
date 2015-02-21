
int main() {
  int n;
  scanf("%d", &n);
  for (int i = 0; i < n; i++) {
    scanf("%d%d", x + i, h + i);
  }
  for (int i = 0; i < n; i++) {
    int left = std::lower_bound(x, x + n, x[i] - h[i]);
    toleft[i] = getmax(left, i - 1);
    setit(i, toleft[i]);
  }
  for (int i = n - 1; i >= 0; i--) {
    
  }
}
