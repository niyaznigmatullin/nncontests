program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const eps = 1e-9;

var a,b,c,x,y,r,d:extended;
procedure init;
  begin
    reset(input,'circle.in');
    rewrite(output,'circle.out');
    read(a,b,c);
    read(x,y,r);
    close(input);
  end;

procedure solve;
  begin
    if abs(a * x + b * y + c) / sqrt(a * a + b * b) - r > -eps then write(0) else
      begin
        d := abs(a * x + b * y + c) / sqrt(a * a + b * b);
        write(2 * sqrt(r * r - d * d) : 0 : 10);
      end;
    close(output);
  end;

begin
  init;
  solve;
end.
 