{$APPTYPE CONSOLE}
uses
  SysUtils;
var n,kol,s:integer;
procedure rec(minusone,s,k:integer);
var i:integer;
begin
  if k=1 then begin inc(kol); exit; end;
   for i:=minusone to s div k do
    rec(i,s-i,k-1);
end;
begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  read(n);
  s:=n;
  rec(1,s,4);
  write(kol);
end.
  
