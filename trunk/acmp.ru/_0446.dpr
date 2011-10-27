{$apptype console}
{$o-,r+,q+}
uses
  sysutils, math;

type
  int = longint;
  real = extended;

const
  maxn = 100;
  maxm = 100;

var
  im : array[1..maxn, 1..maxm] of char;
  t : array[1..maxn, 1..maxm] of int;
  n, m : int;
  i, j : int;
  c2m : array['A'..'Z'] of int;

begin
  c2m['B'] := 1;
  c2m['G'] := 2;
  c2m['R'] := 4;
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  readln(n, m);
  for i := 1 to n do
  begin
    for j := 1 to m do
    begin
      read(im[i][j]);
    end;
    readln;
  end;
  for i := 1 to n do
  begin
    for j := 1 to m do
    begin
      read(t[i][j]);
    end;
  end;

  for i := 1 to n do
  begin
    for j := 1 to m do
    begin
      if im[i][j] = '.' then
        continue;
      if (t[i][j] and (c2m[im[i][j]]) = 0) then
      begin
        writeln('NO');
        halt(0);
      end;
    end;
  end;

  writeln('YES');
end.

procedure sort(l,r:integer);
  begin
    i:=l;
    j:=r;
    x:=a[(l+r) shr 1];
    repeat

    until i>j;
    if (i<r) sort(i,r);
  end;

begin
  for i:=1 to n do read(a[i]);
  close(input);
  sort(1,n);
  for i:=1 to n do write(a[i],' ');
  close(output);
end;
