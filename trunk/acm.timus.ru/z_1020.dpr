program z_1020;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

type TPoint=record
      x,y:extended;
      end;

var p:array[0..101] of TPoint;
    ans,r:extended;
    n:integer;

procedure init;
var i:integer;
  begin
//    reset(input,'input.txt');
//    rewrite(output,'output.txt');
    readln(n,r);
    for i:=1 to n do read(p[i].x,p[i].y);
  end;

procedure solve;
var i:integer;
  begin
    ans:=2*pi*r;
    p[n+1]:=p[1];
    for i:=1 to n do
      begin
        ans:=ans+hypot(p[i].x-p[i+1].x,p[i].y-p[i+1].y);
      end;
  end;

procedure done;
  begin
    write(ans:0:2);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 