/*** C ***/
#include <bits/stdc++.h>

using namespace std;

#define n 11

void boo(int a[])
{
  int i;
  for (i = 0; i < n; i++) {
    a[i] = i;
  }
}

void foo(int a[])
{
  int i, c;
  c = (a[n-1] + a[0]/3) % 1001;
  for (i = 0; i < n-1; i++) {
    a[i] = a[i+1];
  }
  a[n-1] = c;
}

int equal(int a[], int b[])
{
  int i, result;
  result = 1;
  for (i = 0; i < n; i++)
    if (a[i] != b[i]) result = 0;
  return result;
}


int main(void)
{
  int a[n], b[n];
  int ac, bc;

  printf("*");
  boo(a);
  ac = 1;
  map<vector<int>, int> z;
  while (true) {
    vector<int> aa(a, a + n);
    if (z.find(aa) != z.end()) {
        cout << z[aa] << ' ' << ac << endl;
        return 0;
    }
    z[aa] = ac;
    foo(a);
    ac++;
  }
  ac = 1;
  boo(a);
  ac++;
  foo(a);
  bc = 1;
  boo(b);
  while (bc <= ac || !equal(a,b)) {/*
    printf("%lld %lld\n", ac, bc);
    for (int i = 0; i < n; i++) printf("%d ", a[i]);
    puts("");
    for (int i = 0; i < n; i++) printf("%d ", b[i]);
    puts("");*/
    bc++;
    foo(b);
    if (false && ac == bc) {
      printf("*");
      ac++;
      foo(a);
      bc = 1;
      boo(b);
    }
  }
  printf("%lld %lld\n", ac, bc);
}
/*

{*** Pascal ***}
const n=11;
type arr=array[1..n] of integer;

procedure boo(var a:arr);
var i:integer;
begin
  for i:=1 to n do
    a[i]:=i-1;
end;

procedure foo(var a:arr);
var i,c:integer;
begin
  c:=(a[n] + (a[1] div 3)) mod 1001;
  for i:=1 to n-1 do
    a[i]:=a[i+1];
  a[n]:=c;
end;

function equal(a,b:arr):boolean;
var i: integer;
    result: boolean;
begin
  result:=true;
  for i:=1 to n do
    if a[i]<>b[i] then result:=false;
  equal:=result;
end;

var a,b:arr;
   ac,bc:longint;

begin
  write('*');
  ac:=1;
  boo(a);
  ac:=ac+1;
  foo(a);
  bc:=1;
  boo(b);
  while not(equal(a,b)) do begin
    bc:=bc+1;
    foo(b);
    if ac=bc then begin
      write('*');
      ac:=ac+1;
      foo(a);
      bc:=1;
      boo(b);
    end
  end
end.
*/