program _0175;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,k,m:integer;
    a:array[0..10000] of integer;

procedure init;
var i:integer;
  begin
    read(n,k,m);
    for i:=1 to n do read(a[i]);
  end;

function check(x:integer):boolean;
var rt:int64; i:integer;
  begin
    rt:=0;
    for i:=1 to n do rt:=rt+a[i] div x;
    result:=rt>=k;
  end;

procedure solve;
var l,r,mid:integer;
  begin
    l:=m-1;
    r:=10000000;
    while (r>l) do
      begin
        mid:=(l+r) shr 1 + 1;
        if (check(mid)) then l:=mid else r:=mid-1;
      end;
    if (l=m-1) then write(0) else write(l);
    readln;
    readln;
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
end.
 