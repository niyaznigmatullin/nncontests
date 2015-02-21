#include <cstdio>

int main() {
  int a, b, c;
  scanf("%d:%d:%d", &a, &b, &c);
  int dh = 360 * 60 * 60 / 12 / 60 / 60;
  int dm = 360 * 60 * 60 / 60 / 60;
  int all = 360 * 60 * 60;
  int t = a * 3600 + b * 60 + c;
  int angle1 = t * dh % all;
  int angle2 = t * dm % all;
  int dif = angle1 - angle2;
  if (dif < 0) dif += all;
  if (dif > all - dif) dif = all - dif;
  printf("%d %d %d\n", dif / 60 / 60, dif / 60 % 60, dif % 60);
}