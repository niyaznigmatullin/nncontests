program z_A;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y:integer;
      end;

const w:array[1..8] of integer=(1,2,4,8,16,32,64,128);
      z:array[1..8] of integer=(3,4,1,2,7,8,5,6);

var g,u:array[0..10] of boolean;
    p:array[0..1001] of TPoint;
    n:integer;
    a,b:array[0..1001,0..1001] of boolean;
    d,s:array[0..1001] of integer;

function check(e:integer):boolean;
var i:integer;
  begin
    for i:=1 to 8 do u[i]:=(w[i] and e)<>0;
    for i:=1 to n-1 do
      begin
        if u[1] then if (p[i].x=p[i+1].x) and (p[i].y<p[i+1].y) then continue;
        if u[2] then if (p[i].y=p[i+1].y) and (p[i].x<p[i+1].x) then continue;
        if u[3] then if (p[i].x=p[i+1].x) and (p[i].y>p[i+1].y) then continue;
        if u[4] then if (p[i].y=p[i+1].y) and (p[i].x>p[i+1].x) then continue;
        if u[5] then if (p[i+1].y-p[i].y=p[i+1].x-p[i].x) then continue;
        if u[6] then if (p[i].y-p[i+1].y=p[i+1].x-p[i].x) then continue;
        if u[7] then if (p[i].y-p[i+1].y=p[i].x-p[i+1].x) then continue;
        if u[8] then if (p[i+1].y-p[i].y=p[i].x-p[i+1].x) then continue;
        if u[1] and u[2] then if (p[i].x<p[i+1].x) and (p[i].y<p[i+1].y) then continue;
        if u[2] and u[3] then if (p[i].x<p[i+1].x) and (p[i].y>p[i+1].y) then continue;
        if u[3] and u[4] then if (p[i].x>p[i+1].x) and (p[i].y>p[i+1].y) then continue;
        if u[1] and u[4] then if (p[i].x>p[i+1].x) and (p[i].y<p[i+1].y) then continue;
        if u[5] and u[4] then if (p[i+1].y>=p[i].y) and (p[i+1].x-p[i].x<=p[i+1].y-p[i].y) then continue;
        if u[5] and u[2] then if (p[i+1].y>=p[i].y) and (p[i+1].x>=p[i].x) and (p[i+1].x-p[i].x>=p[i+1].y-p[i].y) then continue;
        if u[5] and u[1] then if (p[i+1].y>=p[i].y) and (p[i+1].x>=p[i].x) and (p[i+1].y-p[i].y>=p[i+1].x-p[i].x) then continue;
        if u[5] and u[3] then if (p[i+1].x>=p[i].x) and (p[i+1].x-p[i].x>=p[i+1].y-p[i].y) then continue;
        if u[6] and u[4] then if (p[i+1].y<=p[i].y) and (p[i+1].x-p[i].x<=p[i].y-p[i+1].y) then continue;
        if u[6] and u[2] then if (p[i+1].y<=p[i].y) and (p[i+1].x>=p[i].x) and (p[i+1].x-p[i].x>=p[i].y-p[i+1].y) then continue;
        if u[6] and u[1] then if (p[i+1].x>=p[i].x) and (p[i].y-p[i+1].y<=p[i+1].x-p[i].x) then continue;
        if u[6] and u[3] then if (p[i+1].y<=p[i].y) and (p[i+1].x>=p[i].x) and (p[i+1].x-p[i].x<=p[i].y-p[i+1].y) then continue;
        if u[7] and u[1] then if (p[i+1].x<=p[i].x) and (p[i].x-p[i+1].x>=p[i].y-p[i+1].y) then continue;
        if u[7] and u[2] then if (p[i+1].y<=p[i].y) and (p[i].x-p[i+1].x<=p[i].y-p[i+1].y) then continue;
        if u[7] and u[3] then if (p[i+1].x<=p[i].x) and (p[i+1].y<=p[i].y) and (p[i].x-p[i+1].x<=p[i].y-p[i+1].y) then continue;
        if u[7] and u[4] then if (p[i+1].x<=p[i].x) and (p[i+1].y<=p[i].y) and (p[i].x-p[i+1].x>=p[i].y-p[i+1].y) then continue;
        if u[8] and u[1] then if (p[i+1].x<=p[i].x) and (p[i+1].y>=p[i].y) and (p[i].x-p[i+1].x<=p[i+1].y-p[i].y) then continue;
        if u[8] and u[2] then if (p[i+1].y>=p[i].y) and (p[i].x-p[i+1].x<=p[i+1].y-p[i].y) then continue;
        if u[8] and u[3] then if (p[i+1].x<=p[i].x) and (p[i].x-p[i+1].x>=p[i+1].y-p[i].y) then continue;
        if u[8] and u[4] then if (p[i+1].x<=p[i].x) and (p[i+1].y>=p[i].y) and (p[i].x-p[i+1].x>=p[i+1].y-p[i].y) then continue;
        if u[5] and u[6] then if (p[i+1].y>=p[i].y) and (not odd(abs(p[i+1].x-p[i].x)+p[i+1].y-p[i].y)) then continue;
        if u[6] and u[7] then if (p[i+1].x>=p[i].x) and (not odd(abs(p[i+1].y-p[i].y)+p[i+1].x-p[i].x)) then continue;
        if u[7] and u[8] then if (p[i+1].y<=p[i].y) and (not odd(abs(p[i+1].x-p[i].x)+p[i].y-p[i+1].y)) then continue;
        if u[5] and u[8] then if (p[i+1].x<=p[i].x) and (not odd(abs(p[i+1].y-p[i].y)+p[i].x-p[i+1].x)) then continue;
        result:=false;
        exit;
      end;
    result:=true;
  end;

