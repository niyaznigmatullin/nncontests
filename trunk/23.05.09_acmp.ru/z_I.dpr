program z_B;
{$APPTYPE CONSOLE}
uses
  SysUtils;

type TPoint=record
      x,y:int64;
      end;

var t,e,p:array[0..1501] of TPoint;
    dist:array[0..1501] of int64;
    n:integer;
    q,w:int64;

function find(s1,s2:int64):boolean;
var l,r,mid:integer;
  begin
    l:=1;
    r:=n;
    result:=true;
    while l<=r do
      begin
        mid:=(l+r) div 2;
        if (e[mid].x=s1) and (e[mid].y=s2) then exit else
        if (e[mid].x<s1) or ((e[mid].x=s1) and (e[mid].y<s2)) then l:=mid+1 else r:=mid-1;
      end;
    result:=false;
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

procedure sortd(l,r:integer);
var i,j:integer; x,y:int64; yy:TPoint;
  begin
    i:=l;
    j:=r;
    x:=dist[random(r-l+1)+l];
    repeat
      while (x>dist[i]) do inc(i);
      while (x<dist[j]) do dec(j);
      if (i<=j) then
        begin
          y:=dist[i];
          dist[i]:=dist[j];
          dist[j]:=y;
          yy:=p[i];
          p[i]:=p[j];
          p[j]:=yy;
          inc(i);
          dec(j);
        end;
    until i>j;
    if (i<r) then sortd(i,r);
    if (l<j) then sortd(l,j);
  end;

procedure sort(l,r:integer);
var i,j:integer; x,y:TPoint;
  begin
    if L>=R then exit;
    i:=l;
    j:=r;
    x:=e[random(r-l+1)+l];
    repeat
      while (x.x>e[i].x) or ((x.x=e[i].x) and (x.y>e[i].y)) do inc(i);
      while (x.x<e[j].x) or ((x.x=e[j].x) and (x.y<e[j].y)) do dec(j);
      if (i<=j) then
        begin
          y:=e[i];
          e[i]:=e[j];
          e[j]:=y;
          inc(i);
          dec(j);
        end;
    until i>j;
    sort(i,r);
    sort(l,j);
  end;

procedure solve;
var i,j,k:integer; dx,dy,r:int64;
  begin
    e:=p;
    t:=p;
    sort(1,n);
    q:=0;
    w:=0;
    for i:=1 to n do
      begin
        for j:=1 to n do dist[j]:=int64(t[i].x-p[j].x)*(t[i].x-p[j].x)+int64(t[i].y-p[j].y)*(t[i].y-p[j].y);
        sortd(1,n);
        j:=1;
        while j<=n do
          begin
            k:=j;
            while (k<n) and (dist[k]=dist[k+1]) do
              begin
                inc(k);
              end;
            r:=k-j+1;
            q:=q+((r*(r-1)) div 2);
            j:=k+1;
          end;
        for j:=1 to n do
          begin
            dx:=t[i].x-p[j].x;
            dy:=t[i].y-p[j].y;
            if (dx=0) and (dy=0) then continue;
            if (find(t[i].x+dx,t[i].y+dy)) then
              w:=w+1;
          end;
      end;
  end;

procedure done;
  begin
    write(q-w div 2);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
