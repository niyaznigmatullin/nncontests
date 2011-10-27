program z_G;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var   a:array[0..100]of integer;
    n:integer;

procedure swap(var x,y:integer);
var p:integer;
  begin
    p := x;
    x := y;
    y := p;
  end;

procedure init;
var i:integer;
  begin
    reset(input,'skating.in');
    rewrite(output,'skating.out');
    read(n);
    for i:=1 to n do read(a[i]);
    close(input);
  end;

procedure solve;
var ans:extended; i,j:integer;
  begin
    ans := 0;
    for i:=1 to n do
    for j:=i+1 to n do if (a[i] > a[j]) then swap(a[i],a[j]);
    for i:=2 to n - 1 do
      begin
        ans := ans + a[i];
      end;
    write(ans / (n - 2) : 0 : 10);
    close(output);
  end;

begin
  init;
  solve;
end.
