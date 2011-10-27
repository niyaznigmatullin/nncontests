program z_1214;
{$APPTYPE CONSOLE}
uses
  SysUtils;

procedure P(var x, y: longint);
var
  i, j: longint;
begin
  if (x>0) and (y>0) then
  begin
    for i := 1 to x+y do
    begin
      y := x*x+y;
      x := x*x+y;
      y := round(sqrt(x+(y/abs(y))*(-abs(y))));
      for j := 1 to 2*y do
        x := x-y;
    end;
  end;
end;

procedure init;
var x,y:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    read(x,y);
//    P(x,y);
    if (x mod 2<>y mod 2) and (x>0) and (y>0) then write(y,' ',x) else
    write(x,' ',y);
  end;

begin
  init;
end.
 