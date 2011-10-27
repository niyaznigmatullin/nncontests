program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,B:integer;
    a:array[0..100] of integer;

function int(x,y:integer):boolean;
  begin
    while (x > 0) and (y > 0) do
      begin
        if (x mod 10 = y mod 10) then x := x div 10;
        y := y div 10;
      end;
    result:= x = 0;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'number.in');
    rewrite(output,'number.out');
    read(n, B);
    for i:=1 to n do read(a[i]);
    close(input);
  end;

procedure solve;
var i,ans:integer;
  begin
    ans := 0;
    for i:=1 to n do
      if (int(B,a[i])) then
        inc(ans);
    write(ans);
    close(output);
  end;

begin
  init;
  solve;
end.
 