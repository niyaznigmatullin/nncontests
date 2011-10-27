program z_C;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint = record
      x,y:extended;
      end;

var p,old,new : array[ 0.. 1000] of TPoint;
    k,n : integer;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(p[i].x, p[i].y);
    read(k);
    close(input);
  end;

procedure solve;
var i,j : integer; ans :extended;
  begin
    old := p;
    for i:=1 to k do
      begin
        old[n + 1] := old[1];
        for j := 1 to n do
          begin
            new[j].x := (old[j + 1].x + old[j].x) / 2;
            new[j].y := (old[j + 1].y + old[j].y) / 2;
          end;
        old := new;
      end;
    old[n + 1] := old[1];
    ans := 0;
    for i:=1 to n do ans := ans + sqrt(sqr(old[i + 1].x - old[i].x) + sqr(old[i + 1].y - old[i].y));
    write(ans : 0 : 10);
    close(output);
  end;

begin
  init;
  solve;
end.
