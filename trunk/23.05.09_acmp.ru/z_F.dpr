program z_F;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

var ans, n:integer;

procedure init;
var i,j,x:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do for j:=1 to n do begin read(x); ans := ans + x; end;
    close(input);
  end;

procedure solve;
  begin
    write(ans div 2);
    close(output);
  end;

begin
  init;
  solve;
end.
 