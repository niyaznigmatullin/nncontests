program z_H;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

var n : integer;
procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
  end;

procedure solve;
var kol : integer;
  begin
    dec(n);
    kol := 0;
    while ( n > 0) do begin inc(kol); n := n and (n - 1); end;
    write(kol mod 3);
    close(output);
  end;

begin
  init;
  solve;
end.
