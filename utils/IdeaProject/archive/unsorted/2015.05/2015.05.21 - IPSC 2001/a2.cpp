#include <stdio.h>
#include <cassert>

int foo(int a, int b)
{
  return a<b?0:a-b;
}

int mx(int a, int b)
{
  return a+foo(b,a);
}

int mn(int a, int b)
{
  return a+b-mx(b,a);
}

int moo(int a, int b)
{
  int i,j,r;
  r = 0;
  for (i=b+mn(a,b)-mx(b,a); i<=b+foo(a,b)+foo(b,a)+7; i++) {
    if (i<=0) continue;
    j = foo(a%i, b%i) + a%i;
    j += foo(b%i, a%i);
    if (!j) r=i;
  }
  return ( mn(r,b)==(a-foo(a,b)) );
}

int soo(int a)
{
    int ret = 0;
  for (int i = 1; i * i <= a; i++) {
    if (a % i != 0) continue;
    ++ret;
    if (i * i != a) ++ret;
  }
  return ret;
}

int main(void){
  int i;
  i = 0;
  while(soo(i * i) != 7777)
    i++;
  printf("%d\n",i * i);
  return 0;
}