function find_edge(d1,d2:TPoint):boolean;
var i:integer;
  begin
    for i:=1 to 1 do
      begin
        if u[1] then if (d2.x=d1.x) and (d2.y<d1.y) then continue;
        if u[2] then if (d2.y=d1.y) and (d2.x<d1.x) then continue;
        if u[3] then if (d2.x=d1.x) and (d2.y>d1.y) then continue;
        if u[4] then if (d2.y=d1.y) and (d2.x>d1.x) then continue;
        if u[5] then if (d1.y-d2.y=d1.x-d2.x) and (d1.y-d2.y>0) and (d1.x-d2.x>0) then continue;
        if u[6] then if (d2.y-d1.y=d1.x-d2.x) and (d2.y-d1.y>0) and (d1.x-d2.x>0) then continue;
        if u[7] then if (d2.y-d1.y=d2.x-d1.x) and (d2.y-d1.y>0) and (d2.x-d1.x>0) then continue;
        if u[8] then if (d1.y-d2.y=d2.x-d1.x) and (d1.y-d2.y>0) and (d2.x-d1.x>0) then continue;
        if u[1] and u[2] then if (d2.x<d1.x) and (d2.y<d1.y) then continue;
        if u[2] and u[3] then if (d2.x<d1.x) and (d2.y>d1.y) then continue;
        if u[3] and u[4] then if (d2.x>d1.x) and (d2.y>d1.y) then continue;
        if u[1] and u[4] then if (d2.x>d1.x) and (d2.y<d1.y) then continue;
        if u[5] and u[4] then if (d1.y>=d2.y) and (d1.x-d2.x<=d1.y-d2.y) then continue;
        if u[5] and u[2] then if (d1.y>=d2.y) and (d1.x>=d2.x) and (d1.x-d2.x>=d1.y-d2.y) then continue;
        if u[5] and u[1] then if (d1.y>=d2.y) and (d1.x>=d2.x) and (d1.y-d2.y>=d1.x-d2.x) then continue;
        if u[5] and u[3] then if (d1.x>=d2.x) and (d1.x-d2.x>=d1.y-d2.y) then continue;
        if u[6] and u[4] then if (d1.y<=d2.y) and (d1.x-d2.x<=d2.y-d1.y) then continue;
        if u[6] and u[2] then if (d1.y<=d2.y) and (d1.x>=d2.x) and (d1.x-d2.x>=d2.y-d1.y) then continue;
        if u[6] and u[1] then if (d1.x>=d2.x) and (d2.y-d1.y<=d1.x-d2.x) then continue;
        if u[6] and u[3] then if (d1.y<=d2.y) and (d1.x>=d2.x) and (d1.x-d2.x<=d2.y-d1.y) then continue;
        if u[7] and u[1] then if (d1.x<=d2.x) and (d2.x-d1.x>=d2.y-d1.y) then continue;
        if u[7] and u[2] then if (d1.y<=d2.y) and (d2.x-d1.x<=d2.y-d1.y) then continue;
        if u[7] and u[3] then if (d1.x<=d2.x) and (d1.y<=d2.y) and (d2.x-d1.x<=d2.y-d1.y) then continue;
        if u[7] and u[4] then if (d1.x<=d2.x) and (d1.y<=d2.y) and (d2.x-d1.x>=d2.y-d1.y) then continue;
        if u[8] and u[1] then if (d1.x<=d2.x) and (d1.y>=d2.y) and (d2.x-d1.x<=d1.y-d2.y) then continue;
        if u[8] and u[2] then if (d1.y>=d2.y) and (d2.x-d1.x<=d1.y-d2.y) then continue;
        if u[8] and u[3] then if (d1.x<=d2.x) and (d2.x-d1.x>=d1.y-d2.y) then continue;
        if u[8] and u[4] then if (d1.x<=d2.x) and (d1.y>=d2.y) and (d2.x-d1.x>=d1.y-d2.y) then continue;
        if u[5] and u[6] then if (d1.y>=d2.y) and (not odd(abs(d1.x-d2.x)+d1.y-d2.y)) then continue;
        if u[6] and u[7] then if (d1.x>=d2.x) and (not odd(abs(d1.y-d2.y)+d1.x-d2.x)) then continue;
        if u[7] and u[8] then if (d1.y<=d2.y) and (not odd(abs(d1.x-d2.x)+d2.y-d1.y)) then continue;
        if u[5] and u[8] then if (d1.x<=d2.x) and (not odd(abs(d1.y-d2.y)+d2.x-d1.x)) then continue;
        result:=false;
        exit;
      end;
    result:=true;
  end;

