program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n, m : integer;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    close(input);
        if (n = 1) then begin write(0); close(output);
    halt(0); end;
    if (n mod 2 = 0) then
      begin
        write(n div 2);
      end else write(n);
    close(output);
    halt(0);
  end;

procedure solve;
var kol, kol2 : integer;
  begin
    kol := 0;
    kol2 := 1;
    read(m);
    while (m and 1 = 0) do begin kol := kol + kol2 div 2 + kol2 mod 2; kol2 := kol2 shl 1; m := m shr 1; end;
    write(n - kol);
    close(output);
  end;

begin
  init;
  solve;
end.
