program z_1507;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type Matrix = array [0 .. 50, 0 .. 50] of boolean;

var a,b,c:Matrix;
    n:integer;

procedure mul(var c:Matrix; const a,b:Matrix);
var d:Matrix; k,i,j:integer;
  begin
    fillchar(d,sizeof(d),false);
    for i:=1 to n do
    for j:=1 to n do
      begin
        d[i][j]:=false;
        for k:=1 to n do
          begin
            d[i][j]:=d[i][j] or (a[i][k] and b[k][j]);
          end;
      end;
    c:=d;
  end;

procedure sum(var c:Matrix; const a,b:Matrix);
var d:Matrix; i,j:integer;
  begin
    fillchar(d,sizeof(d),false);
    for i:=1 to n do
    for j:=1 to n do d[i][j]:=a[i][j] or b[i][j];
    c:=d;
  end;
procedure power(var c:Matrix; a:Matrix; v:integer);
var res:Matrix; i:integer;
  begin
    fillchar(res,sizeof(res),false);
    for i:=1 to n do res[i][i]:=true;
    while (v > 0) do
      begin
        if (v and 1 = 1) then mul(res,res,a);
        mul(a,a,a);
        v:=v shr 1;
      end;
    c:=res;
  end;

procedure init;
var i,j,x:integer; ok:boolean;
  begin
    read(n);
    ok:=false;
    for i:=1 to n do
    for j:=1 to n do
      begin
        read(x);
        a[i][j] := x <> 0;
        ok := ok or a[i][j];
      end;
    if (not ok) then
      begin
        write('No');
        halt(0);
      end;
  end;

procedure solve;
var i:integer;
  begin
    power(b,a,n*(n-1));
    for i:=n*(n-1) to n*(n+1) do
      begin
        sum(c,c,b);
        mul(b,b,a);
      end;
  end;

procedure done;
var ans:boolean; i,j:integer;
  begin
    ans:=true;
    for i:=1 to n do
    for j:=1 to n do
      begin
        if (not c[i][j]) then
          begin
            ans:=false;
            break;
          end;
      end;
    if (ans) then write('Yes') else write('No');
    readln;
    readln;
  end;

begin
  init;
  solve;
  done;
end.
