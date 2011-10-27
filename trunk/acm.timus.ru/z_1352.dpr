program z_1352;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const p:array[1..38] of integer=(2, 3, 5, 7, 13, 17, 19, 31, 61, 89, 107, 127, 521, 607, 1279, 2203, 2281, 3217, 4253, 4423, 9689, 9941, 11213, 19937, 21701, 23209, 44497, 86243, 110503, 132049, 216091, 756839, 859433, 1257787, 1398269, 2976221, 3021377, 6972593);

var n:integer;

procedure done(n:integer); Forward;

procedure init;
var t,i:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    readln(t);
    for i:=1 to t do begin readln(n); done(n); end;
  end;

procedure done(n:integer);
  begin
    writeln(p[n]);
  end;

begin
  init;
end.
