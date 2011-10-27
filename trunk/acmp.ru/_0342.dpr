program _0342;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y:extended;
      end;
     TVect=record
      a,b:extended;
      end;
     TLine=record
      a,b,c:extended;
      end;

const eps=1e-15;

var p:array[0..10] of TPoint;
    t:array[0..10] of TLine;
    p0:TPoint;
    n:integer;
    miny:extended;

function int_line(t1,t2:TLine):TPoint;
var z:extended;
  begin
    z:=(t1.a*t2.b-t2.a*t1.b);
    result.y:=-(t1.a*t2.c-t2.a*t1.c)/z;
    result.x:=(t1.b*t2.c-t2.b*t1.c)/z;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(p[i].x,p[i].y);
    close(input);
  end;

function make_vect(s1,s2:TPoint):TVect;
  begin
    result.a:=s2.x-s1.x;
    result.b:=s2.y-s1.y;
  end;

function add_vects(v1,v2:TVect):TVect;
  begin
    result.a:=v1.a+v2.a;
    result.b:=v1.b+v2.b;
  end;

function get_bisect(s1,s2,s3:TPoint):TLine;
var v1,v2,v3:TVect; z:extended;
  begin
    v1:=make_vect(s1,s2);
    v2:=make_vect(s1,s3);
    z:=sqrt(v1.a*v1.a+v1.b*v1.b);
    v1.a:=v1.a/z;
    v1.b:=v1.b/z;
    z:=sqrt(v2.a*v2.a+v2.b*v2.b);
    v2.a:=v2.a/z;
    v2.b:=v2.b/z;
    v3:=add_vects(v1,v2);
    result.a:=-v3.b;
    result.b:=v3.a;
    result.c:=-(result.a*s1.x+result.b*s1.y);
  end;

function get_line(s1,s2:TPoint):TLine;
  begin
    result.a:=s2.y-s1.y;
    result.b:=s1.x-s2.x;
    result.c:=-(s1.x*result.a+s1.y*result.b);
  end;

procedure solve;
var i:integer; ok:boolean; tt:TLine; r:extended;
  begin
    p[0]:=p[n];
    p[n+1]:=p[1];
    for i:=1 to n do
      begin
        t[i]:=get_bisect(p[i],p[i+1],p[i-1]);
      end;
    p0:=int_line(t[1],t[2]);
    ok:=true;
    for i:=1 to n do
      begin
        ok:=ok and (abs(t[i].a*p0.x+t[i].b*p0.y+t[i].c)<=eps);
      end;
    if not ok then
      begin
        write('NO');
        close(output);
        halt(0);
      end;
    miny:=1e10;
    for i:=1 to n-1 do
      begin
        tt:=get_line(p[i],p[i+1]);
        r:=abs(tt.a*p0.x+tt.b*p0.y+tt.c)/sqrt(tt.a*tt.a+tt.b*tt.b);
        if r<miny then miny:=r;
      end;
  end;

procedure done;
  begin
    writeln('YES');
    if abs(p0.x)<=eps then p0.x:=0;
    if abs(p0.y)<=eps then p0.y:=0;
    write(p0.x:0:3,' ',p0.y:0:3,' ',miny:0:3);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
