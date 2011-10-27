program _0026;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,x:integer;
    a:array[0..100] of integer;

procedure solve;
var j,i:integer;
  begin
    read(n);
    fillchar(a,sizeof(a),0);
    for i:=1 to n do
      begin
        read(x);
        inc(a[x]);
      end;
    for i:=0 to 100 do
    for j:=1 to a[i] do writeln(i);
    readln;
    readln;
    halt(0);
  end;

begin
  solve;
end.
 