program z_M;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y,z:extended;
      end;

var l,w,h:extended;
    p1,p2:TPoint;
    w1,w2:integer;
    ans:extended=1e30;

function find(p:TPoint):integer;
  begin
    if (p.z=0) then result:=1 else
    if (p.z=h) then result:=2 else
    if (p.y=0) then result:=3 else
    if (p.y=w) then result:=4 else
    if (p.x=0) then result:=5 else result:=6;
  end;

function can(e:integer; s:TPoint):boolean;
  begin
    if (e=1) then result:=(s.z=0) else
    if (e=2) then result:=(s.z=h) else
    if (e=3) then result:=(s.y=0) else
    if (e=4) then result:=(s.y=w) else
    if (e=5) then result:=(s.x=0) else
    if (e=6) then result:=(s.x=l) else result:=false
  end;

procedure swap(var p1,p2:TPoint);
var s:TPoint; p:integer;
  begin
    p:=w1;
    w1:=w2;
    w2:=p;
    s:=p1;
    p1:=p2;
    p2:=s;
  end;

function dist1(s1,s2:TPoint):extended;
  begin
    result:=sqr(s1.x-s2.x)+sqr(s1.y-s2.y)+sqr(s1.z-s2.z);
  end;

function dist:extended;
  begin
    result:=dist1(p1,p2);
  end;

function min(x,y:extended):extended;
  begin
    if (x<y)then result:=x else result:=y;
  end;

procedure print_ans(x:extended);
  begin
    write(x:0:5);
    close(output);
    halt(0);
  end;

procedure print;
  begin
    write(dist:0:6);
    close(output);
    halt(0);
  end;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(l,w,h);
    read(p1.x,p1.y,p1.z,p2.x,p2.y,p2.z);
    close(input);
  end;

function check(w1,w2:integer; p1,p2:TPoint):extended;
var p:TPoint; e:integer; h1,d1,d2,d3,d4:extended;
  begin
    result:=1e30;
    if (w1>w2) then
      begin
        p:=p1;
        p1:=p2;
        p2:=p;
        e:=w1;
        w1:=w2;
        w2:=e;
      end;
    if (w1=w2) then result:=dist1(p1,p2);
    if (w1=1) and (w2=6) then
      begin
        h1:=p2.z;
        p2.z:=p2.z-h1;
        p2.x:=p2.x+h1;
        result:=dist1(p1,p2);
      end;
    if (w1=1) and (w2=3) then
      begin
        h1:=p2.z;
        p2.z:=p2.z-h1;
        p2.y:=p2.y-h1;
        result:=dist1(p1,p2);
      end;
    if (w1=1) and (w2=5) then
      begin
        h1:=p2.z;
        p2.z:=p2.z-h1;
        p2.x:=p2.x-h1;
        result:=dist1(p1,p2);
      end;
    if (w1=1) and (w2=4) then
      begin
        h1:=p2.z;
        p2.z:=p2.z-h1;
        p2.y:=p2.y+h1;
        result:=dist1(p1,p2);
      end;
    if (w1=2) and (w2=6) then
      begin
        h1:=h-p2.z;
        p2.z:=p2.z+h1;
        p2.x:=p2.x+h1;
        result:=dist1(p1,p2);
      end;
    if (w1=2) and (w2=5) then
      begin
        h1:=h-p2.z;
        p2.z:=p2.z+h1;
        p2.x:=p2.x-h1;
        result:=dist1(p1,p2);
      end;
    if (w1=2) and (w2=3) then
      begin
        h1:=h-p2.z;
        p2.z:=p2.z+h1;
        p2.y:=p2.y-h1;
        result:=dist1(p1,p2);
      end;
    if (w1=2) and (w2=4) then
      begin
        h1:=h-p2.z;
        p2.z:=p2.z+h1;
        p2.y:=p2.y+h1;
        result:=dist1(p1,p2);
      end;
    if (w1=3) and (w2=5) then
      begin
        h1:=p2.y;
        p2.y:=p2.y-h1;
        p2.x:=p2.x-h1;
        result:=dist1(p1,p2);
      end;
    if (w1=3) and (w2=6) then
      begin
        h1:=p2.y;
        p2.y:=p2.y-h1;
        p2.x:=p2.x+h1;
        result:=dist1(p1,p2);
      end;
    if (w1=4) and (w2=5) then
      begin
        h1:=w-p2.y;
        p2.y:=p2.y+h1;
        p2.x:=p2.x-h1;
        result:=dist1(p1,p2);
      end;
    if (w1=4) and (w2=6) then
      begin
        h1:=w-p2.y;
        p2.y:=p2.y+h1;
        p2.x:=p2.x+h1;
        result:=dist1(p1,p2);
      end;
    if (w1=1) and (w2=2) then
      begin
        h1:=p2.y;
        p2.y:=p2.y-h1-h-h1;
        p2.z:=0;
        d1:=dist1(p1,p2);
        p2.y:=h1;
        p2.z:=h;
        h1:=w-p2.y;
        p2.z:=0;
        p2.y:=p2.y+h1+h1+h;
        d2:=dist1(p1,p2);
        p2.y:=w-h1;
        p2.z:=h;
        h1:=p2.x;
        p2.x:=p2.x-h1-h1-h;
        p2.z:=0;
        d3:=dist1(p1,p2);
        p2.z:=h;
        p2.x:=h1;
        h1:=l-p2.x;
        p2.x:=p2.x+h1+h1+h;
        p2.z:=0;
        d4:=dist1(p1,p2);
        result:=(min(min(d3,d4),min(d1,d2)));
      end;
    if (w1=3) and (w2=4) then
      begin
        h1:=p2.z;
        p2.z:=p2.z-h1-w-h1;
        p2.y:=0;
        d1:=dist1(p1,p2);
        p2.z:=h1;
        p2.y:=w;
        h1:=h-p2.z;
        p2.y:=0;
        p2.z:=p2.z+h1+h1+w;
        d2:=dist1(p1,p2);
        p2.z:=h-h1;
        p2.y:=w;
        h1:=p2.x;
        p2.x:=p2.x-h1-h1-w;
        p2.y:=0;
        d3:=dist1(p1,p2);
        p2.y:=w;
        p2.x:=h1;
        h1:=l-p2.x;
        p2.x:=p2.x+h1+h1+w;
        p2.y:=0;
        d4:=dist1(p1,p2);
        result:=(min(min(d3,d4),min(d1,d2)));
      end;
    if (w1=5) and (w2=6) then
      begin
        h1:=p2.y;
        p2.y:=p2.y-l-h1-h1;
        p2.x:=0;
        d1:=dist1(p1,p2);
        p2.y:=p2.y+l+h1+h1;
        h1:=w-p2.y;
        p2.y:=p2.y+l+h1+h1;
        d2:=dist1(p1,p2);
        p2.y:=p2.y-l-h1-h1;
        h1:=p2.z;
        p2.z:=p2.z-l-h1-h1;
        d3:=dist1(p1,p2);
        p2.z:=p2.z+l+h1+h1;
        h1:=h-p2.z;
        p2.z:=p2.z+l+h1+h1;
        d4:=dist1(p1,p2);
        result:=(min(min(d3,d4),min(d1,d2)));
      end;
  end;

procedure solve;
var i,j:integer;
  begin
    for i:=1 to 6 do
    for j:=1 to 6 do
      begin
        if (can(i,p1) and can(j,p2)) then ans:=min(ans,check(i,j,p1,p2));
      end;
  end;

procedure done;
  begin
    write(ans:0:6);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
