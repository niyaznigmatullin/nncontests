program _0320;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var m,n:integer;
    a:array[-1..100,-1..100] of int64;
    q:int64;

procedure init;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(m,n);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    a[0,0]:=1;
    for i:=1 to 50 do
    for j:=0 to i+1 do a[i,j]:=a[i-1,j]+a[i-1,j-1];
    q:=0;
    for i:=1 to n div m do inc(q,a[n-i*(m-1),i]);
  end;

procedure done;
  begin
    write(q+1);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
 