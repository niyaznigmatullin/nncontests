program z_1349;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n:integer;

procedure init;
  begin
    readln(n);
  end;

procedure done;
  begin
    if n=1 then write('1 2 3') else if (n=2) then write('3 4 5') else write(-1);
  end;

begin
  init;
  done;
end.
