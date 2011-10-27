program z_F;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

var s,t,n,Dd,R,c1,c2,c3 :integer;
    d,z:array[0..100] of integer;

procedure init;
var i:integer;
  begin
    reset(input,'railway.in');
    rewrite(output,'railway.out');
    read(n);
    for i:=1 to n do read(d[i]);
    for i:=1 to n do read(z[i]);
    read(s,t);
    read(c1,c2,c3,R);
    close(input);
  end;

procedure solve;
  begin
    d[0] := 0;
    z[0] := 0;
    writeln(c1 * Math.max(1, abs(z[s] - z[t])));
    Dd := abs(d[s] - d[t]);
    if (Dd < R) then
      begin
        writeln(c2 * Dd);
      end else writeln(c3 * Dd);
    close(output);
  end;

begin
  init;
  solve;
end.
