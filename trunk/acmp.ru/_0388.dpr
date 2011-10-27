program _0388;
{$APPTYPE CONSOLE}
uses
  SysUtils, Math;

var q,n,m:integer;
    a:array[0..1000,0..1000] of integer;
    e,d:array[0..1000] of integer;

procedure init;
var i,j:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n,m);
    for i:=1 to n do
    for j:=1 to m do read(a[i,j]);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    for i:=1 to max(n,m) do begin e[i]:=maxint; d[i]:=-maxint; end;
    for i:=1 to n do
    for j:=1 to m do
      begin
        if e[i]>a[i,j] then e[i]:=a[i,j];
        if d[j]<a[i,j] then d[j]:=a[i,j];
      end;
    for i:=1 to n do
    for j:=1 to m do
      begin
        if (a[i,j]=e[i]) and (a[i,j]=d[j]) then inc(q);
      end;
  end;

procedure done;
  begin
    write(q);
    close(output);
    halt(0);
  end;

begin
  init;
  solve;
  done;
end.
