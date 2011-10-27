{$APPTYPE CONSOLE}
{$o-}
uses
  SysUtils;
const di:array[1..4]of longint=(-1,0,1,0);
const dj:array[1..4]of longint=(0,1,0,-1);
var
a,b:array[0..101,0..101]of longint;
ch:char;
n,m,i,j,tekx,teky,newx,newy,min,newdir,kolnew,dir,i0,j0,ans:longint;
u:boolean;
new:array[1..3,1..4]of longint;
procedure dfs(x,y:longint);
var i:longint;
begin
 for i:=1 to 4 do
  if (b[x+di[i],y+dj[i]]=0)and(a[x+di[i],y+dj[i]]=0) then begin
      b[x+di[i],y+dj[i]]:=1;
      dfs(x+di[i],y+dj[i]);
  end;
end;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 readln(n,m);
 for i:=1 to n do begin
  for j:=1 to m do begin
   read(ch);
   if ch='@' then a[i,j]:=1 else a[i,j]:=0;
  end;
  readln;
 end;
 fillchar(b,sizeof(b),0);
 b[2,2]:=1;
 dfs(2,2);
 if b[n-1,m-1]=0 then begin write(-1); close(output); halt(0); end;
 fillchar(b,sizeof(b),0);
 tekx:=2;
 teky:=2;
 dir:=1;
 while true do begin
   inc(b[tekx,teky]);
   kolnew:=0;
   min:=maxlongint;
   for i:=1 to 4 do begin
    i0:=tekx+di[i];
    j0:=teky+dj[i];
    if a[i0,j0]=0 then if b[i0,j0]<min then min:=b[i0,j0];
   end;
   for i:=1 to 4 do begin
    i0:=tekx+di[i];
    j0:=teky+dj[i];
    if b[i0,j0]=min then
    if a[i0,j0]=0 then
                        begin
                          inc(kolnew);
                          new[1,kolnew]:=i0;
                          new[2,kolnew]:=j0;
                          new[3,kolnew]:=i;
                        end;
   end;
   u:=false;
   for i:=1 to kolnew do
    if new[3,i]=dir then  begin u:=true; newx:=new[1,i]; newy:=new[2,i]; newdir:=dir; end;
   if not u then begin
     for i:=1 to kolnew do
      if new[3,i]=3  then begin u:=true; newx:=new[1,i]; newy:=new[2,i]; newdir:=3; end;
     if not u then
     for i:=1 to kolnew do
      if new[3,i]=2  then begin u:=true; newx:=new[1,i]; newy:=new[2,i]; newdir:=2; end;
     if not u then
     for i:=1 to kolnew do
      if new[3,i]=1  then begin u:=true; newx:=new[1,i]; newy:=new[2,i]; newdir:=1; end;
     if not u then
     for i:=1 to kolnew do
      if new[3,i]=4  then begin u:=true; newx:=new[1,i]; newy:=new[2,i]; newdir:=4; end;
   end;
   tekx:=newx;
   teky:=newy;
   dir:=newdir;
   inc(ans);
   if (tekx=n-1)and(teky=m-1) then break;
 end;
 write(ans);
end.  
