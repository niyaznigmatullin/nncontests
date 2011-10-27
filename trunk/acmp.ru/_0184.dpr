program _0184;

{$APPTYPE CONSOLE}
 {$R+,I+,Q+,S+}
uses
  SysUtils;
const may:array[1..12]of longint=(31,28,31,30,31,30,31,31,30,31,30,31);
var
a:array[1..5,1..6000]of int64;
s:string;
v,d,ansm,ansh,n:int64;
i,j:longint;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 readln(n);
 for i:=1 to n do begin
  readln(s);
  a[1,i]:=(ord(s[4])-ord('0'))*10+ord(s[5])-ord('0');
  a[2,i]:=(ord(s[1])-ord('0'))*10+ord(s[2])-ord('0');
  a[3,i]:=(ord(s[8])-ord('0'))*10+ord(s[9])-ord('0');
  a[4,i]:=(ord(s[11])-ord('0'))*10+ord(s[12])-ord('0');
  for j:=1 to a[1,i]-1 do
  inc(a[5,i],may[j]*8*60);
  inc(a[5,i],a[2,i]*8*60+a[3,i]*60+a[4,i])
 end;
 for i:=1 to n-1 do
 for j:=i+1 to n do
  if a[5,i]>a[5,j] then begin v:=a[5,i]; a[5,i]:=a[5,j]; a[5,j]:=v;
                              v:=a[4,i]; a[4,i]:=a[4,j]; a[4,j]:=v;
                              v:=a[3,i]; a[3,i]:=a[3,j]; a[3,j]:=v;
                              v:=a[2,i]; a[2,i]:=a[2,j]; a[2,j]:=v;
                              v:=a[1,i]; a[1,i]:=a[1,j]; a[1,j]:=v;
  end;
 for i:=0 to (n div 2)-1 do begin
  inc(ansm,a[5,i*2+2]-a[5,i*2+1]+1);
 end;
 inc(ansh,ansm div 60);
 ansm:=ansm mod 60;
 write(ansh,':');
 if ansm<10 then write(0);
 write(ansm);
end.
