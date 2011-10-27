{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program z_374;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

type TPoint=record
      x,y:extended;
      end;

const eps=1e-20;

var p,w:array[0..1001] of TPoint;
    ang:array[0..1001] of extended;
    miny,kol,n:integer;
    per:extended;

procedure sort(l,r:integer);
var x,y:extended; m,i,j:integer; e:TPoint;
  begin
    m:=l+random(r-l+1);
    i:=l;
    j:=r;
    x:=ang[m];
    repeat
      while ang[i]<x do inc(i); { a[i] > x  - сортировка по убыванию}
      while x<ang[j] do dec(j); { x > a[j]  - сортировка по убыванию}
      if i<=j then
        begin
          y:=ang[i]; ang[i]:=ang[j]; ang[j]:=y;
          e:=p[i];
          p[i]:=p[j];
          p[j]:=e;
          inc(i);
          dec(j);
        end;
    until i>j;
    if l<j then sort(l,j);
    if i<r then sort(i,r);
  end;

function cosine(s1,s2,s3:TPoint):extended;
  begin
    result:=(s1.x-s2.x)*(s3.x-s2.x)+(s1.y-s2.y)*(s3.y-s2.y);
    result:=result/sqrt(sqr(s1.x-s2.x)+sqr(s1.y-s2.y));
    result:=result/sqrt(sqr(s3.x-s2.x)+sqr(s3.y-s2.y));
  end;

function vect(s1,s2,s3:TPoint):extended;
  begin
    result:=(s1.x-s2.x)*(s3.y-s2.y)-(s3.x-s2.x)*(s1.y-s2.y);
  end;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do
      begin
        read(p[i].x,p[i].y);
        if (miny=0) or (p[i].y<p[miny].y) or ((p[i].y=p[miny].y) and (p[i].x<p[miny].x)) then miny:=i;
      end;
    close(Input);
  end;

procedure solve;
var i:integer; e:TPoint; r:extended;
  begin
    e:=p[1];
    p[1]:=p[miny];
    p[miny]:=e;
    for i:=2 to n do
      begin
        ang[i]:=-arctan2(p[i].x-p[1].x,p[i].y-p[1].y);
      end;
    sort(2,n);
    p[n+1]:=p[1];
    kol:=2;
    w[1]:=p[1];
    w[2]:=p[2];
    for i:=3 to n+1 do
      begin
        r:=cosine(w[kol-1],w[kol],p[i]);
        if abs(r+1)<=eps then w[kol]:=p[i] else if abs(r-1)<=eps then continue else
          begin
            while vect(w[kol-1],w[kol],p[i])>eps do dec(kol);
            inc(kol);
            w[kol]:=p[i];
          end;
      end;
    per:=0;
    for i:=1 to kol-1 do per:=per+{sqrt(sqr(w[i].x-w[i+1].x)+sqr(w[i].y-w[i+1].y));}(w[i].x+w[i+1].x)*(w[i+1].y-w[i].y);
    per:=abs(per*0.5);
  end;

procedure done;
  begin
    write(per:0:0);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
