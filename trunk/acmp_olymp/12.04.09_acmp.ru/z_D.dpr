program z_D;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y:integer;
      end;

var a:array[0..1000,0..1000] of boolean;
    p1,p2:array[0..1000] of TPoint;
    ans,n:integer;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(p1[i].x,p1[i].y, p2[i].x,p2[i].y);
    read(p1[n+1].x,p1[n+1].y,p2[n+1].x,p2[n+1].y);
    close(input);
  end;

procedure swap(var x,y:integer);
var p:integer;
  begin
    p:=x;
    x:=y;
    y:=p;
  end;

procedure solve;
var i,x,y:integer;
  begin
    for i:=1 to n+1 do
      begin
        dec(p2[i].x);
        if (p1[i].x>p2[i].x) then swap(p1[i].x,p2[i].x);
        dec(p2[i].y);
        if (p1[i].y>p2[i].y) then swap(p1[i].y,p2[i].y);
      end;
    for i:=1 to n do
      begin
        for x:=p1[i].x to p2[i].x do
        for y:=p1[i].y to p2[i].y do a[x][y]:=true;
      end;
    ans:=0;
    for x:=p1[n+1].x to p2[n+1].x do
    for y:=p1[n+1].y to p2[n+1].y do if (a[x][y]) then inc(ans);
  end;

procedure done;
  begin
    write(ans);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
