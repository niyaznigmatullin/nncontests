program _0339;

{$APPTYPE CONSOLE}

uses
  SysUtils;
const m:array[1..12]of longint=(31,28,31,30,31,30,31,31,30,31,30,31);
var
d1,d2,m2,y1,y2,i,v,ans,m1:longint;
c:char;
u,uu:boolean;
s:string;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 readln(s);
 d1:=((ord(s[1])-ord('0'))*10+(ord(s[2])-ord('0')));
 m1:=((ord(s[4])-ord('0'))*10+(ord(s[5])-ord('0')));
 y1:=((ord(s[7])-ord('0'))*1000+(ord(s[8])-ord('0'))*100+(ord(s[9])-ord('0'))*10+(ord(s[10])-ord('0')));
 read(s);
 d2:=((ord(s[1])-ord('0'))*10+(ord(s[2])-ord('0')));
 m2:=((ord(s[4])-ord('0'))*10+(ord(s[5])-ord('0')));
 y2:=((ord(s[7])-ord('0'))*1000+(ord(s[8])-ord('0'))*100+(ord(s[9])-ord('0'))*10+(ord(s[10])-ord('0')));
 v:=y1;
 {for i:=v to y2-2 do begin
  if ((y1 mod 4=0)and(y1 mod 100<>0))or(y1 mod 400=0) then
  inc(ans,366) else
  inc(ans,365);
  inc(y1);
 end;}
 while (d1<>d2)or(m1<>m2)or(y1<>y2) do begin
  inc(ans);
  u:=false;
  uu:=false;
  if ((y1 mod 4=0)and(y1 mod 100<>0))or(y1 mod 400=0) then uu:=true;
   if (m1=2)and(uu) then begin  if d1=m[m1]+1 then u:=true; end else
   if d1=m[m1] then u:=true;
   if (m1=12)and(u) then inc(y1);
   if u then if m1=12 then m1:=1 else inc(m1);
   if u then d1:=1 else inc(d1);
 end;
 write(ans+1);
end.
