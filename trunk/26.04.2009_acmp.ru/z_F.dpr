program z_F;
{$APPTYPE CONSOLE}
uses
  SysUtils,
  Math;

var a:array[0..1000] of integer;
    i,n,ans:integer;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  read(n);
  ans:=0;
  for i:=0 to n-1 do read(a[i]);
  for i:=0 to n-1 do ans:=max(ans,a[i]+a[(i+1) mod n]+a[(i+2) mod n]);
  write(ans);
  close(input);
  close(output);
  halt(0);
end.
 