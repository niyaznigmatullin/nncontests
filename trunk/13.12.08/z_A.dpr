program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y:int64;
      end;
     TVect=record
      a,b:int64;
      end;

var p:array[0..2001] of TPoint;
    n:integer;
    pp:TPoint;
    ok:boolean;

function search(s1:TPoint):boolean;
var lo,hi,m:integer;
  begin
    lo:=1;
    hi:=n;
    while lo<hi do
      begin
        m:=(lo+hi) div 2;
        if (p[m].x>s1.x) or ((p[m].x=s1.x) and (p[m].y>s1.y)) then
          begin
            hi:=m-1;
          end else
        if (p[m].x=s1.x) and (p[m].y=s1.y) then
          begin
            result:=true;
            exit;
          end else lo:=m+1;
      end;
    if lo=hi then result:=(p[lo].x=s1.x) and (p[lo].y=s1.y) else result:=false;
  end;

procedure sort(l,r:integer);
var i,j:integer; x,y:TPoint;
  begin
    i:=l;
    j:=r;
    x:=p[random(r-l+1)+l];
    repeat
      while (p[i].x<x.x) or ((p[i].x=x.x) and (p[i].y<x.y)) do inc(i);
      while (p[j].x>x.x) or ((p[j].x=x.x) and (p[j].y>x.y)) do dec(j);
      if i<=j then
        begin
          y:=p[i];
          p[i]:=p[j];
          p[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    if i<r then sort(i,r);
    if l<j then sort(l,j);
  end;

function get_vect(s1,s2:TPoint):TVect;
  begin
    result.a:=s2.x-s1.x;
    result.b:=s2.y-s1.y;
  end;

function get_per1(s1:TVect):TVect;
  begin
    result.a:=-s1.b;
    result.b:=s1.a;
  end;

function get_per2(s1:TVect):TVect;
  begin
    result.a:=s1.b;
    result.b:=-s1.a;
  end;

function inv_vect(s1:TVect):TVect;
  begin
    result.a:=-s1.a;
    result.b:=-s1.b;
  end;

function add_vect(s1:TPoint; s2:TVect):TPoint;
  begin
    result.x:=s1.x+s2.a;
    result.y:=s1.y+s2.b;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'collider2.in');
    rewrite(output,'collider2.out');
    read(n);
    for i:=1 to n do read(p[i].x,p[i].y);
    close(input);
  end;

procedure solve;
var i,j:integer; v0,v1,v2,v3:TVect; p1,p2:TPoint; ok1,ok2:boolean;
  begin
    if n=1 then
      begin
        writeln(3);
        writeln(p[1].x-1,' ',p[1].y-1);
        writeln(p[1].x,' ',p[1].y-1);
        writeln(p[1].x-1,' ',p[1].y);
        close(output);
        halt(0);
      end;
    sort(1,n);
    for i:=1 to n-1 do
    for j:=i+1 to n do
      begin
        v0:=get_vect(p[i],p[j]);
        v1:=get_per1(v0);
        v2:=get_per2(v0);
        v3:=inv_vect(v0);
        p1:=add_vect(p[i],v1);
        p2:=add_vect(p[j],v1);
        ok1:=search(p1);
        ok2:=search(p2);
        if ok1 and ok2 then
          begin
            write(0);
            close(output);
            halt(0);
          end else
        if ok1 then
          begin
            pp:=add_vect(p1,v0);
            ok:=true;
          end else
        if ok2 then
          begin
            pp:=add_vect(p2,v3);
            ok:=true;
          end;
        p1:=add_vect(p[i],v2);
        p2:=add_vect(p[j],v2);
        ok1:=search(p1);
        ok2:=search(p2);
        if ok1 and ok2 then
          begin
            write(0);
            close(output);
            halt(0);
          end else
        if ok1 then
          begin
            pp:=add_vect(p1,v0);
            ok:=true;
          end else
        if ok2 then
          begin
            pp:=add_vect(p2,v3);
            ok:=true;
          end;
      end;
  end;

procedure done;
var v1:TVect;
  begin
    if ok then
      begin
        writeln(1);
        write(pp.x,' ',pp.y);
        close(output);
        halt(0);
      end else
      begin
        writeln(2);
        v1:=get_per1(get_vect(p[1],p[2]));
        writeln(p[1].x+v1.a,' ',p[1].y+v1.b);
        writeln(p[2].x+v1.a,' ',p[2].y+v1.b);
        close(output);
        halt(0);
      end;
  end;

begin
  init;
  solve;
  done;
end.
