program _448;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var ans:extended;
    n,i:integer;
    a:array[0..30001]of integer;

begin
  reset(input,'input.txt');
  rewrite(output,'output.txt');
  read(n);
  ans:=0;
  for i:=1 to n do
    begin
      read(a[i]);
    end;
  for i:=1 to n-1 do
    begin
      ans:=ans+(a[i]+a[i+1])/2;
    end;
  write(ans/(n-1):0:10);
  close(output);
  close(input);
  halt(0);
end.
 