function find_edge2(d1,d2:TPoint):boolean;
var i:integer;
  begin
    for i:=1 to 1 do
      begin
        if g[1] then if (d2.x=d1.x) and (d2.y<d1.y) then continue;
        if g[2] then if (d2.y=d1.y) and (d2.x<d1.x) then continue;
        if g[3] then if (d2.x=d1.x) and (d2.y>d1.y) then continue;
        if g[4] then if (d2.y=d1.y) and (d2.x>d1.x) then continue;
        if g[5] then if (d1.y-d2.y=d1.x-d2.x) and (d1.y-d2.y>0) and (d1.x-d2.x>0) then continue;
        if g[6] then if (d2.y-d1.y=d1.x-d2.x) and (d2.y-d1.y>0) and (d1.x-d2.x>0) then continue;
        if g[7] then if (d2.y-d1.y=d2.x-d1.x) and (d2.y-d1.y>0) and (d2.x-d1.x>0) then continue;
        if g[8] then if (d1.y-d2.y=d2.x-d1.x) and (d1.y-d2.y>0) and (d2.x-d1.x>0) then continue;
        if g[1] and g[2] then if (d2.x<d1.x) and (d2.y<d1.y) then continue;
        if g[2] and g[3] then if (d2.x<d1.x) and (d2.y>d1.y) then continue;
        if g[3] and g[4] then if (d2.x>d1.x) and (d2.y>d1.y) then continue;
        if g[1] and g[4] then if (d2.x>d1.x) and (d2.y<d1.y) then continue;
        if g[5] and g[4] then if (d1.y>=d2.y) and (d1.x-d2.x<=d1.y-d2.y) then continue;
        if g[5] and g[2] then if (d1.y>=d2.y) and (d1.x>=d2.x) and (d1.x-d2.x>=d1.y-d2.y) then continue;
        if g[5] and g[1] then if (d1.y>=d2.y) and (d1.x>=d2.x) and (d1.y-d2.y>=d1.x-d2.x) then continue;
        if g[5] and g[3] then if (d1.x>=d2.x) and (d1.x-d2.x>=d1.y-d2.y) then continue;
        if g[6] and g[4] then if (d1.y<=d2.y) and (d1.x-d2.x<=d2.y-d1.y) then continue;
        if g[6] and g[2] then if (d1.y<=d2.y) and (d1.x>=d2.x) and (d1.x-d2.x>=d2.y-d1.y) then continue;
        if g[6] and g[1] then if (d1.x>=d2.x) and (d2.y-d1.y<=d1.x-d2.x) then continue;
        if g[6] and g[3] then if (d1.y<=d2.y) and (d1.x>=d2.x) and (d1.x-d2.x<=d2.y-d1.y) then continue;
        if g[7] and g[1] then if (d1.x<=d2.x) and (d2.x-d1.x>=d2.y-d1.y) then continue;
        if g[7] and g[2] then if (d1.y<=d2.y) and (d2.x-d1.x<=d2.y-d1.y) then continue;
        if g[7] and g[3] then if (d1.x<=d2.x) and (d1.y<=d2.y) and (d2.x-d1.x<=d2.y-d1.y) then continue;
        if g[7] and g[4] then if (d1.x<=d2.x) and (d1.y<=d2.y) and (d2.x-d1.x>=d2.y-d1.y) then continue;
        if g[8] and g[1] then if (d1.x<=d2.x) and (d1.y>=d2.y) and (d2.x-d1.x<=d1.y-d2.y) then continue;
        if g[8] and g[2] then if (d1.y>=d2.y) and (d2.x-d1.x<=d1.y-d2.y) then continue;
        if g[8] and g[3] then if (d1.x<=d2.x) and (d2.x-d1.x>=d1.y-d2.y) then continue;
        if g[8] and g[4] then if (d1.x<=d2.x) and (d1.y>=d2.y) and (d2.x-d1.x>=d1.y-d2.y) then continue;
        if g[5] and g[6] then if (d1.y>=d2.y) and (not odd(abs(d1.x-d2.x)+d1.y-d2.y)) then continue;
        if g[6] and g[7] then if (d1.x>=d2.x) and (not odd(abs(d1.y-d2.y)+d1.x-d2.x)) then continue;
        if g[7] and g[8] then if (d1.y<=d2.y) and (not odd(abs(d1.x-d2.x)+d2.y-d1.y)) then continue;
        if g[5] and g[8] then if (d1.x<=d2.x) and (not odd(abs(d1.y-d2.y)+d2.x-d1.x)) then continue;
        result:=false;
        exit;
      end;
    result:=true;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'game.in');
    rewrite(output,'game.out');
    read(n);
    for i:=1 to n do
      begin
        read(p[i].x,p[i].y);
      end;
    close(input);
  end;

