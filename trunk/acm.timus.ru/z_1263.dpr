program z_1263;
{$APPTYPE CONSOLE}

uses
  SysUtils;

var n,m,i,x:integer;
    a:array[0..100000] of integer;

begin
  read(n,m);
  fillchar(a,sizeof(a),0);
  for i:=1 to m do
    begin
      read(x);
      inc(a[x]);
    end;
  for i:=1 to n do
    begin
      writeln(a[i] / m * 100 : 0 : 2,'%');
    end;
end.
 