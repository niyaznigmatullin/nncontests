program z_360;

{$APPTYPE CONSOLE}

uses
  SysUtils;
var
a:array[1..2000,1..2000]of smallint;
max:smallint;
i,j,n:smallint;
s:string;
label lb1;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 read(n);
 max:=-301;
 for i:=1 to n do
 for j:=1 to n do read(a[i,j]);
 for i:=2 to n do
 for j:=2 to n do begin
   if i>=3 then if a[i,j]+a[i-1,j]+a[i-2,j]>max then max:=a[i,j]+a[i-1,j]+a[i-2,j];
   if j>=3 then if a[i,j]+a[i,j-1]+a[i,j-2]>max then max:=a[i,j]+a[i,j-1]+a[i,j-2];
   if (i>=2)and(j>=2) then begin
     if a[i,j]+a[i-1,j]+a[i-1,j-1]>max then max:=a[i,j]+a[i-1,j]+a[i-1,j-1];
     if a[i,j]+a[i,j-1]+a[i-1,j-1]>max then max:=a[i,j]+a[i,j-1]+a[i-1,j-1];
     if a[i-1,j]+a[i,j-1]+a[i-1,j-1]>max then max:=a[i-1,j]+a[i,j-1]+a[i-1,j-1];
     if a[i,j]+a[i,j-1]+a[i-1,j]>max then max:=a[i,j]+a[i,j-1]+a[i-1,j];
   end;
   if max=300 then goto lb1;
 end;
 lb1:
 write(max);
end.

{$A8,B-,C+,D+,E-,F-,G+,H+,I+,J-,K-,L+,M-,N+,O+,P+,Q+,R+,S+,T-,U-,V+,W-,X+,Y+,Z1}
program _0360;
{$APPTYPE CONSOLE}
uses
  SysUtils;

const di:array[1..4] of shortint=(-1,0,1,0);
      dj:array[1..4] of shortint=(0,1,0,-1);

var a:array[0..2001,0..2001] of shortint;
    max1,max2,maxy,n,inf,i,j,i1,p:integer;


begin
  reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    inf:=-101;
    fillchar(a,sizeof(a),-100);
    for i:=1 to n do
    for j:=1 to n do
      begin
        read(a[i,j]);
      end;
    close(INput);
    maxy:=inf;
    for i:=1 to n do
    for j:=1 to n do
      begin
        max1:=inf;
        max2:=inf;
        for i1:=1 to 4 do // if (i+di[i1]>0) and (j+dj[i1]>0) and (i+di[i1]<=n) and (j+dj[i1]<=n) then
//        for j1:=1 to 4 do if i1<>j1 then
          begin
            p:=a[i+di[i1],j+dj[i1]];
            //if (a[i,j])+a[i+di[i1],j+dj[i1]]+a[i+di[j1],j+dj[j1]]>maxy then maxy:=a[i,j]+a[i+di[i1],j+dj[i1]]+a[i+di[j1],j+dj[j1]];
            if p>max1 then
              begin
                max2:=max1;
                max1:=p;
              end else
            if p>max2 then max2:=p;
          end;
        if a[i,j]+max1+max2>maxy then maxy:=max1+max2+a[i,j];
        if maxy=300 then
          begin
            write(300);
            close(output);
            halt(0);
          end;
      end;
  write(maxy);
    close(output);
    halt(0);;
end.