procedure solve;
var i,pp,q,j,k,miny,maxy:integer; ok1,ok2,ok3,ok4:boolean;
  begin
    ok1:=true;
    ok2:=true;
    ok3:=true;
    ok4:=true;
    for i:=1 to n do
      begin
        ok1:=ok1 and (p[1].x=p[i].x);
        ok2:=ok2 and (p[1].y=p[i].y);
        ok3:=ok3 and (p[i].x-p[1].x=p[i].y-p[1].y);
        ok4:=ok4 and (p[i].x-p[1].x=p[1].y-p[i].y);
      end;
    if ok1 or ok2 or ok3 or ok4 then
      begin
        write(7);
        close(output);
        halt(0);
      end;
    for i:=1 to 255 do
      begin
        for j:=1 to 8 do u[j]:=(w[j] and i)<>0;
        fillchar(a,sizeof(a),false);
        fillchar(b,sizeof(b),false);
        for j:=1 to n do
        for k:=1 to n do if j<>k then
          a[j,k]:=find_edge(p[k],p[j]);
        for j:=1 to 8 do g[j]:=(w[z[j]] and i)<>0;
        for j:=1 to n do
        for k:=1 to n do if j<>k then b[j,k]:=find_edge2(p[k],p[j]);
        for j:=1 to n do if a[1,j] then d[j]:=1 else d[j]:=0;
        for j:=1 to n do
        for k:=1 to n do if a[j,k] then if d[j]+1>d[k] then d[k]:=d[j]+1;
        for j:=1 to n do if b[1,j] then s[j]:=1 else s[j]:=0;
        for j:=1 to n do
        for k:=1 to n do if b[j,k] then if s[j]+1>s[k] then s[k]:=s[j]+1;
        miny:=0;
        maxy:=0;
        for j:=1 to n do
          begin
            if miny<d[j] then miny:=d[j];
            if maxy<s[j] then maxy:=s[j];
          end;
        if miny+maxy=n then
          begin
            write(6);
            close(output);
            halt(0);
          end;
        {if check(i) then
          begin
            pp:=1;
            q:=0;
            while pp<=i do
              begin
                if pp and i<>0 then inc(q);
                pp:=pp*2;
              end;
            if 8-q>maxy then
              maxy:=8-q;
          end;}
      end;
  end;

procedure done;
  begin
    write(5);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
