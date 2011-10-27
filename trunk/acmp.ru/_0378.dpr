program _0378;
{$APPTYPE CONSOLE}
uses
  SysUtils;

var n,q:integer;
    a:array[0..501] of integer;
    f:array[0..100000] of boolean;

procedure init;
var i:integer;
  begin
    reset(input,'input.txt');
    rewrite(output,'output.txt');
    read(n);
    for i:=1 to n do read(a[i]);
    close(input);
  end;

procedure solve;
var i,j:integer;
  begin
    f[0]:=true;
    for i:=1 to n do
      begin
        for j:=50000 downto 0 do if f[j] then f[j+a[i]]:=true;
      end;
    q:=0;
    for i:=0 to 50000 do if f[i] then inc(q);
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
 