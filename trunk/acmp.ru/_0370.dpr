program _0370;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y:extended;
      end;

var p:array[0..50001] of TPoint;
    n:integer;
    area:extended;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(p[i].x,p[i].y);
    close(input);
  end;

procedure solve;
var i:integer;
  begin
    area:=0;
    p[n+1]:=p[1];
    for i:=1 to n do area:=area+(p[i].x*p[i+1].y-p[i+1].x*p[i].y);
    area:=0.5*abs(area);
  end;

procedure done;
  begin
    write(area:0:1);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
