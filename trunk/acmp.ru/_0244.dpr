program _0244;

{$APPTYPE CONSOLE}
 {$o-,q+,r-,i+}
uses
  SysUtils;
var
a:array[1..100000]of longint;
n,k,i,osh:longint;
label lb1;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 read(n,k);
 for i:=1 to n do
  read(a[i]);
 osh:=0; 
 for i:=1 to n-k do begin
   if (a[i]<>a[i+k])and(osh<>0) then begin lb1: write('FAIL'); close(output); halt(0); end;
   if (a[i]<>a[i+k])then begin
                          if ((i+k+k>n)or(a[i+k]=a[i+k+k]))and((abs(a[i]-1)=a[i-k])or(i-k<1)) then osh:=i else osh:=i+k;
                          a[osh]:=abs(a[osh]-1);
                          if (osh=i)and(i-k>=1)and(a[osh]<>a[osh-k]) then goto lb1;
                         end;
 end;
 writeln('OK');
 write(osh);
end.    
