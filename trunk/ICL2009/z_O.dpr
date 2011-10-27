program z_O;

{$APPTYPE CONSOLE}

uses
  SysUtils;

const eps=1e-12;

type TPPoint=record x,y,z:extended; end;
     TPoint=record x,y:extended; end;
     TLine=record a,b,c:extended; end;
     mas=array[1..10000] of integer;
var
a:array[1..10000] of TPoint;
b:array[1..10000] of TPPoint;
ang,len:array[1..10000] of extended;
c:mas;
n,kol:integer;


procedure init;
var i:integer;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 read(n);
 for i:=1 to n do
  read(b[i].x,b[i].y,b[i].z);
end;

function dist(s1,s2:TPoint):extended;
begin
  result:=sqrt(sqr(s1.x-s2.x)+sqr(s1.y-s2.y));
end;

function vect(s1,s2,s3:TPoint):extended;
  begin
    result:=(s1.x-s2.x)*(s3.y-s2.y)-(s3.x-s2.x)*(s1.y-s2.y);
  end;

function scal(s1,s2,s3:TPoint):extended;
  begin
    result:=((s1.x-s2.x)*(s3.x-s2.x)+(s1.y-s2.y)*(s3.y-s2.y));
    result:=result/sqrt(sqr(s1.x-s2.x)+sqr(s1.y-s2.y));
    result:=result/sqrt(sqr(s3.x-s2.x)+sqr(s3.y-s2.y));
  end;  

procedure sort (l,r:integer);
var i,j,w:integer; x,y,e:extended; t:TPoint;
begin
  i:=l; j:=r;
  x:=ang[(l+r) div 2];
  y:=len[(l+r) div 2];
  repeat
   while (ang[i]>x) or ((ang[i]=x) and (len[i]>y)) do inc(i);
   while (ang[j]<x) or ((ang[j]=x) and (len[j]<y)) do dec(j);
    if i<=j then
     begin
       t:=a[i]; a[i]:=a[j]; a[j]:=t;
       e:=ang[i]; ang[i]:=ang[j]; ang[j]:=e;
       e:=len[i]; len[i]:=len[j]; len[j]:=e;
       w:=c[i]; c[i]:=c[j]; c[j]:=w;
       inc(i); dec(j);
     end;
  until i>j;
  if i<r then sort(i,r);
  if l<j then sort(l,j);
end;

function build_shell:mas;
var
 bb:array[1..10000] of TPoint;
 min,i:integer;
 e:TPoint;
begin
  min:=1;
  for i:=1 to n do c[i]:=i;
  for i:=2 to n do
   if (a[i].y<a[min].y) or ( (a[i].y=a[min].y) and (a[i].x<a[min].x) ) then min:=i;
  e:=a[1]; a[1]:=a[min]; a[min]:=e;
  c[1]:=min; c[min]:=1;

  for i:=2 to n do len[i]:=dist(a[1],a[i]);
  for i:=2 to n do ang[i]:=(a[i].x-a[1].x)/len[i];

  sort(2,n);   fillchar(result,sizeof(result),0);
  bb[1]:=a[1];  result[1]:=c[1]; result[2]:=c[2];
  bb[2]:=a[2];
  a[n+1]:=a[1]; kol:=2;
   for i:=3 to n do
    begin
      while vect(bb[kol-1],bb[kol],a[i])>eps do dec(kol);
       if abs(scal(bb[kol-1],bb[kol],a[i])+1)<eps then
        begin
          bb[kol]:=a[i]; result[kol]:=c[i];
        end else
      if abs(scal(bb[kol-1],bb[kol],a[i])-1)>eps then
       begin
         inc(kol);
         bb[kol]:=a[i]; result[kol]:=c[i];
       end;
    end;
end;

function Get_line(x1,y1,x2,y2:extended):TLine;
var l:TLine;
begin
 l.a:=y2-y1;
 l.b:=x1-x2;
 l.c:=-x1*l.a-y1*l.b;
 result:=l;
end;

function dist3(s1,s2:TPPoint):extended;
begin
  result:=sqrt(sqr(s1.x-s2.x)+sqr(s1.y-s2.y)+sqr(s1.z-s2.z));
end;

function find_s(s1,s2,s3:TPPoint):extended;
var d1,d2,d3,p:extended;
begin
  d1:=dist3(s1,s2);
  d2:=dist3(s2,s3);
  d3:=dist3(s3,s1);
  p:=(d1+d2+d3)/2;
  result:=sqrt(p*(p-d1)*(p-d2)*(p-d3));
end;

procedure solve;
var
 l:TLine; ok:boolean; i:integer;
 e,per,plosh:extended;
 num:mas;
begin
  l:=Get_line(b[1].x,b[1].y,b[3].x,b[3].y);
ok:=false;
  for i:=1 to n do
   if abs(l.a*b[i].x+l.b*b[i].y+l.c)>eps then begin ok:=true; break; end;

if not ok then
   for i:=1 to n do
    begin e:=b[i].y; b[i].y:=b[i].x; b[i].z:=e; end;

for i:=1 to n do
 begin  a[i].x:=b[i].x; a[i].y:=b[i].y; end;
 num:=build_shell;
 per:=0;     plosh:=0;
 for i:=1 to kol-2 do
  plosh:=plosh+find_s(b[num[i]],b[num[i+1]],b[num[i+2]]);

 for i:=1 to kol-1 do
  per:=per+dist3(b[num[i]],b[num[i+1]]);
  per:=per+dist3(b[num[1]],b[num[kol]]);
  write(plosh:0:6,' ',per:0:6);  

end;

begin
  init;
  solve;
end. 
