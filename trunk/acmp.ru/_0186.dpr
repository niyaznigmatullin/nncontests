program _0186;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a,b:array[0..1001] of integer;
    n:integer;

procedure sort(l,r:integer);
var i,j,x,y:integer;
begin
i:=l;
j:=r;
x:=a[(l+r) div 2];
 repeat
  while a[i]<x do inc(i);
  while a[j]>x do dec(j);
   if i<=j then
    begin
     y:=a[i]; a[i]:=a[j]; a[j]:=y;
     inc(i);dec(j);
    end;
 until i>j;
if i<r then sort(i,r);
if l<j then sort(l,j);
end;

function max(x,y:integer):integer;
  begin
    if x>y then result:=x else result:=y;
  end;

function min(x,y:integer):integer;
  begin
    if x<y then result:=x else result:=y;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    sort(1,n);
    b[2]:=a[2]-a[1];
    b[3]:=a[3]-a[1];
    for i:=5 to n do
      begin
        b[i]:=min(max(b[i-2],a[i]-a[i-1]),max(b[i-3],a[i]-a[i-2]));
      end;
  end;

procedure done;
  begin
    write(b[n]);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
