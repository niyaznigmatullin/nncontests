program z_1026;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[0..100001] of integer;
    k,n:integer;

procedure sort(l,r:integer);
var i,j,x,y:integer;
  begin
    i:=l;
    j:=r;
    x:=a[random(r-l+1)+l];
    repeat
      while a[i]<x do inc(i);
      while a[j]>x do dec(j);
      if (i<=j) then
        begin
          y:=a[i];
          a[i]:=a[j];
          a[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    if i<r then sort(i,r);
    if l<j then sort(l,j);
  end;

procedure init;
var i,x:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    readln(n);
    for i:=1 to n do read(a[i]);
    sort(1,n);
    readln;
    readln;
    read(k);
    for i:=1 to k do
      begin
        read(x);
        writeln(a[x]);
      end;
    halt(0);
  end;

begin
  init;
end.
