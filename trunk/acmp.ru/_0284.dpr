program _0284;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var a:array[1..10000] of longint;
    n,m,b,c:longint;

procedure init;
var i,j:longint;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    read(m);
    for i:=1 to m do
      begin
        read(b,c);
        for j:=b to c do write(a[j],' ');
        writeln;
      end;
    close(input);
    close(output);
  end;

begin
  init;
end. 
