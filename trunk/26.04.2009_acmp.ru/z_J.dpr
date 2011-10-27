program z_J;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

type TPoint=record
      x,y:integer;
      end;

const eps=1e-9;

var n:integer;
    p:array[0..1500] of TPoint;
    maxx,maxy,minx,miny:integer;
    u:array[0..1500] of boolean;
    a:array[0..1500,0..1500] of boolean;
    pai:array[0..1500] of byte;
    d:array[0..1200*1200] of integer;
    dis:array[0..1200,0..1200] of integer;

function gr(x,y:real48):boolean;
  begin
    result:=x-y>eps
  end;

function eq(x,y:real48):boolean;
  begin
    result:=abs(x-y)<eps;
  end;

procedure sort(l,r:integer);
var i,j:integer; x,y:integer;
  begin
    if (l>=r) then exit;
    i:=l;
    j:=r;
    x:=d[(l+r) shr 1];
    repeat
      while (x>d[i]) do inc(i);
      while (x<d[j]) do dec(j);
      if (i<=j) then
        begin
          y:=d[i];
          d[i]:=d[j];
          d[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    sort(i,r);
    sort(l,j);
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    maxx:=-maxint;
    minx:=maxint;
    maxy:=-maxint;
    miny:=maxint;
    for i:=1 to n do
      begin
        read(p[i].x,p[i].y);
        maxx:=max(p[i].x,maxx);
        minx:=min(p[i].x,minx);
        maxy:=max(p[i].y,maxy);
        miny:=min(p[i].y,miny);
      end;
    close(input);
  end;

function dfs(x,k:integer):boolean;
var i:integer;
  begin
    u[x]:=true;
    pai[x]:=k;
    result:=true;
    for i:=1 to n do if (a[x][i]) then
      begin
        if (not u[i]) then if (not dfs(i,3-k)) then
          begin
            result:=false;
            exit;
          end;
        if (pai[x]=pai[i]) then
          begin
            result:=false;
            exit;
          end;
      end;
  end;

function dist(p1,p2:TPoint):real48;
  begin
    result:=sqrt(sqr(p1.x-p2.x)+sqr(p1.y-p2.y));
  end;

function check(x:integer):boolean;
var i,j:integer; ret:boolean;
  begin
    fillchar(a,sizeof(a),false);
    fillchar(u,sizeof(u),false);
    for i:=1 to n-1 do
    for j:=i+1 to n do if (i<>j) and (x>dis[i][j]) then
      begin
        a[i][j]:=true;
        a[j][i]:=true;
      end;
    ret:=true;
    for i:=1 to n do if (not u[i]) then ret:=ret and dfs(i,1);
    result:=ret;
  end;

procedure solve;
var l,r,mid:integer; i,j:integer; kol,kol2:integer;
  begin
    kol:=0;
    for i:=1 to n-1 do
    for j:=i+1 to n do if (i<>j) then
      begin
        dis[i][j]:=sqr(p[i].x-p[j].x)+sqr(p[i].y-p[j].y);
        dis[j][i]:=dis[i][j];
        inc(kol);
        d[kol]:=sqr(p[i].x-p[j].x)+sqr(p[i].y-p[j].y);
      end;
    sort(1,kol);
    kol2:=1;
    for i:=2 to kol do if d[i]<>d[i-1] then
      begin
        inc(kol2);
        d[kol2]:=d[i];
      end;
    l:=1;
    r:=kol2;
    while (l<>r) do
      begin
        mid:=(l+r) div 2+1;
        if (check(d[mid])) then l:=mid else r:=mid-1;
      end;
    check(d[l]);
    writeln(sqrt(d[l])/2:0:15);
    for i:=1 to n do write(pai[i],' ');
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
end.
