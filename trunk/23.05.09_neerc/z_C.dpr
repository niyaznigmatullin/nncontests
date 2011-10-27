program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,p:integer;

procedure init;
  begin
    reset(input,'key.in');
    rewrite(output,'key.out');
    read(n,p);
    close(input);
  end;

procedure solve;
  begin
    write(p + n - 1);
    close(output);
  end;

begin
  init;
  solve;
end.
 