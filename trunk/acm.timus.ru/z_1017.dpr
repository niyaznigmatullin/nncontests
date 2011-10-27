program z_1017;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[1..500,1..500] of int64;
    n:longint;
procedure init;
  begin
    read(n);
  end;

procedure solve;
var i,j:longint;
  begin
    for i:=1 to n do a[i,i]:=1;
    for i:=2 to n do
    for j:=i-1 downto 1 do
      begin
        a[i,j]:=a[i-j,j+1]+a[i,j+1];
      end;
  end;

procedure out;
  begin
    write(a[n,1]-1);
    readln;
    readln;
  end;

begin
  init;
  solve;
  out;
end. 
