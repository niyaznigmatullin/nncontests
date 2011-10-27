program z_196;

{$APPTYPE CONSOLE}
{$o-}
uses
  SysUtils;
var
a:array[0..101,0..101]of longint;
i,j,n,m,i0,j0,ii,jj,d:longint;
begin
 reset(input,'input.txt');
 rewrite(output,'output.txt');
 read(n);
 for i:=0 to n+1 do
 for j:=0 to n+1 do
  a[i,j]:=-1;
 d:=1;
 i0:=1;
 j0:=1;
 for i:=1 to n do
 for j:=1 to n do
  a[i,j]:=0;
 for i:=1 to n*n do
  begin
   a[i0,j0]:=i;
   if d=1 then inc(j0);
   if d=2 then inc(i0);
   if d=3 then dec(j0);
   if d=4 then dec(i0);
   if a[i0,j0]<>0 then
      begin
        if d=1 then dec(j0);
        if d=2 then dec(i0);
        if d=3 then inc(j0);
        if d=4 then inc(i0);
        if d=4 then d:=1 else inc(d);
        if d=1 then inc(j0);
        if d=2 then inc(i0);
        if d=3 then dec(j0);
        if d=4 then dec(i0);
      end;
  end;
  for i:=1 to n do
  begin
  for j:=1 to n do
   write(a[i,j],'  ');
    writeln;
  end;
end. 