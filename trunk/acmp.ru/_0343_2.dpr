program _0343_2;

{$APPTYPE CONSOLE}
{$o-}
uses
  SysUtils;
var
a:array[-10..1000,-10..1000]of longint;
i,j,n,m,x1,y1,x2,x3,y2,y3,k,tip,ans,x,y:longint;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 read(n,m);
 for i:=-1 to n+2 do
 for j:=-1 to m+2 do
  a[i,j]:=-1;
 for i:=1 to n do
 for j:=1 to m do
  a[i,j]:=0;
 read(k);
 for i:=1 to k do begin
  read(tip,x,y);
  if tip =1 then  begin
    x1:=x;
    y1:=y+1;
    x2:=x+1;
    y2:=y+1;
    x3:=x+1;
    y3:=y;
  end;
  if tip =2 then  begin
    x1:=x;
    y1:=y;
    x2:=x+1;
    y2:=y+1;
    x3:=x+1;
    y3:=y;
  end;
  if tip =3 then  begin
    x1:=x;
    y1:=y+1;
    x2:=x+1;
    y2:=y+1;
    x3:=x;
    y3:=y;
  end;
  if tip =4 then  begin
    x1:=x;
    y1:=y+1;
    x2:=x;
    y2:=y;
    x3:=x+1;
    y3:=y;
  end;
  if a[x1,y1]=0 then
  if a[x2,y2]=0 then
  if a[x3,y3]=0 then
  begin a[x1,y1]:=1; a[x2,y2]:=1; a[x3,y3]:=1; inc(ans,3); end;
 end;
 write(ans);
end. 
